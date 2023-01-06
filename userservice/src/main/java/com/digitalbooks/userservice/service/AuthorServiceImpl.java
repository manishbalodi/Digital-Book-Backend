package com.digitalbooks.userservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.digitalbooks.userservice.dto.BookDto;
import com.digitalbooks.userservice.entity.AuthorBooks;
import com.digitalbooks.userservice.entity.ReaderBooks;
import com.digitalbooks.userservice.exception.UserServiceException;
import com.digitalbooks.userservice.feignclient.BookClient;
import com.digitalbooks.userservice.repository.AuthorBooksRepository;
import com.digitalbooks.userservice.repository.ReaderBooksRepository;

@Service
public class AuthorServiceImpl implements AuthorService{
	
	@Autowired
	BookClient bookClient;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${baseUrl}")
	String baseUrl;
	
	@Autowired
	AuthorBooksRepository authorRepo;
	
	@Autowired
	ReaderBooksRepository readerRepo;

	@Override
	public List<BookDto> getAllWrittenBooks(String authorUserName) {
		
		BookDto[] response = restTemplate.getForObject(baseUrl+"/api/v1/digitalbooks/books/getAuthorAllBooks?authorUserName="+authorUserName, BookDto[].class);
		List<BookDto> list = Arrays.asList(response);
		return list;
	}

	@Override
	public String createBook(BookDto bookDto) {
		
		Long bookId = restTemplate.postForObject(baseUrl+"/api/v1/digitalbooks/books/createBook", bookDto, Long.class);
		authorRepo.save(new AuthorBooks(0,bookDto.getBookAuthorUserName(),bookId));
		return "Book Saved";
	}

	@Override
	public String toggleBookLock(String userName, Long bookId) throws UserServiceException {
		Boolean toggled = restTemplate.postForObject(baseUrl+"/api/v1/digitalbooks/books/toggleBookLock?userName="+userName+"&"+"bookId="+bookId, null,Boolean.class);
		if(toggled) {
			return "Operation Succesfull";
		}else
		{
			throw new UserServiceException("Locking/Unlocking Book failed");
		}
	}

	@Override
	public boolean deleteBook(String userName,Long bookId) throws UserServiceException {
		boolean yes=false;
		Optional<List<ReaderBooks>> list = readerRepo.findByBookId(bookId); 
		if(list.isPresent()) {
			if(list.get().size()!=0)
				return false;
		}
			
		ResponseEntity<Boolean> result = restTemplate.exchange(baseUrl+"/api/v1/digitalbooks/books/deleteBook?bookId="+bookId,HttpMethod.DELETE, null,Boolean.class);
		if(result.getBody()) {
			AuthorBooks authorBooks = authorRepo.findByAuthorUserNameAndBookId(userName,bookId);
			authorRepo.delete(authorBooks);
			return true;
		}else
			throw new UserServiceException("Deleting Book failed");
	}

}
