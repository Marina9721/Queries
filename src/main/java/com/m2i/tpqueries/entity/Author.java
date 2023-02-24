package com.m2i.tpqueries.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.github.javafaker.Faker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name="authors")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Author {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private Date birthday;
	private String nationality;
	
	@OneToMany(targetEntity=Book.class, cascade=CascadeType.ALL)
	@JoinColumn(name="fk_author_id", referencedColumnName="id")
	private List<Book> books;

	public Author(Faker f) {
		this.books = new ArrayList<>();
		this.name = f.book().author();
		this.birthday = f.date().birthday();
		this.nationality = f.country().name();
	}
}
