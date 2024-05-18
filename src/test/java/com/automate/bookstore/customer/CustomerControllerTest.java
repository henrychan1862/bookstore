package com.automate.bookstore.customer;

import com.automate.bookstore.book.Book;
import com.automate.bookstore.book.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.automate.bookstore.book.BookController;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(controllers = CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)   // turn off security filter
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private Customer customerA;
    private CustomerDto customerDtoA;

    @BeforeEach
    public void init() {
        customerA = new Customer(1L, "test", "Testy", "Tester", "test@gmail.com", "1 Test Road", 12345678);
        customerDtoA = new CustomerDto("Testy", "Tester", "test@gmail.com", "1 Test Road", 12345678);
    }

    @Test
    public void customerDetails_ReturnCustomer() throws Exception {
        given(customerService.getCustomerInfo(anyString())).willReturn(customerA);

        mockMvc.perform(get("/api/customers/{username}", "test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("customerId").value(1L))
                .andExpect(jsonPath("userName").value("test"))
                .andExpect(jsonPath("firstName").value("Testy"))
                .andExpect(jsonPath("lastName").value("Tester"))
                .andExpect(jsonPath("emailAddress").value("test@gmail.com"))
                .andExpect(jsonPath("deliveryAddress").value("1 Test Road"))
                .andExpect(jsonPath("phoneNumber").value(12345678));

        verify(customerService).getCustomerInfo(anyString());
    }


    @Test
    public void customerInfoUpdateWithJson_ReturnUpdatedCustomer() throws Exception {
        given(customerService.updateCustomerInfo(anyString(), any(CustomerDto.class))).willReturn(customerA);

        mockMvc.perform(put("/api/customers/{username}", "test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDtoA)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/customers/test"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("customerId").value(1L))
                .andExpect(jsonPath("userName").value("test"))
                .andExpect(jsonPath("firstName").value("Testy"))
                .andExpect(jsonPath("lastName").value("Tester"))
                .andExpect(jsonPath("emailAddress").value("test@gmail.com"))
                .andExpect(jsonPath("deliveryAddress").value("1 Test Road"))
                .andExpect(jsonPath("phoneNumber").value(12345678));

        verify(customerService).updateCustomerInfo(anyString(), any(CustomerDto.class));
    }

    /**
    * helper function from spring.io
    */
    protected static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
