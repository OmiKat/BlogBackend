package com.omi.Blog.mapper;

import com.omi.Blog.Model.CreatePostRequest;
import com.omi.Blog.Model.Dto.CreatePostRequestDto;
import com.omi.Blog.Model.Dto.PostDto;
import com.omi.Blog.Model.Entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {


    @Mapping(target = "author" , source = "author")
    @Mapping(target = "category" , source = "category")
    @Mapping(target = "tags" , source = "tags")
    PostDto toDto(Post post);


    @Mapping(target = "author" , source = "author")
    @Mapping(target = "category" , source = "category")
    @Mapping(target = "tags" , source = "tags")
    Post toEntity(PostDto postDto);


    CreatePostRequest toDto(CreatePostRequestDto createPostRequestDto);


}
