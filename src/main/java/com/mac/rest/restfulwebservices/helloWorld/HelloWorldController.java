package com.mac.rest.restfulwebservices.helloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping(path="hello-world")
	public String helloWorld()
	{
		return "Hello World";
	}

	//Returnning json back
	
	@GetMapping(path="hello-world-bean")
	public HelloWorldBean helloWorldBean()
	{
		return new HelloWorldBean("Hello WOrld");
	}
	
	
	//http://localhost:8080/hello-world-bean/Mayank - here Mayank is the path variable that is captured in {name}
	@GetMapping(path="hello-world-bean/{name}")
	public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name )
	{
		return new HelloWorldBean("Hello WOrld "+ name);
	}
	
}
