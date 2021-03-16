package com.in28minutes.springboot.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//have to tell this is a controller
@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	//GET- should say it is get request
	//URI- /hello-world
	//method- should return "Hello World"
	@GetMapping(path="/hello-world")
	public String helloWorld()
	{
		return "Hello World";
	}
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean()
	{
		return new HelloWorldBean("Hello World");
	}
	//hello-world/path-variable/shiva:shiva will be mapped to name variable
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name)
	{
		return new HelloWorldBean("Hello World " + name);
	}
	@GetMapping(path="/hello-world-internationalized")
	public String helloWorldInternationalized()
	{
		return messageSource.getMessage("good.morning.message",null,LocaleContextHolder.getLocale());
	}

}
