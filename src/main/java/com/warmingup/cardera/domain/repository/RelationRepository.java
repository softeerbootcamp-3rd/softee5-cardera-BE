package com.warmingup.cardera.domain.repository;

import com.warmingup.cardera.domain.entity.Relation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RelationRepository extends JpaRepository<Relation, Long> {
    Optional<Relation> findByRelationship(String relation);
}
