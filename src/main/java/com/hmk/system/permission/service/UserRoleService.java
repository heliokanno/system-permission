package com.hmk.system.permission.service;

import com.hmk.system.permission.domain.Role;

import java.util.List;

public interface UserRoleService {

    void save(Long userId, Long roleId);

    List<Role> findAllRolesByUserId(Long userId);

    void delete(Long userId, Long roleId);

}
