package com.omi.Blog.controller;


import com.omi.Blog.Model.Dto.CreateTagsRequest;
import com.omi.Blog.Model.Dto.TagsDto;
import com.omi.Blog.Model.Entity.Tags;
import com.omi.Blog.mapper.TagsMapper;
import com.omi.Blog.service.TagsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tags")
public class TagsController {

    private final TagsService service;
    private final TagsMapper mapper;

    @GetMapping
    private ResponseEntity<List<TagsDto>> GetAllTags(){
        List<Tags> tagsList = service.getAll();
        List<TagsDto> tagsDtosList = tagsList.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(tagsDtosList);
    }

    @PostMapping
    private ResponseEntity<List<TagsDto>> createTag(@Valid @RequestBody CreateTagsRequest createTagsRequest){
        List<Tags> savedTags = service.createTag(createTagsRequest.getNames());
        List<TagsDto> tagsDtos = savedTags.stream().map(mapper::toDto).toList();

        return new ResponseEntity<>(tagsDtos, HttpStatus.CREATED);
    }

    @DeleteMapping("/{tag-id}")
    public ResponseEntity<Void> deleteTag(@PathVariable("tag-id")UUID id){
        service.deleteTag(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PutMapping("/{tag-id}")
//    public ResponseEntity<TagsDto> updateTag(@PathVariable("tag-id") UUID id ,@RequestBody TagsDto tagsDto){
//        Tags tags = mapper.toEntity(tagsDto);
//        Tags updatedTag = service.updateATag(id, tags);
//        TagsDto updatedDto = mapper.toDto(updatedTag);
//        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
//    }
}
