package com.automate.bookstore.order;

import com.automate.bookstore.book.Book;
import com.automate.bookstore.customer.Customer;
import jakarta.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@PreAuthorize("#userName == principal.username")
public interface OrderService {

    @Transactional
    Order placeOrder(String userName, OrderTicket orderTicket);

    List<Order> viewOrders(String userName);

    Order getOrderInfoWithCustomer(String userName, long orderId);

    @Transactional
    void cancelOrder(String userName, long orderId);

}
