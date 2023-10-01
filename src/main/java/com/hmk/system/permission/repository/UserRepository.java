package com.hmk.system.permission.repository;

import com.hmk.system.permission.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
