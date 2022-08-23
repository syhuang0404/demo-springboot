package com.sample.demo.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import com.sample.demo.model.dao.BookDao;
import com.sample.demo.model.entity.Book;
import com.sample.demo.service.DataService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@DirtiesContext
class DataServiceImplTest {
    @InjectMocks
    private DataService dataService = new DataServiceImpl();

    @Mock
    private BookDao bookDao;

    @Test
    void shouldReturnInsertBookSuccess() {
        Book book = new Book();
        dataService.insertBook(book);
        verify(bookDao, times(1)).save(book);
    }

    @Test
    void shouldReturnUpdateBookByIdSuccess() {
    	Instant updatedTime = Instant.now();
    	Book book = new Book();
    	book.setId(1);
    	book.setName("BOOK");
    	book.setUpdatedTime(updatedTime);
        
        // Mock
        Mockito.when(bookDao.selectBookById(1)).thenReturn(book);        
        dataService.updateBookById(1, book);
        verify(bookDao, times(1)).updateBookById(Mockito.any(), Mockito.any(), Mockito.any());
    }
    
    @Test
    void shouldReturnUpdateBookByIdSuccessWhenResourceIsNotExisted() {
    	Instant updatedTime = Instant.now();
    	Book book = new Book();
    	book.setId(1);
    	book.setName("BOOK");
    	book.setUpdatedTime(updatedTime);
        
        // Mock
        Mockito.when(bookDao.selectBookById(1)).thenReturn(null);        
        dataService.updateBookById(1, book);
        verify(bookDao, times(0)).updateBookById(Mockito.any(), Mockito.any(), Mockito.any());
    }

    @Test
    void shouldReturnDeleteBookByIdSuccess() {
        dataService.deleteBookById(1);
        verify(bookDao, times(1)).deleteById(1);
    }

    @Test
    void shouldReturnSelectBooksSuccess() {
        List<Book> expectedResult = new ArrayList<>();
    	Book book = new Book();
    	book.setId(1);
    	book.setName("BOOK");
    	book.setUpdatedTime(Instant.now());
        expectedResult.add(book);

        // Mock
        Mockito.when(bookDao.findAll()).thenReturn(expectedResult);

        List<Book> result = dataService.selectBooks();
        Assertions.assertEquals(expectedResult, result);
    }
    
}