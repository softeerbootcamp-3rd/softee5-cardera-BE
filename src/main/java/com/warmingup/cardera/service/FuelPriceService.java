package com.warmingup.cardera.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.warmingup.cardera.domain.entity.UserChoice;
import com.warmingup.cardera.domain.repository.UserChoiceRepository;
import com.warmingup.cardera.dto.CarpoolType;
import com.warmingup.cardera.dto.FuelPriceRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;

import static com.warmingup.cardera.service.ApiInfoConst.*;

@Service
@RequiredArgsConstructor
public class FuelPriceService {

    private final UserChoiceRepository userChoiceRepository;

    public int getFuelPrice(FuelPriceRequestDto fuelPriceRequestDto) {

        // 1. start, goal 주소를 경도, 위도로 변환
        String startCoordinate = getCoordinate(fuelPriceRequestDto.getStart());
        String goalCoordinate = getCoordinate(fuelPriceRequestDto.getGoal());

        // 2. 출발지, 도착지의 경도와 위도 정보에서 유류비 받아오기
        int fuelPrice = getFuelPrice(startCoordinate, goalCoordinate);

        //todo 계산 방법 바뀔 수도 있음.
        CarpoolType carpoolType = fuelPriceRequestDto.getCarpoolType();
        return fuelPrice * fuelPriceRequestDto.getCarpoolCount() / fuelPriceRequestDto.getPassengerNumber();
    }

    public List<UserChoice> getUserChoices(){
        return userChoiceRepository.findAll();
    }

    private String getCoordinate(String address) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(GEOCODE_API_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT);

        WebClient webClient1 = WebClient.builder().uriBuilderFactory(factory).build();
        String response = webClient1.get()
                .uri(uriBuilder -> uriBuilder.queryParam("query", address).build())
                .header("X-NCP-APIGW-API-KEY-ID", API_KEY_Id)
                .header("X-NCP-APIGW-API-KEY", API_KEY)
                .retrieve().bodyToMono(String.class)
                .block();

        //response 파싱
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonObject addresses = jsonObject.getAsJsonArray("addresses").get(0).getAsJsonObject();
        String longitude = addresses.get("x").toString().replace("\"", "");
        String latitude = addresses.get("y").toString().replace("\"", "");

        return longitude + "," + latitude;
    }

    private int getFuelPrice(String startCoordinate, String goalCoordinate) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(DIRECTIONS5_API_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder().uriBuilderFactory(factory).baseUrl(DIRECTIONS5_API_URL).build();
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("start", startCoordinate).queryParam("goal",goalCoordinate).build())
                .header("X-NCP-APIGW-API-KEY-ID", API_KEY_Id)
                .header("X-NCP-APIGW-API-KEY", API_KEY)
                .retrieve().bodyToMono(String.class)
                .block();

        //todo
        // null check -> optional
        //response 파싱
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

        int fuelPrice = jsonObject
                .getAsJsonObject("route")
                .getAsJsonArray("traoptimal")
                .get(0)
                .getAsJsonObject()
                .getAsJsonObject("summary")
                .get("fuelPrice").getAsInt();

        return fuelPrice;
    }


}
