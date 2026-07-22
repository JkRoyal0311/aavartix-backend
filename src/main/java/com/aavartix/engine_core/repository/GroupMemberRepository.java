package com.aavartix.engine_core.repository;

import com.aavartix.engine_core.model.ChitGroup;
import com.aavartix.engine_core.model.GroupMember;
import com.aavartix.engine_core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, UUID> {

    // Checks if a user is already in this specific group
    boolean existsByGroupAndUser(ChitGroup group, User user);

    // Counts how many people are currently in the group
    long countByGroup(ChitGroup group);
}