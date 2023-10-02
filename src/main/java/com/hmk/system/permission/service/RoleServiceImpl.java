package com.hmk.system.permission.service;

import com.hmk.system.permission.converter.RoleEntityConverter;
import com.hmk.system.permission.domain.Role;
import com.hmk.system.permission.domain.filter.RoleFilter;
import com.hmk.system.permission.domain.view.PaginationView;
import com.hmk.system.permission.exception.NotFoundException;
import com.hmk.system.permission.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Override
    public Role save(Role role) {
        role.selfValidation();
        var roleEntity = RoleEntityConverter.from(role);
        repository.save(roleEntity);
        return RoleEntityConverter.to(roleEntity);
    }

    @Override
    public Role update(Long roleId, Role role) {
        role.selfValidation();
        var savedRole = repository.findById(roleId)
                .orElseThrow(() -> new NotFoundException("Role not found"));
        savedRole.setName(role.getName());
        savedRole.setDescription(role.getDescription());
        repository.save(savedRole);
        return RoleEntityConverter.to(savedRole);
    }

    @Override
    public Role findById(Long id) {
        var roleEntity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role not found"));
        return RoleEntityConverter.to(roleEntity);
    }

    @Override
    public PaginationView<Role> findByFilterPageable(final RoleFilter filter, final int page, final int size) {
        var pageable = PageRequest.of(page, size, Sort.by("name"));

        var pageView = repository.findAllByNameContains(filter.name(), pageable);

        return PaginationView.<Role>builder()
                .currentPage(page)
                .totalPages(pageView.getTotalPages())
                .totalItems(pageView.getTotalElements())
                .content(pageView.isEmpty() ? Collections.emptyList() : pageView.getContent()
                        .stream()
                        .map(RoleEntityConverter::to)
                        .collect(Collectors.toList()))
                .build();
    }
}
