package com.houseviz.catalog.service.impl;

import com.houseviz.catalog.domain.Material;
import com.houseviz.catalog.domain.MaterialType;
import com.houseviz.catalog.repository.MaterialCategoryRepository;
import com.houseviz.catalog.repository.MaterialRepository;
import com.houseviz.catalog.repository.MaterialTypeRepository;
import com.houseviz.catalog.repository.MaterialVariantRepository;
import com.houseviz.catalog.service.MaterialCatalogService;
import com.houseviz.catalog.service.MaterialMapper;
import com.houseviz.catalog.service.MaterialNotFoundException;
import com.houseviz.catalog.web.dto.CategoryDto;
import com.houseviz.catalog.web.dto.MaterialDto;
import com.houseviz.catalog.web.dto.MaterialTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MaterialCatalogServiceImpl implements MaterialCatalogService {

    private final MaterialCategoryRepository categoryRepo;
    private final MaterialTypeRepository typeRepo;
    private final MaterialRepository materialRepo;
    private final MaterialVariantRepository variantRepo;
    private final MaterialMapper mapper;

    @Override
    public List<CategoryDto> listCategories() {
        return categoryRepo.findAllByActiveTrueOrderBySortOrderAsc()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<MaterialTypeDto> listTypes(UUID categoryId) {
        List<MaterialType> types = (categoryId == null)
                ? typeRepo.findAllByActiveTrue()
                : typeRepo.findAllByCategoryIdAndActiveTrue(categoryId);
        return types.stream().map(mapper::toDto).toList();
    }

    @Override
    public List<MaterialDto> listMaterials(UUID companyId, UUID typeId) {
        List<Material> materials = (typeId == null)
                ? materialRepo.findAllByCompanyIdAndActiveTrue(companyId)
                : materialRepo.findAllByCompanyIdAndTypeIdAndActiveTrue(companyId, typeId);

        return materials.stream()
                .map(m -> mapper.toDto(m, variantRepo.findAllByMaterialIdAndActiveTrue(m.getId())))
                .toList();
    }

    @Override
    public MaterialDto getMaterial(UUID companyId, UUID materialId) {
        Material m = materialRepo.findByIdAndCompanyId(materialId, companyId)
                .orElseThrow(() -> new MaterialNotFoundException(materialId));
        return mapper.toDto(m, variantRepo.findAllByMaterialIdAndActiveTrue(m.getId()));
    }
}
