package com.warmingup.cardera.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApiConfig {

    @Value("${api.url.local}")
    private String LocalApiUrl;

    @Value("${api.url.directions5}")
    private String Directions5ApiUrl;

    @Value("${api.kakao-key}")
    private String apiKakaoKey;

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.key-id}")
    private String apiKeyId;



}
