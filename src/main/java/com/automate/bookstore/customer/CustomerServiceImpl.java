package com.automate.bookstore.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void getCustomerInfo(String customerName) {
        Customer foundCustomer = customerRepository.findByHandleName(customerName);
        System.out.println("This handle name is used by " + foundCustomer.getFirstName() + " "+ foundCustomer.getLastName());
        return;
    }
}
