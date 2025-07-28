package com.omi.Blog.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
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

    @OneToMany(mappedBy = "category" )
    List<Post> posts = new ArrayList<>();
}
