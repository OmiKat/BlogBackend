package com.omi.Blog.mapper;

import com.omi.Blog.Enum.PostStatus;
import com.omi.Blog.Model.Dto.CreateTagsRequest;
import com.omi.Blog.Model.Dto.TagsDto;
import com.omi.Blog.Model.Entity.Post;
import com.omi.Blog.Model.Entity.Tags;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagsMapper {

    @Mapping(target = "postCount" , source = "posts" , qualifiedByName = "calculatePostCount")
    TagsDto toDto(Tags tags);

    @Mapping(target = "posts" , ignore = true)
    Tags toEntity(TagsDto tagsDto);

    Tags toEntity(CreateTagsRequest createTagsRequest);



    @Named("calculatePostCount")
    default long calkulatePostKuht(List<Post> posts){
        if(posts == null){
            return 0;
        }
        return posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getPostStatus()))
                .count();
    }
}
