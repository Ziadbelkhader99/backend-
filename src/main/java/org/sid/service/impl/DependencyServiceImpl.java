package org.sid.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.sid.entity.Dependency;
import org.sid.repository.DependencyRepository;
import org.sid.service.DependencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class DependencyServiceImpl implements DependencyService {

	@Autowired
	private DependencyRepository dependencyRepository;

	@Override
	public Dependency createDependency(Dependency dependency) {
		Dependency	 d = dependencyRepository.findDependencyByDate(dependency.getDate());
		if(d == null) { 
			//createUser
			dependencyRepository.save(dependency);
			return dependency;
		}else {
			System.out.println("this dependency already exist !");
		}
		return dependency;
	}

	@Override
	public List<Dependency> getAllDependency() {
		return dependencyRepository.findAll();
		
	}

	@Override
	public Dependency updateDependency(Dependency dependency) {
		Dependency d = dependencyRepository.findDependencyById(dependency.getId());
		if(d == null) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found ");
			return null;
		}else {
			//user contient just les parties  modifiee
			//u est le nouveau utilisateur modifiee
			if(dependency.getDate() != null) d.setDate(dependency.getDate());
			if(dependency.getAmount() != null) d.setAmount(dependency.getAmount());
			if(dependency.getCategorie() != null) d.setCategorie(dependency.getCategorie() );
			
			dependencyRepository.save(d);
		}
		return d;

	}

//	@Override
//	public List<Dependency> getDependencyByDate(Date date) {
//		
//		
//		
//		return dependencyRepository.findDependencyByDate();
//	}
	
	@Override
	public List<Dependency> getAllBetweenDates(Date startDate,Date endDate){
		
		return dependencyRepository.getAllBetweenDates(startDate, endDate);
	}

	@Override
	public Page<Dependency> getAllDependencyById(int page, int limit) {
		if(page > 0) page -= 1;
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<Dependency> dependencyPage = dependencyRepository.findByOrderByIdDesc( pageableRequest);
		//findAll
		List<Dependency> dependencyList = new ArrayList<>();
		for(Dependency d: dependencyPage) {
			dependencyList.add(d);
		}
		Page<Dependency> pagesDependency = new PageImpl<Dependency>(dependencyList, pageableRequest, dependencyList.size());
		
		return pagesDependency;
		
	}
	
	@Override
	public Page<Dependency> getAllDependencyByDate(int page, int limit) {
	if(page > 0) page -= 1;
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<Dependency> dependencyPage = dependencyRepository.findByOrderByDateDesc( pageableRequest);
		//findAll
		List<Dependency> dependencyList = new ArrayList<>();
		for(Dependency d: dependencyPage) {
			dependencyList.add(d);
		}
		Page<Dependency> pagesDependency = new PageImpl<Dependency>(dependencyList, pageableRequest, dependencyList.size());
		
		return pagesDependency;
		
	}
	
	@Override
	public boolean deleteDependency(Long id) {
		dependencyRepository.deleteById(id);
		Dependency d = dependencyRepository.findDependencyById(id);
		if(d == null) return true;
		return	false; 
	}

	@Override
	public List<Dependency> getAllDependencyOfYear(int year) {
		
		return dependencyRepository.getAllDependencyOfYear(year);
	}

}
