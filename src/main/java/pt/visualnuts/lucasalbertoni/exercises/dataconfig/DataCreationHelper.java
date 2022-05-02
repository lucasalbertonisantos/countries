package pt.visualnuts.lucasalbertoni.exercises.dataconfig;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.visualnuts.lucasalbertoni.exercises.entity.Country;
import pt.visualnuts.lucasalbertoni.exercises.entity.CountryLanguage;
import pt.visualnuts.lucasalbertoni.exercises.entity.CountryLanguageID;
import pt.visualnuts.lucasalbertoni.exercises.entity.Language;
import pt.visualnuts.lucasalbertoni.exercises.repository.CountryRepository;
import pt.visualnuts.lucasalbertoni.exercises.repository.LanguageRepository;

@Component
public class DataCreationHelper {
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	public Country create(String coountry, String[] languages) {
		return countryRepository.save(getCountry(coountry, languages));
	}
	
	private Country getCountry(String abbreviation, String[] languages) {
		Country country = new Country();
		country.setAbbreviation(abbreviation);
		country.setLanguages(new ArrayList<>());
		for(String language : languages) {
			country.getLanguages().add(getCountryLanguage(country, language));
		}
		return country;
	}
	
	private CountryLanguage getCountryLanguage(Country country, String language) {
		CountryLanguage cl = new CountryLanguage();
		CountryLanguageID id = new CountryLanguageID();
		id.setCountry(country);
		id.setLanguage(languageRepository.save(new Language(language)));
		cl.setId(id);
		return cl;
	}

}
