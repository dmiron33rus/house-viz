package com.houseviz.security;

import com.houseviz.user.domain.User;
import com.houseviz.user.domain.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class JwtService {

    private final JwtProperties props;
    private final SecretKey key;

    public JwtService(JwtProperties props) {
        this.props = props;
        this.key = Keys.hmacShaKeyFor(props.secret().getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user) {
        Instant now = Instant.now();
        Instant expiry = now.plus(props.accessTokenTtl());

        var builder = Jwts.builder()
                .subject(user.getId().toString())
                .issuer(props.issuer())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiry))
                .claim("email", user.getEmail())
                .claim("role", user.getRole().name());

        if (user.getCompany() != null) {
            builder.claim("companyId", user.getCompany().getId().toString());
        }

        return builder.signWith(key).compact();
    }

    /**
     * Парсит и валидирует токен. Бросает JwtException, если невалиден/просрочен.
     */
    public AuthenticatedUser parse(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .requireIssuer(props.issuer())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        UUID userId = UUID.fromString(claims.getSubject());
        String email = claims.get("email", String.class);
        UserRole role = UserRole.valueOf(claims.get("role", String.class));

        String companyIdStr = claims.get("companyId", String.class);
        UUID companyId = companyIdStr != null ? UUID.fromString(companyIdStr) : null;

        return new AuthenticatedUser(userId, email, role, companyId);
    }
}
