package com.omi.Blog.controller;

import com.omi.Blog.Model.Dto.CategoryDto;
import com.omi.Blog.Model.Dto.CreateCategoryRequest;
import com.omi.Blog.Model.Entity.Category;
import com.omi.Blog.mapper.CategoryMapper;
import com.omi.Blog.service.Impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryServiceImpl service;
    private final CategoryMapper mapper;


    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<Category> categoryEntity = service.getAllCategory();
        List<CategoryDto> categoryDtos = categoryEntity.stream().map(mapper::toDto).toList();
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

//    @PostMapping   //old way
//    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
//        Category category = mapper.toEntity(categoryDto);
//        Category createCategory = service.createACategory(category);
//        CategoryDto dto = mapper.toDto(createCategory);
//        return new ResponseEntity<>(dto,HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CreateCategoryRequest categoryRequest) {
        Category category = mapper.toEntity(categoryRequest);
        Category createdCategory = service.createACategory(category);
        CategoryDto dto = mapper.toDto(createdCategory);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{category-id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("category-id") UUID id){
        service.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
