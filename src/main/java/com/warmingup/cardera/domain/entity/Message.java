package com.warmingup.cardera.domain.entity;


import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long relationId;

    @Column
    private String text;

}
