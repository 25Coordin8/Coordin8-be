package com.example.coordin8.service.cardService;

import com.example.coordin8.dto.cardDto.CardRequestDto;
import com.example.coordin8.entity.CardEntity;
import com.example.coordin8.entity.ProjectEntity;
import com.example.coordin8.entity.UserEntity;
import com.example.coordin8.repository.CardRepository;
import com.example.coordin8.repository.ProjectRepository;
import com.example.coordin8.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public CardServiceImpl(CardRepository cardRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CardEntity createCard(CardRequestDto dto) {

        // Project 가져오기
        ProjectEntity project = null;
        if (dto.getProjectId() != null) {
            project = projectRepository.findById(dto.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Project not found"));
        }

        // User 가져오기
        UserEntity user = null;
        if (dto.getUserId() != null) {
            user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }

        // Card 생성
        CardEntity card = CardEntity.builder()
                .cardTitle(dto.getCardTitle())
                .cardContent(dto.getCardContent())
                .cardDate(dto.getCardDate())
                .project(project)
                .user(user)
                .build();

        // 카드 저장
        CardEntity savedCard = cardRepository.save(card);

        // ⭐ 카드 개수 +1 증가
        if (user != null) {
            Integer oldCount = user.getCardCount() == null ? 0 : user.getCardCount();
            user.setCardCount(oldCount + 1);
            userRepository.save(user);
        }

        return savedCard;
    }

    @Override
    public CardEntity updateCard(Long cardId, CardRequestDto dto) {
        CardEntity card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        card.setCardTitle(dto.getCardTitle());
        card.setCardContent(dto.getCardContent());
        card.setCardDate(dto.getCardDate());

        if (dto.getProjectId() != null) {
            ProjectEntity project = projectRepository.findById(dto.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Project not found"));
            card.setProject(project);
        }

        if (dto.getUserId() != null) {
            UserEntity user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            card.setUser(user);
        }

        return cardRepository.save(card);
    }

    @Override
    public List<CardEntity> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Optional<CardEntity> getCardById(Long cardId) {
        return cardRepository.findById(cardId);
    }

    @Override
    public void deleteCard(Long cardId) {

        CardEntity card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        UserEntity user = card.getUser();

        // ⭐ 카드 삭제 시 card_count -1
        if (user != null && user.getCardCount() != null && user.getCardCount() > 0) {
            user.setCardCount(user.getCardCount() - 1);
            userRepository.save(user);
        }

        cardRepository.deleteById(cardId);
    }
}