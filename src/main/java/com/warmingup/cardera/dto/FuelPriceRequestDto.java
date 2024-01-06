package com.warmingup.cardera.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FuelPriceRequestDto {

    private String start;

    private String goal;

    private int passengerNumber;

    private int carpoolCount;
}
