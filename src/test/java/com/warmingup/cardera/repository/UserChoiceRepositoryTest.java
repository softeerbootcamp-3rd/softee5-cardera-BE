package com.warmingup.cardera.repository;

import com.warmingup.cardera.domain.entity.UserChoice;
import com.warmingup.cardera.domain.repository.UserChoiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserChoiceRepositoryTest {

    @Autowired
    private UserChoiceRepository userChoiceRepository;

    @Test
    @DisplayName("get UserChoice entity by 'multiple' value")
    void findByMultipleTest(){
        // given
        double multiple = 1.2;
        userChoiceRepository.save(UserChoice.builder().multiple(multiple).build());

        // when
        UserChoice findUserchoice = userChoiceRepository.findByMultiple(multiple)
                .orElseThrow(EntityNotFoundException::new);

        // then
        Assertions.assertThat(findUserchoice.getMultiple()).isEqualTo(multiple);
    }
}
