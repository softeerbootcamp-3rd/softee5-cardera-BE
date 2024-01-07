package com.warmingup.cardera.service;

import com.warmingup.cardera.domain.entity.UserChoice;
import com.warmingup.cardera.domain.repository.UserChoiceRepository;
import com.warmingup.cardera.dto.UserChoiceRequestDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserChoiceService {
    private final UserChoiceRepository userChoiceRepository;

    @Transactional
    public Long updateUserChoice(UserChoiceRequestDto userChoiceRequestDto) {
        // 1. multiple에 해당하는 userChoice 필드 가져오기
        UserChoice userChoice = userChoiceRepository.findByMultiple(userChoiceRequestDto.getMultiple())
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with multiple: " + userChoiceRequestDto.getMultiple()));
        // 2. 해당 필드의 choiceCount에 1을 더해서 업데이트하기
        userChoice.incrementCount();
        return userChoice.getId();
    }
}
