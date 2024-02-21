package com.ecommerce.service;

import java.util.List;

import com.ecommerce.entity.User;
import com.ecommerce.exception.UserException;

public interface UserService {
	public User addUser(User user) throws UserException;
	public User update(User user) throws UserException;
	public List<User> findAllUser()throws UserException;
	public User findByEmail(String email) throws UserException;
	public User deltUser(String email) throws UserException;

}
