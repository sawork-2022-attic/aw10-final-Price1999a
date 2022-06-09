package com.micropos.products.rest;

import com.micropos.products.api.ProductsApi;
import com.micropos.products.dto.ProductDto;
import com.micropos.products.mapper.ProductMapper;
import com.micropos.products.model.Product;
import com.micropos.products.service.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("products-api")
public class ProductController implements ProductsApi {

    private final ProductMapper productMapper;

    private final ProductService productService;

    private Log logger = LogFactory.getLog(ProductController.class);

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @Override
    public Mono<ResponseEntity<Flux<ProductDto>>> listProducts(ServerWebExchange exchange) {
        return Mono.just(dataForReact())
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    private Flux<ProductDto> dataForReact() {
        List<ProductDto> products = new ArrayList<>(productMapper.toProductsDto(this.productService.products()));
        return Flux.fromArray(products.toArray(new ProductDto[0]));
    }
    //    @Override
//    public ResponseEntity<List<ProductDto>> listProducts() {
//        List<ProductDto> products = new ArrayList<>(productMapper.toProductsDto(this.productService.products()));
//        if (products.isEmpty()) {
//            logger.info("products is empty!");
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }

//    @Override
//    public ResponseEntity<ProductDto> showProductById(
//            @Parameter(name = "productId", description = "The id of the product to retrieve", required = true, schema = @Schema(description = "")) @PathVariable("productId") String productId
//    ) {
//        ProductDto product = productMapper.toProductDto(productService.getProduct(productId));
////        if (product == null) {
////            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
////        }
//        return new ResponseEntity<>(product, HttpStatus.OK);
//    }


    @Override
    public Mono<ResponseEntity<ProductDto>> showProductById(String productId, ServerWebExchange exchange) {
        return Mono.justOrEmpty(
                        productMapper.toProductDto(productService.getProduct(productId))
                )
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
