package com.houseviz.houseviz.catalog.service;

import com.houseviz.houseviz.catalog.web.dto.CategoryDto;
import com.houseviz.houseviz.catalog.web.dto.MaterialDto;
import com.houseviz.houseviz.catalog.web.dto.MaterialTypeDto;

import java.util.List;
import java.util.UUID;

/**
 * Каталог материалов: категории, типы, SKU, варианты.
 *
 * Все операции чтения — read-only. Изоляция по компании: материалы одной
 * компании не видны другой.
 */
public interface MaterialCatalogService {

    /**
     * Все активные категории материалов, отсортированные по sortOrder.
     */
    List<CategoryDto> listCategories();

    /**
     * Типы материалов. Если categoryId == null — все типы.
     */
    List<MaterialTypeDto> listTypes(UUID categoryId);

    /**
     * Материалы компании. Если typeId == null — все материалы компании.
     * Включает варианты (цветовые исполнения).
     */
    List<MaterialDto> listMaterials(UUID companyId, UUID typeId);

    /**
     * Один материал с вариантами.
     * @throws MaterialNotFoundException если материал не найден или принадлежит другой компании
     */
    MaterialDto getMaterial(UUID companyId, UUID materialId);
}
