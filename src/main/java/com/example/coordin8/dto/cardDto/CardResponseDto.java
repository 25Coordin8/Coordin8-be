package com.example.coordin8.dto.cardDto;

import com.example.coordin8.entity.CardEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CardResponseDto {

    private Long cardId;
    private String cardTitle;
    private String cardContent;
    private LocalDateTime cardDate;
    private Long projectId;
    private Long userId;

    public CardResponseDto(CardEntity cardEntity) {
        this.cardId = cardEntity.getCardId();
        this.cardTitle = cardEntity.getCardTitle();
        this.cardContent = cardEntity.getCardContent();
        this.cardDate = cardEntity.getCardDate();
        this.projectId = cardEntity.getProject() != null ? cardEntity.getProject().getId() : null;
        this.userId = cardEntity.getUser() != null ? cardEntity.getUser().getId() : null;
    }
}
