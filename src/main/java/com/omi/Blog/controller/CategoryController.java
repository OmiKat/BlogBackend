package com.omi.Blog.controller;

import com.omi.Blog.Model.Dto.CategoryDto;
import com.omi.Blog.Model.Entity.Category;
import com.omi.Blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<Category> categoryEntity = service.getAllCategory();
        List<CategoryDto> categoryDtos = categoryEntity.stream().map()
    }

}
