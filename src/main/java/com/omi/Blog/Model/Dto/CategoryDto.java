package com.omi.Blog.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private UUID id;
    private String name;
    private Long postCount;
}
