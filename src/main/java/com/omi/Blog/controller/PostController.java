package com.omi.Blog.controller;

import com.omi.Blog.Model.Dto.PostDto;
import com.omi.Blog.Model.Entity.Post;
import com.omi.Blog.mapper.PostMapper;
import com.omi.Blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService service;
    private final PostMapper mapper;

    @GetMapping
    public ResponseEntity<List<PostDto>> GetAllPost(@RequestParam(required = false)UUID categoryId,
                                                    @RequestParam(required = false)UUID tagId){

        List<Post> allPost = service.getAllPost(categoryId, tagId);

        List<PostDto> allPostDto = allPost
                .stream()
                .map(mapper::toDto)
                .toList();
        return new ResponseEntity<>(allPostDto, HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<PostDto> CreatePost(@RequestBody PostDto postDto){
        Post requestPostEntity = mapper.toEntity(postDto);
        Post createdPost = service.createPost(requestPostEntity);
        PostDto createdPostDto = mapper.toDto(createdPost);
        return new ResponseEntity<>(createdPostDto,HttpStatus.CREATED);
    }

}
