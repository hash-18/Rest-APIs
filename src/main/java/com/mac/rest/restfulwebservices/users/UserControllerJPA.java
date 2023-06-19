package com.mac.rest.restfulwebservices.users;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mac.rest.restfulwebservices.jpa.PostRepository;
import com.mac.rest.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserControllerJPA {
	
	@Autowired
	private UserRepository UserRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/users")
	public List<User> getUser()
	{
		return UserRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable int id)
	{
		User user= UserRepository.findById(id).get();
		if(user==null)
			throw new UserNotFoundException("id: "+ id);
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user)
	{
		User savedUser=UserRepository.save(user);
	//	URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId());	
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();   
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		UserRepository.deleteById(id);
	}
	
	@GetMapping("/users/{id}/posts")
	public List<Post> getPostsForUser(@PathVariable int id)
	{
		User user=UserRepository.findById(id).get();
		if(user==null)
		{
			throw new UserNotFoundException("User doesnot exists");
		}
		return user.getPosts();
		
	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Post> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post)
	{
		User user=UserRepository.findById(id).get();
		if(user==null)
		{
			throw new UserNotFoundException("User doesnot exists");
		}
		post.setUser(user);
		Post savedPost=postRepository.save(post);
		//	URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId());	
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(savedPost.getId())
					.toUri();   
			return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/users/{uid}/posts/{pid}")
	public Post getPostById(@PathVariable int uid, @PathVariable int pid )
	{
		User user=UserRepository.findById(uid).get();
		if(user==null)
		{
			throw new UserNotFoundException("User doesnot exists");
		}
		Post post=postRepository.findById(pid).get();
		if(post==null)
		{
			throw new PostNotFoundException("User doesnot exists");
		}
		
		return post;
		
		
	}

}
