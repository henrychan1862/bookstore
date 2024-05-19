package com.automate.bookstore.customer;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/**
 * A mapper class to map customerDto object to customer object
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

    void updateCustomer(CustomerDto customerDto, @MappingTarget Customer customer);
}
