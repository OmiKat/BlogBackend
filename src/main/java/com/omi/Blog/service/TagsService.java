package com.omi.Blog.service;

import com.omi.Blog.Model.Entity.Tags;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagsService {

    List<Tags> getAll();

    List<Tags> createTag(Set<String> tagNames);

    void deleteTag(UUID id);

    Tags findTagById(UUID id);

//    Tags updateATag(UUID id, Tags tags);
}
