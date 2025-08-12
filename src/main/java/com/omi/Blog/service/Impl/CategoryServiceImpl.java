package com.omi.Blog.service.Impl;

import com.omi.Blog.Model.Dto.CategoryDto;
import com.omi.Blog.Model.Entity.Category;
import com.omi.Blog.Repo.CategoryRepo;
import com.omi.Blog.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public void deleteCategory(UUID id){
        Optional<Category> category = repo.findById(id);
        if(category.isPresent()){
            if(!category.get().getPosts().isEmpty()){
                throw new IllegalStateException("Category has Post Associated with it");
            }
            else{
                repo.deleteById(id);
            }
        }
    }

    @Override
    public Category updateCategory(Category category, UUID id) {
        Category existingCategory = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category does not exist"));

        existingCategory.setName(category.getName());

        return repo.save(category);

    }
}
