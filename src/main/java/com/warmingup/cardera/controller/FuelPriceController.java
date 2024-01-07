package com.warmingup.cardera.controller;


import com.warmingup.cardera.domain.entity.UserChoice;
import com.warmingup.cardera.dto.FuelPriceRequestDto;
import com.warmingup.cardera.dto.ResponseData;
import com.warmingup.cardera.dto.UserChoiceDto;
import com.warmingup.cardera.service.FuelPriceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class FuelPriceController {

    private final FuelPriceService fuelPriceService;
    @GetMapping("/calculation")
    public ResponseEntity<ResponseData> calculateFuelPrice(@ModelAttribute FuelPriceRequestDto fuelPriceRequestDto) {

        int fuelPrice = fuelPriceService.getFuelPrice(fuelPriceRequestDto);
        List<UserChoiceDto> userChoiceDtoList = getUserCountByMultiple();
        return new ResponseEntity<>(new ResponseData(fuelPrice, userChoiceDtoList), HttpStatus.OK);
    }

    @Transactional
    private List<UserChoiceDto> getUserCountByMultiple() {
        List<UserChoice> userChoices = fuelPriceService.getUserChoices();
        return userChoices.stream()
                .map(uc -> new UserChoiceDto(uc.getMultiple(),uc.getChoiceCount()))
                .toList();
    }

}
