package com.devkenya.user.dto.req;

import java.net.URL;
import java.util.List;

public record UserRegisterRequest(
        String openId,

        URL avatar,
        URL businessDocument,
        URL businessLogo,

        String firstName,
        String lastName,
        String phone,
        String email,
        String fcmToken,

        String businessName,
        String businessAddress,
        String businessPhone,

        Boolean isOrganisation,

        Integer type,

        List<String> selectedCategories
) {}
