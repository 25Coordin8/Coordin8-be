package com.example.coordin8.controller;

import com.example.coordin8.service.imageService.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Tag(name = "image", description = "이미지 업로드 API")
@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    @Operation(summary = "이미지 업로드", description = "이미지 파일을 업로드하고 URL을 반환합니다.")
    public ResponseEntity<Map<String, String>> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "directory", defaultValue = "general") String directory) {
        
        try {
            String imageUrl = imageService.saveImage(file, directory);
            
            Map<String, String> response = new HashMap<>();
            response.put("imageUrl", imageUrl);
            response.put("message", "이미지 업로드 성공");
            
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        } catch (IOException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "파일 저장 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/upload/avatar")
    @Operation(summary = "아바타 이미지 업로드", description = "아바타 이미지를 업로드합니다.")
    public ResponseEntity<Map<String, String>> uploadAvatar(@RequestParam("file") MultipartFile file) {
        return uploadImage(file, "avatar");
    }

    @DeleteMapping
    @Operation(summary = "이미지 삭제", description = "이미지 파일을 삭제합니다.")
    public ResponseEntity<Map<String, String>> deleteImage(@RequestParam("imageUrl") String imageUrl) {
        boolean deleted = imageService.deleteImage(imageUrl);
        
        Map<String, String> response = new HashMap<>();
        if (deleted) {
            response.put("message", "이미지 삭제 성공");
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "이미지 삭제 실패");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}

