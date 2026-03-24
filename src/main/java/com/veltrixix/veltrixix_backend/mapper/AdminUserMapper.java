package com.veltrixix.veltrixix_backend.mapper;

import com.veltrixix.veltrixix_backend.dto.admin.AdminUserResponse;
import com.veltrixix.veltrixix_backend.entity.AdminUser;

public final class AdminUserMapper {

    private AdminUserMapper() {
    }

    public static AdminUserResponse toResponse(AdminUser adminUser) {
        if (adminUser == null) {
            return null;
        }

        AdminUserResponse response = new AdminUserResponse();
        response.setId(adminUser.getId());
        response.setName(adminUser.getName());
        response.setEmail(adminUser.getEmail());
        response.setRole(adminUser.getRole() != null ? adminUser.getRole().name() : null);
        response.setActive(adminUser.getActive());
        return response;
    }
}





