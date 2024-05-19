package com.automate.bookstore.customer;

import com.automate.bookstore.book.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of service layer for customers
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;  // get mapper class

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    /**
     * get customer info given user name
     * @param userName username of customer
     * @return the customer object
     */
    @Override
    public Customer getCustomerInfo(String userName) {
        return customerRepository.findByUserName(userName);
    }

    /**
     * update customer info with given username and new info
     * @param userName username of customer
     * @param customerNewInfo new info used to update existing customer
     * @return the customer object
     * @throws CustomerInfoUpdateFailedException if given new info a null object
     */
    @Override
    public Customer updateCustomerInfo(String userName, CustomerDto customerNewInfo) {

        if (customerNewInfo.equals(new CustomerDto()))
            throw new CustomerInfoUpdateFailedException("Cannot update customer info with prohibited keys / null values");

        Customer customerToBeUpdated = getCustomerInfo(userName);
        updateCustomerFromDto(customerNewInfo, customerToBeUpdated);

        customerRepository.save(customerToBeUpdated);

        return customerToBeUpdated;
    }

    /**
     * map customerDto to customer object,
     * @param customerDto customerDto object
     * @param customer customer object
     * @return the mapped customer object
     */
    @Override
    public void updateCustomerFromDto(CustomerDto customerDto, Customer customer) {
        customerMapper.updateCustomer(customerDto, customer);
    }


}
