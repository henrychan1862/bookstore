package com.automate.bookstore.customer;

import jakarta.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

public interface CustomerService {

    @PreAuthorize("#userName == principal.username")
    Customer getCustomerInfo(String userName);

    @Transactional
    @PreAuthorize("#userName == principal.username")
    Customer updateCustomerInfo(String userName, CustomerDto customerNewInfo);

    void updateCustomerFromDto(CustomerDto customerDto, Customer customer);
}
