package com.warmingup.cardera.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TollFareAndFuelPriceDto {

    private int tollFare;

    private int fuelPrice;
}
