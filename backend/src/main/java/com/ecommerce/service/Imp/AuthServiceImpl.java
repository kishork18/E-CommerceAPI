package com.ecommerce.service.Imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.config.JwtTokenProvider;
import com.ecommerce.dto.LoginDTO;
import com.ecommerce.entity.User;
import com.ecommerce.exception.UserException;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserRepository userrepo;

	@Autowired
	private AuthenticationManager authmanager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public String login(LoginDTO logindto) {
        Optional<User> user= userrepo.findByEmail(logindto.getEmail());
        if(!user.isPresent()) {
        	throw new UserException("Wrong Email please provide correct email");
        }
        if(user.isPresent()) {
        	User u= user.get();
        	
        	if(!encoder.matches(logindto.getPassword(), u.getPassword())) {
        		throw new UserException("Wrong password please provide currect password");
        	}
        }
   
        
		Authentication authentication = authmanager
				.authenticate(new UsernamePasswordAuthenticationToken(logindto.getEmail(), logindto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtTokenProvider.generateToken(authentication);
		return token;
	}

}
