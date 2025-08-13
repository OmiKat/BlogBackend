package com.omi.Blog.service;

import com.omi.Blog.Model.Entity.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {

    List<Post> getAllPost(UUID categoryId , UUID tagId);

}
