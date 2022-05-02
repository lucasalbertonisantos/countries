package pt.visualnuts.lucasalbertoni.exercises.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.visualnuts.lucasalbertoni.exercises.entity.Country;
import pt.visualnuts.lucasalbertoni.exercises.entity.CountryLanguage;
import pt.visualnuts.lucasalbertoni.exercises.exeception.CountryNotFoundException;
import pt.visualnuts.lucasalbertoni.exercises.repository.CountryLanguageRepository;
import pt.visualnuts.lucasalbertoni.exercises.repository.CountryRepository;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {
	
	@InjectMocks
	private CountryService countryService;
	
	@Mock
	private CountryRepository countryRepository;
	
	@Mock
	private CountryLanguageRepository countryLanguageRepository;
	
	@Test
	public void testFindMostOfficialLanguagesByLanguageNotFound() {
		String language = "l";
		Mockito.when(countryRepository.findByLanguagesIdLanguageAbbreviation(language)).thenReturn(List.of());
		assertThrows(CountryNotFoundException.class, 
				() -> countryService.findMostOfficialLanguagesByLanguage(language)); 
	}
	
	@Test
	public void testFindMostOfficialLanguagesByLanguageWithMoreLanguagesAfter() {
		String languageAbbreviation = "US";
		
		Country country1 = new Country();
		country1.setAbbreviation("US");
		country1.setLanguages(new ArrayList<>());
		
		Country country2 = new Country();
		country1.setAbbreviation("BE");
		CountryLanguage language = new CountryLanguage();
		country2.setLanguages(new ArrayList<>());
		country2.getLanguages().add(language);
		
		Mockito.when(countryRepository.findByLanguagesIdLanguageAbbreviation(languageAbbreviation))
											.thenReturn(List.of(country1, country2));
		
		Country returnedCountry = countryService.findMostOfficialLanguagesByLanguage(languageAbbreviation);
		assertEquals(country2, returnedCountry);
	}
	
	@Test
	public void testFindTheHighestNumberOfOfficialLanguagesEmptyList() {
		Mockito.when(countryRepository.findAll()).thenReturn(List.of());
		assertThrows(CountryNotFoundException.class, 
				() -> countryService.findTheHighestNumberOfOfficialLanguages());
	}
	
	@Test
	public void testFindTheHighestNumberOfOfficialLanguages() {
		Country country1 = new Country();
		country1.setAbbreviation("US");
		
		Country country2 = new Country();
		country2.setAbbreviation("BE");
		CountryLanguage language1 = new CountryLanguage();
		country2.setLanguages(new ArrayList<>());
		country2.getLanguages().add(language1);
		
		Country country3 = new Country();
		country2.setAbbreviation("DE");
		
		Country country4 = new Country();
		country2.setAbbreviation("ES");
		CountryLanguage language2 = new CountryLanguage();
		country4.setLanguages(new ArrayList<>());
		country4.getLanguages().add(language1);
		country4.getLanguages().add(language2);
		
		Mockito.when(countryRepository.findAll()).thenReturn(List.of(country1, country2, country3, country4));
		Country returnedCountry = countryService.findTheHighestNumberOfOfficialLanguages();
		assertEquals(country4, returnedCountry);
	}

}
