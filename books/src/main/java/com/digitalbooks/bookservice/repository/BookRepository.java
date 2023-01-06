package com.digitalbooks.bookservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalbooks.bookservice.dto.BookDto;
import com.digitalbooks.bookservice.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
//	public List<Book> findByBookTitleorBookAuthororBookPublisherorBookPublishedDate(String title ,String author, String publisher,LocalDate publishedDate);
	public List<Book> findByBookAuthorUserName(String authorUserName);
	
}
