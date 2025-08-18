package com.omi.Blog.controller;

import com.omi.Blog.Model.CreatePostRequest;
import com.omi.Blog.Model.Dto.CreatePostRequestDto;
import com.omi.Blog.Model.Dto.PostDto;
import com.omi.Blog.Model.Entity.Post;
import com.omi.Blog.Model.Entity.User;
import com.omi.Blog.mapper.PostMapper;
import com.omi.Blog.service.PostService;
import com.omi.Blog.service.UserService;
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
    private final UserService userService;

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
    public ResponseEntity<PostDto> CreatePost(@RequestBody CreatePostRequestDto postDto, @RequestAttribute UUID userId){
        User user = userService.getUserById(userId);
        CreatePostRequest requestPost = mapper.toDto(postDto);
        Post createdPost = service.createPost(requestPost , user);
        PostDto createdPostDto = mapper.toDto(createdPost);
        return new ResponseEntity<>(createdPostDto,HttpStatus.CREATED);
    }


    @GetMapping("/drafts")
    public ResponseEntity<List<PostDto>> GetDraftPosts(@RequestAttribute UUID userId){
        User userById = userService.getUserById(userId);
        List<Post> draftPost = service.getDraftPost(userById);
        List<PostDto> draftPostListDto = draftPost.stream().map(mapper::toDto).toList();
        return new ResponseEntity<>(draftPostListDto,HttpStatus.OK);

    }



}
