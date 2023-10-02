package com.hmk.system.permission.controller;

import com.hmk.system.permission.controller.request.PermissionRequest;
import com.hmk.system.permission.controller.response.PermissionResponse;
import com.hmk.system.permission.domain.filter.PermissionFilter;
import com.hmk.system.permission.domain.view.PaginationView;
import com.hmk.system.permission.service.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1/permissions")
@RequiredArgsConstructor
public class PermissionResource {

    private final PermissionService permissionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PermissionResponse create(@RequestBody @Valid PermissionRequest permissionRequest) {
        var permission = permissionRequest.to();
        return PermissionResponse.from(permissionService.save(permission));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PaginationView<PermissionResponse> getAll(@RequestParam(name = "name", required = false, defaultValue = "") String name,
                                                     @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                     @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        var filter = new PermissionFilter(name);
        var view = permissionService.findByFilterPageable(filter, page, size);
        return PaginationView.<PermissionResponse>builder()
                .currentPage(page)
                .totalPages(view.getTotalPages())
                .totalItems(view.getTotalItems())
                .content(view.getContent()
                        .stream()
                        .map(PermissionResponse::from)
                        .collect(Collectors.toList())
                )
                .build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PermissionResponse getById(@PathVariable("id") Long permissionId) {
        return PermissionResponse.from(permissionService.findById(permissionId));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PermissionResponse update(@PathVariable("id") Long permissionId, @RequestBody @Valid PermissionRequest permissionRequest) {
        var role = permissionRequest.to();
        return PermissionResponse.from(permissionService.update(permissionId, role));
    }
}
