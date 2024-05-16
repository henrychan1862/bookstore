package com.automate.bookstore.book;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;
    private long ISBN13;
    private String title;
    private String author;
    private float price;
    private String category;
    private float rating;


    public Book() {
    }

    public Book(long bookId, long ISBN, String title, String author, float price, String category, float rating) {
        this.bookId = bookId;
        this.ISBN13 = ISBN;
        this.title = title;
        this.author = author;
        this.price = price;
        this.category = category;
        this.rating = rating;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getISBN13() {
        return ISBN13;
    }

    public void setISBN13(long ISBN13) {
        this.ISBN13 = ISBN13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
