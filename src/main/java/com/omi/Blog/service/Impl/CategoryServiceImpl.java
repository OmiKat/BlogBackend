package com.omi.Blog.service.Impl;

import com.omi.Blog.Model.Entity.Category;
import com.omi.Blog.Repo.CategoryRepo;
import com.omi.Blog.service.CategoryService;
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


}
