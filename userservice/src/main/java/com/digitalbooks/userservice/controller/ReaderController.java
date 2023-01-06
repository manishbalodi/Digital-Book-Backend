package com.digitalbooks.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.userservice.dto.BookDto;
import com.digitalbooks.userservice.service.ReaderService;

@RestController
@RequestMapping("/api/v1/digitalbooks/users/reader")
public class ReaderController {
	
	@Autowired
	ReaderService readerService;
	
	@PreAuthorize("hasRole('ROLE_READER')")
	@GetMapping("/subscribeBook")
	public ResponseEntity<?> subscribeBook(@RequestParam Long bookId,@RequestParam String userName
			,@RequestHeader(name="Authorization") String token){
		
		boolean result = readerService.subscribeBook(bookId,userName);
				return new ResponseEntity("subscribed"+result,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_READER')")
	@PostMapping("/unsubscribeBook")
	public ResponseEntity<?> unsubscribeBook(@RequestParam Long bookId,@RequestParam String userName
			,@RequestHeader(name="Authorization") String token){
		
		boolean result = readerService.unsubscribeBook(bookId,userName);
				return new ResponseEntity("unsubscribed  "+result,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_READER')")
	@GetMapping("/getSubscribedBooks")
	public ResponseEntity<?> getSubscribedBooks(@RequestParam String userName
			,@RequestHeader(name="Authorization") String token){
		List<BookDto> bookList = readerService.getSubscribedBooks(userName);
		return new ResponseEntity(bookList,HttpStatus.OK);
		
	}
	

}
