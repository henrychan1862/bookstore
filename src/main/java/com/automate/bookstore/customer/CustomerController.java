package com.automate.bookstore.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers/{customerName}")
    public void customerDetails(@PathVariable("customerName") String handleName) {
        customerService.getCustomerInfo(handleName);
    }

}
