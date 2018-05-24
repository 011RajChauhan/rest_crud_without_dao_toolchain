package rj.prac.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import rj.prac.model.Country;

@Service
public class CountryService {

	static HashMap<Integer, Country> countryIdMap = getCountryIdMap();
	
	public CountryService() {
		
		if(countryIdMap == null) {
			countryIdMap = new HashMap<Integer,Country>();
			//creating some objects of country while initializing
			Country india = new Country(1,"India",1500000000);
			Country usa = new Country(4,"USA",5000000);
			Country china = new Country(3,"China",200000000);
			Country japan = new Country(2,"Japan",70000000);
			Country russia = new Country(8,"Rusia",1500000000);
			
			countryIdMap.put(1, india);
			countryIdMap.put(4, usa);
			countryIdMap.put(3, china);
			countryIdMap.put(2, japan);
			countryIdMap.put(8, russia);
		}
	}
	
	public static HashMap<Integer,Country> getCountryIdMap() {
		return countryIdMap;
	}
	
	public int getMaxId() {
		int max = 0;
		for(int i : countryIdMap.keySet()) {
			if(max <= i) {
				max = i;
			}
		}
		return max;
	}
	
	public List<Country> getAllCountries() {
		List<Country> countries = new ArrayList<Country>(countryIdMap.values());
		return countries;
	}
	
	public Country getCountry(int countryId) {
		Country country = countryIdMap.get(countryId);
		return country;
	}
	
	public Country addCountry(Country country) {
		country.setId(getMaxId()+1);
		countryIdMap.put(country.getId(), country);
		return country;
	}
	
	public Country updateCountry(Country country) {
		if(country.getId() <= 0) {
			return null;
		}
		countryIdMap.put(country.getId(), country);
		return country;
	}
	
	public String delete(int countryId) {
		if(countryIdMap.containsKey(countryId)) {
			countryIdMap.remove(countryId);
			return "country deleted successfully";
		} else {
			return "no country found with this id";
		}
	}
}
