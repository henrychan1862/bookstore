package com.automate.bookstore.book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book getBookInfo(long bookId);

//    List<Book> searchBook(Optional<String> category, Optional<String> author, Optional<String> title);

    List<Book> searchBook(Optional<String> category,
                          Optional<String> author,
                          Optional<String> title,
                          Optional<Integer> priceMin,
                          Optional<Integer> priceMax,
                          Optional<Integer> ratingAbove);

    List<Book> getBookRecommendations();
}
