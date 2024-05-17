package com.automate.bookstore.customer;

import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

public interface CustomerService {

    @PreAuthorize("#userName == principal.username")
    Customer getCustomerInfo(String userName);

    @PreAuthorize("#userName == principal.username")
    void updateCustomerInfo(String userName,
                            Optional<String> firstName,
                            Optional<String> lastName,
                            Optional<String> emailAddress,
                            Optional<String> deliveryAddress,
                            Optional<Integer> phoneNumber);
}
