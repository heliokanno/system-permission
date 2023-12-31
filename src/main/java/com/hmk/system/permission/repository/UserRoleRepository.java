package com.hmk.system.permission.repository;

import com.hmk.system.permission.entity.RoleEntity;
import com.hmk.system.permission.entity.UserRoleEntity;
import com.hmk.system.permission.entity.pk.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRolePK> {
    @Query("SELECT r FROM UserRoleEntity ur " +
            "JOIN ur.role r " +
            "WHERE ur.pk.userId = :userId ")
    List<RoleEntity> findAllRolesByUserId(@Param("userId") Long userId);
}
