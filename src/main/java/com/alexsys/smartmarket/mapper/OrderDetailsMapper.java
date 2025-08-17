package com.alexsys.smartmarket.mapper;

import org.mapstruct.*;

import com.alexsys.smartmarket.model.OrderDetails;

@Mapper(componentModel = "spring")
public interface OrderDetailsMapper {

    // Update existing OrderDetails (ignore null values)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget OrderDetails target, OrderDetails source);
}
