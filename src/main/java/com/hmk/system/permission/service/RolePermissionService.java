package com.hmk.system.permission.service;

import com.hmk.system.permission.domain.Permission;

import java.util.List;

public interface RolePermissionService {

    void save(Long roleId, Long permissionId);

    List<Permission> findAllPermissionsByRoleId(Long roleId);

    List<Permission> findAllPermissionsByUserId(Long userId);

    void delete(Long roleId, Long permissionId);

}
