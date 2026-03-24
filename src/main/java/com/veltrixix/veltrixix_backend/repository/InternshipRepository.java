package com.veltrixix.veltrixix_backend.repository;

import com.veltrixix.veltrixix_backend.entity.Internship;
import com.veltrixix.veltrixix_backend.enums.InternshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InternshipRepository extends JpaRepository<Internship, Long> {
    List<Internship> findByStatus(InternshipStatus status);
}