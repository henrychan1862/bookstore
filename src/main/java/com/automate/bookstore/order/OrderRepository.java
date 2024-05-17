package com.automate.bookstore.order;

import com.automate.bookstore.customer.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByCustomer(Customer customer);

    List<Order> findByCustomerAndStatus(Customer customer, OrderStatus status);
}
