package com.automate.bookstore.order;

import com.automate.bookstore.book.Book;
import com.automate.bookstore.book.BookService;
import com.automate.bookstore.customer.Customer;
import com.automate.bookstore.customer.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookService bookService;

    @PostMapping("/order/{bookId}")
    public ResponseEntity<String> orderBook(@PathVariable long bookId, Principal principal) {
        Customer customerLoggedIn = customerService.getCustomerInfo(principal.getName());
        Book bookOrdering = bookService.getBookInfo(bookId);
        orderService.placeOrder(customerLoggedIn, bookOrdering);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("You ordered the book " + bookOrdering.getTitle());
    }

    @GetMapping("/{username}")
    public List<Order> orderView(@PathVariable("username") String userName) {
        Customer customerLoggedIn = customerService.getCustomerInfo(userName);
        return orderService.viewOrders(customerLoggedIn);
    }

    @PutMapping("/cancel/{orderId}")
    public String orderCancellation(@PathVariable long orderId, Principal principal) {
        if (orderService.getOrderInfo(orderId).getCustomer()
                .equals(customerService.getCustomerInfo(principal.getName()))
        ) {
            orderService.cancelOrder(orderId);
            return "You have cancelled order with Id " + orderId;
        } else
            throw new RuntimeException("You have no order with id " + orderId);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("We cannot find your order!" + "\n" + e.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Invalid Operation!" + "\n" + e.getMessage());
    }
}
