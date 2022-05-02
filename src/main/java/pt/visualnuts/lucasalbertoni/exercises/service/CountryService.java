package pt.visualnuts.lucasalbertoni.exercises.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.visualnuts.lucasalbertoni.exercises.entity.Country;
import pt.visualnuts.lucasalbertoni.exercises.exeception.CountryNotFoundException;
import pt.visualnuts.lucasalbertoni.exercises.repository.CountryLanguageRepository;
import pt.visualnuts.lucasalbertoni.exercises.repository.CountryRepository;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private CountryLanguageRepository countryLanguageRepository;
	
	public List<Country> getAll() {
		return countryRepository.findAll();
	}
	
	public long getNumberOfTheCountries() {
		return countryRepository.count();
	}
	
	public Country findMostOfficialLanguagesByLanguage(String language) {
		Country foundCountry = null;
		List<Country> countries = countryRepository.findByLanguagesIdLanguageAbbreviation(language);
		for(Country country : countries) {
			if(foundCountry == null) {
				foundCountry = country;
			} else {
				if(foundCountry.getLanguages().size() < country.getLanguages().size()) {
					foundCountry = country;
				}
			}
		}
		if(foundCountry == null) {
			throw new CountryNotFoundException("There is no country for the given language");
		}
		return foundCountry;
	}
	
	public long numberOfLanguageSpokenByCountry(List<String> abbreviations) {
		List<Country> countries = countryRepository.findAllById(abbreviations);
		Set<String> languages = new HashSet<>();
		for(Country country : countries) {
			languages.addAll(country.getLanguages().stream().map(l->l.getId().getLanguage().getAbbreviation()).collect(Collectors.toList()));
		}
		return languages.size();
	}
	
	public Country findTheHighestNumberOfOfficialLanguages() {
		List<Country> countries = getAll();
		Country highestNumber = null;
		for(Country country : countries) {
			if(highestNumber == null) {
				highestNumber = country;
			} else {
				if(highestNumber.getLanguages() == null) {
					highestNumber = country;
				} else if(country.getLanguages() != null 
						&& highestNumber.getLanguages().size() < country.getLanguages().size()) {
					highestNumber = country;
				}
			}
		}
		if(highestNumber == null) {
			throw new CountryNotFoundException("There is no country in database");
		}
		return highestNumber;
	}
	
	public List<String> mostCommonOfficialLanguagesForCountries() {
		List<String> languages = countryLanguageRepository.findAllByOrderByIdLanguageAbbreviationAsc();
		return languages.stream().map(l -> l).collect(Collectors.toList());
	}

}
