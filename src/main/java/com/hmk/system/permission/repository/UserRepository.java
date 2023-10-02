package com.hmk.system.permission.repository;

import com.hmk.system.permission.entity.UserEntity;
import com.hmk.system.permission.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Page<UserEntity> findAllByNameContainsOrEmailContainsOrStatusEquals(final String name, final String email, final StatusEnum status, final Pageable pageable);
}
