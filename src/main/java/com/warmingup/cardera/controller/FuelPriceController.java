package com.warmingup.cardera.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Controller
public class CostController {

    private final String apiUrl = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving";
    private final String apiKeyId = "5y27ee5zb1";
    private final String apiKey = "Lrm4u7Ma8xCh1CtqcqlCabNvHUkAxINyrQOEP1Md";

    @GetMapping("/calculation")
    public String calculateCost(@RequestParam("start") String start, @RequestParam("goal") String goal
            ,@RequestParam("number") int number, @RequestParam("count") int count) {
        //계산 로직
        //1. 네이버 api로 start, goal 보내서 유류비 받기
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(apiUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder().uriBuilderFactory(factory).baseUrl(apiUrl).build();
        String response = webClient.get().uri(uriBuilder -> uriBuilder.queryParam("start", start).queryParam("goal", goal)
                .build())
                .header("X-NCP-APIGW-API-KEY-ID", apiKeyId)
                .header("X-NCP-APIGW-API-KEY", apiKey)
                .retrieve().bodyToMono(String.class)
                .block();

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

        JsonElement fuelPriceElement = jsonObject
                .getAsJsonObject("route")
                .getAsJsonArray("traoptimal")
                .get(0)
                .getAsJsonObject()
                .getAsJsonObject("summary")
                .get("fuelPrice");

        int fuelPrice = fuelPriceElement.getAsInt();

        // 내야 할 값 = 유류비 * number / count
        fuelPrice = fuelPrice * number / count;

        return "";
    }

}
