package org.sid.repository;

import org.sid.entity.Dependency;
import org.sid.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findUserByEmail(String email);
	@Query(value="FROM User f ORDER BY f.id DESC")
	public Page<User> findByOrderByIdDesc(Pageable pageable);

}
