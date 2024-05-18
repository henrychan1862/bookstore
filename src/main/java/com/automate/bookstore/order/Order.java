package com.automate.bookstore.order;

import com.automate.bookstore.book.Book;
import com.automate.bookstore.customer.Customer;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "CustomerOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @ManyToOne
    @JoinColumn(name = "customer_fk")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "book_fk")
    private Book book;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastModified;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private int amount;

    public Order() {
    }

    public Order(long orderId, Customer customer, Book book, LocalDateTime createdAt, LocalDateTime lastModified, OrderStatus status, int amount) {
        this.orderId = orderId;
        this.customer = customer;
        this.book = book;
        this.createdAt = createdAt;
        this.lastModified = lastModified;
        this.status = status;
        this.amount = amount;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}


