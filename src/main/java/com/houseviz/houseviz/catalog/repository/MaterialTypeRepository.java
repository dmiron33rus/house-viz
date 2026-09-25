package com.houseviz.houseviz.catalog.repository;

import com.houseviz.houseviz.catalog.domain.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MaterialTypeRepository extends JpaRepository<MaterialType, UUID> {

    Optional<MaterialType> findByCode(String code);
    List<MaterialType> findAllByCategoryIdAndActiveTrue(UUID categoryId);
    List<MaterialType> findAllByActiveTrue();
}
