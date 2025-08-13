package com.omi.Blog.controller;

import com.omi.Blog.Model.Dto.PostDto;
import com.omi.Blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @GetMapping
    public ResponseEntity<List<PostDto>> GetAllPost(@RequestParam(required = false)UUID categoryId,
                                                    @RequestParam(required = false)UUID tagId){

    }

}
