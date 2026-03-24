package com.veltrixix.veltrixix_backend.service.impl;

import com.veltrixix.veltrixix_backend.dto.admin.AdminStatusUpdateRequest;
import com.veltrixix.veltrixix_backend.dto.admin.AdminUserRequest;
import com.veltrixix.veltrixix_backend.dto.admin.AdminUserResponse;
import com.veltrixix.veltrixix_backend.entity.AdminUser;
import com.veltrixix.veltrixix_backend.enums.Role;
import com.veltrixix.veltrixix_backend.exception.BadRequestException;
import com.veltrixix.veltrixix_backend.exception.DuplicateResourceException;
import com.veltrixix.veltrixix_backend.exception.ResourceNotFoundException;
import com.veltrixix.veltrixix_backend.mapper.AdminUserMapper;
import com.veltrixix.veltrixix_backend.repository.AdminUserRepository;
import com.veltrixix.veltrixix_backend.service.AdminUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminUserRepository adminUserRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AdminUserServiceImpl(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }

    @Override
    public AdminUserResponse createAdmin(AdminUserRequest request) {
        if (adminUserRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Admin email already exists");
        }

        AdminUser adminUser = new AdminUser();
        adminUser.setName(request.getName());
        adminUser.setEmail(request.getEmail());
        adminUser.setPassword(passwordEncoder.encode(request.getPassword()));
        adminUser.setRole(parseRole(request.getRole()));
        adminUser.setActive(request.getActive() != null ? request.getActive() : true);

        return AdminUserMapper.toResponse(adminUserRepository.save(adminUser));
    }

    @Override
    public List<AdminUserResponse> getAllAdmins() {
        return adminUserRepository.findAll()
                .stream()
                .map(AdminUserMapper::toResponse)
                .toList();
    }

    @Override
    public AdminUserResponse getAdminById(Long id) {
        AdminUser adminUser = adminUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
        return AdminUserMapper.toResponse(adminUser);
    }

    @Override
    public AdminUserResponse updateAdmin(Long id, AdminUserRequest request) {
        AdminUser adminUser = adminUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));

        if (!adminUser.getEmail().equalsIgnoreCase(request.getEmail())
                && adminUserRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Admin email already exists");
        }

        adminUser.setName(request.getName());
        adminUser.setEmail(request.getEmail());
        adminUser.setRole(parseRole(request.getRole()));

        if (request.getPassword() != null && !request.getPassword().trim().isEmpty()) {
            adminUser.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getActive() != null) {
            adminUser.setActive(request.getActive());
        }

        return AdminUserMapper.toResponse(adminUserRepository.save(adminUser));
    }

    @Override
    public void updateAdminStatus(Long id, AdminStatusUpdateRequest request) {
        AdminUser adminUser = adminUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));

        adminUser.setActive(request.getActive());
        adminUserRepository.save(adminUser);
    }

    @Override
    public void deleteAdmin(Long id) {
        AdminUser adminUser = adminUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
        adminUserRepository.delete(adminUser);
    }

    private Role parseRole(String role) {
        try {
            return Role.valueOf(role.trim().toUpperCase());
        } catch (Exception ex) {
            throw new BadRequestException("Invalid role value");
        }
    }
}