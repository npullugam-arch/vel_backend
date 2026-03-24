package com.veltrixix.veltrixix_backend.config;

import com.veltrixix.veltrixix_backend.entity.AdminUser;
import com.veltrixix.veltrixix_backend.enums.Role;
import com.veltrixix.veltrixix_backend.repository.AdminUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminDataInitializer {

    @Bean
    public CommandLineRunner seedAdminUser(
            AdminUserRepository adminUserRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            String email = "admin@gmail.com";

            if (!adminUserRepository.existsByEmail(email)) {
                AdminUser adminUser = new AdminUser();
                adminUser.setName("Admin User");
                adminUser.setEmail(email);
                adminUser.setPassword(passwordEncoder.encode("Admin123"));
                adminUser.setRole(Role.ADMIN);
                adminUser.setActive(true);

                adminUserRepository.save(adminUser);

                System.out.println("✅ Default admin created: " + email);
            } else {
                System.out.println("ℹ️ Default admin already exists: " + email);
            }
        };
    }
}