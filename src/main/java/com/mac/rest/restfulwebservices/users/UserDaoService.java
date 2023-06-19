package com.mac.rest.restfulwebservices.users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users=new ArrayList<>();
	private static int count=1;
	static
	{
		users.add(new User(count++,"Mac",LocalDate.now().minusYears(10)));
		users.add(new User(count++,"Cam",LocalDate.now().minusYears(20)));
	}
	
	public List<User> findAll()
	{
		return users;
	}
	public User findById(int id)
	{
		Predicate<? super User> predicate = user -> user.getId()==id;
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	public User save(User user)
	{
		user.setId(count);
		users.add(user);
		return user;
	}
	
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId()==id;
		users.removeIf(predicate);
	}

}
