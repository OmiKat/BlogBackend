package com.omi.Blog.Model.Dto;

import com.omi.Blog.Enum.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePostRequestDto {

    @NotBlank(message = "the title should not be blank")
    private String title;

    @NotBlank(message = "the message should not be blank")
    @Size(min = 3 , max = 100 , message = "the content should be between {min} and {max}")
    private String content;

    @NotBlank(message = "category Id is required")
    private UUID categoryId;

    @Builder.Default
    @Size(max = 10 , message = "only {max} tags are allowed")
    private Set<UUID> tagsId = new HashSet<>();

    @NotBlank(message = "PostStatus is required")
    private PostStatus status;


}
