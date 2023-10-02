package com.hmk.system.permission.controller;


import com.hmk.system.permission.controller.request.UserRequest;
import com.hmk.system.permission.controller.request.UserRoleRequest;
import com.hmk.system.permission.controller.response.PermissionResponse;
import com.hmk.system.permission.controller.response.RoleResponse;
import com.hmk.system.permission.controller.response.UserResponse;
import com.hmk.system.permission.domain.filter.UserFilter;
import com.hmk.system.permission.domain.view.PaginationView;
import com.hmk.system.permission.enumeration.StatusEnum;
import com.hmk.system.permission.service.RolePermissionService;
import com.hmk.system.permission.service.UserRoleService;
import com.hmk.system.permission.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    private final UserRoleService userRoleService;

    private final RolePermissionService rolePermissionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody @Valid UserRequest roleRequest) {
        var user = roleRequest.to();
        return UserResponse.from(userService.save(user));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PaginationView<UserResponse> getAll(@RequestParam(name = "name", required = false, defaultValue = "") String name,
                                               @RequestParam(name = "email", required = false, defaultValue = "") String email,
                                               @RequestParam(name = "status", required = false) String status,
                                               @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                               @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        var filter = new UserFilter(name, email, Objects.nonNull(status) ? StatusEnum.valueOf(status) : null);
        var view = userService.findByFilterPageable(filter, page, size);
        return PaginationView.<UserResponse>builder()
                .currentPage(page)
                .totalPages(view.getTotalPages())
                .totalItems(view.getTotalItems())
                .content(view.getContent()
                        .stream()
                        .map(UserResponse::from)
                        .collect(Collectors.toList())
                )
                .build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getById(@PathVariable("id") Long userId) {
        return UserResponse.from(userService.findById(userId));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse update(@PathVariable("id") Long userId, @RequestBody @Valid UserRequest roleRequest) {
        var user = roleRequest.to();
        return UserResponse.from(userService.update(userId, user));
    }

    @PostMapping("/{userId}/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRole(@PathVariable("userId") Long userId, @RequestBody @Valid UserRoleRequest userRoleRequest) {
        userRoleService.save(userId, userRoleRequest.getRoleId());
    }

    @GetMapping("/{userId}/roles")
    @ResponseStatus(HttpStatus.OK)
    public List<RoleResponse> getAllRoles(@PathVariable("userId") Long userId) {
        var roles = userRoleService.findAllRolesByUserId(userId);

        return roles.stream()
                .map(RoleResponse::from)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{userId}/roles/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRole(@PathVariable("userId") Long userId, @PathVariable("roleId") Long roleId) {
        userRoleService.delete(userId, roleId);
    }

    @GetMapping("/{userId}/permissions")
    @ResponseStatus(HttpStatus.OK)
    public List<PermissionResponse> getAllPermissions(@PathVariable("userId") Long userId) {
        var permissions = rolePermissionService.findAllPermissionsByUserId(userId);
        return permissions.stream()
                .map(PermissionResponse::from)
                .collect(Collectors.toList());
    }
}
