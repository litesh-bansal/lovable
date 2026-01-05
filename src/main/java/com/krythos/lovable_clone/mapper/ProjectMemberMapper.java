package com.krythos.lovable_clone.mapper;

import com.krythos.lovable_clone.dto.member.MemberResponse;
import com.krythos.lovable_clone.entity.ProjectMember;
import com.krythos.lovable_clone.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "projectRole", constant = "OWNER")
    MemberResponse toUser(User owner);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "projectRole", source = "role")
    MemberResponse toProjectMemberResponseFromMember(ProjectMember projectMember);
}
