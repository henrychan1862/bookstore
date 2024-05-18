package com.automate.bookstore.book;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final BookSpecification bookSpecification;

    public BookServiceImpl(BookRepository bookRepository, BookSpecification bookSpecification) {
        this.bookRepository = bookRepository;
        this.bookSpecification = bookSpecification;
    }


    @Override
    public Book getBookInfo(long bookId) {
        Optional<Book> foundBook = bookRepository.findByBookId(bookId);
        if (foundBook.isEmpty())
            throw new BookNotFoundException("The book with id " + bookId + " is not found.");
        return foundBook.get();
    }

    @Override
    public Book getBookInfoWithISBN13(long ISBN13) {
        Optional<Book> foundBook = bookRepository.findByISBN13(ISBN13);
        if (foundBook.isEmpty())
            throw new BookNotFoundException("The book with ISBN13 " + ISBN13 + " is not found.");
        return foundBook.get();
    }

    @Override
    public List<Book> searchBook(String category,
                                 String author,
                                 String title,
                                 Integer priceMin,
                                 Integer priceMax,
                                 Integer ratingAbove) {

        List<Book> foundBooks = bookRepository.findAll(bookSpecification.buildFindAllByStringSpecs(category, author, title, priceMin, priceMax, ratingAbove));

        if (foundBooks.isEmpty())
            throw new BookNotFoundException("No Book is found with criteria provided.");

        return foundBooks;
    }

    @Override
    public List<Book> getBookRecommendations() {
        return bookRepository.findAll();
    }
}
