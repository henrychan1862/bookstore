package com.automate.bookstore.order;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{username}")
    public List<Order> ordersView(@PathVariable("username") String userName) {

        return orderService.viewOrders(userName);
    }


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

    @GetMapping("/{username}/{orderId}")
    public Order orderDetails(@PathVariable("username") String userName,
                              @PathVariable("orderId") long orderId) {

        return orderService.getOrderInfoWithCustomer(userName, orderId);
    }


    @DeleteMapping("/{username}/{orderId}")
    public String orderCancellation(@PathVariable("username") String userName,
                                    @PathVariable("orderId") long orderId) {

        orderService.cancelOrder(userName, orderId);

        return "You have cancelled order with Id " + orderId;
    }

}
