package com.warmingup.cardera.dto;

import lombok.Data;

@Data
public class FuelPriceRequestDto {

    private String start;

    private String goal;

    private int passengerNumber;

    private int carpoolCount;
}
