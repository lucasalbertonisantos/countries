package pt.visualnuts.lucasalbertoni.exercises.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Exercise1Service {
	
	protected static final String VISUAL = "Visual";
	protected static final String NUTS = "Nuts";
	protected static final String VISUAL_NUTS = VISUAL + " " + NUTS; 
	
	@Autowired
	private ValidatorIfDivisibleService validatorIfDivisibleService;
	
	public List<String> getStringNumber(long number) {
		List<String> strings = new ArrayList<>();
		for(int i=1; i<=number; i++) {
			if(validatorIfDivisibleService.isDivisibleBy3And5(i)) {
				System.out.println(VISUAL_NUTS);
				strings.add(VISUAL_NUTS);
			} else if(validatorIfDivisibleService.isDivisibleBy3(i)) {
				System.out.println(VISUAL);
				strings.add(VISUAL);
			} else if(validatorIfDivisibleService.isDivisibleBy5(i)) {
				System.out.println(NUTS);
				strings.add(NUTS);
			} else {
				System.out.println(i);
				strings.add(String.valueOf(i));
			}
		}
		return strings;
	}

}
