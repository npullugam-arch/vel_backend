package com.veltrixix.veltrixix_backend.service.impl;

import com.veltrixix.veltrixix_backend.dto.auth.ChangePasswordRequest;
import com.veltrixix.veltrixix_backend.dto.auth.LoginRequest;
import com.veltrixix.veltrixix_backend.dto.auth.LoginResponse;
import com.veltrixix.veltrixix_backend.dto.auth.RegisterAdminRequest;
import com.veltrixix.veltrixix_backend.entity.AdminUser;
import com.veltrixix.veltrixix_backend.enums.Role;
import com.veltrixix.veltrixix_backend.exception.BadRequestException;
import com.veltrixix.veltrixix_backend.exception.DuplicateResourceException;
import com.veltrixix.veltrixix_backend.exception.ResourceNotFoundException;
import com.veltrixix.veltrixix_backend.repository.AdminUserRepository;
import com.veltrixix.veltrixix_backend.security.CustomUserDetails;
import com.veltrixix.veltrixix_backend.security.JwtService;
import com.veltrixix.veltrixix_backend.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(
            AdminUserRepository adminUserRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        String email = normalizeEmail(request.getEmail());
        String password = request.getPassword() != null ? request.getPassword().trim() : "";

        AdminUser adminUser = adminUserRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));

        if (Boolean.FALSE.equals(adminUser.getActive())) {
            throw new BadRequestException("Admin account is inactive");
        }

        if (!passwordEncoder.matches(password, adminUser.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        CustomUserDetails userDetails = new CustomUserDetails(adminUser);
        String token = jwtService.generateToken(userDetails);

        return new LoginResponse(
                token,
                adminUser.getId(),
                adminUser.getName(),
                adminUser.getEmail(),
                adminUser.getRole().name()
        );
    }

    @Override
    public String registerAdmin(RegisterAdminRequest request) {
        String email = normalizeEmail(request.getEmail());

        if (adminUserRepository.existsByEmail(email)) {
            throw new DuplicateResourceException("Admin email already exists");
        }

        AdminUser adminUser = new AdminUser();
        adminUser.setName(request.getName() != null ? request.getName().trim() : null);
        adminUser.setEmail(email);
        adminUser.setPassword(passwordEncoder.encode(request.getPassword().trim()));
        adminUser.setRole(parseRole(request.getRole()));
        adminUser.setActive(true);

        adminUserRepository.save(adminUser);
        return "Admin registered successfully";
    }

    @Override
    public String changePassword(Long adminUserId, ChangePasswordRequest request) {
        AdminUser adminUser = adminUserRepository.findById(adminUserId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));

        String currentPassword = request.getCurrentPassword() != null ? request.getCurrentPassword().trim() : "";
        String newPassword = request.getNewPassword() != null ? request.getNewPassword().trim() : "";

        if (!passwordEncoder.matches(currentPassword, adminUser.getPassword())) {
            throw new BadRequestException("Current password is incorrect");
        }

        adminUser.setPassword(passwordEncoder.encode(newPassword));
        adminUserRepository.save(adminUser);

        return "Password changed successfully";
    }

    private Role parseRole(String role) {
        try {
            return Role.valueOf(role.trim().toUpperCase());
        } catch (Exception ex) {
            throw new BadRequestException("Invalid role value");
        }
    }

    private String normalizeEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new BadRequestException("Email is required");
        }
        return email.trim().toLowerCase();
    }
}