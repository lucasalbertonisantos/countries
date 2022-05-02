package pt.visualnuts.lucasalbertoni.exercises.service;

import org.springframework.stereotype.Service;

@Service
public class ValidatorIfDivisibleService {
	
	public boolean isDivisibleBy3(long number) {
		return number % 3 == 0;
	}

	public boolean isDivisibleBy5(long number) {
		return number % 5 == 0;
	}
	
	public boolean isDivisibleBy3And5(long number) {
		return isDivisibleBy3(number) && isDivisibleBy5(number);
	}

}
