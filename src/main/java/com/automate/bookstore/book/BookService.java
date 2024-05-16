package com.automate.bookstore.book;

import java.util.List;

public interface BookService {

    Book getBookInfo(long bookId);

    List<Book> searchBook(String category);

    List<Book> getBookRecommendations();
}
