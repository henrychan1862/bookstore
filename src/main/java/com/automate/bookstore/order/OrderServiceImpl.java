package com.automate.bookstore.order;

import com.automate.bookstore.book.Book;
import com.automate.bookstore.book.BookService;
import com.automate.bookstore.customer.Customer;
import com.automate.bookstore.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookService bookService;

    @Override
    public Order placeOrder(String userName, OrderTicket orderTicket) {

        Customer customerLoggedIn = customerService.getCustomerInfo(userName);
        Book bookOrdering = bookService.getBookInfoWithISBN13(orderTicket.getISBN13());

        Order order = new Order();
        order.setCustomer(customerLoggedIn);
        order.setBook(bookOrdering);
        order.setAmount(orderTicket.getAmount());
        order.setStatus(OrderStatus.ORDERED);

        orderRepository.save(order);

        return order;
    }


    @Override
    public List<Order> viewOrders(String userName) {

        Customer customerLoggedIn = customerService.getCustomerInfo(userName);
        List<Order> foundOrders = orderRepository.findByCustomer(customerLoggedIn);

        if (foundOrders.isEmpty())
            throw new OrderNotFoundException("You haven't placed any order yet.");

        return foundOrders;
    }


    @Override
    public Order getOrderInfoWithCustomer(String userName, long orderId) {

        Customer customerLoggedIn = customerService.getCustomerInfo(userName);

        return orderRepository.findByOrderIdAndCustomer(orderId, customerLoggedIn).orElseThrow(
                () -> new OrderNotFoundException("No order with id " + orderId));
    }


    @Override
    public void cancelOrder(String userName, long orderId) {

        Order orderToBeCancelled = getOrderInfoWithCustomer(userName, orderId);

        orderRepository.delete(orderToBeCancelled);
    }

}
