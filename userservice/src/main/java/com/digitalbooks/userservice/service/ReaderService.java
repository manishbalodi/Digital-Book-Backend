package com.digitalbooks.userservice.service;

import java.util.List;

import com.digitalbooks.userservice.dto.BookDto;

public interface ReaderService {

	boolean subscribeBook(Long bookId, String userName);
	boolean unsubscribeBook(Long bookId, String userName);
	List<BookDto> getSubscribedBooks(String userName);

}
