package com.houseviz.houseviz.catalog.repository;

import com.houseviz.houseviz.catalog.domain.MaterialVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MaterialVariantRepository extends JpaRepository<MaterialVariant, UUID> {

    List<MaterialVariant> findAllByMaterialIdAndActiveTrue(UUID materialId);
}
