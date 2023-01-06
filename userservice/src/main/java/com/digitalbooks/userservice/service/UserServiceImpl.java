package com.digitalbooks.userservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.digitalbooks.userservice.dto.BookDto;
import com.digitalbooks.userservice.entity.User;
import com.digitalbooks.userservice.payload.RegisterPayload;
import com.digitalbooks.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userInfoRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RestTemplate restTemplate;
	@Value("${baseUrl}")
	String baseUrl;
    
	@Override
	public boolean registerUser(User user) {
		
		Optional<User> opt = userInfoRepository.findByUserName(user.getUserName());
    	if(opt.isPresent()) {
    		return false;
    	}
    	else {
    		String encodedPass = passwordEncoder.encode(user.getUserPassword());
    		user.setUserPassword(encodedPass);
    		userInfoRepository.save(user);
    		return true;
    	}
		
	}

	@Override
	public List<BookDto> getAllActiveBooks() {
		BookDto[] response = restTemplate.getForObject(baseUrl+"/api/v1/digitalbooks/books/allBooks", BookDto[].class);
//		return bookClient.getAuthorAllBooks(authorUserName);
		List<BookDto> list = Arrays.asList(response);
		list.stream().forEach(book->book.setContent("Subscribe to Read"));
		return list;
	}

}
