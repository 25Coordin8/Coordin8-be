package com.example.coordin8.service.focussissionService;

import com.example.coordin8.dto.focussessionDto.*;
import com.example.coordin8.entity.FocusSessionEntity;
import com.example.coordin8.entity.ProjectEntity;
import com.example.coordin8.entity.UserEntity;
import com.example.coordin8.repository.FocusSessionRepository;
import com.example.coordin8.repository.ProjectRepository;
import com.example.coordin8.repository.UserRepository;
import com.example.coordin8.service.focussissionService.FocusSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FocusSessionServiceImpl implements FocusSessionService {

    private final FocusSessionRepository focusSessionRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public FocusSessionResponseDto createSession(FocusSessionRequestDto requestDto) {

        ProjectEntity project = projectRepository.findById(requestDto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Invalid project ID"));

        UserEntity user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Invalid user ID"));

        FocusSessionEntity session = FocusSessionEntity.builder()
                .project(project)
                .user(user)
                .sessionType(requestDto.getSessionType())
                .startTime(requestDto.getStartTime())
                .endTime(requestDto.getEndTime())
                .durationSeconds(null)
                .build();

        FocusSessionEntity saved = focusSessionRepository.save(session);

        return convertToDto(saved);
    }

    @Override
    public FocusSessionResponseDto endSession(Long sessionId) {
        FocusSessionEntity session = focusSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        if (session.getEndTime() == null) {
            session.setEndTime(java.time.LocalDateTime.now());
        }

        int duration = (int) Duration.between(session.getStartTime(), session.getEndTime()).getSeconds();
        session.setDurationSeconds(duration);

        FocusSessionEntity updated = focusSessionRepository.save(session);

        return convertToDto(updated);
    }

    @Override
    public List<FocusSessionResponseDto> getSessionsByUser(Long userId) {
        return focusSessionRepository.findAll().stream()
                .filter(s -> s.getUser().getId().equals(userId))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FocusSessionResponseDto> getSessionsByProject(Long projectId) {
        return focusSessionRepository.findAll().stream()
                .filter(s -> s.getProject().getId().equals(projectId))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private FocusSessionResponseDto convertToDto(FocusSessionEntity entity) {
        return FocusSessionResponseDto.builder()
                .id(entity.getId())
                .projectId(entity.getProject().getId())
                .userId(entity.getUser().getId())
                .sessionType(entity.getSessionType())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .durationSeconds(entity.getDurationSeconds())
                .build();
    }
}
