package com.omi.Blog.service.Impl;

import com.omi.Blog.Model.Entity.User;
import com.omi.Blog.Repo.UserRepo;
import com.omi.Blog.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo repo;

    @Override
    public User getUserById(UUID id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("entity not found with id : " + id));
    }
}
