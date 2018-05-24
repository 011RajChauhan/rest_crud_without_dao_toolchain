package rj.prac.model;

public class Country {
	
	private int id;
	private String countryName;
	private long population;
	private String unwantedProperty;
	
	public Country() {
		super();
	}
	public Country(int id, String countryName, long population) {
		this.id = id;
		this.countryName = countryName;
		this.population = population;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public long getPopulation() {
		return population;
	}

	public void setPopulation(long population) {
		this.population = population;
	}
	public String getUnwantedProperty() {
		return unwantedProperty;
	}
	public void setUnwantedProperty(String unwantedProperty) {
		this.unwantedProperty = unwantedProperty;
	}
}
