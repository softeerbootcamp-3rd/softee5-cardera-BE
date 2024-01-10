package com.warmingup.cardera.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.warmingup.cardera.config.ApiConfig;
import com.warmingup.cardera.domain.entity.UserChoice;
import com.warmingup.cardera.domain.repository.UserChoiceRepository;
import com.warmingup.cardera.dto.FuelPriceRequestDto;
import com.warmingup.cardera.dto.TollFareAndFuelPriceDto;
import com.warmingup.cardera.exception.FailSearchException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FuelPriceService {

    private final ApiConfig apiConfig;
    private final UserChoiceRepository userChoiceRepository;

    public int calculateFuelPrice(FuelPriceRequestDto fuelPriceRequestDto) {

        // 1. start, goal 주소를 경도, 위도로 변환

        String startCoordinate = getCoordinate(fuelPriceRequestDto.getStart());
        String goalCoordinate = getCoordinate(fuelPriceRequestDto.getGoal());

        // 2. 출발지, 도착지의 경도와 위도 정보에서 유류비 받아오기
        TollFareAndFuelPriceDto tollFareAndFuelPrice = getTollFareAndFuelPrice(startCoordinate, goalCoordinate);
        return calculateCarPoolPricePerPerson(tollFareAndFuelPrice, fuelPriceRequestDto);
    }


    private int calculateCarPoolPricePerPerson(TollFareAndFuelPriceDto tollFareAndFuelPrice, FuelPriceRequestDto fuelPriceRequestDto) {
        int price = ((tollFareAndFuelPrice.getTollFare() + tollFareAndFuelPrice.getFuelPrice()) * fuelPriceRequestDto.getCarpoolCount()) / fuelPriceRequestDto.getPassengerNumber();
        return roundToTen(price);
    }

    // 1의 자리에서 반올림 하는 함수
    private int roundToTen(int price) {
        return (price+5)/10*10;
    }

    public List<UserChoice> getUserChoices(){
        return userChoiceRepository.findAll();
    }

    private String getCoordinate(String address) {
        Optional<String> response = getCoordinateResponseByAddress(address);
        return extractCoordinateFromResponse(response);
    }

    private Optional<String> getCoordinateResponseByAddress(String address) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(apiConfig.getGeocodeApiUrl());
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT);

        WebClient webClient = WebClient.builder().uriBuilderFactory(factory).build();

        return Optional.ofNullable(webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("query", address).build())
                .header("X-NCP-APIGW-API-KEY-ID", apiConfig.getApiKeyId())
                .header("X-NCP-APIGW-API-KEY", apiConfig.getApiKey())
                .retrieve().bodyToMono(String.class)
                .block());
    }

    private String extractCoordinateFromResponse(Optional<String> response) {
        JsonObject jsonObject = JsonParser.parseString(response.orElseThrow()).getAsJsonObject();
        JsonArray addressesArray = jsonObject.getAsJsonArray("addresses");
        if (addressesArray.isEmpty()) {
            throw new FailSearchException("주소를 (경도,위도)로 변경할 수 없습니다.");
        }
        JsonObject addresses = addressesArray.get(0).getAsJsonObject();
        String longitude = addresses.get("x").toString().replace("\"", "");
        String latitude = addresses.get("y").toString().replace("\"", "");

        return longitude + "," + latitude;
    }

    private TollFareAndFuelPriceDto getTollFareAndFuelPrice(String startCoordinate, String goalCoordinate) {
        Optional<String> response = getTollFareAndFuelPriceByCoordinate(startCoordinate, goalCoordinate);

        //response 파싱
        JsonObject jsonObject = JsonParser.parseString(response.orElseThrow()).getAsJsonObject();
        int code = jsonObject.get("code").getAsInt();
        if (code != 0) {
            String message = jsonObject.get("message").getAsString();
            throw new FailSearchException(message);
        }
        JsonObject resultSummaryJsonObject = jsonObject
                .getAsJsonObject("route")
                .getAsJsonArray("traoptimal")
                .get(0)
                .getAsJsonObject()
                .getAsJsonObject("summary");

        int tollFare = resultSummaryJsonObject.get("tollFare").getAsInt();
        int fuelPrice = resultSummaryJsonObject.get("fuelPrice").getAsInt();

        return new TollFareAndFuelPriceDto(tollFare, fuelPrice);

    }

    private Optional<String> getTollFareAndFuelPriceByCoordinate(String startCoordinate, String goalCoordinate) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(apiConfig.getDirections5ApiUrl());
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder().uriBuilderFactory(factory).baseUrl(apiConfig.getDirections5ApiUrl()).build();

        Optional<String> response = Optional.ofNullable(webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("start", startCoordinate).queryParam("goal", goalCoordinate).build())
                .header("X-NCP-APIGW-API-KEY-ID", apiConfig.getApiKeyId())
                .header("X-NCP-APIGW-API-KEY", apiConfig.getApiKey())
                .retrieve().bodyToMono(String.class)
                .block());
        return response;
    }
}
