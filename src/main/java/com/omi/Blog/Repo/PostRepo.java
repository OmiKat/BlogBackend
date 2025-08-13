package com.omi.Blog.Repo;

import com.omi.Blog.Enum.PostStatus;
import com.omi.Blog.Model.Entity.Category;
import com.omi.Blog.Model.Entity.Post;
import com.omi.Blog.Model.Entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepo extends JpaRepository<Post, UUID> {

    List<Post> findByStatusAndCategoryAndTagsContaining(PostStatus postStatus , Category category , Tags tags);

}
