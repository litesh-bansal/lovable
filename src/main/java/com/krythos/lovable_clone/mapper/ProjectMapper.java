package com.krythos.lovable_clone.mapper;

import com.krythos.lovable_clone.dto.member.MemberResponse;
import com.krythos.lovable_clone.dto.project.ProjectResponse;
import com.krythos.lovable_clone.dto.project.ProjectSummaryResponse;
import com.krythos.lovable_clone.entity.Project;
import com.krythos.lovable_clone.entity.ProjectMember;
import com.krythos.lovable_clone.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);

    ProjectSummaryResponse toProjectSummaryResponse(Project project);

    List<ProjectSummaryResponse> toProjectSummaryResponseList(List<Project> projects);


}
