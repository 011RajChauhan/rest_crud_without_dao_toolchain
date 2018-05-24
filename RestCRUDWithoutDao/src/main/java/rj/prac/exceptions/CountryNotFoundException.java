package rj.prac.exceptions;

public class CountryNotFoundException extends RuntimeException{

	private int countryId;
	
	public CountryNotFoundException(int countryId) {
		this.countryId = countryId;
	}
	
	public int getCountryId() {
		return countryId;
	}
}
