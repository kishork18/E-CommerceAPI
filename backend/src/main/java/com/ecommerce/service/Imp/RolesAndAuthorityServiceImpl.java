package com.ecommerce.service.Imp;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.RolesAndAuthority;
import com.ecommerce.exception.UserException;
import com.ecommerce.repository.RolesAndAuthorityRepository;
import com.ecommerce.service.RolesAndAuthorityService;
@Service
public class RolesAndAuthorityServiceImpl implements RolesAndAuthorityService{
	@Autowired
	private RolesAndAuthorityRepository rar;

	@Override
	public RolesAndAuthority findByName(String name) throws UserException {
		Optional<RolesAndAuthority> ra= rar.findByName(name);
		if(!ra.isPresent()) {
			throw new UserException("Role or Authority entered with name "+ name+ " is not present");
		}
		return ra.get();
	}

}
