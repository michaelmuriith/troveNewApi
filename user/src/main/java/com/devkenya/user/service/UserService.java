package com.devkenya.user.service;

import com.devkenya.clients.user.UserResponse;
import com.devkenya.user.dto.req.UserRegisterRequest;
import com.devkenya.user.model.LoginType;
import com.devkenya.user.model.User;
import com.devkenya.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User LoginUser(UserRegisterRequest registerRequest) {
        if (registerRequest.type() == 2) {
            //get user by id if it doesn't exist create new user
            User user = userRepository.findByEmail(registerRequest.email()).orElse(null);
            if (user == null) {
                return registerUser(registerRequest);
            }

        }
        return userRepository.findByEmail(registerRequest.email()).orElseThrow(() -> new IllegalStateException("User not found"));
    }

    public User getUser(String id) {
        return userRepository.findById(UUID.fromString(id)).orElseThrow(() -> new IllegalStateException("User not found"));
    }

    //get all users other than the current user and check if current has followed them
    public List<User> getUsers() {
        log.info("Getting users");
        return userRepository.findAllByIdNot(UUID.randomUUID());
    }

    public User registerUser(UserRegisterRequest registerRequest) {
        try {
            log.info("Registering user with openId: {}", registerRequest.openId());

            // Check if user already exists
            if (userRepository.existsByOpenId(registerRequest.openId())) {
                throw new IllegalStateException("User already exists");
            }

            // Check if email already exists
            if (userRepository.existsByEmail(registerRequest.email())) {
                throw new IllegalStateException("Email already exists");
            }

            // Check organization-specific requirements
            if (Boolean.TRUE.equals(registerRequest.isOrganisation())) {
                validateOrganizationUser(registerRequest);
            } else {
                validateNonOrganizationUser(registerRequest);
            }

            // Create user object
            User user = createUserFromRequest(registerRequest);

            log.info("User created: {}", user);

            // Save user to the database
            userRepository.save(user);

            log.info("User with openId: {} registered successfully", registerRequest.openId());

            return user;
        } catch (Exception e) {
            log.error("Error registering user", e);
            throw new IllegalStateException(e.getMessage());
        }
    }

    private void validateOrganizationUser(UserRegisterRequest registerRequest) {
        requireNonNull(registerRequest.businessName(), "Business name is required");
        requireNonNull(registerRequest.businessAddress(), "Business address is required");
        requireNonNull(registerRequest.businessPhone(), "Business phone is required");
    }

    private void validateNonOrganizationUser(UserRegisterRequest registerRequest) {
        requireNonNull(registerRequest.firstName(), "First name is required");
        requireNonNull(registerRequest.lastName(), "Last name is required");
    }

    private User createUserFromRequest(UserRegisterRequest registerRequest) {
        //generate access token
        var accessToken = registerRequest.openId() + System.currentTimeMillis();

        User.UserBuilder userBuilder = User.builder().openId(registerRequest.openId()).fcmToken(registerRequest.fcmToken()).isOnline(true).isBlocked(false).isDeleted(false).isAdmin(false).accessToken(accessToken).token(accessToken).type(LoginType.fromValue(registerRequest.type()));

        if (Boolean.TRUE.equals(registerRequest.isOrganisation())) {
            userBuilder.businessDocument(registerRequest.businessDocument()).businessLogo(registerRequest.businessLogo()).businessName(registerRequest.businessName()).businessAddress(registerRequest.businessAddress()).businessPhone(registerRequest.businessPhone()).isOrganisation(true).isApproved(false);
        } else {
            userBuilder.avatar(registerRequest.avatar()).firstName(registerRequest.firstName()).lastName(registerRequest.lastName()).email(registerRequest.email()).phone(registerRequest.phone()).selectedCategories(registerRequest.selectedCategories());
        }

        return userBuilder.build();
    }

    private void requireNonNull(Object value, String errorMessage) {
        if (value == null) {
            throw new IllegalStateException(errorMessage);
        }
    }

    public List<UserResponse> getUsersByIds(List<String> ids) {
        //convert ths string ids to UUID
        List<UUID> uuids = ids.stream().map(UUID::fromString).toList();
        List<User> users = userRepository.findAllById(uuids);

        return users.stream().map(user -> new UserResponse(user.getId(), user.getBusinessLogo(), user.getAvatar(), user.getBusinessName(), user.getFirstName(), user.getLastName(), user.getPhone(), user.getEmail(), user.getFcmToken(), user.getToken(), user.getAccessToken(), user.getDescription(), user.isOrganisation(), user.isOnline(), user.isApproved(), user.isBlocked())).toList();

    }

    public UserResponse getUserById(String userId) {
        User user = userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new IllegalStateException("User not found"));

        return new UserResponse(user.getId(), user.getBusinessLogo(), user.getAvatar(), user.getBusinessName(), user.getFirstName(), user.getLastName(), user.getPhone(), user.getEmail(), user.getFcmToken(), user.getToken(), user.getAccessToken(), user.getDescription(), user.isOrganisation(), user.isOnline(), user.isApproved(), user.isBlocked());
    }
}
