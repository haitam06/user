package com.alexsys.smartmarket.user.mapper;

import org.mapstruct.*;

import com.alexsys.smartmarket.user.model.User;

@Mapper(
    componentModel = "spring"
)
public interface UserMapper {

    // Update sans écraser les nulls
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget User target, User source);
}