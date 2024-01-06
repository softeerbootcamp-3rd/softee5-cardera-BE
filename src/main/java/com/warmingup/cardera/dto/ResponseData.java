package com.warmingup.cardera.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseData {

    private int fuelPrice;

    private List<UserChoiceDto> userChoiceDtoList;
}
