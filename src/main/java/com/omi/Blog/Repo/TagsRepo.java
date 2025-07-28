package com.omi.Blog.Repo;

import com.omi.Blog.Model.Entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TagsRepo extends JpaRepository<Tags, UUID> {
}
