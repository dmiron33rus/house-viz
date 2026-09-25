package com.houseviz.catalog.domain;

import com.houseviz.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "material_variants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialVariant extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    @Column(nullable = false)
    private String name;         // "RAL 7024 графит"

    @Column(name = "color_hex", length = 7)
    private String colorHex;     // "#3A3F44"

    @Column(name = "ral_code", length = 20)
    private String ralCode;      // "RAL 7024"

    // Переопределённые текстуры — если вариант отличается
    @Column(name = "albedo_url", length = 500)
    private String albedoUrl;

    // Наценка к базовой цене (может быть 0)
    @Column(name = "price_modifier", precision = 12, scale = 2)
    private BigDecimal priceModifier = BigDecimal.ZERO;

    @Column(nullable = false)
    private boolean active = true;
}
