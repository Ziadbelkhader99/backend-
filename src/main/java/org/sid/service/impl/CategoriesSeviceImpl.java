package org.sid.service.impl;

import java.util.List;

import org.sid.entity.Categories;

import org.sid.repository.CategoriesRepository;
import org.sid.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriesSeviceImpl implements CategoriesService {
	@Autowired
	public CategoriesRepository categoriesRepository;

	@Override
	public Categories createCategories(Categories categories) {
		Categories c = categoriesRepository.findCategoriesByIdCategory(categories.getIdCategory());
		if(c == null) { 
			//createUser
			categoriesRepository.save(categories);
			return categories;
		}else {
			System.out.println("this categorie already exist !");
		}
		return categories;
	}
		
	

	@Override
	public Categories updateCategories(Categories categories) {
		Categories c = categoriesRepository.findCategoriesByIdCategory(categories.getIdCategory());
		if(c == null) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found ");
			return null;
		}else {
			//user contient just les parties  modifiee
			//u est le nouveau utilisateur modifiee
			if(categories.getName() != null) c.setName(categories.getName());
		
			categoriesRepository.save(c);
		}
		return c;
		
	}

	@Override
	public boolean deleteCategories(Long idCategory) {
		// TODO Auto-generated method stub

	
			categoriesRepository.deleteById(idCategory);
			Categories c = categoriesRepository.findCategoriesByIdCategory(idCategory);
			if(c == null) return true;
			return	false;
			 
		
	}



	@Override
	public  List<Categories> getCategories() {
		// TODO Auto-generated method stub
		return categoriesRepository.findAll();
	}

}
