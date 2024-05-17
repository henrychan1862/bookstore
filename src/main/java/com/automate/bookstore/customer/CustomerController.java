package com.automate.bookstore.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{username}")
    public Customer customerDetails(@PathVariable("username") String userName) {
        return customerService.getCustomerInfo(userName);
    }

    @PutMapping("/{username}/edit")
    public void customerInfoUpdate(@PathVariable("username") String userName,
                                   @RequestParam(name = "first_name") Optional<String> firstName,
                                   @RequestParam(name = "last_name") Optional<String> lastName,
                                   @RequestParam(name = "email") Optional<String> emailAddress,
                                   @RequestParam(name = "home") Optional<String> deliveryAddress,
                                   @RequestParam(name = "phone") Optional<Integer> phoneNumber) {

        customerService.updateCustomerInfo(userName, firstName, lastName, emailAddress, deliveryAddress, phoneNumber);
    }

}
