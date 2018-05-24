package rj.prac.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rj.prac.model.Address;
import rj.prac.model.Person;

@RestController
@RequestMapping("/rest")
public class RestProducingController {
	
	@RequestMapping(value = "/fetchjson/{id}")
	public Person getForObjectJsonDemo(@PathVariable String id) {
		Address address = new Address("Bhaim", "Tehari Garwhal","Uttranchal");
		return new Person(Integer.valueOf(id),"Rajan", address);
	}
	
	@RequestMapping(value = "/exchange/{id}")
	public ResponseEntity<Person> getDataUsingExchange(@PathVariable Integer id) {
		Address address = new Address("Dangal", "Tehari Garwhal", "Uttranchal");
		return new ResponseEntity<Person>(new Person(id,"sandy",address), HttpStatus.OK);
	}
}
