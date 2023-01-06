package com.digitalbooks.bookservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalbooks.bookservice.dto.BookDto;
import com.digitalbooks.bookservice.entity.Book;
import com.digitalbooks.bookservice.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	BookRepository bookRepo;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<BookDto> searchBooks(String title, String author, String publisher) {
		
		List<Book> bookList= bookRepo.findAll();
		List<Book> filteredList = bookList.stream().filter(book -> book.getBookActive().equals(true))
		.filter(book -> book.getBookTitle().equalsIgnoreCase(title)||book.getBookAuthor().equalsIgnoreCase(author)
				||book.getBookPublisher().equalsIgnoreCase(publisher))
		.collect(Collectors.toList());
		System.out.println(filteredList);
		List<BookDto> listBookDto = filteredList.stream().map(book->modelMapper.map(book, BookDto.class))
				.collect(Collectors.toList());
		return listBookDto;
	}

	@Override
	public Long createBook(BookDto bookDto) {
		Book book = new Book();
		modelMapper.map(bookDto, book);
		Long bookId = bookRepo.save(book).getBookId();
		return bookId;
	}

	@Override
	public BookDto getBookById(Long bookId) {
		Optional<Book> book = bookRepo.findById(bookId);
		BookDto bookDto = modelMapper.map(book.get(), BookDto.class);
		System.out.println(bookDto);
		return bookDto;
	}

	@Override
	public List<BookDto> findByBookAuthorUserName(String authorUserName) {
		List<Book> bookList = bookRepo.findByBookAuthorUserName(authorUserName);
		List<BookDto> bookDtoList = bookList.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
		return bookDtoList;
	}

	@Override
	public List<BookDto> allBooks() {
		List<Book> bookList = bookRepo.findAll();
		List<BookDto> bookDtoList = bookList.stream().filter(book->book.getBookActive()).map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
		return bookDtoList;
	}

	@Override
	public boolean toggleBookLock(String userName, Long bookId) {
		Optional<Book> oBook = bookRepo.findById(bookId);
		if(oBook.isPresent()) {
			Book book = oBook.get();
		if(book.getBookAuthorUserName().equals(userName)) {
			boolean newValue = !book.getBookActive();
			book.setBookActive(newValue);
			bookRepo.save(book);
		}
		return true;
		}else
		{
		return false;
		}
	}

	@Override
	public boolean deleteBook(Long bookId) {
		Optional<Book> oBook = bookRepo.findById(bookId);
		if(oBook.isPresent()) {
			bookRepo.deleteById(bookId);
			return true;
		}else
		return false;
	}

	@Override
	public boolean isBookPresent(Long bookId) {
		Optional<Book> oBook = bookRepo.findById(bookId);
		if(oBook.isPresent()) {
			return true;
		}else
		return false;
	}

}
