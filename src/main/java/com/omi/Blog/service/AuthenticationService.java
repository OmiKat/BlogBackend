package com.omi.Blog.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    UserDetails authenticate(String email,String password);

    String genToken(UserDetails userDetails);

    UserDetails validateToken(String token);
}
