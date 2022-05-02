package pt.visualnuts.lucasalbertoni.exercises.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.visualnuts.lucasalbertoni.exercises.service.Exercise1Service;

@ExtendWith(MockitoExtension.class)
public class Exercise1ControllerTest {
	
	@InjectMocks
	private Exercise1Controller exercise1Controller;
	
	@Mock
	private Exercise1Service exercise1Service;
	
	@Test
	public void testGet() {
		long number = 100;
		
		List<String> strings = new ArrayList<>();
		strings.add("ok");
		Mockito.when(exercise1Service.getStringNumber(number)).thenReturn(strings);
		
		List<String> returnedStrings = exercise1Controller.get(number);
		assertEquals(strings, returnedStrings);
	}

}
