package com.digitalbooks.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.userservice.dto.BookDto;
import com.digitalbooks.userservice.exception.UserServiceException;
import com.digitalbooks.userservice.security.util.JwtUtil;
import com.digitalbooks.userservice.service.AuthorService;

@RestController
@RequestMapping("/api/v1/digitalbooks/users/author")
public class AuthorController {
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	AuthorService authorService;
	
	@PreAuthorize("hasRole('ROLE_AUTHOR')")
	@GetMapping("/books")
	public ResponseEntity<?> getAllWrittenBooks(@RequestParam String userName,
			@RequestHeader(name="Authorization") String token){
			List<BookDto> bookList = authorService.getAllWrittenBooks(userName);
			return new ResponseEntity(bookList,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_AUTHOR')")
	@PostMapping("/createBook")
	public ResponseEntity<?> createBook(@RequestBody BookDto bookDto,@RequestHeader(name="Authorization") String token){
		
		authorService.createBook(bookDto);
		return new ResponseEntity("Book Saved",HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_AUTHOR')")
	@PutMapping("/toggleLock")
	public ResponseEntity<?> toggleBookLock(@RequestParam String userName , @RequestParam Long bookId,
			@RequestHeader(name="Authorization") String token) throws UserServiceException{
		String result = authorService.toggleBookLock(userName, bookId);
		return new ResponseEntity(result,HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ROLE_AUTHOR')")
	@DeleteMapping("/deleteBook")
	public ResponseEntity<?> deleteBook(@RequestParam Long bookId,@RequestParam String userName
			,@RequestHeader(name="Authorization") String token) throws UserServiceException{
		Boolean result = authorService.deleteBook(userName,bookId);
		if(result) {
			return new ResponseEntity("deleted",HttpStatus.ACCEPTED);
		}else
		return new ResponseEntity("not deleted",HttpStatus.NOT_MODIFIED);
		
	}
	
}
