package com.ecommerce.service.Imp;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.RolesAndAuthority;
import com.ecommerce.entity.User;
import com.ecommerce.exception.UserException;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.RolesAndAuthorityService;
import com.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository ur;
	@Autowired
	private RolesAndAuthorityService ras;

	@Override
	public User addUser(User user) throws UserException {
		Optional<User> userop = ur.findByEmail(user.getEmail());
		if (userop.isPresent()) {
			throw new UserException("User with "+user.getEmail() + " is already present please try with differnt email");
		}
		user.setRegistrationDate(LocalDate.now());
		Set<RolesAndAuthority> managedSet = new HashSet<>();

		managedSet.add(ras.findByName("ROLE_USER"));

		user.setAuthSet(managedSet);
		return ur.save(user);
	}

	@Override
	public User update(User user) throws UserException {
		Optional<User> useropt = ur.findByEmail(user.getEmail());
		if (!useropt.isPresent()) {
			throw new UserException("Provided email is not register!");
		}
		User us=useropt.get();
		Set<RolesAndAuthority> set = user.getAuthSet();
		Set<RolesAndAuthority> managedSet = us.getAuthSet();
		for (RolesAndAuthority roles : set) {
			managedSet.add(ras.findByName(roles.getName()));
		}
	
		us.setAuthSet(managedSet);
		return ur.save(us);
	}

	@Override
	public List<User> findAllUser() throws UserException {
		List<User> list= ur.findAll();
		if(list.isEmpty()) {
			throw new UserException("No user any user found register");
		}
		return list;
	}

	@Override
	public User findByEmail(String email) throws UserException {
		Optional<User> user= ur.findByEmail(email);
		if(!user.isPresent()) {
			throw new UserException("user not found with "+email );
		}
		return user.get();
	}

	@Override
	public User deltUser(String email) throws UserException {
		Optional<User> user= ur.findByEmail(email);
		if(!user.isPresent()) {
			throw new UserException("user not found with "+email );
		}
		ur.delete(user.get());
		return user.get();
	}

}
