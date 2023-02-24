package com.m2i.tpqueries.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.m2i.tpqueries.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>{
	
	// 2. Renvoie tous les auteurs triés par nationalité
	List<Author> findAll(Sort sort);
	
	// 3. Renvoie les auteurs avec une pagination
	Page<Author> findAll(Pageable p);
	
	// 5. Récupère la somme de tous les livres vendus d'un auteur
	@Query(value="SELECT SUM(b.sales) FROM Author a JOIN a.books b WHERE a.name=?1")
	Integer getSalesCountByAuthor(String name);
}
