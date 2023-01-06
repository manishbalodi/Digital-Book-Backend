//package com.digitalbooks.userservice.security.service;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.digitalbooks.userservice.repository.UserRepository;
//import com.digitalbooks.userservice.entity.*;
//
//@Service
//public class JwtUserDetailsService implements UserDetailsService {
//	
//	@Autowired
//	UserRepository userInfoRepository;
//	
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    	
//    	Optional<User> userOptional = userInfoRepository.findByUserName(username);
//    	if(userOptional.isPresent()) {
//    		User user = userOptional.get();
//    		Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
//    		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()));
//    		return new User(user.getUserName(),user.getUserPassword(),authorities);
//    	}else {
//			throw new UsernameNotFoundException("User Not Found");
//		}
//    }
//}