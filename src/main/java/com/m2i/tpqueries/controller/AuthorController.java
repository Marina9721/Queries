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
import com.m2i.tpqueries.entity.Author;
import com.m2i.tpqueries.entity.Book;
import com.m2i.tpqueries.model.AuthorNameAndTitle;
import com.m2i.tpqueries.repository.AuthorRepository;
import com.m2i.tpqueries.repository.BookRepository;
import com.m2i.tpqueries.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {
	
	@Autowired
	AuthorService service;
	
	@Autowired
	AuthorRepository aRepo;
	
	@Autowired
	BookRepository bRepo;
	
	@PostMapping("/fake/{number}")
	public void createFakeAuthors(@PathVariable int number) {
		Faker f = new Faker();
		for(int i = 0; i < number; i++) {
			Author a = new Author(f);
			for(int j = 0; j < 3; j++) {
				Book b = new Book(f);
				a.getBooks().add(b);
				bRepo.save(b);
			}
			this.aRepo.save(a);
		}
	}
	
	@GetMapping("/{id}")
	public Author getAuthorById(@PathVariable int id) {
		return service.getById(id);
	}

	@GetMapping("/all")
	public List<Author> getAllAuthors(){
		return service.getAll();
	}
	
	@PostMapping
	public void postAuthor(@RequestBody Author a) {
		service.create(a);
	}
	
	
	@PutMapping("/{id}")
	public void putAuthor(@PathVariable int id, @RequestBody Author a) {
		service.update(id, a);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAuthor(@PathVariable int id) {
		service.delete(id);
	}
	
	@GetMapping("/getAll")
	public List<Author> getAll(){
		return aRepo.findAll();
	}
	
	
	@GetMapping("/nationality")
	public List<Author> getAllSortedByNationality(){
		return service.findAllSortedByNationality();
	}
	
	
	@GetMapping("/all/{page}/{size}")
	public Page<Author> getAll(@PathVariable("page") int page, @PathVariable("size") int size){
		return service.findAllWithPagination(page,size);
	}
	
	@GetMapping("/{name}/sales")
	public int getTotalSalesByAuthor(@PathVariable String name) {
		return aRepo.getSalesCountByAuthor(name);
	}
	
	@GetMapping("/{name}/nbBooks")
	public int getTotalBooksByAuthor(@PathVariable String name) {
		return aRepo.countBookByAuthor(name);
	}
	
	@GetMapping("/{nameAuthor}/list")
	public List<String> find(@PathVariable String nameAuthor) {
		return aRepo.findAllTitleByAuthor(nameAuthor);
	}
	
	@GetMapping("/allbooks")
	public List<AuthorNameAndTitle> getAllBooksByAuthor(){
		return aRepo.getAuthorNameAndTitleList();
	}
}
