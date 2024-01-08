package com.warmingup.cardera.service;

import com.warmingup.cardera.domain.entity.Message;
import com.warmingup.cardera.domain.entity.Relation;
import com.warmingup.cardera.domain.repository.MessageRepository;
import com.warmingup.cardera.domain.repository.RelationRepository;
import com.warmingup.cardera.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final RelationRepository relationRepository;
    private final MessageRepository messageRepository;

    public Long getRelationId(String relation) {
        Relation r = relationRepository.findByRelationship(relation)
                .orElseThrow();

        Long r_id = r.getId();

        return r_id;
    }

    public List<MessageDto> getMessageText(Long relation_id) {
        List<Message> messageList = messageRepository.findAllByRelationId(relation_id)
                .orElseThrow();

        return messageList.stream()
                .map(m -> MessageDto.builder()
                        .text(m.getText()).build()).toList();
    }

}
