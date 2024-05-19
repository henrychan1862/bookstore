package com.automate.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * This controller handles routing for viewing and searching books.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * view all books on database
     * @return list of books
     */
    @GetMapping({""})
    public List<Book> bookPickups() {return bookService.getBookRecommendations(); }

    /**
     * view single book with given book id
     * @param id book Id
     * @return the book object
     */
    @GetMapping("/{id}")
    public Book bookDetails(@PathVariable Long id) {
        return bookService.getBookInfo(id);
    }

    /**
     * view single book with given ISBN13 code
     * @param isbn13 iSBN13 code
     * @return the book object
     */
    @GetMapping("/isbn13/{isbn13}")
    public Book bookDetailsByISBN13(@PathVariable Long isbn13) {
        return bookService.getBookInfoWithISBN13(isbn13);
    }

    /**
     * Search books with given request parameters
     * @param category category, default null
     * @param author author, default null
     * @param title title, default null
     * @param priceMin minimum price, default 'price_min=0'
     * @param priceMax maximum price, default 'price_max=10000'
     * @param ratingAbove minimum rating, default 'rating_above=0'
     * @return list of book
     */
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
