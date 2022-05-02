package pt.visualnuts.lucasalbertoni.exercises.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.visualnuts.lucasalbertoni.exercises.dto.CountryDTO;
import pt.visualnuts.lucasalbertoni.exercises.parser.CountryParser;
import pt.visualnuts.lucasalbertoni.exercises.service.CountryService;

@RestController
@RequestMapping("/countries")
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private CountryParser countryParser;

	@GetMapping
	public List<CountryDTO> get() {
		return countryService.getAll()
				.stream()
				.map(c -> countryParser.toDTO(c))
				.collect(Collectors.toList());
	}
	
	//Exercise 1
	@GetMapping("/world-countries-number")
	public long getNumberOfTheCountries() {
		return countryService.getNumberOfTheCountries();
	}
	
	//Exercise 2 - But it is generic, if you want to put de you just have to send it in the request param
	@GetMapping("/most-official-languages")
	public CountryDTO findMostOfficialLanguagesByLanguage(@RequestParam String language) {
		return countryParser.toDTO(countryService.findMostOfficialLanguagesByLanguage(language));
	}
	
	//Exercise 3
	@GetMapping("/number-of-spoken-languages")
	public long numberOfLanguageSpokenByCountry(@RequestParam List<String> countries) {
		return countryService.numberOfLanguageSpokenByCountry(countries);
	}
	
	//Exercise 4
	@GetMapping("/highest-number-of-official-languages")
	public CountryDTO findTheHighestNumberOfOfficialLanguages() {
		return countryParser.toDTO(countryService.findTheHighestNumberOfOfficialLanguages());
	}
	
	//Exercise 5
	@GetMapping("/most-common-languages")
	public List<String> getMostCommonLanguages() {
		return countryService.mostCommonOfficialLanguagesForCountries();
	}

}
