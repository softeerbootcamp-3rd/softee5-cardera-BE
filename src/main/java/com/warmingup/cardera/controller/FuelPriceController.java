package com.warmingup.cardera.controller;


import com.warmingup.cardera.dto.FuelPriceRequestDto;
import com.warmingup.cardera.dto.ResponseData;
import com.warmingup.cardera.dto.UserChoiceDto;
import com.warmingup.cardera.service.FuelPriceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class FuelPriceController {

    private final FuelPriceService fuelPriceService;
    @GetMapping("/calculation")
    public ResponseData calculateFuelPrice(@ModelAttribute FuelPriceRequestDto fuelPriceRequestDto) {

        int fuelPrice = fuelPriceService.getFuelPrice(fuelPriceRequestDto);
        List<UserChoiceDto> userChoiceDtoList = getUserCountByMultiple();
        return new ResponseData(fuelPrice, userChoiceDtoList);
    }

    @Transactional
    private List<UserChoiceDto> getUserCountByMultiple() {
        List<UserChoice> userChoices = fuelPriceService.getUserChoices();
        return userChoices.stream()
                .map(uc -> new UserChoiceDto(uc.getMultiple(),uc.getChoiceCount()))
                .toList();
    }

}
