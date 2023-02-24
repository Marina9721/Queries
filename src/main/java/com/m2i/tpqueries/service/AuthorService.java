package com.m2i.tpqueries.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.m2i.tpqueries.entity.Author;
import com.m2i.tpqueries.repository.AuthorRepository;

@Service
public class AuthorService {
	
	@Autowired
	AuthorRepository aRepo;
	
	public Author getById(int id) {
		return aRepo.findById(id).orElse(null);
	}
	
	public List<Author> getAll(){
		return aRepo.findAll();
	}
	
	public void create(Author a) {
		aRepo.save(a);
	}
	
	public void update(int id, Author a) {
		Author author = aRepo.findById(id).orElse(null);
		if(a!=null) {
			author.setName(a.getName());
			author.setBirthday(a.getBirthday());
			author.setNationality(a.getNationality());
			aRepo.save(author);
		}
	}
	
	public void delete(int id) {
		Author author = aRepo.findById(id).orElse(null);
		if (author!= null) {
			aRepo.delete(author);
		}
		
	}
	
	public List<Author> findAllSortedByNationality(){
		Sort sort = Sort.by("nationality").ascending();
		
		return aRepo.findAll(sort);
		
	}
	
	public Page<Author> findAllWithPagination(int page, int size){
		Pageable p = PageRequest.of(page,size);
		
		return aRepo.findAll(p);
	}
}
