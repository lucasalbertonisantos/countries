package pt.visualnuts.lucasalbertoni.exercises.dto;

import java.util.List;

public class CountryDTO {
	
	private String country;
	private List<String> languages;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public List<String> getLanguages() {
		return languages;
	}
	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

}
