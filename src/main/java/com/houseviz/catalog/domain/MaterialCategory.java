package com.houseviz.catalog.domain;

import com.houseviz.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "material_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialCategory extends BaseEntity {

    @Column(nullable = false, unique = true, length = 50)
    private String code;         // FACADE, INTERIOR, ROOF, FLOOR

    @Column(nullable = false)
    private String name;         // "Фасадная отделка"

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 0;

    @Column(nullable = false)
    private boolean active = true;
}
