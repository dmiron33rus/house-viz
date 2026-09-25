package com.houseviz.houseviz.catalog.domain;

import com.houseviz.houseviz.common.entity.BaseEntity;
import com.houseviz.houseviz.user.domain.Company;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "materials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Material extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;    // материалы принадлежат компании

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_id", nullable = false)
    private MaterialType type;

    @Column(nullable = false)
    private String name;        // "Grand Line, металл 20×140"

    @Column(length = 1000)
    private String description;

    @Column(name = "supplier_name")
    private String supplierName;

    // === Цена ===
    @Column(name = "price_per_unit", nullable = false, precision = 12, scale = 2)
    private BigDecimal pricePerUnit;

    @Enumerated(EnumType.STRING)
    @Column(name = "price_unit", nullable = false, length = 20)
    private PriceUnit priceUnit;   // SQM, LINEAR_METER, PIECE

    // === Геометрия (для тайловых материалов) ===
    @Column(name = "unit_width_mm")
    private Integer unitWidthMm;    // ширина доски, мм

    @Column(name = "unit_length_mm")
    private Integer unitLengthMm;   // длина, мм (0 = произвольная)

    @Column(name = "unit_thickness_mm")
    private Integer unitThicknessMm;

    // === 3D-ресурсы (ссылки в MinIO) ===
    @Column(name = "albedo_url", length = 500)
    private String albedoUrl;

    @Column(name = "normal_url", length = 500)
    private String normalUrl;

    @Column(name = "roughness_url", length = 500)
    private String roughnessUrl;

    @Column(name = "ao_url", length = 500)
    private String aoUrl;

    // === Коэффициенты для сметы ===
    @Column(name = "waste_coefficient_horizontal", precision = 5, scale = 3)
    private BigDecimal wasteCoefficientHorizontal = new BigDecimal("1.05");

    @Column(name = "waste_coefficient_vertical", precision = 5, scale = 3)
    private BigDecimal wasteCoefficientVertical = new BigDecimal("1.15");

    @Column(nullable = false)
    private boolean active = true;
}
