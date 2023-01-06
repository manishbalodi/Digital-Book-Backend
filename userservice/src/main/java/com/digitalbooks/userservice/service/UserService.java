package com.digitalbooks.userservice.service;

import java.util.List;

import com.digitalbooks.userservice.dto.BookDto;
import com.digitalbooks.userservice.entity.User;

public interface UserService {

	public boolean registerUser(User user);
	
	public List<BookDto> getAllActiveBooks();

}
