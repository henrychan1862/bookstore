package com.automate.bookstore.book;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping({"", "/"})
    public List<Book> bookPickups() {return bookService.getBookRecommendations(); }

    @GetMapping("/{id}")
    public Book bookDetails(@PathVariable Long id) {
        return bookService.getBookInfo(id);
    }

    @GetMapping("/search")
    public List<Book> bookSearch(@RequestParam Optional<String> category,
                                 @RequestParam Optional<String> author,
                                 @RequestParam Optional<String> title,
                                 @RequestParam(name = "price_min") Optional<Integer> priceMin,
                                 @RequestParam(name = "price_max") Optional<Integer> priceMax,
                                 @RequestParam(name = "rating_above") Optional<Integer> ratingAbove) {
        return bookService.searchBook(category, author, title, priceMin, priceMax, ratingAbove);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("We cannot find your book!" + "\n" + e.getMessage());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid type. Please revise searching keywords." + "\n" + e.getMessage());
    }

}
