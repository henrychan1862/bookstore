package com.automate.bookstore.order;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

/**
 * This controller handles routing for viewing, placing and deleting order
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     * view all orders placed by customers with given username
     * @param userName username of customer
     * @return list of orders
     */
    @GetMapping("/{username}")
    public List<Order> ordersView(@PathVariable("username") String userName) {

        return orderService.viewOrders(userName);
    }

    /**
     * place an order for customers with given username
     * @param userName username of customer
     * @param orderTicket order ticket, contains ISBN13 of book and amount
     * @return the placed order object
     */
    @PostMapping("/{username}")
    public ResponseEntity<Order> orderBook(@PathVariable("username") String userName,
                                           @RequestBody OrderTicket orderTicket) {

        Order orderPlaced = orderService.placeOrder(userName, orderTicket);

        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{orderId}")
                        .buildAndExpand(orderPlaced.getOrderId())
                        .toUri())
                .body(orderPlaced);
    }

    /**
     * view single order placed by customers with given username and order ID
     * @param userName username of customer
     * @param orderId order ID
     * @return the order object
     */
    @GetMapping("/{username}/{orderId}")
    public Order orderDetails(@PathVariable("username") String userName,
                              @PathVariable("orderId") long orderId) {

        return orderService.getOrderInfoWithCustomer(userName, orderId);
    }

    /**
     * delete single order placed by customers given username and order ID
     * @param userName username of customer
     * @param orderId order ID
     * @return message to confirm order is deleted
     */
    @DeleteMapping("/{username}/{orderId}")
    public String orderCancellation(@PathVariable("username") String userName,
                                    @PathVariable("orderId") long orderId) {

        orderService.cancelOrder(userName, orderId);

        return "You have cancelled order with Id " + orderId;
    }

}
