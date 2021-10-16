package net.illay.senla_project.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import net.illay.senla_project.model.Adress;
import net.illay.senla_project.service.ConvertingService;

@RestController
@RequestMapping("/locationConverting")
public class ConvertingRestController {

	private final ConvertingService convertingService;

	// Body of Post-request:
	// "coordinates" : "27.447826 53.8515"
	// "adress" : "Минск, проезд Слободской, дом 18"
	
	// Format of answer 
	// "answer": "27.447826 53.8515"
	// "answer": "Беларусь, Минск, Московский район, Малиновка, Слободской проезд, 18"

	@Autowired
	public ConvertingRestController(ConvertingService convertingService) {
		this.convertingService = convertingService;
	}

	@PostMapping
	public ResponseEntity convertingStaff(@RequestBody Adress adress)
			throws JsonMappingException, JsonProcessingException {
		return ResponseEntity
				.ok(convertingService.takingDataFromYandex(convertingService.creatingUrls(adress), adress));
	}

}
