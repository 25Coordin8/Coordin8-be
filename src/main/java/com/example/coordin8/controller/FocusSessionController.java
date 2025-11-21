package com.example.coordin8.controller;

import com.example.coordin8.dto.focussessionDto.*;
import com.example.coordin8.service.focussissionService.FocusSessionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/session")
@RequiredArgsConstructor
public class FocusSessionController {

    private final FocusSessionService focusSessionService;

    @Operation(summary = "포커스 세션 생성")
    @PostMapping
    public FocusSessionResponseDto createSession(@RequestBody FocusSessionRequestDto requestDto) {
        return focusSessionService.createSession(requestDto);
    }

    @Operation(summary = "포커스 세션 종료")
    @PostMapping("/{sessionId}/end")
    public FocusSessionResponseDto endSession(@PathVariable Long sessionId) {
        return focusSessionService.endSession(sessionId);
    }

    @Operation(summary = "사용자별 세션 조회")
    @GetMapping("/user/{userId}")
    public List<FocusSessionResponseDto> getUserSessions(@PathVariable Long userId) {
        return focusSessionService.getSessionsByUser(userId);
    }

    @Operation(summary = "프로젝트별 세션 조회")
    @GetMapping("/project/{projectId}")
    public List<FocusSessionResponseDto> getProjectSessions(@PathVariable Long projectId) {
        return focusSessionService.getSessionsByProject(projectId);
    }
}
