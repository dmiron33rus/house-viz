package com.houseviz.houseviz.catalog.service;

import com.houseviz.houseviz.catalog.domain.Material;
import com.houseviz.houseviz.catalog.domain.MaterialCategory;
import com.houseviz.houseviz.catalog.domain.MaterialType;
import com.houseviz.houseviz.catalog.domain.MaterialVariant;
import com.houseviz.houseviz.catalog.web.dto.CategoryDto;
import com.houseviz.houseviz.catalog.web.dto.MaterialDto;
import com.houseviz.houseviz.catalog.web.dto.MaterialTypeDto;
import com.houseviz.houseviz.catalog.web.dto.MaterialVariantDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MaterialMapper {

    public CategoryDto toDto(MaterialCategory c) {
        return new CategoryDto(c.getId(), c.getCode(), c.getName(), c.getSortOrder());
    }

    public MaterialTypeDto toDto(MaterialType t) {
        return new MaterialTypeDto(
                t.getId(),
                t.getCode(),
                t.getName(),
                t.getCategory().getId(),
                t.getCategory().getName(),
                t.getDescription(),
                t.isHasDirection(),
                t.isHasProfile(),
                t.isAllowsColorTinting(),
                t.isTiled()
        );
    }

    public MaterialVariantDto toDto(MaterialVariant v) {
        return new MaterialVariantDto(
                v.getId(),
                v.getName(),
                v.getColorHex(),
                v.getRalCode(),
                v.getAlbedoUrl(),
                v.getPriceModifier()
        );
    }

    public MaterialDto toDto(Material m, List<MaterialVariant> variants) {
        MaterialType t = m.getType();
        return new MaterialDto(
                m.getId(),
                m.getName(),
                m.getDescription(),
                m.getSupplierName(),
                t.getId(),
                t.getCode(),
                t.getName(),
                t.isHasDirection(),
                t.isHasProfile(),
                t.isAllowsColorTinting(),
                m.getPricePerUnit(),
                m.getPriceUnit(),
                m.getUnitWidthMm(),
                m.getUnitLengthMm(),
                m.getUnitThicknessMm(),
                m.getAlbedoUrl(),
                m.getNormalUrl(),
                m.getRoughnessUrl(),
                m.getAoUrl(),
                variants.stream().map(this::toDto).toList()
        );
    }
}
