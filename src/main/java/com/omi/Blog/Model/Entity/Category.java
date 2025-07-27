package com.omi.Blog.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id",nullable = false , unique = true)
    private UUID id;

    @Column(name = "name", nullable = false , unique = true)
    private String name;

    @OneToMany
    @JoinColumn(name = "post_id")
    List<Post> posts;
}
