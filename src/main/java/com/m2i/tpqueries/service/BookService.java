package com.m2i.tpqueries.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.m2i.tpqueries.entity.Book;
import com.m2i.tpqueries.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bRepo;
	
	public Book getById(int id) {
		return bRepo.findById(id).orElse(null);
	}
	
	public List<Book> getAll(){
		return bRepo.findAll();
	}
	
	public void create(Book b) {
		bRepo.save(b);
	}
	
	public void update(int id, Book b) {
		Book book = bRepo.findById(id).orElse(null);
		if(b!=null) {
			book.setTitle(b.getTitle());
			book.setSales(b.getSales());
			book.setPrice(b.getPrice());
			book.setEdition(b.getEdition());
			bRepo.save(book);
		}
	}
	
	public void delete(int id) {
		Book book = bRepo.findById(id).orElse(null);
		if (book!= null) {
			bRepo.delete(book);
		}
		
	}
	
	
	public Page<Book> findAllWithPagination(int page, int size){
		Sort sort = Sort.by("title").ascending();
		Pageable p = PageRequest.of(page, size, sort);
		
		return bRepo.findAll(p);
	}


}
