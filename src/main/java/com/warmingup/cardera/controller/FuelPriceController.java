package com.warmingup.cardera.controller;


import com.warmingup.cardera.domain.entity.UserChoice;
import com.warmingup.cardera.dto.FuelPriceRequestDto;
import com.warmingup.cardera.dto.FuelPriceResponseDto;
import com.warmingup.cardera.dto.UserChoiceDto;
import com.warmingup.cardera.service.FuelPriceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FuelPriceController {

    private final FuelPriceService fuelPriceService;
    @GetMapping("/calculation")
    public ResponseEntity<FuelPriceResponseDto> getFuelPriceAndUserChoice(@ModelAttribute FuelPriceRequestDto fuelPriceRequestDto) {

        int fuelPrice = fuelPriceService.calculateFuelPrice(fuelPriceRequestDto);
        List<UserChoiceDto> userChoiceDtoList = getUserCountByMultiple();
        return new ResponseEntity<>(new FuelPriceResponseDto(fuelPrice, userChoiceDtoList), HttpStatus.OK);
    }

    @Transactional
    private List<UserChoiceDto> getUserCountByMultiple() {
        List<UserChoice> userChoices = fuelPriceService.getUserChoices();
        return userChoices.stream()
                .map(uc -> new UserChoiceDto(uc.getMultiple(),uc.getChoiceCount()))
                .toList();
    }

}
