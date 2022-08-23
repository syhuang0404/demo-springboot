package com.sample.demo.model.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Book", description = "Book Table Schema")
@Entity
@Table(name = "T_BOOK_INFO")
public class Book {
	@Schema(description = "Book Id", required = true, example = "1")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

	@Schema(description = "Book Name", required = true, example = "Book Name")
    @Column
    String name;

	@Schema(description = "Data Updated Time", required = true)
    @Column
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    Instant updatedTime;
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUpdatedTime(Instant updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Instant getUpdatedTime() {
        return updatedTime;
    }
}
