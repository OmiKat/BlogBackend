package com.omi.Blog.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id" , nullable = false , unique = true)
    UUID id;

    @Column(name = "name" , nullable = false)
    String name;

    @ManyToMany
    @JoinColumn(name = "post_id")
    List<Post> posts;


}
