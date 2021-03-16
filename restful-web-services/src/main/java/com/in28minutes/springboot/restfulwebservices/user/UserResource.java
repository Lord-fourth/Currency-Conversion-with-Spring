package com.in28minutes.springboot.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	//method to retrieve all users
	//GET/users
	@Autowired
	private UserDaoService service;
	@GetMapping("/users")
	public List<User> retrieveAllUsers ()
	{
		return service.findAll();
	}
	//method to retrieve a user
	
		//GET/users/{userId}
	@GetMapping("users/{id}")
	public User retrieveUser(@PathVariable int id)
	{
		User user = service.findOne(id);
		if(user==null)
		{
			throw new UserNotFoundException("id-"+id);
		}
		
		return user;
	}
	@DeleteMapping("users/{id}")
	public User DeleteUser(@PathVariable int id)
	{
		User user = service.deleteById(id);
		if(user==null)
		{
			throw new UserNotFoundException("id-"+id);
		}
		return user;
	}
	@PostMapping("/users")
	 public ResponseEntity createUser(@Valid @RequestBody User user)
	 {
		 User savedUser=service.save(user);
		 //return a status saying user is created
		  
		 
		 //set uri of created resouce into the response
		 URI location =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri(); //appending id to the existing url:"/users" with id and giving id as savedUser.getId
		 return ResponseEntity.created(location).build();
	 }
	
	

}
