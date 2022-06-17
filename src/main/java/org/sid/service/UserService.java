package org.sid.service;

import java.util.List;

import org.sid.entity.Dependency;
import org.sid.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {
	
	public User createUser(User user);
	public List<User> getAllUser();
	public User updateUser(User user);
	public boolean deleteUser(String email);
	public Page<User> getAllUserById(int page, int limit);

}
