package com.warmingup.cardera.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warmingup.cardera.controller.UserChoiceController;
import com.warmingup.cardera.dto.UserChoiceRequestDto;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(UserChoiceController.class)
public class ExceptionControllerAdviceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserChoiceController userChoiceController;
    @Test
    void testUserChoiceControllerException() {
        // given
        UserChoiceRequestDto requestDto = new UserChoiceRequestDto();
        requestDto.setMultiple(1.9);

        when(userChoiceController.updateChoice(any())).thenThrow(new EntityNotFoundException("Entity not found with multiple: " + requestDto.getMultiple()));

        ObjectMapper mapper = new ObjectMapper();

        // when
        try {
            mockMvc.perform(MockMvcRequestBuilders
                            .patch("/choice")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(requestDto)))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andExpect(MockMvcResultMatchers.content().string("Entity not found with multiple: " + requestDto.getMultiple()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
