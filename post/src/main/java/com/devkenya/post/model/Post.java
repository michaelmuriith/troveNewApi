package com.devkenya.post.model;

import com.devkenya.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.net.URI;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "t_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String content;
    private URI image;

    @Column(name = "has_image", nullable = false, columnDefinition = "boolean default false")
    private boolean hasImage;

    @Column(name = "likes_count", nullable = false, columnDefinition = "integer default 0")
    private int likesCount;

    @Column(name = "comments_count", nullable = false, columnDefinition = "integer default 0")
    private int commentsCount;

    @Column(name ="last_comment", nullable = false, columnDefinition = "text default ''")
    private String lastComment;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "boolean default false")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
