package com.automate.bookstore.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomerInfo(String userName) {
        return customerRepository.findByUserName(userName);
    }

    @Override
    public void updateCustomerInfo(String userName,
                                   Optional<String> firstName,
                                   Optional<String> lastName,
                                   Optional<String> emailAddress,
                                   Optional<String> deliveryAddress,
                                   Optional<Integer> phoneNumber) {

        Customer customerToBeUpdated = getCustomerInfo(userName);

        firstName.ifPresent(customerToBeUpdated::setFirstName);
        lastName.ifPresent(customerToBeUpdated::setLastName);
        emailAddress.ifPresent(customerToBeUpdated::setEmailAddress);
        deliveryAddress.ifPresent(customerToBeUpdated::setDeliveryAddress);
        phoneNumber.ifPresent(customerToBeUpdated::setPhoneNumber);

        customerRepository.save(customerToBeUpdated);
    }
}
