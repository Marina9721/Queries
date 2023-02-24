package com.m2i.tpqueries.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.m2i.tpqueries.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	//1. Renvoie le nom de tous les livres de cette edition
	@Query(value="SELECT b.title FROM Book b WHERE b.edition=?1")
	List<String> findTitleByEdition(String edition);
	
	// 4. Renvoie les livres avec une pagination et triés par titre
	Page<Book> findAll(Pageable p);
}
