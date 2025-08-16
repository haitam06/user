package com.alexsys.smartmarket.mapper;

import org.mapstruct.*;

import com.alexsys.smartmarket.model.ProductAttribute;

@Mapper(componentModel = "spring")
public interface ProductAttributeMapper {

    // Update existing (ignore nulls)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget ProductAttribute target, ProductAttribute source);
}
