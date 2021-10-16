package net.illay.senla_project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import net.illay.senla_project.model.Adress;
import net.illay.senla_project.model.Answer;

public interface ConvertingService {

	String creatingUrls(Adress dataFromClient);

	String convertingAdress(String dataFromClient);

	Answer takingDataFromYandex(String url, Adress dataFromClient) throws JsonMappingException, JsonProcessingException;
}
