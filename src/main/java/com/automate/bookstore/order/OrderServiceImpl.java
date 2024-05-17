package com.automate.bookstore.order;

import com.automate.bookstore.book.Book;
import com.automate.bookstore.customer.Customer;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public void placeOrder(Customer customer, Book book) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setBook(book);
        order.setStatus(OrderStatus.ORDERED);

        orderRepository.save(order);
    }

    @Override
    public List<Order> viewOrders(Customer customer) {
        List<Order> foundOrders = orderRepository.findByCustomer(customer);
        if (foundOrders.isEmpty())
            throw new EntityNotFoundException("You haven't placed any order yet.");
        return foundOrders;
    }

    @Override
    public void cancelOrder(long orderId) {

        Optional<Order> orderToBeCancelled = orderRepository.findById(orderId);

        if (orderToBeCancelled.isEmpty())
            throw new EntityNotFoundException("Cannot find order with Id " + orderId);
        if (!orderToBeCancelled.get().getStatus().equals(OrderStatus.CANCELLED))
            orderToBeCancelled.get().setStatus(OrderStatus.CANCELLED);

        orderRepository.save(orderToBeCancelled.get());
    }

    @Override
    public Order getOrderInfo(long orderId) {
        return orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
    }
}
