package com.omi.Blog.Model.Entity;

import com.omi.Blog.Enum.PostStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "posts")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id" , nullable = false ,unique = true)
    private UUID id;

    @Column(name = "title" , nullable = false)
    private String title;

    @Column(name = "content" , nullable = false)
    private String content;

    @ManyToOne
    private User author;

    @ManyToOne
    private Category category;

    @ManyToMany
    @JoinColumn(name = "tags_id")
    Set<Tags> tags;

    @Column(name = "post_status" , nullable = false)
    private PostStatus postStatus;

    @Column(name = "reading_time" , nullable = false)
    private Integer readingTime;

    @Column(name = "created_at" , nullable = false)
    private LocalDateTime createdAt;

    @Column(name =  "updated_at" , nullable = false)
    private LocalDateTime updatedAt;

}
