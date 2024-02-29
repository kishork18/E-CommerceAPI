package com.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.JWTAuthResponse;
import com.ecommerce.dto.LoginDTO;
import com.ecommerce.entity.Cart;
import com.ecommerce.entity.User;
import com.ecommerce.service.AuthService;
import com.ecommerce.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
	@Autowired
	private UserService us;
	@Autowired
	private BCryptPasswordEncoder passencode;
	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<User> adduserhandler(@RequestBody User user) {
		String hashpass = passencode.encode(user.getPassword());
		user.setPassword(hashpass);
		user.setOrders(new ArrayList<>());
		User u = us.addUser(user);
		return new ResponseEntity<User>(u, HttpStatus.CREATED);
	}

	@PatchMapping("/updateuser")
	public ResponseEntity<User> updateuserhandler(@RequestBody User user) {
		User u = us.update(user);
		log.info("inside controller of update");
		return new ResponseEntity<User>(u, HttpStatus.ACCEPTED);
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<User>> getMethodName() {
		List<User> list = us.findAllUser();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	@GetMapping("/logged-in-customer")
	public ResponseEntity<User> getLoggedInCustomer() {
		User customer = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new ResponseEntity<User>(customer, HttpStatus.OK);
	}

	@PostMapping("/signin")
	public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO logindto) {

		String token = authService.login(logindto);
		JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
		jwtAuthResponse.setAccessToken(token);
		return new ResponseEntity<JWTAuthResponse>(jwtAuthResponse, HttpStatus.ACCEPTED);

	}

	@GetMapping("/findbyemail")
	public ResponseEntity<User> findbyemailhandller(@RequestParam("email") String email) {
		User user = us.findByEmail(email);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<User> deletehandller(@RequestParam("email") String email) {
		User user = us.deltUser(email);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
