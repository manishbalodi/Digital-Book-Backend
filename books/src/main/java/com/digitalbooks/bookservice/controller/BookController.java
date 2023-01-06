package com.digitalbooks.bookservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.bookservice.dto.BookDto;
import com.digitalbooks.bookservice.service.BookService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/digitalbooks/books")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/search")
	public List<BookDto> searchBooks(@RequestParam(name="title", required=false) String title, @RequestParam(name="author" ,required = false) String author,
			@RequestParam(name="publisher" ,required = false) String publisher){
		
		List<BookDto> bookList = bookService.searchBooks(title, author, publisher);
		
		return bookList;
	}
	
	@GetMapping("/allBooks")
	public List<BookDto> allBooks(){
		return bookService.allBooks();
	}
	
	@GetMapping("/searchById")
	public BookDto searchBookById(@RequestParam Long bookId) {
		return bookService.getBookById(bookId);
	}
	
	@GetMapping("/getAuthorAllBooks")
	public List<BookDto> getAuthorAllBooks(@RequestParam String authorUserName){
		return bookService.findByBookAuthorUserName(authorUserName);
	}
	
	@PostMapping("/createBook")
	public Long createBook(@RequestBody BookDto bookDto) {
		
		return bookService.createBook(bookDto);

	}
	
	@PostMapping("/toggleBookLock")
	public boolean toggleBookLock(@RequestParam String userName , @RequestParam Long bookId) {
		return bookService.toggleBookLock(userName, bookId);
	}
	
	@DeleteMapping("/deleteBook")
	public boolean deleteBook(@RequestParam Long bookId) {
		return bookService.deleteBook(bookId);
	}
	
	@GetMapping("/isBookPresent")
	public boolean isBookPresent(@RequestParam Long bookId) {
		return bookService.isBookPresent(bookId);
	}

}
