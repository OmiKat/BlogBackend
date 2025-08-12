package com.omi.Blog.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

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
    @Column(nullable = false , unique = true)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Post> posts = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tags tags = (Tags) o;
        return Objects.equals(id, tags.id) && Objects.equals(name, tags.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
