package com.krythos.lovable_clone.repository;

import com.krythos.lovable_clone.dto.member.InviteMemberRequest;
import com.krythos.lovable_clone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
