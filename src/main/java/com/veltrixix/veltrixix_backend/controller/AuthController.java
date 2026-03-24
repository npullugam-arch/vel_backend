package com.veltrixix.veltrixix_backend.controller;

import com.veltrixix.veltrixix_backend.dto.auth.ChangePasswordRequest;
import com.veltrixix.veltrixix_backend.dto.auth.LoginRequest;
import com.veltrixix.veltrixix_backend.dto.auth.LoginResponse;
import com.veltrixix.veltrixix_backend.dto.auth.RegisterAdminRequest;
import com.veltrixix.veltrixix_backend.dto.common.ApiResponse;
import com.veltrixix.veltrixix_backend.dto.common.MessageResponse;
import com.veltrixix.veltrixix_backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.success("Login successful", authService.login(request));
    }

    @PostMapping("/register-admin")
    public ApiResponse<MessageResponse> registerAdmin(@Valid @RequestBody RegisterAdminRequest request) {
        String message = authService.registerAdmin(request);
        return ApiResponse.success(message, MessageResponse.of(message));
    }

    @PatchMapping("/change-password/{adminUserId}")
    public ApiResponse<MessageResponse> changePassword(
            @PathVariable Long adminUserId,
            @Valid @RequestBody ChangePasswordRequest request
    ) {
        String message = authService.changePassword(adminUserId, request);
        return ApiResponse.success(message, MessageResponse.of(message));
    }
}