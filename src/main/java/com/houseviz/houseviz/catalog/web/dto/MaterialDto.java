package com.houseviz.houseviz.catalog.web.dto;

import com.houseviz.houseviz.catalog.domain.PriceUnit;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record MaterialDto(
        UUID id,
        String name,
        String description,
        String supplierName,
        UUID typeId,
        String typeCode,
        String typeName,
        boolean hasDirection,
        boolean hasProfile,
        boolean allowsColorTinting,
        BigDecimal pricePerUnit,
        PriceUnit priceUnit,
        Integer unitWidthMm,
        Integer unitLengthMm,
        Integer unitThicknessMm,
        String albedoUrl,
        String normalUrl,
        String roughnessUrl,
        String aoUrl,
        List<MaterialVariantDto> variants
) {}
