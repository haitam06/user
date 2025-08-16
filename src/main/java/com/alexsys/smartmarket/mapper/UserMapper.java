package com.alexsys.smartmarket.mapper;

import org.mapstruct.*;

import com.alexsys.smartmarket.model.User;

@Mapper(
    componentModel = "spring"
)
public interface UserMapper {

    // Update sans écraser les nulls
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget User target, User source);
}