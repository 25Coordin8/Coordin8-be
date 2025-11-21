package com.example.coordin8.controller;

import com.example.coordin8.entity.AvatarEntity;
import com.example.coordin8.repository.AvatarRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "avatar", description = "아바타 관리 API")
@RestController
@RequestMapping("/api/v1/avatars")
@RequiredArgsConstructor
public class AvatarController {

    private final AvatarRepository avatarRepository;

    @PostMapping
    @Operation(summary = "아바타 생성", description = "이미지 URL 경로를 입력하여 아바타를 생성합니다.")
    public ResponseEntity<Map<String, Object>> createAvatar(@RequestBody Map<String, String> request) {
        String imageUrl = request.get("imageUrl");
        
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "imageUrl은 필수입니다.");
            return ResponseEntity.badRequest().body(error);
        }

        AvatarEntity avatar = AvatarEntity.builder()
                .imageUrl(imageUrl)
                .build();

        AvatarEntity saved = avatarRepository.save(avatar);

        Map<String, Object> response = new HashMap<>();
        response.put("id", saved.getId());
        response.put("imageUrl", saved.getImageUrl());
        response.put("message", "아바타 생성 성공");

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "전체 아바타 조회", description = "모든 아바타를 조회합니다.")
    public ResponseEntity<List<Map<String, Object>>> getAllAvatars() {
        List<AvatarEntity> avatars = avatarRepository.findAll();
        
        List<Map<String, Object>> response = avatars.stream()
                .map(avatar -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", avatar.getId());
                    map.put("imageUrl", avatar.getImageUrl());
                    return map;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "아바타 조회", description = "ID로 특정 아바타를 조회합니다.")
    public ResponseEntity<Map<String, Object>> getAvatar(@PathVariable Long id) {
        return avatarRepository.findById(id)
                .map(avatar -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("id", avatar.getId());
                    response.put("imageUrl", avatar.getImageUrl());
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

