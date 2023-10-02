package com.hmk.system.permission.repository;

import com.hmk.system.permission.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Page<RoleEntity> findAllByNameContains(final String name, final Pageable pageable);
}
