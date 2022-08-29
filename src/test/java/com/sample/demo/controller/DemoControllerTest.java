package com.sample.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sample.demo.model.entity.Book;
import com.sample.demo.model.response.DemoResponse;
import com.sample.demo.service.DataService;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class DemoControllerTest {
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataService dataService;
    
    private ObjectMapper objectMapper;
    
    @BeforeEach
    void setUp() {
    	objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }
    
    @Test
    void shouldReturnSelectBooksSuccess() {
        String expectedResult = "";
        String result = "";
        Instant updatedTime = Instant.now();

        List<Book> bookList = new ArrayList<>();
        Book book = new Book();
        book.setId(1);
        book.setName("BOOK_1");
        book.setUpdatedTime(updatedTime);
        
        bookList.add(book);

        List<Object> rows = new ArrayList<>(bookList);
        DemoResponse demoResponse = new DemoResponse();
        demoResponse.setMessage("Read Success");
        demoResponse.setRows(rows);
        try {
            expectedResult = objectMapper.writeValueAsString(demoResponse);
        } catch (JsonProcessingException e) {
            expectedResult = "Json processing error";
        }

        // Mock
        Mockito.when(dataService.selectBooks()).thenReturn(bookList);

        try {
            result = mockMvc.perform(MockMvcRequestBuilders.get("/demo/books")
                            .accept(MediaType.APPLICATION_JSON ))
                            .andExpect(status().isOk())
                            .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            result = "abnormal";
        }
        Assertions.assertEquals(expectedResult, result);
    }
    
    @Test
    void shouldReturnInsertBookEntitySuccess() {
        String expectedResult = "";
        String result = "";

        DemoResponse demoResponse = new DemoResponse();
        demoResponse.setMessage("Create Success");
        
        
        String body = "{\"name\": \"Book Name\"}";
        try {
            expectedResult = objectMapper.writeValueAsString(demoResponse);
        } catch (JsonProcessingException e) {
            expectedResult = "Json processing error";
        }

        // Mock
        Mockito.doNothing().when(dataService).insertBook(Mockito.any());

        try {
            result = mockMvc.perform(MockMvcRequestBuilders.post("/demo/book")
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            result = "abnormal";
        }
        Assertions.assertEquals(expectedResult, result);
    }
    
    @Test
    void shouldReturnUpdateBookEntitySuccess() {
        String expectedResult = "";
        String result = "";

        DemoResponse demoResponse = new DemoResponse();
        demoResponse.setMessage("Update Success");
        
        List<Book> bookList = new ArrayList();
        
        String body = "{}";
        try {
        	Book book = new Book();
        	book.setId(1);
        	book.setName("BOOK_1");
        	book.setUpdatedTime(Instant.now());
        	List<Object> rows = new ArrayList();
        	rows.add(book);
        	bookList.add(book);
        	demoResponse.setRows(rows);
        	body = objectMapper.writeValueAsString(book);
            expectedResult = objectMapper.writeValueAsString(demoResponse);
        } catch (JsonProcessingException e) {
            expectedResult = "Json processing error";
        }

        // Mock
        Mockito.doNothing().when(dataService).updateBookById(Mockito.any(), Mockito.any());
        Mockito.when(dataService.selectBooks()).thenReturn(bookList);

        try {
            result = mockMvc.perform(MockMvcRequestBuilders.put("/demo/book/1")
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            result = "abnormal";
        }
        Assertions.assertEquals(expectedResult, result);
    }
    
    @Test
    void shouldReturnDeleteBookEntitySuccess() {
        String expectedResult = "";
        String result = "";

        DemoResponse demoResponse = new DemoResponse();
        demoResponse.setMessage("Delete Success");
        
        try {
            expectedResult = objectMapper.writeValueAsString(demoResponse);
        } catch (JsonProcessingException e) {
            expectedResult = "Json processing error";
        }

        // Mock
        Mockito.doNothing().when(dataService).deleteBookById(1);;

        try {
            result = mockMvc.perform(MockMvcRequestBuilders.delete("/demo/book/1")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            result = "abnormal";
        }
        Assertions.assertEquals(expectedResult, result);
    }
    
    
}
