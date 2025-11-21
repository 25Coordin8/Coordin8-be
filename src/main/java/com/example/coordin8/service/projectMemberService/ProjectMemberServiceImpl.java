package com.example.coordin8.service.projectMemberService;

import com.example.coordin8.dto.projectmemberDto.ProjectMemberRequestDto;
import com.example.coordin8.entity.ProjectMemberEntity;
import com.example.coordin8.entity.ProjectEntity;
import com.example.coordin8.entity.RoleEntity;
import com.example.coordin8.entity.UserEntity;
import com.example.coordin8.repository.ProjectMemberRepository;
import com.example.coordin8.repository.ProjectRepository;
import com.example.coordin8.repository.RoleRepository;
import com.example.coordin8.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public ProjectMemberServiceImpl(ProjectMemberRepository projectMemberRepository,
                                    ProjectRepository projectRepository,
                                    UserRepository userRepository,
                                    RoleRepository roleRepository) {
        this.projectMemberRepository = projectMemberRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    // ---------------------- CREATE ----------------------
    @Override
    public ProjectMemberEntity createProjectMember(ProjectMemberRequestDto dto) {
        ProjectEntity project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        RoleEntity role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        ProjectMemberEntity projectMember = new ProjectMemberEntity();
        projectMember.setProject(project);
        projectMember.setUser(user);
        projectMember.setRole(role);

        ProjectMemberEntity saved = projectMemberRepository.save(projectMember);

        // ⭐ User의 project_count +1
        int oldCount = user.getProjectCount() == null ? 0 : user.getProjectCount();
        user.setProjectCount(oldCount + 1);
        userRepository.save(user);

        return saved;
    }

    // ---------------------- READ ALL ----------------------
    @Override
    public List<ProjectMemberEntity> getAllProjectMembers() {
        return projectMemberRepository.findAll();
    }

    // ---------------------- READ ONE ----------------------
    @Override
    public Optional<ProjectMemberEntity> getProjectMemberById(Long id) {
        return projectMemberRepository.findById(id);
    }

    // ---------------------- UPDATE ----------------------
    @Override
    public ProjectMemberEntity updateProjectMember(Long id, ProjectMemberRequestDto dto) {
        ProjectMemberEntity projectMember = projectMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProjectMember not found"));

        if (dto.getProjectId() != null) {
            ProjectEntity project = projectRepository.findById(dto.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Project not found"));
            projectMember.setProject(project);
        }

        if (dto.getUserId() != null) {
            UserEntity user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            projectMember.setUser(user);
        }

        if (dto.getRoleId() != null) {
            RoleEntity role = roleRepository.findById(dto.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            projectMember.setRole(role);
        }

        return projectMemberRepository.save(projectMember);
    }

    // ---------------------- DELETE ----------------------
    @Override
    public void deleteProjectMember(Long id) {
        ProjectMemberEntity member = projectMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProjectMember not found"));

        UserEntity user = member.getUser();

        // ⭐ 삭제 시 project_count -1
        if (user != null && user.getProjectCount() != null && user.getProjectCount() > 0) {
            user.setProjectCount(user.getProjectCount() - 1);
            userRepository.save(user);
        }

        projectMemberRepository.deleteById(id);
    }
}