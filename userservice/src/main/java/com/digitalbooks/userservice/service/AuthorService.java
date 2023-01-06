package com.digitalbooks.userservice.service;

import java.util.List;

import com.digitalbooks.userservice.dto.BookDto;
import com.digitalbooks.userservice.exception.UserServiceException;

public interface AuthorService {
	
	public List<BookDto> getAllWrittenBooks(String authorUserName);
	
	public String createBook(BookDto bookDto);
	
	public String toggleBookLock(String userName,Long bookId) throws UserServiceException;
	
	public boolean deleteBook(String userName, Long bookId) throws UserServiceException;

}
