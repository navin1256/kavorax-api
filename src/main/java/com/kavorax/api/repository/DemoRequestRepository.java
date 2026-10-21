package com.kavorax.api.repository;

import com.kavorax.api.entity.DemoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRequestRepository extends JpaRepository<DemoRequest, Long> {
    long countByStatus(String status);
    long countByType(String type);
    Page<DemoRequest> findByType(String type, Pageable pageable);
    Page<DemoRequest> findByTypeIgnoreCase(String type, Pageable pageable);
}
