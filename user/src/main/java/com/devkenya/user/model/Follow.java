package com.devkenya.user.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "t_follow")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "follower_id", insertable = false, updatable = false)
    private String followerId;

    @Column(name = "following_id", insertable = false, updatable = false)
    private String followingId;

}
