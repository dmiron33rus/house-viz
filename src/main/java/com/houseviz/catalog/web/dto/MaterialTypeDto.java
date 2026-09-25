package com.houseviz.catalog.web.dto;

import java.util.UUID;

public record MaterialTypeDto(
        UUID id,
        String code,
        String name,
        UUID categoryId,
        String categoryName,
        String description,
        boolean hasDirection,
        boolean hasProfile,
        boolean allowsColorTinting,
        boolean isTiled
) {}
