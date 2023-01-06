package com.digitalbooks.bookservice.dto;

import java.time.LocalDate;
import java.util.Arrays;

public class BookDto {
	
	private Long bookId;
	private String bookLogo;
	private String bookTitle;
	private String bookCategory;
	private Double bookPrice;
	private String bookAuthorUserName;
	private String bookAuthor;
	private String bookPublisher;
	private LocalDate bookPublishedDate;
	private Boolean bookActive;
	private String content;
	
	public BookDto() {
		super();
	}

	public BookDto(Long bookId, String bookLogo, String bookTitle, String bookCategory, Double bookPrice,
			String bookAuthorUserName, String bookAuthor, String bookPublisher, LocalDate bookPublishedDate,
			Boolean bookActive, String content) {
		super();
		this.bookId = bookId;
		this.bookLogo = bookLogo;
		this.bookTitle = bookTitle;
		this.bookCategory = bookCategory;
		this.bookPrice = bookPrice;
		this.bookAuthorUserName = bookAuthorUserName;
		this.bookAuthor = bookAuthor;
		this.bookPublisher = bookPublisher;
		this.bookPublishedDate = bookPublishedDate;
		this.bookActive = bookActive;
		this.content = content;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getBookLogo() {
		return bookLogo;
	}

	public void setBookLogo(String bookLogo) {
		this.bookLogo = bookLogo;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	public Double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(Double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getBookAuthorUserName() {
		return bookAuthorUserName;
	}

	public void setBookAuthorUserName(String bookAuthorUserName) {
		this.bookAuthorUserName = bookAuthorUserName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public LocalDate getBookPublishedDate() {
		return bookPublishedDate;
	}

	public void setBookPublishedDate(LocalDate bookPublishedDate) {
		this.bookPublishedDate = bookPublishedDate;
	}

	public Boolean getBookActive() {
		return bookActive;
	}

	public void setBookActive(Boolean bookActive) {
		this.bookActive = bookActive;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
