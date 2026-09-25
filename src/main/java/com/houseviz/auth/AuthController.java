package com.houseviz.auth;


import com.houseviz.auth.dto.LoginRequest;
import com.houseviz.auth.dto.LoginResponse;
import com.houseviz.auth.dto.RegisterRequest;
import com.houseviz.security.AuthenticatedUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest req) {
        return authService.login(req);
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@Valid @RequestBody RegisterRequest req) {
        return ResponseEntity.status(201).body(authService.register(req));
    }

    /**
     * Эндпоинт для проверки, что токен жив. Требует auth.
     * Фронт может дёргать при загрузке приложения.
     */
    @GetMapping("/me")
    public LoginResponse.UserInfo me(@AuthenticationPrincipal AuthenticatedUser user) {
        // Позже тут можно вернуть свежие данные из БД
        return new LoginResponse.UserInfo(
                user.userId(), user.email(), null, user.role(), user.companyId(), null
        );
    }
}
