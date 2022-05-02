package pt.visualnuts.lucasalbertoni.exercises.parser;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import pt.visualnuts.lucasalbertoni.exercises.dto.CountryDTO;
import pt.visualnuts.lucasalbertoni.exercises.entity.Country;

@Component
public class CountryParser {
	
	public CountryDTO toDTO(Country country) {
		CountryDTO dto = new CountryDTO();
		if(country != null) {
			dto.setCountry(country.getAbbreviation());
			if(country.getLanguages() != null) {
				dto.setLanguages(country.getLanguages()
						.stream()
						.map(l -> l.getId().getLanguage().getAbbreviation())
						.collect(Collectors.toList()));
			}
		}
		return dto;
	}

}
