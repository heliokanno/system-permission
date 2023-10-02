package com.hmk.system.permission.service;

import com.hmk.system.permission.converter.PermissionEntityConverter;
import com.hmk.system.permission.domain.Permission;
import com.hmk.system.permission.domain.filter.PermissionFilter;
import com.hmk.system.permission.domain.view.PaginationView;
import com.hmk.system.permission.exception.NotFoundException;
import com.hmk.system.permission.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository repository;

    @Override
    public Permission save(Permission permission) {
        permission.selfValidation();
        var permissionEntity = PermissionEntityConverter.from(permission);
        repository.save(permissionEntity);
        return PermissionEntityConverter.to(permissionEntity);
    }

    @Override
    public Permission update(Long permissionId, Permission permission) {
        permission.selfValidation();
        var savedPermission = repository.findById(permissionId)
                .orElseThrow(() -> new NotFoundException("Permission not found"));
        savedPermission.setName(permission.getName());
        savedPermission.setDescription(permission.getDescription());
        repository.save(savedPermission);
        return PermissionEntityConverter.to(savedPermission);
    }

    @Override
    public Permission findById(Long id) {
        var permissionEntity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Permission not found"));
        return PermissionEntityConverter.to(permissionEntity);
    }

    @Override
    public PaginationView<Permission> findByFilterPageable(final PermissionFilter filter, final int page, final int size) {
        var pageable = PageRequest.of(page, size, Sort.by("name"));

        var pageView = repository.findAllByNameContains(filter.name(), pageable);

        return PaginationView.<Permission>builder()
                .currentPage(page)
                .totalPages(pageView.getTotalPages())
                .totalItems(pageView.getTotalElements())
                .content(pageView.isEmpty() ? Collections.emptyList() : pageView.getContent()
                        .stream()
                        .map(PermissionEntityConverter::to)
                        .collect(Collectors.toList()))
                .build();
    }

}
