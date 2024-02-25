package com.ecommerce.service;

import java.util.List;

import com.ecommerce.entity.Product;
import com.ecommerce.exception.ProductException;

public interface ProductService {
  public Product addproduct(Product product) throws ProductException;
  public Product updateproduct(Product product) throws ProductException;
  public List<Product> getallproduct() throws ProductException;
  public Product findByTitle(String title) throws ProductException;
  public Product delete(String title)throws ProductException;
}
