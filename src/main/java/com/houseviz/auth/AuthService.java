package com.houseviz.auth;

import com.houseviz.auth.dto.LoginRequest;
import com.houseviz.auth.dto.LoginResponse;
import com.houseviz.auth.dto.RegisterRequest;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    LoginResponse register(RegisterRequest request);
}
