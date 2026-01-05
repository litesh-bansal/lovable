package com.krythos.lovable_clone.service.impl;

import com.krythos.lovable_clone.dto.member.InviteMemberRequest;
import com.krythos.lovable_clone.dto.member.MemberResponse;
import com.krythos.lovable_clone.dto.member.UpdateMemberRoleRequest;
import com.krythos.lovable_clone.entity.Project;
import com.krythos.lovable_clone.entity.ProjectMember;
import com.krythos.lovable_clone.entity.ProjectMemberId;
import com.krythos.lovable_clone.entity.User;
import com.krythos.lovable_clone.mapper.ProjectMemberMapper;
import com.krythos.lovable_clone.repository.ProjectMemberRepository;
import com.krythos.lovable_clone.repository.ProjectsRepository;
import com.krythos.lovable_clone.repository.UserRepository;
import com.krythos.lovable_clone.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectsRepository projectsRepository;
    private final ProjectMemberMapper projectMemberMapper;
    private final UserRepository userRepository;

    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {

        Project project = getAccessibleProjectById(projectId, userId);

        List<MemberResponse> memberResponseList =  projectMemberRepository.findByIdProjectId(projectId)
                        .stream()
                        .map(projectMemberMapper::toProjectMemberResponseFromMember)
                        .toList();
        return memberResponseList;
    }

    @Override
    public MemberResponse inviteMember(Long projectId, Long userId, InviteMemberRequest request) {
        Project project = getAccessibleProjectById(projectId, userId);

        User invitedUser = userRepository.findByUsername(request.username()).orElseThrow();

        if(invitedUser.getId().equals(userId)) {
            throw new RuntimeException("cannot invite yourself");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, userId);

        if(projectMemberRepository.existsById(projectMemberId)) {
            throw new RuntimeException("user already exists! Can't invite again");
        }

        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitedUser)
                .role(request.role())
                .invitedAt(Instant.now())
                .build();

        projectMemberRepository.save(projectMember);

        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, Long userId, UpdateMemberRoleRequest request) {

        Project project = projectsRepository.findAccessibleProjectById(projectId, userId).orElseThrow();

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);

        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();

        projectMember.setRole(request.role());
        projectMemberRepository.save(projectMember);
        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    @Override
    public void removeProjectMember(Long projectId, Long memberId, Long userId) {

        Project project = projectsRepository.findAccessibleProjectById(projectId, userId).orElseThrow();

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);

        if(!projectMemberRepository.existsById(projectMemberId)) {
            throw new RuntimeException("user does not exists! Can't delete member");
        }
        projectMemberRepository.deleteById(projectMemberId);
    }

    public Project getAccessibleProjectById(Long userId, Long id) {
        return projectsRepository.findAccessibleProjectById(id, userId).orElseThrow();
    }
}
