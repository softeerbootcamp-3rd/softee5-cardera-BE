package com.warmingup.cardera.domain.repository;

import com.warmingup.cardera.domain.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<List<Message>> findAllByRelationId(Long relation_id);
}
