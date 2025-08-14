package com.omi.Blog.service.Impl;

import com.omi.Blog.Enum.PostStatus;
import com.omi.Blog.Model.Entity.Category;
import com.omi.Blog.Model.Entity.Post;
import com.omi.Blog.Model.Entity.Tags;
import com.omi.Blog.Repo.PostRepo;
import com.omi.Blog.service.CategoryService;
import com.omi.Blog.service.PostService;
import com.omi.Blog.service.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
            return postRepo.findByStatusAndCategoryAndTagsContaining(
                    PostStatus.PUBLISHED,
                    category,
                    tag
            );
        }
        if(categoryId != null){
            Category category = categoryService.findCategoryById(categoryId);
            return postRepo.findByStatusAndCategory(
                    PostStatus.PUBLISHED,
                    category
            );
        }
        if(tagId != null){
            Tags tag = tagsService.findTagById(tagId);
            return postRepo.findByStatusAndTags(
                    PostStatus.PUBLISHED,
                    tag
            );
        }

        return postRepo.findByStatus(PostStatus.PUBLISHED);
    }
}
