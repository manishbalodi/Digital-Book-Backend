package com.digitalbooks.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalbooks.userservice.dto.BookDto;
import com.digitalbooks.userservice.entity.AuthorBooks;

@Repository
public interface AuthorBooksRepository extends JpaRepository<AuthorBooks, Long>{
	
	public AuthorBooks findByAuthorUserNameAndBookId(String userName,Long bookId);

}
