package com.automate.bookstore.book;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> bookPickups() {
        return bookService.getBookRecommendations();
    }

    @GetMapping("/books/{id}")
    public Book bookDetails(@PathVariable Long id) {
        return bookService.getBookInfo(id);
    }

    @GetMapping("/books/search")
    public List<Book> bookSearch(@RequestParam String category) {
        return bookService.searchBook(category);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(EntityNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("We cannot find your book!");
    }

}
