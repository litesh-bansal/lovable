package com.krythos.lovable_clone.service.impl;

import com.krythos.lovable_clone.dto.project.ProjectRequest;
import com.krythos.lovable_clone.dto.project.ProjectResponse;
import com.krythos.lovable_clone.dto.project.ProjectSummaryResponse;
import com.krythos.lovable_clone.entity.Project;
import com.krythos.lovable_clone.entity.ProjectMember;
import com.krythos.lovable_clone.entity.ProjectMemberId;
import com.krythos.lovable_clone.entity.User;
import com.krythos.lovable_clone.enums.ProjectRole;
import com.krythos.lovable_clone.exception.ResourceNotFoundException;
import com.krythos.lovable_clone.mapper.ProjectMapper;
import com.krythos.lovable_clone.repository.ProjectMemberRepository;
import com.krythos.lovable_clone.repository.ProjectsRepository;
import com.krythos.lovable_clone.repository.UserRepository;
import com.krythos.lovable_clone.security.AuthUtil;
import com.krythos.lovable_clone.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectsRepository projectsRepository;
    private final UserRepository userRepository;
    private final ProjectMapper projectMapper;
    private final ProjectMemberRepository projectMemberRepository;
    private final AuthUtil authUtil;

    @Override
    public ProjectResponse createProject(ProjectRequest request) {

        Long userId = authUtil.getCurrentUserId();
        User owner = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user", userId.toString())
        );
        Project project = Project.builder()
                .name(request.name())
                .isPrivate(true)
                .build();


        project = projectsRepository.save(project);

        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(), owner.getId());
        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .role(ProjectRole.OWNER)
                .user(owner)
                .project(project)
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .build();
        projectMemberRepository.save(projectMember);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects() {
        Long userId = authUtil.getCurrentUserId();
        List<Project> project = projectsRepository.findAllProjectByUser(userId);
        return projectMapper.toProjectSummaryResponseList(project);
    }

    @Override
    public ProjectResponse getUserProjectById(Long id) {

        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(userId, id);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request) {

        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(userId, id);

        project.setName(request.name());
        projectsRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long id) {

        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(userId, id);
        project.setDeletedAt(Instant.now());
        projectsRepository.save(project);
    }

    public Project getAccessibleProjectById(Long userId, Long id) {
        return projectsRepository.findAccessibleProjectById(id, userId).orElseThrow(() -> new ResourceNotFoundException("project", id.toString()));
    }
}




