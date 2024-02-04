package com.devkenya.user.model;

import lombok.*;

import javax.persistence.*;
import java.net.URL;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "t_users")
public class User {
    @Id
    private String id;

    private URL avatar;
    private URL businessDocument;
    private URL businessLogo;

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
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
}
