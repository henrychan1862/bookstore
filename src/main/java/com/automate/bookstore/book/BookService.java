package com.automate.bookstore.book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book getBookInfo(long bookId);

    Book getBookInfoWithISBN13(long ISBN13);

//    List<Book> searchBook(Optional<String> category, Optional<String> author, Optional<String> title);

    List<Book> searchBook(String category,
                          String author,
                          String title,
                          Integer priceMin,
                          Integer priceMax,
                          Integer ratingAbove);

    List<Book> getBookRecommendations();
}
