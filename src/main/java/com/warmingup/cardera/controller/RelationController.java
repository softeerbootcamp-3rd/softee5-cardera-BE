package com.warmingup.cardera.controller;

import com.warmingup.cardera.domain.entity.Message;
import com.warmingup.cardera.domain.entity.Relation;
import com.warmingup.cardera.domain.repository.MessageRepository;
import com.warmingup.cardera.domain.repository.RelationRepository;
import com.warmingup.cardera.dto.MessageDto;
import com.warmingup.cardera.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RelationController {
    private final MessageService messageService;

    Long r_id = 0L;

    //Relation 테이블의 relation과 파라미터 relation이 일치하는 relation 반환
    @GetMapping("/message")
    public List<MessageDto> findRelationId(@RequestParam("relation") String relation) {
        Long r_id = messageService.getRelationId(relation);

        return messageService.getMessageText(r_id);
    }
}
