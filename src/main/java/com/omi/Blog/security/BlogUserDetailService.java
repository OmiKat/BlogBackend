package com.omi.Blog.security;

import com.omi.Blog.Model.Entity.User;
import com.omi.Blog.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
public class BlogUserDetailService implements UserDetailsService {

    private final UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User name not found with email" + email));
        return new BlogUserDetails(user);
    }
}
