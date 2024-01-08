package com.warmingup.cardera.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FuelPriceResponseDto {

    private int fuelPrice;

    private List<UserChoiceDto> tipOptions;
}
