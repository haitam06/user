package com.alexsys.smartmarket.mapper;

import org.mapstruct.*;

import com.alexsys.smartmarket.model.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    // Update existing OrderItem (ignore null values)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget OrderItem target, OrderItem source);
}
