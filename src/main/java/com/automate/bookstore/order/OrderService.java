package com.automate.bookstore.order;

import com.automate.bookstore.book.Book;
import com.automate.bookstore.customer.Customer;
import jakarta.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    @PreAuthorize("#customer.getUserName() == principal.username")
    void placeOrder(Customer customer, Book book);

    @PreAuthorize("#customer.getUserName() == principal.username")
    List<Order> viewOrders(Customer customer);

    void cancelOrder(long orderId);

    Order getOrderInfo(long orderId);
}
