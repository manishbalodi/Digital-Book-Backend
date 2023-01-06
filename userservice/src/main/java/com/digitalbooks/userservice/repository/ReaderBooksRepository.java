package com.digitalbooks.userservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalbooks.userservice.dto.BookDto;
import com.digitalbooks.userservice.entity.ReaderBooks;

@Repository
public interface ReaderBooksRepository extends JpaRepository<ReaderBooks, Long>{

	Optional<ReaderBooks> findByBookIdAndUserName(Long bookId, String userName);

	Optional<List<ReaderBooks>> findByUserName(String userName);

	Optional<List<ReaderBooks>> findByBookId(Long bookId);

}
