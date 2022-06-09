package com.micropos.cart.rest;

import com.micropos.cart.api.CartApi;
import com.micropos.cart.api.CheckoutApi;
import com.micropos.cart.dto.ItemDto;
import com.micropos.cart.mapper.CartMapper;
import com.micropos.cart.model.Item;
import com.micropos.cart.model.Product;
import com.micropos.cart.service.CartService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("cart-api")
public class CartController implements CheckoutApi, CartApi {
    private Log logger = LogFactory.getLog(CartController.class);

    private final CartMapper cartMapper;

    private final CartService cartService;

    private List<Item> cart = null;

    public void setCart(List<Item> c) {
        cart = c;
    }

    public CartController(CartService cs, CartMapper cm) {
        cartMapper = cm;
        cartService = cs;
    }

//    @Autowired
//    private WebSession webSession;
//    private void test() {
//        webSession.getAttributes().put("aaa", 112);
//        webSession.getAttribute("aaa");
//    }
//    @Autowired
//    private HttpSession session;
//    @Override
//    public Optional<NativeWebRequest> getRequest() {
//        return Optional.empty();
//    }

    //    @Override
//    public ResponseEntity<List<ItemDto>> checkout() {
//        logger.info("checkout()");
//        getCart();
//        cartService.checkout(cart, session.getId());
//        session.setAttribute("cart", cart);
//        List<ItemDto> castDto = new ArrayList<>(cartMapper.toCartDto(cart));
//        return new ResponseEntity<>(castDto, HttpStatus.OK);
//    }
    private Flux<ItemDto> checkout1(ServerWebExchange exchange) throws ExecutionException, InterruptedException {
        //logger.info("checkout1()");
        return exchange.getSession().map(
                        webSession -> {
                            List<Item> cart = webSession.getAttribute("cart");
                            if (cart == null) {
                                cart = new ArrayList<Item>();
                            }
                            cartService.checkout(cart, webSession.getId());
                            webSession.getAttributes().put("cart", cart);
                            webSession.save();
                            return cart;
                        }
                )
                .map(cartMapper::toCartDto)
                .map(carts -> Flux.fromArray(carts.toArray(new ItemDto[0])))
                .toFuture().get();
    }

    @Override
    public Mono<ResponseEntity<Flux<ItemDto>>> checkout(ServerWebExchange exchange) {
        Mono b = Mono.fromCallable(
                        () -> checkout1(exchange)
                )
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
        b = b.subscribeOn(Schedulers.boundedElastic());
        return b;
    }


//    @Override
//    public ResponseEntity<List<ItemDto>> listCart() {
//        logger.info("listCart()");
//        getCart();
//        List<ItemDto> castDto = new ArrayList<>(cartMapper.toCartDto(cart));
//        return new ResponseEntity<>(castDto, HttpStatus.OK);
//    }

    @Override
    public Mono<ResponseEntity<Flux<ItemDto>>> listCart(ServerWebExchange exchange) {
        logger.info("listCart()");
        Mono b = Mono.fromCallable(
                        () -> exchange.getSession()
                                .map(webSession -> {
                                    logger.info(webSession.getId());
                                    List<Item> cart = webSession.getAttribute("cart");
                                    if (cart == null) {
                                        cart = new ArrayList<Item>();
                                        webSession.getAttributes().put("cart", cart);
                                        webSession.save();
                                    }
                                    return cart;
                                })
                                .map(cartMapper::toCartDto)
                                .map(carts -> Flux.fromArray(carts.toArray(new ItemDto[0])))
                                .toFuture().get()
                )
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
        b = b.subscribeOn(Schedulers.boundedElastic());
        return b;
    }


