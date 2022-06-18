package org.sid.service;

import java.util.Date;
import java.util.List;

import org.sid.entity.Dependency;
import org.springframework.data.domain.Page;

public interface DependencyService {

	public Dependency createDependency(Dependency dependency);

	public List<Dependency> getAllDependency();

	public Dependency updateDependency(Dependency dependency);

	public boolean deleteDependency(Long id);

//	public List<Dependency> getDependencyByDate(Date date);
	// public boolean deleteDependency(String email);
//	public List<Dependency> getAllBetweenDates();
	public List<Dependency> getAllBetweenDates(Date startDate, Date endDate);

	public Page<Dependency> getAllDependencyById(int page, int limit);

	public Page<Dependency> getAllDependencyByDate(int page, int limit);

	public List<Dependency> getAllDependencyOfYear(int year);

}
