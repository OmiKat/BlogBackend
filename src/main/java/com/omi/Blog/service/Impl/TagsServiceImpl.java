package com.omi.Blog.service.Impl;

import com.omi.Blog.Model.Entity.Tags;
import com.omi.Blog.Repo.TagsRepo;
import com.omi.Blog.service.TagsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagsServiceImpl implements TagsService {

    private final TagsRepo repo;

    @Override
    public List<Tags> getAll() {
        return repo.findAllWithPostCount();
    }

    @Transactional
    @Override
    public List<Tags> createTag(Set<String> tagNames) {
        List<Tags> existingTags = repo.findByNameIn(tagNames);
        Set<String> existingTagNamesSet = existingTags.stream()
                .map(Tags::getName)
                .collect(Collectors.toSet());

        List<Tags> newTags = tagNames.stream()
                .filter(name -> !existingTagNamesSet.contains(name))
                .map(name -> Tags.builder()
                        .name(name)
                        .posts(new HashSet<>())
                        .build())
                .toList();
        List<Tags> savedTags = new ArrayList<>();
        if(!newTags.isEmpty()){
            savedTags = repo.saveAll(newTags);
        }
        savedTags.addAll(existingTags);
        return savedTags;
    }

}


