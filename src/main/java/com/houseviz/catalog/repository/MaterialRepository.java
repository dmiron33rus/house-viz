package com.houseviz.catalog.repository;

import com.houseviz.catalog.domain.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MaterialRepository extends JpaRepository<Material, UUID> {

    List<Material> findAllByCompanyIdAndActiveTrue(UUID companyId);

    List<Material> findAllByCompanyIdAndTypeIdAndActiveTrue(UUID companyId, UUID typeId);

    @Query("""
        SELECT m FROM Material m
        JOIN FETCH m.type t
        JOIN FETCH t.category
        WHERE m.company.id = :companyId AND m.active = true
        """)
    List<Material> findAllWithTypeAndCategory(UUID companyId);

    Optional<Material> findByIdAndCompanyId(UUID id, UUID companyId);
}
