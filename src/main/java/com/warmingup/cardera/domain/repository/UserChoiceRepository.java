package com.warmingup.cardera.domain.repository;

import com.warmingup.cardera.domain.entity.UserChoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserChoiceRepository extends JpaRepository<UserChoice, Long> {
    Optional<UserChoice> findByMultiple(double multiple);
}
