package com.warmingup.cardera.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warmingup.cardera.dto.UserChoiceRequestDto;
import com.warmingup.cardera.service.UserChoiceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@WebMvcTest(UserChoiceController.class)
public class UserChoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserChoiceService userChoiceService;

    @InjectMocks
    private UserChoiceController userChoiceController;

    @Test
    void testUserChoiceController() {
        // given
        UserChoiceRequestDto requestDto = new UserChoiceRequestDto();
        requestDto.setMultiple(1.5);

        when(userChoiceService.updateUserChoice(requestDto)).thenReturn(1L);

        ObjectMapper mapper = new ObjectMapper();

        // when
        try {
            mockMvc.perform(MockMvcRequestBuilders
                    .patch("/choice")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(requestDto)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string("Success"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
