package com.houseviz.auth.dto;

import com.houseviz.user.domain.UserRole;

import java.util.UUID;

public record LoginResponse(
        String accessToken,
        String tokenType,
        long expiresIn,
        UserInfo user
) {
    public record UserInfo(
            UUID id,
            String email,
            String fullName,
            UserRole role,
            UUID companyId,
            String companyName
    ) {}
}
