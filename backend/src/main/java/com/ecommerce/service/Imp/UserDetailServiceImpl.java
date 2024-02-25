package com.ecommerce.service.Imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.User;
import com.ecommerce.exception.UserException;
import com.ecommerce.repository.UserRepository;
@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
    private UserRepository ur;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user= ur.findByEmail(email);
		if(!user.isPresent()) {
			throw new UserException("user not found with "+email);
		}
		return user.get();
	}

}
