package com.digitalbooks.userservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.digitalbooks.userservice.dto.BookDto;
import com.digitalbooks.userservice.entity.ReaderBooks;
import com.digitalbooks.userservice.repository.ReaderBooksRepository;

@Service
public class ReaderServiceImpl implements ReaderService{
	
	@Autowired
	ReaderBooksRepository readerRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${baseUrl}")
	String baseUrl;

	@Override
	public boolean subscribeBook(Long bookId, String userName) {
		Boolean isPresent = restTemplate.getForObject(baseUrl+"/api/v1/digitalbooks/books/isBookPresent?bookId="+bookId, Boolean.class);
		if(isPresent) {
			readerRepo.save(new ReaderBooks(0, userName, bookId, LocalDate.now()));
			return true;
		}else
		{
			return false;
		}
		
	}
	
	@Override
	public boolean unsubscribeBook(Long bookId, String userName) {
		Optional<ReaderBooks> opt = readerRepo.findByBookIdAndUserName(bookId,userName);
		if(opt.isPresent() && (opt.get().getSubscriptionDate().isBefore(LocalDate.now()))) {
			readerRepo.delete(opt.get());
			return true;
		}else
		{
			return false;
		}
	}

	@Override
	public List<BookDto> getSubscribedBooks(String userName) {
		Optional<List<ReaderBooks>> olist = readerRepo.findByUserName(userName);
		List<BookDto> bookList = new ArrayList<>();
		if(olist.isPresent()) {
			List<ReaderBooks> list = olist.get();
			bookList = list.stream().map(readerBook ->{
				BookDto book = getBook(readerBook.getBookId());
					return book;
			}).filter(book->book.getBookActive()).collect(Collectors.toList());
			}
		return bookList;
		}

	private BookDto getBook(long bookId) {
		BookDto book = restTemplate.getForObject(baseUrl+"/api/v1/digitalbooks/books/searchById?bookId="+bookId, BookDto.class);
		return book;
	}

}
