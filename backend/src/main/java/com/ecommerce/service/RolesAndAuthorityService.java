package com.ecommerce.service;

import com.ecommerce.entity.RolesAndAuthority;
import com.ecommerce.exception.UserException;

public interface RolesAndAuthorityService {
  public RolesAndAuthority findByName(String name) throws UserException;
}
