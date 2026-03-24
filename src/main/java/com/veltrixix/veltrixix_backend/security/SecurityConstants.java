package com.veltrixix.veltrixix_backend.security;

public final class SecurityConstants {

    private SecurityConstants() {
    }

    public static final String JWT_SECRET = "VeltrixixSuperSecureJwtSecretKeyForAdminAuthentication2026";
    public static final long JWT_EXPIRATION_MS = 1000L * 60 * 60 * 24; // 24 hours
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}