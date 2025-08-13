package com.omi.Blog.service.Impl;

import com.omi.Blog.Model.Entity.Tags;
import com.omi.Blog.Repo.TagsRepo;
import com.omi.Blog.service.TagsService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Override
    public void deleteTag(UUID id) {
        repo.findById(id).ifPresent(tag -> {
            if(!tag.getPosts().isEmpty()){
                throw new IllegalArgumentException(" cannot delete the tag with post");
            }
            else{
                repo.deleteById(id);
            }
        });

//        Optional<Tags> tag = repo.findById(id);
//        if(tag.isPresent()){
//            if(!tag.get().getPosts().isEmpty()){
//                throw new IllegalArgumentException("U cannot delete the tag associated with post 😠");
//            }
//            else{
//                repo.deleteById(id);
//            }
//        }
    }

    @Override
    public Tags findTagById(UUID id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("The tag does not Exist"));
    }

//    @Override
//    public Tags updateATag(UUID id, Tags tags) {
//        Tags existingTag = repo.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("the tag does not exist"));
//
//        existingTag.setName(tags.getName());
//        return repo.save(existingTag);
//
//    }
}


