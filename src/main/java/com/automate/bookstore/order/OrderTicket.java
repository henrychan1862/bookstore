package com.automate.bookstore.order;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/**
 * A Dto class to get the important field of an ordering operation
 * requires ISBN13 of book and amount (1-10)
 */
public class OrderTicket {

    private long ISBN13;

    @Min(1)
    @Max(10)
    private int amount;

    public OrderTicket() {
    }

    public OrderTicket(long ISBN13, int amount) {
        this.ISBN13 = ISBN13;
        this.amount = amount;
    }

    public long getISBN13() {
        return ISBN13;
    }

    public void setISBN13(long ISBN13) {
        this.ISBN13 = ISBN13;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
