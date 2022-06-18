package org.sid.repository;

import java.util.Date;
import java.util.List;
import org.sid.entity.Dependency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DependencyRepository extends JpaRepository<Dependency, Long> {
	public Dependency findDependencyByDate(Date date);

	public Dependency findDependencyById(Long id);

//	public List<Dependency> findDependencyByDate();
	@Query(value = "from Dependency t where date BETWEEN :startDate AND :endDate")
	public List<Dependency> getAllBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	public Page<Dependency> findByOrderByCategorieAsc(Pageable pageable);

	@Query(value = "FROM Dependency f ORDER BY f.id DESC")
	public Page<Dependency> findByOrderByIdDesc(Pageable pageable);

	@Query(value = "FROM Dependency f ORDER BY f.date DESC")
	public Page<Dependency> findByOrderByDateDesc(Pageable pageable);

	@Query(value = "select d from Dependency d where extract(year from d.date) = :year")
	public List<Dependency> getAllDependencyOfYear(@Param("year") int year);

//	public boolean deleteDependencyById(Long id);
}
