package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {
	@Autowired
	private ProductService prodserv;

	@PostMapping("/addproduct")
	public ResponseEntity<Product> addProducthandller(@RequestBody Product product) {
		log.info("inside controller class"+ product.getCategory());
		Product prod = prodserv.addproduct(product);
		return new ResponseEntity<Product>(prod, HttpStatus.CREATED);
	}

}
