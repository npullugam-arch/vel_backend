package com.veltrixix.veltrixix_backend.repository;

import com.veltrixix.veltrixix_backend.entity.QrConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QrConfigRepository extends JpaRepository<QrConfig, Long> {
    Optional<QrConfig> findByActiveTrue();
}