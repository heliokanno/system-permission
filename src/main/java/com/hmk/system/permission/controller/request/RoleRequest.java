package com.hmk.system.permission.controller.request;

import com.hmk.system.permission.domain.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class RoleRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

    public Role to() {
        return Role.builder()
                .name(name)
                .description(description)
                .build();
    }

}
