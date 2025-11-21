package com.example.coordin8.service.focussissionService;

import com.example.coordin8.dto.focussessionDto.FocusSessionRequestDto;
import com.example.coordin8.dto.focussessionDto.FocusSessionResponseDto;

import java.util.List;

public interface FocusSessionService {

    FocusSessionResponseDto createSession(FocusSessionRequestDto requestDto);

    FocusSessionResponseDto endSession(Long sessionId);

    List<FocusSessionResponseDto> getSessionsByUser(Long userId);

    List<FocusSessionResponseDto> getSessionsByProject(Long projectId);
}
