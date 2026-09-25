package com.houseviz.catalog.repository;

import com.houseviz.catalog.domain.MaterialCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MaterialCategoryRepository extends JpaRepository<MaterialCategory, UUID> {

    Optional<MaterialCategory> findByCode(String code);
    List<MaterialCategory> findAllByActiveTrueOrderBySortOrderAsc();
}
