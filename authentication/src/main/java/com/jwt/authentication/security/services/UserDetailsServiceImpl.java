package com.jwt.authentication.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.authentication.models.User;
import com.jwt.authentication.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepo;
	
	@Override
	@Transactional
	public UserDetails loadUserByUserName(String userName) throws UsernameNotFoundException {
		
		User user = userRepo.findByUsername(userName)
		  .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userName));
		
		return UserDetailsImpl.build(user);
	}

}
