package com.warmingup.cardera.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Component
@PropertySource("classpath:/api-info.yml")
public class ApiConfig {

    @Value("${api.url.geocode}")
    private String GeocodeApiUrl;

    @Value("${api.url.directions5}")
    private String Directions5ApiUrl;

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.key-id}")
    private String apiKeyId;



}
