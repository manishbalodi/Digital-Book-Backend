package com.digitalbooks.userservice.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.digitalbooks.userservice.dto.BookDto;

@FeignClient(value ="movies",url ="http://localhost:8001")
public interface BookClient {
	
	@GetMapping("/api/v1/digitalbooks/books/getAuthorAllBooks")
	public List<BookDto> getAuthorAllBooks(@RequestParam String authorUsername);

}
