package com.sample.demo.service;

import java.util.List;

import com.sample.demo.model.entity.Book;

public interface DataService {
	void insertBook(Book book);

    void updateBookById(Integer id, Book book);

    void deleteBookById(Integer id);

    List<Book> selectBooks();
}
