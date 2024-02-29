package com.ecommerce.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.exception.CartException;
import com.ecommerce.repository.CartItemRepo;
import com.ecommerce.repository.CartRepo;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.CartItemService;

@Service
public class CartServiceImpl implements CartItemService {
	@Autowired
	private CartItemRepo cir;
	@Autowired
	private ProductRepository pr;
	

	@Override
	public CartItem addtocart(CartItem cartItem) throws CartException {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Cart cart = user.getCart();
		Product prod = pr.findByTitle(cartItem.getProduct().getTitle()).get();
		List<CartItem> cartitems = cart.getCartItems();
		List<CartItem> cartitem = cartitems.stream().filter(c -> c.getProduct().getTitle().equals(prod.getTitle())).toList();
		if (!cartitem.isEmpty()) {
			throw new CartException("Product already Present in cart");
		}
		cartItem.setProduct(prod);
		cartItem.setCart(cart);
		cartItem.setQuantity(1);
		cartitems.add(cartItem);
		cart.setCartItems(cartitems);
		return cir.save(cartItem);
	}

	@Override
	public CartItem removeFromCart(CartItem cartItem) throws CartException {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Cart cart = user.getCart();
		List<CartItem> cartitems= cart.getCartItems().stream().filter(c->c.getProduct().getTitle()
				.equals(cartItem.getProduct().getTitle())).toList();
		  if(cartitems.isEmpty()) {
			  throw new CartException("Product not avaliable in cart");
		  }
		         cir.delete(cartitems.get(0));
		
		return cartitems.get(0);
	}

	@Override
	public CartItem manageQuntity(CartItem cartItem, int qunt) throws CartException {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Cart cart = user.getCart();
		List<CartItem> cartitems= cart.getCartItems().stream().filter(c->c.getProduct().getTitle()
				.equals(cartItem.getProduct().getTitle())).toList();
		  if(cartitems.isEmpty()) {
			  throw new CartException("Product not avaliable in cart");
		  }
		  CartItem prod=cartitems.get(0);
		  prod.setQuantity(qunt);
		  
		return cir.save(prod);
	}

	@Override
	public List<CartItem> getAllcartItems() throws CartException {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Cart cart = user.getCart();
		List<CartItem> list= cart.getCartItems();
		if(list.isEmpty()) {
			throw new CartException("No product present in cart");
		}
		return list;
	}

}
