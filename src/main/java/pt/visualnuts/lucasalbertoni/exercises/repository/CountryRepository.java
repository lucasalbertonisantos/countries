package pt.visualnuts.lucasalbertoni.exercises.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.visualnuts.lucasalbertoni.exercises.entity.Country;

public interface CountryRepository extends JpaRepository<Country, String>{
	List<Country> findByLanguagesIdLanguageAbbreviation(String language);
}
