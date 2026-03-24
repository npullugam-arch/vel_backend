package com.veltrixix.veltrixix_backend.service;

import com.veltrixix.veltrixix_backend.dto.auth.ChangePasswordRequest;
import com.veltrixix.veltrixix_backend.dto.auth.LoginRequest;
import com.veltrixix.veltrixix_backend.dto.auth.LoginResponse;
import com.veltrixix.veltrixix_backend.dto.auth.RegisterAdminRequest;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    String registerAdmin(RegisterAdminRequest request);

    String changePassword(Long adminUserId, ChangePasswordRequest request);
}