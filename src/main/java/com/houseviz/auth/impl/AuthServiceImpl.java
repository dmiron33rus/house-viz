package com.houseviz.auth.impl;

import com.houseviz.auth.AuthService;
import com.houseviz.auth.EmailAlreadyUsedException;
import com.houseviz.auth.InvalidCredentialsException;
import com.houseviz.auth.dto.LoginRequest;
import com.houseviz.auth.dto.LoginResponse;
import com.houseviz.auth.dto.RegisterRequest;
import com.houseviz.security.JwtProperties;
import com.houseviz.security.JwtService;
import com.houseviz.user.domain.Company;
import com.houseviz.user.domain.User;
import com.houseviz.user.domain.UserRole;
import com.houseviz.user.repository.CompanyRepository;
import com.houseviz.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepo;
    private final CompanyRepository companyRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final JwtProperties jwtProps;

    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest req) {
        User user = userRepo.findByEmailWithCompany(req.email())
                .orElseThrow(InvalidCredentialsException::new);

        if (!user.isActive()) {
            throw new InvalidCredentialsException();
        }

        if (!passwordEncoder.matches(req.password(), user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        return buildResponse(user);
    }

    @Override
    @Transactional
    public LoginResponse register(RegisterRequest req) {
        if (userRepo.existsByEmail(req.email())) {
            throw new EmailAlreadyUsedException(req.email());
        }

        Company company = companyRepo.save(Company.builder()
                .name(req.companyName())
                .active(true)
                .build());

        User user = userRepo.save(User.builder()
                .email(req.email())
                .passwordHash(passwordEncoder.encode(req.password()))
                .fullName(req.fullName())
                .phone(req.phone())
                .role(UserRole.MANAGER)   // регистрация создаёт менеджера компании
                .active(true)
                .company(company)
                .build());

        return buildResponse(user);
    }

    private LoginResponse buildResponse(User user) {
        String token = jwtService.generateAccessToken(user);
        // TTL берём из того же источника, что и при генерации — см. ниже
        long expiresIn = jwtProps.accessTokenTtl().getSeconds();

        LoginResponse.UserInfo info = new LoginResponse.UserInfo(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getRole(),
                user.getCompany() != null ? user.getCompany().getId() : null,
                user.getCompany() != null ? user.getCompany().getName() : null
        );

        return new LoginResponse(token, "Bearer", expiresIn, info);
    }
}
