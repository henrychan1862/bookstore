package com.automate.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping({""})
    public List<Book> bookPickups() {return bookService.getBookRecommendations(); }

    @GetMapping("/{id}")
    public Book bookDetails(@PathVariable Long id) {
        return bookService.getBookInfo(id);
    }

    @GetMapping("/isbn13/{isbn13}")
    public Book bookDetailsByISBN13(@PathVariable Long isbn13) {
        return bookService.getBookInfoWithISBN13(isbn13);
    }

    @GetMapping("/search")
    public List<Book> bookSearch(@RequestParam(required = false) String category,
                                 @RequestParam(required = false) String author,
                                 @RequestParam(required = false) String title,
                                 @RequestParam(name = "price_min", required = false, defaultValue = "0") Integer priceMin,
                                 @RequestParam(name = "price_max", required = false, defaultValue = "10000") Integer priceMax,
                                 @RequestParam(name = "rating_above", required = false, defaultValue = "0") Integer ratingAbove) {

        return bookService.searchBook(category, author, title, priceMin, priceMax, ratingAbove);
    }

}
