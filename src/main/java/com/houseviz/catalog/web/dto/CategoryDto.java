package com.houseviz.catalog.web.dto;

import java.util.UUID;

public record CategoryDto(
        UUID id,
        String code,
        String name,
        Integer sortOrder
) {}
