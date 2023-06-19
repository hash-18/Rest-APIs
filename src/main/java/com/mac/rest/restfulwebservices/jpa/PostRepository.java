package com.mac.rest.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mac.rest.restfulwebservices.users.Post;
import com.mac.rest.restfulwebservices.users.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
