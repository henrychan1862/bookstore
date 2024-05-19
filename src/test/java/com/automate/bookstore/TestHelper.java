package com.automate.bookstore;

import com.fasterxml.jackson.databind.*;


public class TestHelper {
    /**
    * helper function from spring.io, parse object to json for request body
    */
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
