package com.hmk.system.permission.repository;

import com.hmk.system.permission.entity.PermissionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    Page<PermissionEntity> findAllByNameContains(final String name, final Pageable pageable);
}
