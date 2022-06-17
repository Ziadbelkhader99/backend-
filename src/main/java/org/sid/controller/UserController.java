package org.sid.controller;

import java.util.ArrayList;
import java.util.List;

import org.sid.entity.Dependency;
import org.sid.entity.User;
import org.sid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@GetMapping("/user")
	public List<User> getAllUsers(){
		return userService.getAllUser();
	}
	
	@PutMapping("/user")
	public ResponseEntity<User> updateUser(@RequestBody User user ){
		
		if(userService.updateUser(user) == null)
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
	}
	@DeleteMapping("/user/{email}")
	public ResponseEntity<User> deleteUser(@PathVariable String email ){
		
		if(userService.deleteUser(email) == true) {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

	}
	@GetMapping("/userPageId")
	public Page<User> getAllUserById(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit) {

		List<User> userList = new ArrayList<>();

		Pageable pageableRequest = PageRequest.of(page, limit);
		

		Page<User> userPage = userService.getAllUserById(page, limit);

		for (User d : userPage) {
			userList.add(d);
		}

		Page<User> pages = new PageImpl<User>(userList, pageableRequest, userList.size());

		return pages;

	}
}

