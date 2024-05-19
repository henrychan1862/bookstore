package com.automate.bookstore.order;

import com.automate.bookstore.TestHelper;
import com.automate.bookstore.book.Book;
import com.automate.bookstore.customer.Customer;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = OrderController.class)
@AutoConfigureMockMvc(addFilters = false)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    private Order orderA;
    private Customer custoemrA;
    private Book bookA;
    private OrderTicket orderTicketA;

    @BeforeEach
    public void init() {
        custoemrA = new Customer(1L, "test", "Testy", "Tester", "test@gmail.com", "1 Test Road", 12345678);
        bookA = new Book(1L, 1234567890123L, "Book A", "Author A", 100.0f, "Category A", 1.0f);
        orderA = new Order(1L, custoemrA, bookA, 1);
        orderTicketA = new OrderTicket(1234567890123L, 1);
    }

    @Test
    public void ordersView_ReturnListOfOrder() throws Exception {
        given(orderService.viewOrders(anyString())).willReturn(List.of(orderA));

        mockMvc.perform(get("/api/orders/{username}", "test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].orderId").value(1L))
                .andExpect(jsonPath("$[0].customer.customerId").value(1L))
                .andExpect(jsonPath("$[0].book.bookId").value(1L))
                .andExpect(jsonPath("$[0].amount").value(1));

        verify(orderService).viewOrders(anyString());

    }

    @Test
    public void ordersDetails_ReturnOrder() throws Exception {
        given(orderService.getOrderInfoWithCustomer(anyString(), anyLong())).willReturn(orderA);

        mockMvc.perform(get("/api/orders/{username}/{orderId}", "test", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("orderId").value(1L))
                .andExpect(jsonPath("customer.customerId").value(1L))
                .andExpect(jsonPath("book.bookId").value(1L))
                .andExpect(jsonPath("amount").value(1));

    }

    @Test
    public void orderCancellation_DeleteOrder() throws Exception {
        doNothing().when(orderService).cancelOrder("test", 1L);

        mockMvc.perform(delete("/api/orders/{username}/{orderId}", "test", 1L))
                .andDo(print())
                .andExpect(status().isOk());

        verify(orderService).cancelOrder(anyString(), anyLong());
    }

    @Test
    public void orderBook_ReturnOrderPlaced() throws Exception {
        when(orderService.placeOrder(anyString(), any())).thenReturn(orderA);

        mockMvc.perform(post("/api/orders/{username}", "test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.asJsonString(orderTicketA)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/orders/test/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("orderId").value(1L))
                .andExpect(jsonPath("customer.customerId").value(1L))
                .andExpect(jsonPath("book.bookId").value(1L))
                .andExpect(jsonPath("amount").value(1));

        verify(orderService).placeOrder(anyString(), any(OrderTicket.class));
    }


}
