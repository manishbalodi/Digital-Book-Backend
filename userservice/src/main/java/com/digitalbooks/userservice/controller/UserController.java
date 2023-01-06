package com.digitalbooks.userservice.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.userservice.dto.BookDto;
import com.digitalbooks.userservice.entity.User;
import com.digitalbooks.userservice.payload.RegisterPayload;
import com.digitalbooks.userservice.security.model.JwtRequest;
import com.digitalbooks.userservice.security.model.JwtResponse;
import com.digitalbooks.userservice.security.service.JwtService;
import com.digitalbooks.userservice.security.util.JwtUtil;
import com.digitalbooks.userservice.service.UserService;


@RestController
@RequestMapping("/api/v1/digitalbooks/users")
public class UserController {
	
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
    	boolean isSaved = userService.registerUser(user);
    	if(!isSaved) {
    		return new ResponseEntity("User already Registered",HttpStatus.ALREADY_REPORTED);
    	}
    	else {
    		return new ResponseEntity("User created :"+user.getUserName(),HttpStatus.CREATED);
    	}
    }
	
	@PostMapping("/login")
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
	
	@GetMapping("/allActiveBooks")
	public ResponseEntity<?> allActivebooks(){
		List<BookDto> list = userService.getAllActiveBooks();
		return new ResponseEntity(list,HttpStatus.OK);

	}

}
