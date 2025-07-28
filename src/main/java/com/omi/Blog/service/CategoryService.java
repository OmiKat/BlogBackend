package com.omi.Blog.service;

import com.omi.Blog.Model.Entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategory();

    Category createACategory(Category category);
}
