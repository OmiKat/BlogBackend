package com.omi.Blog.service;

import com.omi.Blog.Model.Dto.CategoryDto;
import com.omi.Blog.Model.Entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> getAllCategory();

    Category createACategory(Category category);

    void deleteCategory(UUID id);

    Category updateCategory(Category category, UUID id);

    Category findCategoryById(UUID id);
}
