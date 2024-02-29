package com.ecommerce.service;

import java.util.List;

import com.ecommerce.entity.CartItem;
import com.ecommerce.exception.CartException;

public interface CartItemService {
	public CartItem addtocart(CartItem cartItem) throws CartException;
	public CartItem removeFromCart(CartItem cartItem) throws CartException;
	public CartItem manageQuntity(CartItem cartItem, int qunt) throws CartException;
	public List<CartItem> getAllcartItems() throws CartException;
}
