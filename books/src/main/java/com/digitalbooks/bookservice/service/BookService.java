package com.digitalbooks.bookservice.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.digitalbooks.bookservice.dto.BookDto;
import com.digitalbooks.bookservice.entity.Book;

public interface BookService {
	
	public List<BookDto> searchBooks(String title ,String author, String publisher);
	
	public Long createBook(BookDto bookDto);
	
	public BookDto getBookById(Long bookId);
	
	public List<BookDto> findByBookAuthorUserName(String authorUserName);
	
	public List<BookDto> allBooks();
	
	public boolean toggleBookLock(String userName ,Long bookId);
	
	public boolean deleteBook(Long bookId);

	public boolean isBookPresent(Long bookId);

}
