package com.hmk.system.permission.repository;

import com.hmk.system.permission.entity.UserRoleEntity;
import com.hmk.system.permission.entity.pk.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRolePK> {
}
