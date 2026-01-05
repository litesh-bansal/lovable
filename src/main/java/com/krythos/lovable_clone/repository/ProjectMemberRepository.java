package com.krythos.lovable_clone.repository;

import com.krythos.lovable_clone.entity.Project;
import com.krythos.lovable_clone.entity.ProjectMember;
import com.krythos.lovable_clone.entity.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {

    List<ProjectMember> findByIdProjectId(Long projectId);

    Iterable<ProjectMemberId> project(Project project);
}
