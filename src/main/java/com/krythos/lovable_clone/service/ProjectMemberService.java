package com.krythos.lovable_clone.service;

import com.krythos.lovable_clone.dto.member.InviteMemberRequest;
import com.krythos.lovable_clone.dto.member.MemberResponse;
import com.krythos.lovable_clone.dto.member.UpdateMemberRoleRequest;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ProjectMemberService {
    List<MemberResponse> getProjectMembers(Long projectId, Long userId);

    MemberResponse inviteMember(Long projectId, Long userId, InviteMemberRequest request);

    MemberResponse updateMemberRole(Long projectId, Long memberId, Long userId, UpdateMemberRoleRequest request);

    MemberResponse deleteProjectMember(Long projectId, Long memberId, Long userId);
}
