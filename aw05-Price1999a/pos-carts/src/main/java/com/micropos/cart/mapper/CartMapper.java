package com.micropos.cart.mapper;

import com.micropos.cart.dto.ItemDto;
import com.micropos.cart.dto.ProductDto;
import com.micropos.cart.model.Item;
import com.micropos.cart.model.Product;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper
public interface CartMapper {
    Product toProduct(ProductDto productDto);

    ProductDto toProductDto(Product product);

    Item toItem(ItemDto itemDto);

    ItemDto toItemDto(Item item);

    Collection<ItemDto> toCartDto(Collection<Item> cart);

    Collection<Item> toCart(Collection<ItemDto> cartDto);
}
