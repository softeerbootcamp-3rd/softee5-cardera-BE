package com.warmingup.cardera.entity;

import com.warmingup.cardera.domain.entity.UserChoice;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class UserChoiceTest {
    @DisplayName("createUserChoice")
    @Test
    void createUserChoice() {
        UserChoice userChoice = UserChoice.builder()
                .id(1L)
                .multiple(1.5)
                .choiceCount(0)
                .build();

        Assertions.assertEquals(userChoice.getId(), 1L);
        Assertions.assertEquals(userChoice.getMultiple(), 1.5);
        Assertions.assertEquals(userChoice.getChoiceCount(), 0);
    }

    @DisplayName("increaseChoiceCount")
    @Test
    void increaseChoiceCount() {
        UserChoice userChoice = UserChoice.builder()
                .id(1L)
                .multiple(1.5)
                .choiceCount(0)
                .build();

        userChoice.incrementCount();
        Assertions.assertEquals(userChoice.getId(), 1);
    }
}
