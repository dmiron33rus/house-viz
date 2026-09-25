package com.houseviz.security;

import com.houseviz.user.domain.UserRole;

import java.util.UUID;

public record AuthenticatedUser(
        UUID userId,
        String email,
        UserRole role,
        UUID companyId   // null для ADMIN
) {
    public boolean isAdmin() {
        return role == UserRole.ADMIN;
    }

    public boolean belongsTo(UUID otherCompanyId) {
        return companyId != null && companyId.equals(otherCompanyId);
    }
}
