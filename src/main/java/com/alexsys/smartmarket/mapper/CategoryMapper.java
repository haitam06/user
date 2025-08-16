package com.alexsys.smartmarket.mapper;

import org.mapstruct.*;

import com.alexsys.smartmarket.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    // Update existing category (ignore null values)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Category target, Category source);
}
