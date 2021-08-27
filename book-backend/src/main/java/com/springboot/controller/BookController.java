package com.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.exception.ResourceNotFoundException;
import com.springboot.model.Book;
import com.springboot.repository.BookRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class BookController {
	
	@Autowired // @Autowired annotation on properties to get rid of the setter methods.
	private BookRepository bookRepository;
	
	// get all books
	@GetMapping("/books") // the url with /books will return list of book
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	// create Book rest api
	@PostMapping("/books")
	public Book createBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}
	
	// get book by id rest api
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book do not exist with id :" + id));
		return ResponseEntity.ok(book);
	}
	
	// update book rest epi
	@PutMapping("/books/{id}")
	// pathvariable annotation - it would be an endpoint that identifies an entity with a primary key
	// requestbody annotation - to map httprequest body() to a domain object(book object)
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
		// retrieved an book from DB using ID.
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee do not exist with id :" + id));
		
		// get new details from bookDetails.getTitle() 
		// update by using book.setTitle.
		book.setTitle(bookDetails.getTitle());
		book.setAuthor(bookDetails.getAuthor());
		book.setYear(bookDetails.getYear());
		book.setPrice(bookDetails.getPrice());
		
		// return updated book object to the client
		Book updatedBook = bookRepository.save(book);
		return ResponseEntity.ok(updatedBook);
	}
	
	// delete book rest api
	@DeleteMapping("/books/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable Long id){
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not exist with id :" + id));
		
		bookRepository.delete(book);
		// for response
		Map<String, Boolean> response = new HashMap<>();
		// if deleted, response true returns
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	

}