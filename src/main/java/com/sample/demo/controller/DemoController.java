package com.sample.demo.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.demo.model.entity.Book;
import com.sample.demo.model.repo.DemoResponse;
import com.sample.demo.service.DataService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Demo", description = "Spring boot Demo")
@RestController
@RequestMapping(value="/demo")
public class DemoController {
	@Autowired
	private DataService dataService;
	
	@Operation(summary = "Read Books")
	@GetMapping(value="/books", produces = APPLICATION_JSON_UTF8_VALUE)
	public DemoResponse getBooks() {
		List<Object> rows = new ArrayList<>(dataService.selectBooks());
        DemoResponse demoResponse = new DemoResponse();
        demoResponse.setMessage("Read Success");
        demoResponse.setRows(rows);
        return demoResponse;
	}
	
	
	@Operation(summary = "Create new Book")
	@PostMapping(value="/book", produces = APPLICATION_JSON_UTF8_VALUE)
    public DemoResponse createBook(@RequestBody Book book) {
		dataService.insertBook(book);
        DemoResponse demoResponse = new DemoResponse();
        demoResponse.setMessage("Create Success");
        return demoResponse;
    }
	
	@Operation(summary = "Update Book")
	@PutMapping(value="/book/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public DemoResponse updateBook(@PathVariable Integer id, @RequestBody Book book) {
		dataService.updateBookById(id, book);
		List<Object> rows = new ArrayList<>(dataService.selectBooks());
        DemoResponse demoResponse = new DemoResponse();
        demoResponse.setMessage("Update Success");
        demoResponse.setRows(rows);
        return demoResponse;
    }
	
	@Operation(summary = "Delete Book")
	@DeleteMapping(value="/book/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public DemoResponse deleteBook(@PathVariable Integer id) {
		dataService.deleteBookById(id);     
		DemoResponse demoResponse = new DemoResponse();
        demoResponse.setMessage("Delete Success");
        return demoResponse;
    }
}
