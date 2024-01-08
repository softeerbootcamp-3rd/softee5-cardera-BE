package com.warmingup.cardera.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FuelPriceRequestDto {

    private String start; //출발지 주소

    private String goal; //도착지 주소

    private int passengerNumber; //탑승자 수

    private int carpoolCount; //카풀 횟수
}
