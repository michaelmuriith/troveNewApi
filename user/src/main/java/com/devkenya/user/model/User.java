package com.devkenya.user.model;

import lombok.*;

import javax.persistence.*;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "t_users")
public class User {
    @Id
    private UUID id;

    private URL avatar;
    private URL businessDocument;
    private URL businessLogo;

    private String firstName;
    private String lastName;
    private String phone;

    @Column(unique = true)
    private String email;
    private String openId;
    private String fcmToken;
    private String token;
    private String accessToken;
    private String description;

    private String businessName;
    private String businessAddress;
    private String businessPhone;


    private boolean isOrganisation;
    private boolean isOnline;
    private boolean isApproved;

    @Enumerated(EnumType.ORDINAL)
    private LoginType type;

    @ElementCollection
    private List<String> selectedCategories;

    @Column(name = "is_blocked", nullable = false, columnDefinition = "boolean default false")
    private boolean isBlocked;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "boolean default false")
    private boolean isDeleted;

    @Column(name = "is_admin", nullable = false, columnDefinition = "boolean default false")
    private boolean isAdmin;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @PrePersist
    public void prePersist() {
        id = UUID.randomUUID();
        createdAt = new Timestamp(System.currentTimeMillis());
        updatedAt = new Timestamp(System.currentTimeMillis());
        isDeleted = false;
        isBlocked = false;
        isAdmin = false;
        isOnline = false;
        isApproved = false;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreRemove
    public void preRemove() {
        isDeleted = true;
    }
}
