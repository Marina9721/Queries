package com.m2i.tpqueries.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.github.javafaker.Faker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name="books")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Book {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
    private int sales;
    private int price;
    private String edition;
    
    public Book(Faker f) {
    	this.title = f.book().title();
    	this.sales = f.number().numberBetween(0, 500000);
    	this.price = f.number().numberBetween(2, 30);
    	this.edition = f.book().publisher();
    }
}
