package com.example.coordin8.service.roleService;

import com.example.coordin8.dto.roleDto.RoleRequestDto;
import com.example.coordin8.entity.RoleEntity;
import com.example.coordin8.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleEntity createRole(RoleRequestDto dto) {
        RoleEntity role = RoleEntity.builder()
                .task(dto.getTask())
                .build();
        return roleRepository.save(role);
    }

    @Override
    public RoleEntity updateRole(Long id, RoleRequestDto dto) {
        RoleEntity role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        role.setTask(dto.getTask());
        return roleRepository.save(role);
    }

    @Override
    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<RoleEntity> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
