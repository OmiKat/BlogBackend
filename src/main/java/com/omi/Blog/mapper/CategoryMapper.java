package com.omi.Blog.mapper;

import com.omi.Blog.Enum.PostStatus;
import com.omi.Blog.Model.Dto.CategoryDto;
import com.omi.Blog.Model.Dto.CreateCategoryRequest;
import com.omi.Blog.Model.Entity.Category;
import com.omi.Blog.Model.Entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {


    @Mapping(target = "postCount" , source = "posts" , qualifiedByName = "calculatePostCount")
    CategoryDto toDto(Category category);

    @Mapping(target = "posts", ignore = true)
    Category toEntity(CategoryDto categoryDto);

    Category toEntity(CreateCategoryRequest categoryRequest);  


    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts){
        if(posts == null){
            return 0;
        }
        return posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getPostStatus()))
                .count();
    }
}
