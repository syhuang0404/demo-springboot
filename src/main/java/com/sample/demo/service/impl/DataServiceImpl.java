package com.sample.demo.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.demo.model.dao.BookDao;
import com.sample.demo.model.entity.Book;
import com.sample.demo.service.DataService;

@Service
public class DataServiceImpl implements DataService {
	@Autowired
    private BookDao bookDao;

	@Override
	public void insertBook(Book book) {
		book.setUpdatedTime(Instant.now());
		bookDao.save(book);
	}

	@Override
	public void updateBookById(Integer id, Book book) {
		Book oriBook = bookDao.selectBookById(id);
		if(Objects.isNull(oriBook)) {
			return;
		}
		bookDao.updateBookById(book.getName(), Instant.now(), id);
	}

	@Override
	public void deleteBookById(Integer id) {
		bookDao.deleteById(id);		
	}

	@Override
	public List<Book> selectBooks() {
		return bookDao.findAll();
	}

}
