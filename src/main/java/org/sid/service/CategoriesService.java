package org.sid.service;

import java.util.List;

import org.sid.entity.Categories;


public interface CategoriesService {
	public Categories createCategories(Categories categories);
	public Categories updateCategories(Categories categories);
	public boolean deleteCategories(Long idCategory);
	public List<Categories> getCategories();
}
