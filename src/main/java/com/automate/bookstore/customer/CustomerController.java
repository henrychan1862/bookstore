package com.automate.bookstore.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{username}")
    public Customer customerDetails(@PathVariable("username") String userName) {
        return customerService.getCustomerInfo(userName);
    }

    @PutMapping("/{username}")
    public ResponseEntity<Customer> customerInfoUpdateWithJson(@PathVariable("username") String userName,
                                               @RequestBody CustomerDto customerNewInfo) {
        Customer customerUpdated = customerService.updateCustomerInfo(userName, customerNewInfo);
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .build()
                        .toUri())
                .body(customerUpdated);
    }

}
