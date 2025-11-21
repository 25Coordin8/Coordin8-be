package com.example.coordin8.service.cardService;

import com.example.coordin8.dto.cardDto.CardRequestDto;
import com.example.coordin8.entity.CardEntity;

import java.util.List;
import java.util.Optional;

public interface CardService {

    CardEntity createCard(CardRequestDto requestDto);

    CardEntity updateCard(Long cardId, CardRequestDto requestDto);

    List<CardEntity> getAllCards();

    Optional<CardEntity> getCardById(Long cardId);

    void deleteCard(Long cardId);
}
