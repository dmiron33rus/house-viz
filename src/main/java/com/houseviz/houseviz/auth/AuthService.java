package com.houseviz.houseviz.auth;

import com.houseviz.houseviz.auth.dto.LoginRequest;
import com.houseviz.houseviz.auth.dto.LoginResponse;
import com.houseviz.houseviz.auth.dto.RegisterRequest;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    LoginResponse register(RegisterRequest request);
}
