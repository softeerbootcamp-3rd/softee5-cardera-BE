package com.warmingup.cardera;

import com.warmingup.cardera.domain.entity.UserChoice;
import com.warmingup.cardera.domain.repository.UserChoiceRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
//        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final UserChoiceRepository userChoiceRepository;

        public void dbInit1() {
            UserChoice userChoice1 = UserChoice.builder()
                    .multiple(1.25)
                    .choiceCount(20)
                    .build();

            UserChoice userChoice2 = UserChoice.builder()
                    .multiple(1.5)
                    .choiceCount(10)
                    .build();

            UserChoice userChoice3 = UserChoice.builder()
                    .multiple(1.75)
                    .choiceCount(20)
                    .build();

            UserChoice userChoice4 = UserChoice.builder()
                    .multiple(2)
                    .choiceCount(20)
                    .build();

            userChoiceRepository.saveAll(Arrays.asList(userChoice1,userChoice2,userChoice3,userChoice4));
        }
    }
}

