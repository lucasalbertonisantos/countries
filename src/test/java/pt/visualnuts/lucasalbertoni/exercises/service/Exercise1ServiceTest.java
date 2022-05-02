package pt.visualnuts.lucasalbertoni.exercises.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Exercise1ServiceTest {
	
	@InjectMocks
	private Exercise1Service exerciseService;
	
	@Spy
	private ValidatorIfDivisibleService validatorIfDivisibleService;
	
	@Test
	public void testGetStringNumber() {
		long number = 15L;
		long expectedVisualNutsCounter = 1;
		long expectedVisualCounter = 4;
		long expectedNutsCounter = 2;
		long expectedNumbersCounter = 8;
		
		List<String> returnedStrings = exerciseService.getStringNumber(number);
		
		long visualNutsCounter = 0;
		long visualCounter = 0;
		long nutsCounter = 0;
		long numbersCounter = 0;
		for(String string : returnedStrings) {
			if(Exercise1Service.VISUAL_NUTS.equals(string)) {
				visualNutsCounter++;
			} else if(Exercise1Service.VISUAL.equals(string)) {
				visualCounter++;
			} else if(Exercise1Service.NUTS.equals(string)) {
				nutsCounter++;
			} else {
				numbersCounter++;
			}
		}
		assertEquals(expectedVisualNutsCounter, visualNutsCounter);
		assertEquals(expectedVisualCounter, visualCounter);
		assertEquals(expectedNutsCounter, nutsCounter);
		assertEquals(expectedNumbersCounter, numbersCounter);
	}

}
