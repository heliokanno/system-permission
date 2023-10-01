package com.hmk.system.permission.repository;

import com.hmk.system.permission.entity.RolePermissionEntity;
import com.hmk.system.permission.entity.pk.RolePermissionPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, RolePermissionPK> {
}
