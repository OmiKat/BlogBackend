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

import java.time.LocalDateTime;
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
    public Post createPost(Post requestPostEntity) {
//        Post build = Post.builder()
//                .title(requestPostEntity.getTitle())
//                .content(requestPostEntity.getContent())
//                .author(requestPostEntity.getAuthor())
//                .category(requestPostEntity.getCategory())
//                .tags(requestPostEntity.getTags())
//                .readingTime(1)
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
//                .postStatus(requestPostEntity.getPostStatus())
//                .build();
        return postRepo.save(requestPostEntity);
    }
}
