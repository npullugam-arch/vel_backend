package com.veltrixix.veltrixix_backend.security;

import com.veltrixix.veltrixix_backend.entity.AdminUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final AdminUser adminUser;

    public CustomUserDetails(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    public Long getId() {
        return adminUser.getId();
    }

    public String getName() {
        return adminUser.getName();
    }

    public String getEmail() {
        return adminUser.getEmail();
    }

    public boolean isActive() {
        return Boolean.TRUE.equals(adminUser.getActive());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + adminUser.getRole().name()));
    }

    @Override
    public String getPassword() {
        return adminUser.getPassword();
    }

    @Override
    public String getUsername() {
        return adminUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return Boolean.TRUE.equals(adminUser.getActive());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(adminUser.getActive());
    }
}