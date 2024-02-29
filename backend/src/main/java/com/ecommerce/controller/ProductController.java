package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {
	@Autowired
	private ProductService prodserv;

	@PostMapping("/addproduct")
	public ResponseEntity<Product> addProducthandller(@RequestBody Product product) {
		log.info("inside controller class" + product.getCategory());
		Product prod = prodserv.addproduct(product);
		return new ResponseEntity<Product>(prod, HttpStatus.CREATED);
	}

	@PatchMapping("/updateproduct")
	public ResponseEntity<Product> updateproducthandler(@RequestBody Product product) {
		Product prod = prodserv.updateproduct(product);
		return new ResponseEntity<Product>(prod, HttpStatus.OK);
	}

	@GetMapping("/getall")
	public ResponseEntity<List<Product>> getallproducthandler() {
		List<Product> list = prodserv.getallproduct();
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}

	@GetMapping("/getbytitle")
	public ResponseEntity<Product> getByTitle(@RequestParam String name) {
		Product prod = prodserv.findByTitle(name);
		return new ResponseEntity<Product>(prod, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Product> deletehandler(@RequestParam String name) {
		Product prod = prodserv.delete(name);
		return new ResponseEntity<Product>(prod, HttpStatus.OK);

	}

}
