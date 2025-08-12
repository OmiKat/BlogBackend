package com.omi.Blog.Repo;

import com.omi.Blog.Model.Entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TagsRepo extends JpaRepository<Tags, UUID> {

    @Query("SELECT c FROM Tags c LEFT JOIN FETCH c.posts")
    List<Tags> findAllWithPostCount();

    List<Tags> findByNameIn(Set<String> name);

    boolean existsByNameIgnoreCase(String name);

}
