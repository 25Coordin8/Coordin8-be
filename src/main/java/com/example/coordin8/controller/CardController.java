package com.example.coordin8.controller;

import com.example.coordin8.dto.cardDto.CardRequestDto;
import com.example.coordin8.dto.cardDto.CardResponseDto;
import com.example.coordin8.entity.CardEntity;
import com.example.coordin8.service.cardService.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "card", description = "카드 API")
@RestController
@RequestMapping("/api/v1/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    @Operation(summary = "카드 생성")
    public ResponseEntity<CardResponseDto> createCard(@RequestBody CardRequestDto dto) {
        CardEntity card = cardService.createCard(dto);
        return ResponseEntity.ok(new CardResponseDto(card));
    }

    @GetMapping
    @Operation(summary = "전체 카드 조회")
    public ResponseEntity<List<CardResponseDto>> getAllCards() {
        List<CardResponseDto> cards = cardService.getAllCards()
                .stream()
                .map(CardResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{id}")
    @Operation(summary = "카드 단일 조회")
    public ResponseEntity<CardResponseDto> getCard(@PathVariable Long id) {
        return cardService.getCardById(id)
                .map(CardResponseDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "카드 수정")
    public ResponseEntity<CardResponseDto> updateCard(@PathVariable Long id, @RequestBody CardRequestDto dto) {
        try {
            CardEntity card = cardService.updateCard(id, dto);
            return ResponseEntity.ok(new CardResponseDto(card));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "카드 삭제")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }
}
