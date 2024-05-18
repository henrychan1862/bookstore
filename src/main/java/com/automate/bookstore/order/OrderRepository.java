package com.automate.bookstore.order;

import com.automate.bookstore.customer.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByCustomer(Customer customer);

    Optional<Order> findByOrderIdAndCustomer(long orderId, Customer customer);

}
