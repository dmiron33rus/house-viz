package com.houseviz.catalog.service;

import com.houseviz.common.exception.NotFoundException;

import java.util.UUID;

public class MaterialNotFoundException extends NotFoundException {
    private static final String CODE = "MATERIAL_NOT_FOUND";

    public MaterialNotFoundException(UUID materialId) {
        super(CODE, "Material not found: " + materialId);
    }
}
