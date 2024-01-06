package com.warmingup.cardera.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table
public class UserChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private double multiple;

    @Builder.Default
    @Column(nullable = false)
    private int choiceCount = 0;

    public void incrementCount() {
        this.choiceCount += 1;
    }
}
