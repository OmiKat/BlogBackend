package com.omi.Blog.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id" , nullable = false , unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email" , nullable = false , unique = true)
    private String email;

    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "created_at" , nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "author" , cascade = CascadeType.ALL , orphanRemoval = true)  //it will search for author in post class
    List<Post> posts = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(createdAt, user.createdAt) && Objects.equals(posts, user.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, createdAt, posts);
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }



}
