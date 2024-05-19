package com.automate.bookstore.book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of service layer for books
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final BookSpecification bookSpecification;  // get dynamic query helper

    public BookServiceImpl(BookRepository bookRepository, BookSpecification bookSpecification) {
        this.bookRepository = bookRepository;
        this.bookSpecification = bookSpecification;
    }

    /**
     * get single book given book id
     * @param bookId book Id
     * @return the book object
     * @throws BookNotFoundException if book is not found
     */
    @Override
    public Book getBookInfo(long bookId) {
        Optional<Book> foundBook = bookRepository.findByBookId(bookId);
        if (foundBook.isEmpty())
            throw new BookNotFoundException("The book with id " + bookId + " is not found.");
        return foundBook.get();
    }

    /**
     * get single book given book ISBN13
     * @param ISBN13 ISBN13
     * @return the book object
     * @throws BookNotFoundException if book is not found
     */
    @Override
    public Book getBookInfoWithISBN13(long ISBN13) {
        Optional<Book> foundBook = bookRepository.findByISBN13(ISBN13);
        if (foundBook.isEmpty())
            throw new BookNotFoundException("The book with ISBN13 " + ISBN13 + " is not found.");
        return foundBook.get();
    }

    /**
     * Search books using dynamic query from Specification class
     * @param category category
     * @param author author
     * @param title title
     * @param priceMin minimum price
     * @param priceMax maximum price
     * @param ratingAbove minimum rating
     * @return list of book
     * @throws BookNotFoundException if book is not found
     */
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

    /**
     * view all books on database
     * @return list of books
     */
    @Override
    public List<Book> getBookRecommendations() {
        return bookRepository.findAll();
    }
}
