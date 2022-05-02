package pt.visualnuts.lucasalbertoni.exercises.steps;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.RestTemplate;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import pt.visualnuts.lucasalbertoni.exercises.Application;
import pt.visualnuts.lucasalbertoni.exercises.dataconfig.DataCreationHelper;
import pt.visualnuts.lucasalbertoni.exercises.dto.CountryDTO;
import pt.visualnuts.lucasalbertoni.exercises.entity.Country;
import pt.visualnuts.lucasalbertoni.exercises.repository.CountryRepository;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@DirtiesContext
public class CountryStepDefinitions {
	
	private static final String BASE_URL = "http://localhost:";
	private static final String COUNTRY_URL = "/countries";
	private static final String WORLD_COUNTRIES_NUMBER_URL = "/world-countries-number";
	private static final String MOST_OFFICIAL_LANGUAGES_URL = "/most-official-languages";
	private static final String NUMBER_OF_SPOKEN_LANGUAGES_URL = "/number-of-spoken-languages";
	private static final String HIGHEST_NUMBER_OF_OFFICIAL_LANGUAGES_URL = "/highest-number-of-official-languages";
	private static final String MOST_COMMON_LANGUAGES_URL = "/most-common-languages";
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private DataCreationHelper dataCreationHelper;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final List<Country> countries = new ArrayList<>();
	private Long longOfFoundCountries;
	private int httpCode;
	private CountryDTO foundCountry;
	private Long longOfFoundLanguages;
	private String[] foundLanguages;
	
	@Given("there are the bellow countries in the world")
	public void there_are_the_bellow_countries_in_the_world(Map<String, String> countries) {
		countries.forEach((country, languages) -> {
			this.countries.add(dataCreationHelper.create(country, languages.split(", ")));
		});
	}

    @When("I ask for the number of the countries in the world")
    public void I_ask_for_the_number_of_the_countries_in_the_world() {
    	ResponseEntity<Long> response = restTemplate.getForEntity(BASE_URL+port+COUNTRY_URL+WORLD_COUNTRIES_NUMBER_URL, Long.class);
    	longOfFoundCountries = response.getBody();
    	httpCode = response.getStatusCodeValue();
    }
    
    @When("I ask for the most official languages where they officially speak German {string}")
    public void I_ask_for_the_most_official_languages_where_they_officially_speak_German(String language) {
    	ResponseEntity<CountryDTO> response = restTemplate.getForEntity(BASE_URL+port+COUNTRY_URL+MOST_OFFICIAL_LANGUAGES_URL + "?language=" + language, CountryDTO.class);
    	foundCountry = response.getBody();
    	httpCode = response.getStatusCodeValue();
    }
    
    @When("I ask all the official languages spoken in the list bellow")
    public void I_ask_all_the_official_languages_spoken_in_the_list_bellow(List<String> countries) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("?countries=");
    	for(String country: countries) {
    		sb.append(country).append(",");
    	}
    	String parameters = sb.toString();
    	ResponseEntity<Long> response = restTemplate.getForEntity(BASE_URL+port+COUNTRY_URL+NUMBER_OF_SPOKEN_LANGUAGES_URL + parameters.substring(0, parameters.length()-1), Long.class);
    	longOfFoundLanguages = response.getBody();
    	httpCode = response.getStatusCodeValue();
    }
    
    @When("I ask for the highest number of official languages in the world")
    public void I_ask_for_the_highest_number_of_official_languages_in_the_world() {
    	ResponseEntity<CountryDTO> response = restTemplate.getForEntity(BASE_URL+port+COUNTRY_URL+HIGHEST_NUMBER_OF_OFFICIAL_LANGUAGES_URL, CountryDTO.class);
    	foundCountry = response.getBody();
    	httpCode = response.getStatusCodeValue();
    }
    
    @When("I ask for most common official languages in the world")
    public void I_ask_for_most_common_official_languages_in_the_world() {
    	ResponseEntity<String[]> response = restTemplate.getForEntity(BASE_URL+port+COUNTRY_URL+MOST_COMMON_LANGUAGES_URL, String[].class);
    	foundLanguages = response.getBody();
    	httpCode = response.getStatusCodeValue();
    }
    
    @Then("the found country must be {string}")
    public void the_found_country_must_be(String countryAbbreviation) {
    	assertEquals(countryAbbreviation, foundCountry.getCountry());
    }

    @Then("it must be {long} countries")
    public void it_must_be_countries(final long numberOfCountries) {
    	assertEquals(numberOfCountries, longOfFoundCountries);
    }

    @Then("it must return {long} languages")
    public void it_must_return_languages(final long numberOfCountries) {
    	assertEquals(numberOfCountries, longOfFoundLanguages);
    }
    
    @Then("the found country must be")
    public void the_found_country_must_be(final List<String> languages) {
    	for(String language : foundLanguages) {
    		assertTrue(languages.contains(language));
    	}
    }
    
    @And("the http code must be {int}")
    public void the_http_code_must_be(final int httpCode) {
    	assertEquals(httpCode, this.httpCode);
    }
    
    @And("the quantity saved on database must be {long}")
    public void the_quantity_saved_on_database_must_be(final long quantityInDatabase) {
    	assertEquals(quantityInDatabase, countryRepository.count());
    }

}
