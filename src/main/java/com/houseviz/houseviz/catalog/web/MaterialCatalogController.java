package com.houseviz.houseviz.catalog.web;

import com.houseviz.houseviz.catalog.service.MaterialCatalogService;
import com.houseviz.houseviz.catalog.web.dto.CategoryDto;
import com.houseviz.houseviz.catalog.web.dto.MaterialDto;
import com.houseviz.houseviz.catalog.web.dto.MaterialTypeDto;
import com.houseviz.houseviz.common.exception.ForbiddenException;
import com.houseviz.houseviz.common.exception.NoCompanyException;
import com.houseviz.houseviz.security.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/catalog")
@RequiredArgsConstructor
public class MaterialCatalogController {

    private final MaterialCatalogService service;

    @GetMapping("/categories")
    public List<CategoryDto> categories() {
        return service.listCategories();
    }

    @GetMapping("/types")
    public List<MaterialTypeDto> types(@RequestParam(required = false) UUID categoryId) {
        return service.listTypes(categoryId);
    }

    @GetMapping("/materials")
    public List<MaterialDto> materials(@AuthenticationPrincipal AuthenticatedUser user,
                                       @RequestParam(required = false) UUID typeId
    ) {
        if (user.companyId() == null) {
            throw new NoCompanyException();
        }
        return service.listMaterials(user.companyId(), typeId);
    }

    @GetMapping("/materials/{id}")
    public MaterialDto material(
            @RequestParam UUID companyId,
            @PathVariable UUID id
    ) {
        return service.getMaterial(companyId, id);
    }
}
