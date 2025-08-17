package com.alexsys.smartmarket.mapper;

import org.mapstruct.*;

import com.alexsys.smartmarket.model.CartItem;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    // Update existing CartItem (ignore null values)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget CartItem target, CartItem source);
}