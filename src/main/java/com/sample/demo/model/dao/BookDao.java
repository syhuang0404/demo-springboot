package com.sample.demo.model.dao;

import java.time.Instant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.demo.model.entity.Book;

@Service
public interface BookDao extends JpaRepository<Book, Integer>{
	@Transactional
    @Modifying
    @Query(value = "UPDATE T_BOOK_INFO SET NAME=:name, UPDATED_TIME=:updatedTime where ID = :id", nativeQuery = true)
    void updateBookById(String name, Instant updatedTime, Integer id);

    @Query(value = "SELECT * FROM T_BOOK_INFO where ID = :id", nativeQuery = true)
    Book selectBookById(Integer id);

}
