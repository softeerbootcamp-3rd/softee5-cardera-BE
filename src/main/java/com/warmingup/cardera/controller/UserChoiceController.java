package com.warmingup.cardera.controller;

import com.warmingup.cardera.dto.UserChoiceRequestDto;
import com.warmingup.cardera.service.UserChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserChoiceController {
    private final UserChoiceService userChoiceService;

    @PatchMapping("/choice")
    public ResponseEntity<String> updateChoice(@RequestBody UserChoiceRequestDto userChoiceRequestDto) {
        userChoiceService.updateUserChoice(userChoiceRequestDto);
        return ResponseEntity.ok().body("Success");
    }
}
