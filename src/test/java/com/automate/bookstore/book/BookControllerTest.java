package com.automate.bookstore.book;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Endpoints unit tests for book controller.
 * Service layers are being mocked to create isolated testing environment.
 */
@WebMvcTest(controllers = BookController.class)
@AutoConfigureMockMvc(addFilters = false)   // turn off security filter
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private Book bookA;

    @BeforeEach
    public void init() {
        bookA = new Book(1L, 1234567890123L, "Book A", "Author A", 100.0f, "Category A", 1.0f);
    }

    @Test
    public void  bookDetails_ReturnBook() throws Exception {
        given(bookService.getBookInfo(1L)).willReturn(bookA);

        mockMvc.perform(get("/api/books/{bookId}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("bookId").value(1L))
                .andExpect(jsonPath("isbn13").value(1234567890123L))
                .andExpect(jsonPath("title").value("Book A"))
                .andExpect(jsonPath("author").value("Author A"))
                .andExpect(jsonPath("price").value(100.0f))
                .andExpect(jsonPath("category").value("Category A"))
                .andExpect(jsonPath("rating").value(1.0f));

        verify(bookService).getBookInfo(anyLong());
    }

    @Test
    public void  bookDetailsByISBN13_ReturnBook() throws Exception {
        given(bookService.getBookInfoWithISBN13(1234567890123L)).willReturn(bookA);

        mockMvc.perform(get("/api/books/isbn13/{isbn13}", 1234567890123L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("bookId").value(1L))
                .andExpect(jsonPath("isbn13").value(1234567890123L))
                .andExpect(jsonPath("title").value("Book A"))
                .andExpect(jsonPath("author").value("Author A"))
                .andExpect(jsonPath("price").value(100.0f))
                .andExpect(jsonPath("category").value("Category A"))
                .andExpect(jsonPath("rating").value(1.0f));

        verify(bookService).getBookInfoWithISBN13(anyLong());
    }

    @Test
    public void  bookPickups_ReturnListOfBook() throws Exception {
        given(bookService.getBookRecommendations()).willReturn(List.of(bookA));

        mockMvc.perform(get("/api/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].bookId").value(1L))   //json will capture it in a JsonArry
                .andExpect(jsonPath("$[0].isbn13").value(1234567890123L))
                .andExpect(jsonPath("$..title").value("Book A"))
                .andExpect(jsonPath("$..author").value("Author A"))
                .andExpect(jsonPath("$[0].price").value(100.0f))
                .andExpect(jsonPath("$..category").value("Category A"))
                .andExpect(jsonPath("$[0].rating").value(1.0f));
        verify(bookService).getBookRecommendations();
    }

    @Test
    public void  bookSearch_ReturnListOfBook() throws Exception {
        given(bookService.searchBook( "Category A", "Author A","Title A",0,1000, 0))
                .willReturn(List.of(bookA));

        mockMvc.perform(get("/api/books/search")
                        .param("category", "Category A")
                        .param("author", "Author A")
                        .param("title", "Title A")
                        .param("price_min", "0")
                        .param("price_max", "1000")
                        .param("rating_above", "0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].bookId").value(1L))
                .andExpect(jsonPath("$[0].isbn13").value(1234567890123L))
                .andExpect(jsonPath("$..title").value("Book A"))
                .andExpect(jsonPath("$..author").value("Author A"))
                .andExpect(jsonPath("$[0].price").value(100.0f))
                .andExpect(jsonPath("$..category").value("Category A"))
                .andExpect(jsonPath("$[0].rating").value(1.0f));

        verify(bookService).searchBook(anyString(), anyString(), anyString(), anyInt(), anyInt(), anyInt());
    }
}
