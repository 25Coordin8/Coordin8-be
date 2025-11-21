package com.example.coordin8.service.roleService;

import com.example.coordin8.dto.roleDto.RoleRequestDto;
import com.example.coordin8.entity.RoleEntity;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    RoleEntity createRole(RoleRequestDto dto);
    RoleEntity updateRole(Long id, RoleRequestDto dto);
    List<RoleEntity> getAllRoles();
    Optional<RoleEntity> getRoleById(Long id);
    void deleteRole(Long id);
}
