package rj.prac.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class TestRestControllr {
	
	@RequestMapping(method = RequestMethod.GET)
	public String testRest() {
		return "successful rest call";
	}
}
