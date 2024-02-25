package com.ecommerce.service.Imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Category;
import com.ecommerce.exception.CategoryExcpetion;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository catrepo;

	@Override
	public Category addcategory(Category cat) throws CategoryExcpetion {
		Optional<Category> cateop = catrepo.findByName(cat.getName());
		if (cateop.isPresent()) {
			throw new CategoryExcpetion("Category with name " + cat.getName() + " is already present");
		}
		cat.setProducts(new ArrayList<>());
		return catrepo.save(cat);
	}

	@Override
	public List<Category> findAll() throws CategoryExcpetion {
		List<Category> list = catrepo.findAll();
		if (list.isEmpty()) {
			throw new CategoryExcpetion("No category present in database");
		}
		return list;
	}

	@Override
	public Category findbyname(String name) throws CategoryExcpetion {
		Optional<Category> catop = catrepo.findByName(name);
		if (catop.isPresent()) {
			throw new CategoryExcpetion("category with name " + name + " not present");
		}
		return catop.get();
	}

	@Override
	public Category deletecat(String name) throws CategoryExcpetion {
		Optional<Category> catop = catrepo.findByName(name);
		if (catop.isPresent()) {
			throw new CategoryExcpetion("category with name " + name + " not present");
		}
		catrepo.delete(catop.get());
		return catop.get();
	}

}
