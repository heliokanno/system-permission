package com.hmk.system.permission.service;

import com.hmk.system.permission.domain.Permission;
import com.hmk.system.permission.domain.filter.PermissionFilter;
import com.hmk.system.permission.domain.view.PaginationView;

public interface PermissionService {

    Permission save(Permission permission);

    Permission update(Long permissionId, Permission permission);

    Permission findById(Long id);

    PaginationView<Permission> findByFilterPageable(final PermissionFilter filter, final int page, final int size);

}
