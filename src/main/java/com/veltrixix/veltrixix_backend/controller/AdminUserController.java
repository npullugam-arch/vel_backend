package com.veltrixix.veltrixix_backend.controller;

import com.veltrixix.veltrixix_backend.dto.admin.AdminStatusUpdateRequest;
import com.veltrixix.veltrixix_backend.dto.admin.AdminUserRequest;
import com.veltrixix.veltrixix_backend.dto.admin.AdminUserResponse;
import com.veltrixix.veltrixix_backend.dto.common.ApiResponse;
import com.veltrixix.veltrixix_backend.dto.common.MessageResponse;
import com.veltrixix.veltrixix_backend.service.AdminUserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin-users")
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @PostMapping
    public ApiResponse<AdminUserResponse> createAdmin(@Valid @RequestBody AdminUserRequest request) {
        return ApiResponse.success("Admin created successfully", adminUserService.createAdmin(request));
    }

    @GetMapping
    public ApiResponse<List<AdminUserResponse>> getAllAdmins() {
        return ApiResponse.success(adminUserService.getAllAdmins());
    }

    @GetMapping("/{id}")
    public ApiResponse<AdminUserResponse> getAdminById(@PathVariable Long id) {
        return ApiResponse.success(adminUserService.getAdminById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<AdminUserResponse> updateAdmin(
            @PathVariable Long id,
            @Valid @RequestBody AdminUserRequest request
    ) {
        return ApiResponse.success("Admin updated successfully", adminUserService.updateAdmin(id, request));
    }

    @PatchMapping("/{id}/status")
    public ApiResponse<MessageResponse> updateAdminStatus(
            @PathVariable Long id,
            @Valid @RequestBody AdminStatusUpdateRequest request
    ) {
        adminUserService.updateAdminStatus(id, request);
        return ApiResponse.success("Admin status updated successfully", MessageResponse.of("Admin status updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<MessageResponse> deleteAdmin(@PathVariable Long id) {
        adminUserService.deleteAdmin(id);
        return ApiResponse.success("Admin deleted successfully", MessageResponse.of("Admin deleted successfully"));
    }
}