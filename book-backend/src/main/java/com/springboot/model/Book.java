package com.springboot.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity // Entity Object mapping
@Table(name = "books")
public class Book {
	
	@Id // Specifies the primary key of an entity.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // The @GeneratedValue annotation is to configure the way of increment of the specified column(field).
	private long id;
	
	@Column(name = "book_title") // Column name in DB table
	private String title;
	@Column(name = "book_author")
	private String author;
	@Column(name = "book_year")
	private int year;
	@Column(name = "book_price", precision=8, scale=2)
	private double price;
	
	public Book() {
		
	}
	
	public Book(String title, String author, int year, double price) {
		super();
		this.title = title;
		this.author = author;
		this.year = year;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
