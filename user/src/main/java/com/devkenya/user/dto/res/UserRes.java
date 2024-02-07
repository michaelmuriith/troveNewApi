package com.devkenya.user.dto.res;

import com.devkenya.user.model.User;

public record UserRes(
        int code,
        String msg,
        User data
) {}
