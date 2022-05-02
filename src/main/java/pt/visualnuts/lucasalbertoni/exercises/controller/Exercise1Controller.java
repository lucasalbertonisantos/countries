package pt.visualnuts.lucasalbertoni.exercises.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.visualnuts.lucasalbertoni.exercises.service.Exercise1Service;

@RestController
@RequestMapping("/exercise1")
public class Exercise1Controller {
	
	@Autowired
	private Exercise1Service exercise1Service;
	
	@GetMapping
	public List<String> get(@RequestParam long number) {
		return exercise1Service.getStringNumber(number);
	}

}
