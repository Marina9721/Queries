package com.m2i.tpqueries.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.m2i.tpqueries.entity.Author;
import com.m2i.tpqueries.model.AuthorNameAndTitle;

public interface AuthorRepository extends JpaRepository<Author, Integer>{
	
	// 2. http://localhost:8080/author/nationality Renvoie tous les auteurs triés par nationalité
	// ou utiliser findAllOrderByNationality()
	List<Author> findAll(Sort sort);
	
	// 3. localhost:8080/author/all/{page}/{size} Renvoie les auteurs avec une pagination
	Page<Author> findAll(Pageable p);
	
	// 5. http://localhost:8080/author/{nameAuthor}/sales Récupère la somme de tous les livres vendus d'un auteur
	@Query(value="SELECT SUM(b.sales) FROM Author a JOIN a.books b WHERE a.name=?1")
	Integer getSalesCountByAuthor(String name);
	
	// 7. http://localhost:8080/author/{name}/nbBooks Renvoie le nombre de tomes d'un author
	@Query(value="SELECT COUNT(b) FROM Author a JOIN a.books b WHERE a.name=?1")
	Integer countBookByAuthor(String name);
	
	// 8. http://localhost:8080/author/{name}/list Renvoie une liste de string avec le nom de chaque tome écrit par un author
	@Query(value="SELECT b.title FROM Author a JOIN a.books b WHERE a.name=?1")
	List<String> findAllTitleByAuthor(String name);
	
	// 9. http://localhost:8080/author/allbook -> Renvoie une liste d'objets contenant le nom de l'auteur et le nom du livre
	@Query(value="SELECT new com.m2i.tpqueries.model.AuthorNameAndTitle(a.name, b.title) FROM Author a JOIN a.books b")
	List<AuthorNameAndTitle> getAuthorNameAndTitleList();
}
