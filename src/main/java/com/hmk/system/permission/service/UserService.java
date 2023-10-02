package com.hmk.system.permission.service;

import com.hmk.system.permission.domain.User;
import com.hmk.system.permission.domain.filter.UserFilter;
import com.hmk.system.permission.domain.view.PaginationView;

public interface UserService {

    User save(User user);

    User update(Long userId, User user);

    User findById(Long id);

    PaginationView<User> findByFilterPageable(final UserFilter filter, final int page, final int size);

}
