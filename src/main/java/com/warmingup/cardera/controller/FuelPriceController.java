package com.warmingup.cardera.controller;


import com.warmingup.cardera.dto.FuelPriceRequestDto;
import com.warmingup.cardera.service.FuelPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@RequiredArgsConstructor
@Controller
public class FuelPriceController {

    private final FuelPriceService fuelPriceService;
    @GetMapping("/calculation")
    public String calculateCost(@ModelAttribute FuelPriceRequestDto fuelPriceRequestDto) {

        int fuelPrice = fuelPriceService.getFuelPrice(fuelPriceRequestDto);

        return "";
    }

}
