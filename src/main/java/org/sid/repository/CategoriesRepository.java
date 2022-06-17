package org.sid.repository;

import java.util.List;

import org.sid.entity.Categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategoriesRepository extends JpaRepository<Categories, Long>{
	public Categories findCategoriesByIdCategory(Long idCategory);
	


}
