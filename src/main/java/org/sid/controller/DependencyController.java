package org.sid.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.sid.entity.Dependency;
import org.sid.entity.User;
import org.sid.service.DependencyService;
import org.sid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class DependencyController {

	@Autowired
	private DependencyService dependencyService;

	@PostMapping("/dependency")
	public Dependency createDependency(@RequestBody Dependency dependency) {
		System.out.println("date. " + new Date());
		return dependencyService.createDependency(dependency);
	}

	@GetMapping("/dependency")
	public List<Dependency> getAllDependencies() {
		return dependencyService.getAllDependency();
	}

	@PutMapping("/dependency")
	public ResponseEntity<Dependency> updateDependency(@RequestBody Dependency dependency) {

		if (dependencyService.updateDependency(dependency) == null)
			return new ResponseEntity<Dependency>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Dependency>(dependencyService.updateDependency(dependency), HttpStatus.OK);
	}

	@GetMapping("/dependencybetween")
	public List<Dependency> getDependencyByDate(
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("startDate") Date startDate,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("endDate") Date endDate) {
		return dependencyService.getAllBetweenDates(startDate, endDate);

	}

	@GetMapping("/dependencyPageId")
	public Page<Dependency> getAllDependencyById(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit) {

		List<Dependency> dependencyList = new ArrayList<>();

		Pageable pageableRequest = PageRequest.of(page, limit);
		

		Page<Dependency> dependencyPage = dependencyService.getAllDependencyById(page, limit);

		for (Dependency d : dependencyPage) {
			dependencyList.add(d);
		}

		Page<Dependency> pages = new PageImpl<Dependency>(dependencyList, pageableRequest, dependencyList.size());

		return pages;

	}
	@GetMapping("/dependencyPageDate")
	public Page<Dependency> getAllDependencyByDate(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit) {

		List<Dependency> dependencyList = new ArrayList<>();

		Pageable pageableRequest = PageRequest.of(page, limit);
		

		Page<Dependency> dependencyPage = dependencyService.getAllDependencyByDate(page, limit);

		for (Dependency d : dependencyPage) {
			dependencyList.add(d);
		}

		Page<Dependency> pages = new PageImpl<Dependency>(dependencyList, pageableRequest, dependencyList.size());

		return pages;
	}
	
	@DeleteMapping("/dependency/{id}")
	public ResponseEntity<Boolean> deleteDependencyById(@PathVariable Long id){
		boolean deleted = dependencyService.deleteDependency(id);
		if(deleted) return new ResponseEntity<Boolean>(deleted, HttpStatus.OK);
		return new ResponseEntity<Boolean>(dependencyService.deleteDependency(id), HttpStatus.BAD_REQUEST);
		
	}
}
