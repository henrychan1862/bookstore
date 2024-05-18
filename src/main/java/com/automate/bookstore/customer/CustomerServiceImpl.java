package com.automate.bookstore.customer;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public Customer getCustomerInfo(String userName) {
        return customerRepository.findByUserName(userName);
    }

    @Override
    public Customer updateCustomerInfo(String userName, CustomerDto customerNewInfo) {

        if (customerNewInfo.equals(new CustomerDto()))
            throw new CustomerInfoUpdateFailedException("Cannot update customer info with prohibited keys / null values");

        Customer customerToBeUpdated = getCustomerInfo(userName);
        updateCustomerFromDto(customerNewInfo, customerToBeUpdated);

        customerRepository.save(customerToBeUpdated);

        return customerToBeUpdated;
    }

    @Override
    public void updateCustomerFromDto(CustomerDto customerDto, Customer customer) {
        customerMapper.updateCustomer(customerDto, customer);
    }


}
