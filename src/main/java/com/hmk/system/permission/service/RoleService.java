package com.hmk.system.permission.service;

import com.hmk.system.permission.domain.Role;
import com.hmk.system.permission.domain.filter.RoleFilter;
import com.hmk.system.permission.domain.view.PaginationView;

public interface RoleService {

    Role save(Role role);

    Role update(Long roleId, Role role);

    Role findById(Long id);

    PaginationView<Role> findByFilterPageable(final RoleFilter filter, final int page, final int size);

}
