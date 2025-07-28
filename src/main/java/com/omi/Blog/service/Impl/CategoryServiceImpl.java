package com.omi.Blog.service.Impl;

import com.omi.Blog.Model.Entity.Category;
import com.omi.Blog.Repo.CategoryRepo;
import com.omi.Blog.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //this guy will make constructor for injection :P
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo repo;

    @Override
    public List<Category> getAllCategory() {
        return repo.findAllWithPostCount();
    }

    @Transactional
    @Override
    public Category createACategory(Category category) {
        String categoryName = category.getName();
        if(repo.existsByNameIgnoreCase(categoryName)){
            throw new IllegalArgumentException("The Category already exist" + categoryName);
        }
        return repo.save(category);
    }
}
