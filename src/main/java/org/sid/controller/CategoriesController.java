package org.sid.controller;

import java.util.List;
import org.sid.entity.Categories;
import org.sid.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class CategoriesController {
	@Autowired
	private CategoriesService categoriesService;

	@PostMapping("/categories")
	public Categories createCategories(@RequestBody Categories categories) {

		return categoriesService.createCategories(categories);
	}

	@PutMapping("/categories")
	public ResponseEntity<Categories> updateCategories(@RequestBody Categories categories) {

		if (categoriesService.updateCategories(categories) == null)
			return new ResponseEntity<Categories>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Categories>(categoriesService.updateCategories(categories), HttpStatus.OK);
	}

	@DeleteMapping("/categories/{idCategory}")
	public ResponseEntity<Boolean> deleteCategoriesById(@PathVariable Long idCategory) {
		boolean deleted = categoriesService.deleteCategories(idCategory);
		if (deleted)
			return new ResponseEntity<Boolean>(deleted, HttpStatus.OK);
		return new ResponseEntity<Boolean>(categoriesService.deleteCategories(idCategory), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/categories")
	public List<Categories> getCategories() {
		return categoriesService.getCategories();
	}

}
