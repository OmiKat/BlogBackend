package com.omi.Blog.service;

import com.omi.Blog.Model.CreatePostRequest;
import com.omi.Blog.Model.Entity.Post;
import com.omi.Blog.Model.Entity.User;

import java.util.List;
import java.util.UUID;

public interface PostService {

    List<Post> getAllPost(UUID categoryId , UUID tagId);

    List<Post> getDraftPost(User user);

    Post createPost(CreatePostRequest requestPostEntity , User user);

}