    //    @Override
//    public ResponseEntity<List<ItemDto>> addProduct(
//            @Parameter(name = "productId", description = "The id of the product to add to Cart", required = true, schema = @Schema(description = "")) @PathVariable("productId") String productId
//    ) {
//        logger.info("addProduct(String productId)" + productId);
//        getCart();
//        Product product = cartMapper.toProduct(cartService.getProductFromId(productId));
//        cart = cartService.add(cart, product, 1);
//        session.setAttribute("cart", cart);
//        List<ItemDto> castDto = new ArrayList<>(cartMapper.toCartDto(cart));
//        return new ResponseEntity<>(castDto, HttpStatus.OK);
//    }
    @Override
    public Mono<ResponseEntity<Flux<ItemDto>>> addProduct(String productId, ServerWebExchange exchange) {
        logger.info("addProduct(String productId)" + productId);
        Mono b = Mono.fromCallable(
                        () -> exchange.getSession()
                                .map(webSession -> {
                                    logger.info(webSession.getId());
                                    List<Item> cart = webSession.getAttribute("cart");
                                    if (cart == null) {
                                        cart = new ArrayList<Item>();
                                    }
                                    Product product = cartMapper.toProduct(cartService.getProductFromId(productId));
                                    cart = cartService.add(cart, product, 1);
                                    webSession.getAttributes().put("cart", cart);
                                    webSession.save();
                                    return cart;
                                })
                                .map(cartMapper::toCartDto)
                                .map(carts -> Flux.fromArray(carts.toArray(new ItemDto[0])))
                                .toFuture().get()
                )
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
        b = b.subscribeOn(Schedulers.boundedElastic());
        return b;
    }
//    @Override
//    public ResponseEntity<List<ItemDto>> deleteProduct(
//            @Parameter(name = "productId", description = "The id of the product to delete in Cart", required = true, schema = @Schema(description = "")) @PathVariable("productId") String productId
//    ) {
//        logger.info("deleteProduct(String productId)" + productId);
//        getCart();
//        cart = cartService.delete(cart, productId);
//        session.setAttribute("cart", cart);
//        List<ItemDto> castDto = new ArrayList<>(cartMapper.toCartDto(cart));
//        return new ResponseEntity<>(castDto, HttpStatus.OK);
//    }

    @Override
    public Mono<ResponseEntity<Flux<ItemDto>>> deleteProduct(String productId, ServerWebExchange exchange) {
        logger.info("deleteProduct(String productId)" + productId);
        Mono b = Mono.fromCallable(
                        () -> exchange.getSession()
                                .map(webSession -> {
                                    logger.info(webSession.getId());
                                    List<Item> cart = webSession.getAttribute("cart");
                                    if (cart == null) {
                                        cart = new ArrayList<Item>();
                                    }
                                    cart = cartService.delete(cart, productId);
                                    webSession.getAttributes().put("cart", cart);
                                    webSession.save();
                                    return cart;
                                })
                                .map(cartMapper::toCartDto)
                                .map(carts -> Flux.fromArray(carts.toArray(new ItemDto[0])))
                                .toFuture().get()
                )
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
        b = b.subscribeOn(Schedulers.boundedElastic());
        return b;
    }
//    @Override
//    public ResponseEntity<List<ItemDto>> modifyProduct(
//            @Parameter(name = "productId", description = "The id of the product to modify in Cart", required = true, schema = @Schema(description = "")) @PathVariable("productId") String productId,
//            @NotNull @Min(1) @Parameter(name = "productNum", description = "The new number of the product to modify in Cart", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "productNum", required = true) Integer productNum
//    ) {
//        logger.info("modifyProduct(String productId, Integer productNum)" + productId + " " + productNum);
//        getCart();
//        cart = cartService.modify(cart, productId, productNum);
//        session.setAttribute("cart", cart);
//        List<ItemDto> castDto = new ArrayList<>(cartMapper.toCartDto(cart));
//        return new ResponseEntity<>(castDto, HttpStatus.OK);
//    }

    @Override
    public Mono<ResponseEntity<Flux<ItemDto>>> modifyProduct(String productId, Integer productNum, ServerWebExchange exchange) {
        logger.info("modifyProduct(String productId, Integer productNum)" + productId + " " + productNum);
        Mono b = Mono.fromCallable(
                        () -> exchange.getSession()
                                .map(webSession -> {
                                    logger.info(webSession.getId());
                                    List<Item> cart = webSession.getAttribute("cart");
                                    if (cart == null) {
                                        cart = new ArrayList<Item>();
                                    }
                                    cart = cartService.modify(cart, productId, productNum);
                                    webSession.getAttributes().put("cart", cart);
                                    webSession.save();
                                    return cart;
                                })
                                .map(cartMapper::toCartDto)
                                .map(carts -> Flux.fromArray(carts.toArray(new ItemDto[0])))
                                .toFuture().get()
                )
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
        b = b.subscribeOn(Schedulers.boundedElastic());
        return b;
    }


//    @GetMapping("/session")
//    public String session() {
//        logger.info(session.getId());
//        return session.getId();
//    }


//    @SuppressWarnings("unchecked")
//    private void getCart() {
//        logger.info(session.getId());
//        List<Item> cart1 = (List<Item>) session.getAttribute("cart");
//        setCart(cart1 == null ? new ArrayList<Item>() : cart1);
//    }
}
