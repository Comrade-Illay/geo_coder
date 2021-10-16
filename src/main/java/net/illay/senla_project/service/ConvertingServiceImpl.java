package net.illay.senla_project.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.illay.senla_project.model.Adress;
import net.illay.senla_project.model.Answer;

@Service
public class ConvertingServiceImpl implements ConvertingService {

	static final String BASEURL = "https://geocode-maps.yandex.ru/1.x/";
	static final String APIKEY = "fa038def-5579-4bc0-9ab4-4b93f2b3456e";

	public String creatingUrls(Adress dataFromClient) {
		String adress = dataFromClient.getAdress();
		String coordinates = dataFromClient.getCoordinates();
		String convertedAdress = new String();
		if (adress == null) {
			convertedAdress = convertingCoordinates(coordinates);
		} else {
			convertedAdress = convertingAdress(adress);
		}
		final String url = BASEURL + '?' + "format=json" + "&" + "apikey=" + APIKEY + "&geocode=" + convertedAdress;
		System.out.println(url);
		return url;
	}

	@Cacheable(value = "answerCache", key = "#url")
	public Answer takingDataFromYandex(String url, Adress dataFromClient)
			throws JsonMappingException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		Answer answer = new Answer();
		String typeOfDataFromClient = new String();
		ObjectMapper mapper = new ObjectMapper();
		String adress = dataFromClient.getAdress();
		String page = restTemplate.getForEntity(url, String.class).getBody();
		System.out.println(page);
		if (adress == null) {
			typeOfDataFromClient = "text";
		} else {
			typeOfDataFromClient = "pos";
		}

		answer.setAnswer(mapper.readTree(page).findValue(typeOfDataFromClient).asText());
		return answer;
	}

	public String convertingAdress(String adress) {
		String[] words = adress.split("\\s");
		String joined = String.join("+", words);
		return joined;
	}

	public String convertingCoordinates(String coordinates) {
		String[] words = coordinates.split("\\s");
		String joined = String.join(",", words);
		return joined;
	}
}
