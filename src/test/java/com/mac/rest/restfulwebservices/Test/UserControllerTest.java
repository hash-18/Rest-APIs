package com.mac.rest.restfulwebservices.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.mac.rest.restfulwebservices.jpa.PostRepository;
import com.mac.rest.restfulwebservices.jpa.UserRepository;
import com.mac.rest.restfulwebservices.users.User;
import com.mac.rest.restfulwebservices.users.UserControllerJPA;

public class UserControllerTest {

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private PostRepository postRepository;
	
	@InjectMocks
	private UserControllerJPA userController;
	
	 @BeforeEach
	    public void setup() {
	        MockitoAnnotations.openMocks(this);
	    }

	
	
	@Test
	public void testGetUserForGivenId()
	{
		when(userRepository.findById(1)).thenReturn(Optional.of(new User(1,"abcdef",LocalDate.of(2019, 4, 14))));
		User user=userController.getUser(1);
		assertEquals(new User(1,"abcdef",LocalDate.of(2019, 4, 14)), user);
		
	}

}
