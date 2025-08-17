package com.alexsys.smartmarket.mapper;

import org.mapstruct.*;

import com.alexsys.smartmarket.model.PaymentDetails;

@Mapper(componentModel = "spring")
public interface PaymentDetailsMapper {

    // Update existing PaymentDetails (ignore null values)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget PaymentDetails target, PaymentDetails source);
}
