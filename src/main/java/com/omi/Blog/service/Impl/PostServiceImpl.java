package com.omi.Blog.service.Impl;

import com.omi.Blog.Enum.PostStatus;
import com.omi.Blog.Model.CreatePostRequest;
import com.omi.Blog.Model.Entity.Category;
import com.omi.Blog.Model.Entity.Post;
import com.omi.Blog.Model.Entity.Tags;
import com.omi.Blog.Model.Entity.User;
import com.omi.Blog.Repo.PostRepo;
import com.omi.Blog.service.CategoryService;
import com.omi.Blog.service.PostService;
import com.omi.Blog.service.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;
    private final CategoryService categoryService;
    private final TagsService tagsService;


    @Override
    @Transactional(readOnly = true)
    public List<Post> getAllPost(UUID categoryId, UUID tagId) {
        if(categoryId != null && tagId != null ){
            Category category = categoryService.findCategoryById(categoryId);
            Tags tag = tagsService.findTagById(tagId);
            return postRepo.findByPostStatusAndCategoryAndTagsContaining(
                    PostStatus.PUBLISHED,
                    category,
                    tag
            );
        }
        if(categoryId != null){
            Category category = categoryService.findCategoryById(categoryId);
            return postRepo.findByPostStatusAndCategory(
                    PostStatus.PUBLISHED,
                    category
            );
        }
        if(tagId != null){
            Tags tag = tagsService.findTagById(tagId);
            return postRepo.findByPostStatusAndTags(
                    PostStatus.PUBLISHED,
                    tag
            );
        }

        return postRepo.findByPostStatus(PostStatus.PUBLISHED);
    }

    @Override
    @Transactional
    public Post createPost(CreatePostRequest requestPostEntity, User user) {
        Post newPost = new Post();
        newPost.setTitle(requestPostEntity.getTitle());
        newPost.setContent(requestPostEntity.getContent());
        newPost.setPostStatus(requestPostEntity.getStatus());
        newPost.setReadingTime(1);
        newPost.setAuthor(user);

        Category category = categoryService.findCategoryById(requestPostEntity.getCategoryId());
        newPost.setCategory(category);

        Set<UUID> tagsId = requestPostEntity.getTagsId();
        List<Tags> tags = tagsService.findAllbyId(tagsId);
        newPost.setTags(new HashSet<>(tags));

        return postRepo.save(newPost);
    }

    @Override
    public List<Post> getDraftPost(User user) {
        return postRepo.findAllByAuthorAndPostStatus(
                user,
                PostStatus.DRAFT
        );
    }
}
