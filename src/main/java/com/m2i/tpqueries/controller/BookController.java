package com.m2i.tpqueries.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;
import com.m2i.tpqueries.entity.Book;
import com.m2i.tpqueries.repository.BookRepository;
import com.m2i.tpqueries.service.BookService;


@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookService service;
	
	@Autowired
	BookRepository repo;
	
	
	@PostMapping("/fake/{number}")
	public void createFakeBooks(@PathVariable int number) {
		Faker f = new Faker();
		for(int i = 0; i < number; i++) {
			Book b = new Book(f);
			this.repo.save(b);
		}
	}
	
	@GetMapping("/{id}")
	public Book getBookById(@PathVariable int id) {
		return service.getById(id);
	}

	@GetMapping("/all")
	public List<Book> getAllBooks(){
		return service.getAll();
	}
	
	@PostMapping
	public void postBook(@RequestBody Book b) {
		service.create(b);
	}
	
	
	@PutMapping("/{id}")
	public void putBook(@PathVariable int id, @RequestBody Book b) {
		service.update(id, b);
	}
	
	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable int id) {
		service.delete(id);
	}
	
	@GetMapping("/edition/{nameEdition}")
	public List<String> findTitleByEdition(@PathVariable String nameEdition) {
		return repo.findTitleByEdition(nameEdition);
	}
	
	@GetMapping("/all/{page}/{size}")
	public Page<Book> getAll(@PathVariable("page") int page, @PathVariable("size") int size){
		return service.findAllWithPagination(page,size);
	}
	
	@GetMapping("/sales/{quantity}")
	public List<Book> getBestSales(@PathVariable int quantity){
		return repo.findBySalesGreaterThan(quantity);
	}
}
