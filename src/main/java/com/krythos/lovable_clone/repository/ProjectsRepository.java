package com.krythos.lovable_clone.repository;

import com.krythos.lovable_clone.entity.Project;
import com.krythos.lovable_clone.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectsRepository extends JpaRepository<Project, Long> {

    @Query("""
                   SELECT p FROM Project p WHERE p.deletedAt IS NULL AND EXISTS (
                                    SELECT 1 FROM ProjectMember pm
                                    where pm.id.userId = :userId
                                    and pm.id.projectId = :projectId
                               )
                               ORDER BY p.updatedAt DESC
           \s""")
    List<Project> findAllProjectByUser(@Param("userId") Long userId);

    @Query("""
            SELECT p FROM Project p WHERE p.id = :projectId AND p.deletedAt is NULL
            and exists (
                            SELECT 1 FROM ProjectMember pm
                            where pm.id.userId = :userId
                            and pm.id.projectId = :projectId
                        )
            """)
    Optional<Project> findAccessibleProjectById(@Param("projectId") Long projectId,
                                                @Param("userId") Long userId);
}
