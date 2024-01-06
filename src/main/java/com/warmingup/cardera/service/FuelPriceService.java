package com.warmingup.cardera.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.warmingup.cardera.dto.FuelPriceRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FuelPriceService {

    private final String apiUrl = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving";
    private final String apiKeyId = "5y27ee5zb1";
    private final String apiKey = "Lrm4u7Ma8xCh1CtqcqlCabNvHUkAxINyrQOEP1Md";

    private final UserChoiceRepository userChoiceRepository;
    public int getFuelPrice(FuelPriceRequestDto fuelPriceRequestDto) {

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(apiUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder().uriBuilderFactory(factory).baseUrl(apiUrl).build();
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("start", fuelPriceRequestDto.getStart()).queryParam("goal", fuelPriceRequestDto.getGoal()).build())
                .header("X-NCP-APIGW-API-KEY-ID", apiKeyId)
                .header("X-NCP-APIGW-API-KEY", apiKey)
                .retrieve().bodyToMono(String.class)
                .block();

        //todo
        // null check -> optional
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

        int fuelPrice = jsonObject
                .getAsJsonObject("route")
                .getAsJsonArray("traoptimal")
                .get(0)
                .getAsJsonObject()
                .getAsJsonObject("summary")
                .get("fuelPrice").getAsInt();

        return fuelPrice * fuelPriceRequestDto.getCarpoolCount() / fuelPriceRequestDto.getPassengerNumber();
    }

    public List<UserChoice> getUserChoices(){
        return userChoiceRepository.findAll();
    }


}
