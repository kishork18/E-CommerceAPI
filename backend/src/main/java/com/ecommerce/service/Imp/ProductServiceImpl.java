package com.ecommerce.service.Imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Available;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.exception.ProductException;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
    private ProductRepository productrepo;
	@Autowired
	private CategoryRepository catRepo;
	@Override
	public Product addproduct(Product product) throws ProductException {
	    Optional<Product> productopt = productrepo.findByTitle(product.getTitle());
	    if (productopt.isPresent()) {
	        throw new ProductException("Product already present in database");
	    }
	    product.setAvailable(Available.AVAILABLE);
	    
	    
	    if (product.getCategory() == null) {
	        throw new ProductException("Product category cannot be null");
	    }
	    
	    Optional<Category> categoryOpt = catRepo.findByName(product.getCategory().getName());
	    if (!categoryOpt.isPresent()) {
	        throw new ProductException("Category not found");
	    }
	    Category cat = categoryOpt.get();
	    product.setCategory(cat);
	    
	    Product prod = productrepo.save(product);
	    
	
	    cat.getProducts().add(prod);
	    catRepo.save(cat);
	    
	    return prod;
	}

	@Override
	public Product updateproduct(Product product) throws ProductException {
		Optional<Product> productopt= productrepo.findByTitle(product.getTitle());
        if(!productopt.isPresent()) {
        	throw new ProductException("Product with "+product.getTitle()+" is not present");
        }
        Product prod= productopt.get();
        prod.setAvailable(product.getAvailable());
        prod.setDescription(product.getDescription());
        prod.setPrice(product.getPrice());
		return productrepo.save(prod);
	}

	@Override
	public List<Product> getallproduct() throws ProductException {
		List<Product> list= productrepo.findAll();
		if(list.isEmpty()) {
			throw new ProductException("No product available in database please add some product first");
		}
		return list;
	}

	@Override
	public Product findByTitle(String title) throws ProductException {
		Optional<Product> prodopt= productrepo.findByTitle(title);
		if(!prodopt.isPresent()) {
			throw new ProductException("product with "+ title+" is not present");
		}
		return prodopt.get();
	}

	@Override
	public Product delete(String title) throws ProductException {
		Optional<Product> prodopt= productrepo.findByTitle(title);
		if(!prodopt.isPresent()) {
			throw new ProductException("product with "+ title+" is not present");
		}
		productrepo.delete(prodopt.get());
		return prodopt.get();
	}

}
