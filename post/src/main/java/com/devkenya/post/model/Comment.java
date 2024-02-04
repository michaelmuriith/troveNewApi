package com.devkenya.post.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.URI;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_comments")
public class Comment {
    @Id
    private UUID id;

    @Column(name = "post_id", nullable = false)
    private String postId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    private String content;
    private URI image;
    private URI gif;
    private URI video;

    @Column(name = "has_video", nullable = false, columnDefinition = "boolean default false")
    private boolean hasVideo;
    @Column(name = "has_gif", columnDefinition = "boolean default false")
    private boolean hasGif;
    @Column(name = "has_image", nullable = false, columnDefinition = "boolean default false")
    private boolean hasImage;


    @Column(name = "likes_count", nullable = false, columnDefinition = "integer default 0")
    private int likesCount;

    @Column(name = "reply_count", nullable = false, columnDefinition = "integer default 0")
    private int commentsCount;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @PrePersist
    protected void onCreate() {
        this.id = UUID.randomUUID();
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

}
