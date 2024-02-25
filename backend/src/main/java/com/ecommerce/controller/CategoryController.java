package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Category;
import com.ecommerce.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService catserv;

	@PostMapping("/addcategory")
	public ResponseEntity<Category> addcategoryhandler(@RequestBody Category category) {
		Category cat = catserv.addcategory(category);
		return new ResponseEntity<Category>(cat, HttpStatus.CREATED);
	}

	@GetMapping("/getall")
	public ResponseEntity<List<Category>> getallhandler() {
		List<Category> list = catserv.findAll();
		return new ResponseEntity<List<Category>>(list, HttpStatus.ACCEPTED);
	}

	@GetMapping("/getbyname")
	public ResponseEntity<Category> getbynamehandler(@RequestParam String name) {
		Category cat = catserv.findbyname(name);
		return new ResponseEntity<Category>(cat, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Category> deletehandller(@RequestParam String name) {
		Category cat = catserv.deletecat(name);
		return new ResponseEntity<Category>(cat, HttpStatus.OK);
	}
}
