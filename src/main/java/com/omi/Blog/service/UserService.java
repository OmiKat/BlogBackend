package com.omi.Blog.service;


import com.omi.Blog.Model.Entity.User;

import java.util.UUID;

public interface UserService {

    User getUserById(UUID id);

}
