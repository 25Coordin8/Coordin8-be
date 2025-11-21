package com.example.coordin8.controller;

import com.example.coordin8.dto.roleDto.RoleRequestDto;
import com.example.coordin8.dto.roleDto.RoleResponseDto;
import com.example.coordin8.entity.RoleEntity;
import com.example.coordin8.service.roleService.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "role", description = "역할(Role) 관리 API")
@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @Operation(summary = "역할 생성", description = "새로운 역할 생성")
    public ResponseEntity<RoleResponseDto> createRole(@Valid @RequestBody RoleRequestDto dto) {
        RoleEntity role = roleService.createRole(dto);
        return ResponseEntity.ok(new RoleResponseDto(role));
    }

    @GetMapping
    @Operation(summary = "모든 역할 조회", description = "역할 리스트 조회")
    public ResponseEntity<List<RoleResponseDto>> getAllRoles() {
        List<RoleResponseDto> list = roleService.getAllRoles()
                .stream()
                .map(RoleResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "역할 조회", description = "ID로 역할 조회")
    public ResponseEntity<RoleResponseDto> getRole(@PathVariable Long id) {
        return roleService.getRoleById(id)
                .map(RoleResponseDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "역할 수정", description = "ID로 역할 수정")
    public ResponseEntity<RoleResponseDto> updateRole(@PathVariable Long id,
                                                      @Valid @RequestBody RoleRequestDto dto) {
        try {
            RoleEntity updated = roleService.updateRole(id, dto);
            return ResponseEntity.ok(new RoleResponseDto(updated));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "역할 삭제", description = "ID로 역할 삭제")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
