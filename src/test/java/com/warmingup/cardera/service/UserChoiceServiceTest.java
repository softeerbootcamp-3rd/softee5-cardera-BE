package com.warmingup.cardera.service;

import com.warmingup.cardera.domain.entity.UserChoice;
import com.warmingup.cardera.domain.repository.UserChoiceRepository;
import com.warmingup.cardera.dto.UserChoiceRequestDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserChoiceServiceTest {

    @Autowired
    private UserChoiceRepository userChoiceRepository;

    @Autowired
    private UserChoiceService userChoiceService;

    @Mock
    private UserChoiceRepository userChoiceRepositoryMock;

    @InjectMocks
    private UserChoiceService userChoiceServiceMock;

    @Transactional
    @Test
    @DisplayName("update choiceCount += 1")
    void updateUserChoiceTest(){
        // given
        double multiple = 1.5;
        UserChoiceRequestDto requestDto = new UserChoiceRequestDto();
        requestDto.setMultiple(multiple);

        UserChoice userChoice = userChoiceRepository.save(UserChoice.builder().multiple(multiple).build());
        int count = userChoice.getChoiceCount();

        // when
        Long userChoiceId = userChoiceService.updateUserChoice(requestDto);

        // then
        UserChoice updatedUserChoice = userChoiceRepository.findById(userChoiceId).orElseThrow(RuntimeException::new);
        Assertions.assertThat(updatedUserChoice.getChoiceCount()).isEqualTo(count + 1);

    }

    @Test
    @DisplayName("업데이트하려는 multiple에 해당하는 UserChoice 엔티티가 없는 경우")
    void updateUserChoiceTest_EntityNotFound() {
        // given
        double multiple = 1.5;
        UserChoiceRequestDto requestDto = new UserChoiceRequestDto();
        requestDto.setMultiple(multiple);

        when(userChoiceRepositoryMock.findByMultiple(multiple)).thenReturn(Optional.empty());

        // when & then
        Assertions.assertThatThrownBy(() -> userChoiceServiceMock.updateUserChoice(requestDto))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Entity not found with multiple: " + requestDto.getMultiple());
    }
}
