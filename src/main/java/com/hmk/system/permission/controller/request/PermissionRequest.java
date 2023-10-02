package com.hmk.system.permission.controller.request;

import com.hmk.system.permission.domain.Permission;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class PermissionRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

    public Permission to() {
        return Permission.builder()
                .name(name)
                .description(description)
                .build();
    }

}
