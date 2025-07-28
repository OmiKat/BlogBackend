package com.omi.Blog.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "tags")
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id" , nullable = false , unique = true)
    private UUID id;

    @Column(name = "name" , nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tags")
    List<Post> posts = new ArrayList<>();


}
