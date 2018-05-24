package rj.prac.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import rj.prac.exceptions.CountryNotFoundException;
import rj.prac.exceptions.ErrorResponse;
import rj.prac.model.Country;
import rj.prac.model.Sla;
import rj.prac.services.CountryService;
import rj.prac.services.SlaService;
//@Controller
@RestController
@RequestMapping("/country")
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	@Autowired
	private SlaService slaService;
	
	@RequestMapping(value = "/allCountries", method = RequestMethod.GET)
	public  List<Country> getAllCountries() {
		List<Country> countryList = countryService.getAllCountries();
		return countryList;
	}
	
	//handling error without exception handler
	
	/*@RequestMapping(value = "/getCountry/{countryId}", method = RequestMethod.GET)
	public  ResponseEntity<?> getCountry(@PathVariable String countryId) {
		Country country = countryService.getCountry(Integer.valueOf(countryId));
		if(country == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(404, "country with "+countryId+ " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Country>(country, HttpStatus.OK);
	}*/
	
	//handling error with exception handler
	/*as method always return country if exception not occured 
			no more use of ResponseEntity*/
	
	@RequestMapping(value = "/getCountry/{countryId}", method = RequestMethod.GET)
		public Country getCountry(@PathVariable String countryId) {
			Country country = countryService.getCountry(Integer.valueOf(countryId));
			if(country == null) {
				throw new CountryNotFoundException(Integer.parseInt(countryId));
			}
			return country;
	}
		
	
	//error handler method to handle an error with use of ResponseEntity
	
	/*@ExceptionHandler(CountryNotFoundException.class)
	public ResponseEntity<ErrorResponse> countryNotFound(CountryNotFoundException e) {
		int countryId = e.getCountryId();
		ErrorResponse errorResponse = new ErrorResponse(countryId, "country [ "+countryId+" ] not found.");
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}*/
	
	/*
	 * refactored code by removing ResponseEntity as this method always 
	 * return 404 and the response code can be returned by including
	 * @ResponseStatus at method level therefore no need of ResponseEntity 
	 * in this method
	 * */
	@ExceptionHandler(CountryNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse countryNotFound(CountryNotFoundException e) {
		int countryId = e.getCountryId();
		ErrorResponse errorResponse = new ErrorResponse(countryId, "country [ "+countryId+" ] not found.");
		return errorResponse;
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/json")
	public  Country updatedCountry(@RequestBody Country countryToUpdate) {
		Country country = countryService.updateCountry(countryToUpdate);
		return country;
	}
	
	/*@RequestMapping(value = "/addCountry", method = RequestMethod.POST, consumes = "application/json" )
	@ResponseStatus(HttpStatus.CREATED) //providing HTTP Status code of 201 when resource is created
	public  Country addCountry(@RequestBody Country countryToAdd) {
		Country country = countryService.addCountry(countryToAdd);
		return country; 
	}*/
	
	
/*	modified POST method to return location of currently
			created resource with HTTP response status*/
	
	@RequestMapping(value = "/addCountry", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Country> addCountry(@RequestBody Country countryToAdd, UriComponentsBuilder ucb) {
		Country country = countryService.addCountry(countryToAdd);
		
		/*HttpHeaders headers = new HttpHeaders();	//set the location header
		URI locationUri = URI.create("http://localhost:8081/RestCRUDWithoutDao/country/getCountry/"+country.getId());
		headers.setLocation(locationUri);*/
		
		// code refactored to return dynamic resource location
		
		HttpHeaders headers = new HttpHeaders();
		URI locationUri = 
				ucb.path("/country")
				.path("/getCountry/")
				.path(String.valueOf(country.getId()))
				.build()
				.toUri();
		headers.setLocation(locationUri);
		
		ResponseEntity<Country> responseEntity = new ResponseEntity<Country>(country,headers,HttpStatus.CREATED);
		return responseEntity;
	}
	
	@RequestMapping(value = "/deleteCountry/{countryId}", method = RequestMethod.DELETE)
	public String deleteCountry(@PathVariable String countryId) {
		String message = countryService.delete(Integer.valueOf(countryId));
		return message;
	}
	
	@RequestMapping(value = "/xsm/sla/{serviceId}", method = RequestMethod.GET)
	public ArrayList<Sla> getSlaDefination(@PathVariable String serviceId) {
		HashMap<String, ArrayList<Sla>> slaArr = slaService.getSlaDefList();
		ArrayList<Sla> slaData = slaArr.get(serviceId);
		System.out.println(slaData);
		return slaData;
		
	}
}
