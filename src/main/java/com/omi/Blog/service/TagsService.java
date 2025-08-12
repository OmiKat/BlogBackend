package com.omi.Blog.service;

import com.omi.Blog.Model.Entity.Tags;

import java.util.List;
import java.util.Set;

public interface TagsService {

    List<Tags> getAll();

    List<Tags> createTag(Set<String> tagNames);
}
