package com.example.coordin8.controller;

import com.example.coordin8.dto.userDto.UserRequestDto;
import com.example.coordin8.dto.userDto.UserResponseDto;
import com.example.coordin8.entity.UserEntity;
import com.example.coordin8.service.userService.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "user", description = "사용자 API")
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    // 생성자 주입
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ------------------ CREATE ------------------
    @PostMapping("/users")
    @Operation(summary = "사용자 생성", description = "JSON RequestBody로 사용자 생성")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto requestDto) {
        UserEntity userEntity = userService.createUser(requestDto);
        return ResponseEntity.ok(new UserResponseDto(userEntity));
    }

    // ------------------ READ ALL ------------------
    @GetMapping("/users")
    @Operation(summary = "사용자 전체 조회", description = "사용자 리스트 반환")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers()
                .stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    // ------------------ READ ONE ------------------
    @GetMapping("/users/{id}")
    @Operation(summary = "특정 사용자 조회", description = "ID 기준 단일 사용자 조회")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(UserResponseDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ------------------ UPDATE ------------------
    @PutMapping("/users/{id}")
    @Operation(summary = "사용자 수정", description = "ID 기준 유저 정보 수정")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDto requestDto) {

        try {
            UserEntity updatedUser = userService.updateUser(id, requestDto);
            return ResponseEntity.ok(new UserResponseDto(updatedUser));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ------------------ DELETE ------------------
    @DeleteMapping("/users/{id}")
    @Operation(summary = "사용자 삭제", description = "ID 기준 유저 삭제")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
