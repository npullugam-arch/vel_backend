package com.veltrixix.veltrixix_backend.service;

import com.veltrixix.veltrixix_backend.dto.admin.AdminStatusUpdateRequest;
import com.veltrixix.veltrixix_backend.dto.admin.AdminUserRequest;
import com.veltrixix.veltrixix_backend.dto.admin.AdminUserResponse;

import java.util.List;

public interface AdminUserService {

    AdminUserResponse createAdmin(AdminUserRequest request);

    List<AdminUserResponse> getAllAdmins();

    AdminUserResponse getAdminById(Long id);

    AdminUserResponse updateAdmin(Long id, AdminUserRequest request);

    void updateAdminStatus(Long id, AdminStatusUpdateRequest request);

    void deleteAdmin(Long id);
}