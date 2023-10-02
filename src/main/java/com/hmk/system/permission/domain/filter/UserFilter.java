package com.hmk.system.permission.domain.filter;

import com.hmk.system.permission.enumeration.StatusEnum;

public record UserFilter(String name, String email, StatusEnum status) {
}
