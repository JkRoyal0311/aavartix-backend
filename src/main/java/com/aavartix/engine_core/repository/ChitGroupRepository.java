package com.aavartix.engine_core.repository;

import com.aavartix.engine_core.model.ChitGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChitGroupRepository extends JpaRepository<ChitGroup, UUID> {
}