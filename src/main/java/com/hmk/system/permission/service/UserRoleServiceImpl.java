package com.hmk.system.permission.service;

import com.hmk.system.permission.converter.RoleEntityConverter;
import com.hmk.system.permission.domain.Role;
import com.hmk.system.permission.entity.UserRoleEntity;
import com.hmk.system.permission.entity.pk.UserRolePK;
import com.hmk.system.permission.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository repository;

    @Override
    public void save(Long userId, Long roleId) {
        var userRoleEntity = UserRoleEntity.builder()
                .pk(new UserRolePK(userId, roleId))
                .build();
        repository.save(userRoleEntity);
    }

    @Override
    public List<Role> findAllRolesByUserId(Long userId) {
        var roles = repository.findAllRolesByUserId(userId);
        return roles.stream()
                .map(RoleEntityConverter::to)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long userId, Long roleId) {
        var pk = new UserRolePK(userId, roleId);
        repository.deleteById(pk);
    }

}
