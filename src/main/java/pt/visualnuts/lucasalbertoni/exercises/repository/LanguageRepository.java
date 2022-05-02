package pt.visualnuts.lucasalbertoni.exercises.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.visualnuts.lucasalbertoni.exercises.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, String>{
}
