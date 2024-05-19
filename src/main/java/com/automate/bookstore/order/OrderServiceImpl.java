package com.automate.bookstore.order;

import com.automate.bookstore.book.Book;
import com.automate.bookstore.book.BookService;
import com.automate.bookstore.customer.Customer;
import com.automate.bookstore.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Implementation of service layer for orders
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;    // require customer service to get customer info for identity check

    @Autowired
    private BookService bookService;    // require book service to place order

    /**
     * place an order for customers with given username, integrate customer service for
     * getting customer information and book service for getting book details
     * @param userName username of customer
     * @param orderTicket order ticket, contains ISBN13 of book and amount
     * @return the placed order object
     */
    @Override
    public Order placeOrder(String userName, OrderTicket orderTicket) {

        Customer customerLoggedIn = customerService.getCustomerInfo(userName);
        Book bookOrdering = bookService.getBookInfoWithISBN13(orderTicket.getISBN13());

        Order order = new Order();
        order.setCustomer(customerLoggedIn);
        order.setBook(bookOrdering);
        order.setAmount(orderTicket.getAmount());

        orderRepository.save(order);

        return order;
    }


    /**
     * view all orders placed by customers with given username
     * @param userName username of customer
     * @return list of orders
     * @throws OrderNotFoundException if customer has not placed any order yet
     */
    @Override
    public List<Order> viewOrders(String userName) {

        Customer customerLoggedIn = customerService.getCustomerInfo(userName);
        List<Order> foundOrders = orderRepository.findByCustomer(customerLoggedIn);

        if (foundOrders.isEmpty())
            throw new OrderNotFoundException("You haven't placed any order yet.");

        return foundOrders;
    }

    /**
     * view single order placed by customers with given username and order ID
     * @param userName username of customer
     * @param orderId order ID
     * @return the order object
     * @throws OrderNotFoundException if customer has no order with given order id
     */
    @Override
    public Order getOrderInfoWithCustomer(String userName, long orderId) {

        Customer customerLoggedIn = customerService.getCustomerInfo(userName);

        return orderRepository.findByOrderIdAndCustomer(orderId, customerLoggedIn).orElseThrow(
                () -> new OrderNotFoundException("No order with id " + orderId));
    }

    /**
     * delete single order placed by customers given username and order ID
     * @param userName username of customer
     * @param orderId order ID
     * @return void
     */
    @Override
    public void cancelOrder(String userName, long orderId) {

        Order orderToBeCancelled = getOrderInfoWithCustomer(userName, orderId);

        orderRepository.delete(orderToBeCancelled);
    }

}
