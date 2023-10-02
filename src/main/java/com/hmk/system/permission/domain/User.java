package com.hmk.system.permission.domain;

import com.hmk.system.permission.enumeration.StatusEnum;
import com.hmk.system.permission.exception.ValidationException;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private StatusEnum status;

    public void selfValidation() {
        if (StringUtils.isBlank(this.name)) {
            throw new ValidationException("Name should not be null");
        }
        if (StringUtils.isBlank(this.email)) {
            throw new ValidationException("Email should not be null");
        }
    }
}
