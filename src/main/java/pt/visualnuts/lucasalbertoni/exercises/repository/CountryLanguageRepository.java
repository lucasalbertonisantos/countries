package pt.visualnuts.lucasalbertoni.exercises.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pt.visualnuts.lucasalbertoni.exercises.entity.CountryLanguage;
import pt.visualnuts.lucasalbertoni.exercises.entity.CountryLanguageID;

public interface CountryLanguageRepository extends JpaRepository<CountryLanguage, CountryLanguageID>{
	@Query(value = "select LANGUAGE_ABBREVIATION from COUNTRY_LANGUAGE\r\n"
			+ "group by LANGUAGE_ABBREVIATION \r\n"
			+ "having count(LANGUAGE_ABBREVIATION ) = (\r\n"
			+ "select count(cl.language_abbreviation) as quantity  from country_language cl\r\n"
			+ "group by language_abbreviation\r\n"
			+ "order by quantity desc LIMIT 1\r\n"
			+ ");", nativeQuery = true)
	List<String> findAllByOrderByIdLanguageAbbreviationAsc();
}
