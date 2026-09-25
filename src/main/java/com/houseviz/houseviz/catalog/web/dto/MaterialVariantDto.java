package com.houseviz.houseviz.catalog.web.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record MaterialVariantDto(
        UUID id,
        String name,
        String colorHex,
        String ralCode,
        String albedoUrl,
        BigDecimal priceModifier
) {}
