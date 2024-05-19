package com.automate.bookstore.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * This controller handles routing for viewing and updating order's info.
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * view customer with username
     * @param userName customer's username
     * @return the customer object
     */
    @GetMapping("/{username}")
    public Customer customerDetails(@PathVariable("username") String userName) {
        return customerService.getCustomerInfo(userName);
    }

    /**
     * update customer info given new info as JSON in request body
     * @param userName customer's username
     * @param customerNewInfo customer info to be updated
     * @return the updated customer object
     */
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
