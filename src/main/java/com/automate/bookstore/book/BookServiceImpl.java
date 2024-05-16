package com.automate.bookstore.book;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getBookInfo(long bookId) {
        Optional<Book> foundBook = bookRepository.findByBookId(bookId);
        if (foundBook.isEmpty())
            throw new EntityNotFoundException("The book with id " + bookId + " is not found.");
        return foundBook.get();
    }

    @Override
    public List<Book> searchBook(String category) {
        List<Book> foundBooks = bookRepository.findAllByCategory(category);
        if (foundBooks.isEmpty())
            throw new EntityNotFoundException("No Book is found within " + category + " category.");
        return foundBooks;
    }

    @Override
    public List<Book> getBookRecommendations() {
        return bookRepository.findAll();
    }
}
