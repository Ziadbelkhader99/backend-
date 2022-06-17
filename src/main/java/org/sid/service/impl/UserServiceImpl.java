package org.sid.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.sid.entity.Dependency;
import org.sid.entity.User;
import org.sid.repository.UserRepository;
import org.sid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		
		User u = userRepository.findUserByEmail(user.getEmail());
		if(u == null) { 
			//createUser
			userRepository.save(user);
			return user;
		}else {
			System.out.println("this user already exist !");
		}
		return user;
	}

	@Override
	public List<User> getAllUser() {

		return userRepository.findAll();
	}

	@Override
	public User updateUser(User user) {
		User u = userRepository.findUserByEmail(user.getEmail());
		if(u == null) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found ");
			return null;
		}else {
			//user contient just les parties  modifiee
			//u est le nouveau utilisateur modifiee
			if(user.getFirstName() != null) u.setFirstName(user.getFirstName());
			if(user.getLastName() != null) u.setLastName(user.getLastName());
			if(user.getPhone() != null) u.setPhone(user.getPhone() );
			if(user.getPassword() != null) u.setPassword(user.getPassword());
			userRepository.save(u);
		}
		return u;
	}

	@Override
	public boolean deleteUser(String email) {
		User u = userRepository.findUserByEmail(email);
		boolean statut=false;
		if(u == null) {
			statut =  false;
		}else {
			userRepository.delete(u);
			statut = true;
		}

		return statut;
	}

	@Override
	public Page<User> getAllUserById(int page, int limit) {
		if(page > 0) page -= 1;
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<User> userPage = userRepository.findByOrderByIdDesc( pageableRequest);
		//findAll
		List<User> userList = new ArrayList<>();
		for(User d: userPage) {
			userList.add(d);
		}
		Page<User> pagesUser = new PageImpl<User>(userList, pageableRequest, userList.size());
		
		return pagesUser;
		
		
	}

}
