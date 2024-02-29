package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.CartItem;
import com.ecommerce.service.CartItemService;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartItemService cartserv;
	@PostMapping("/addtocart")
	public ResponseEntity<CartItem> addtocarthandler(@RequestBody CartItem cartitem){
		CartItem car=cartserv.addtocart(cartitem);
		return new ResponseEntity<CartItem>(car, HttpStatus.CREATED);
	}
	@DeleteMapping("/removefromcart")
	public ResponseEntity<CartItem> removefromcarthandler(@RequestBody CartItem cartitem){
		CartItem car=cartserv.removeFromCart(cartitem);
		return new ResponseEntity<CartItem>(car, HttpStatus.ACCEPTED);
	}
	@PatchMapping("/updatequent/{quent}")
	public ResponseEntity<CartItem> updateQuentcarthandler(@RequestBody CartItem cartitem , @PathVariable("quent") int quent){
		CartItem car=cartserv.manageQuntity(cartitem, quent);
		return new ResponseEntity<CartItem>(car, HttpStatus.ACCEPTED);
	}
	@GetMapping("/getAllItems")
	public ResponseEntity<List<CartItem>> getAllItem(){
		List<CartItem> car=cartserv.getAllcartItems();
		return new ResponseEntity<List<CartItem>>(car, HttpStatus.CREATED);
	}
}
