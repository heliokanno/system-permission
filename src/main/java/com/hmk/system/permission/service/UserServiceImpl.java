package com.hmk.system.permission.service;

import com.hmk.system.permission.converter.UserEntityConverter;
import com.hmk.system.permission.domain.User;
import com.hmk.system.permission.domain.filter.UserFilter;
import com.hmk.system.permission.domain.view.PaginationView;
import com.hmk.system.permission.exception.NotFoundException;
import com.hmk.system.permission.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User save(User user) {
        user.selfValidation();
        var userEntity = UserEntityConverter.from(user);
        repository.save(userEntity);
        return UserEntityConverter.to(userEntity);
    }

    @Override
    public User update(Long userId, User user) {
        user.selfValidation();
        var savedUser = repository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        savedUser.setName(user.getName());
        savedUser.setEmail(user.getEmail());
        savedUser.setStatus(user.getStatus());
        return UserEntityConverter.to(savedUser);
    }

    @Override
    public User findById(Long id) {
        var userEntity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return UserEntityConverter.to(userEntity);
    }

    @Override
    public PaginationView<User> findByFilterPageable(final UserFilter filter, final int page, final int size) {
        var pageable = PageRequest.of(page, size, Sort.by("name"));

        var pageView = repository.findAllByNameContainsOrEmailContainsOrStatusEquals(filter.name(), filter.email(), filter.status(), pageable);

        return PaginationView.<User>builder()
                .currentPage(page)
                .totalPages(pageView.getTotalPages())
                .totalItems(pageView.getTotalElements())
                .content(pageView.isEmpty() ? Collections.emptyList() : pageView.getContent()
                        .stream()
                        .map(UserEntityConverter::to)
                        .collect(Collectors.toList()))
                .build();
    }

}
