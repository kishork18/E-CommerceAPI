package com.ecommerce.service;

import java.util.List;

import com.ecommerce.entity.Category;
import com.ecommerce.exception.CategoryExcpetion;

public interface CategoryService {
 public Category addcategory(Category cat) throws CategoryExcpetion;
 public List<Category> findAll() throws CategoryExcpetion;
 public Category findbyname(String name)throws CategoryExcpetion;
 public Category deletecat(String name) throws CategoryExcpetion;
}
