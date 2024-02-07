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
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    private String content;
    private URI image;
    private URI video;
    private URI gif;

    @Column(name = "has_video", nullable = false, columnDefinition = "boolean default false")
    private boolean hasVideo;

    @Column(name = "has_gif", nullable = false, columnDefinition = "boolean default false")
    private boolean hasGif;


    @Column(name = "has_image", nullable = false, columnDefinition = "boolean default false")
    private boolean hasImage;

    @Column(name = "likes_count", nullable = false, columnDefinition = "integer default 0")
    private int likesCount;

    @Column(name = "comments_count", nullable = false, columnDefinition = "integer default 0")
    private int commentsCount;


    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "boolean default false")
    private boolean isDeleted;

    @PrePersist
    public void prePersist() {
        createdAt = new Timestamp(System.currentTimeMillis());
        updatedAt = new Timestamp(System.currentTimeMillis());
        isDeleted = false;
        likesCount = 0;
        commentsCount = 0;
        id = UUID.randomUUID();
        if (this.isDeleted) {
            throw new IllegalArgumentException("Cannot create a deleted post");
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreRemove
    public void preRemove() {
        isDeleted = true;
    }

    @PostLoad
    public void postLoad() {
        if (isDeleted) {
            throw new IllegalArgumentException("Cannot load a deleted post");
        }
    }
}
