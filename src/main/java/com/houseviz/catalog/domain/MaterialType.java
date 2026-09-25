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

@Entity
@Table(name = "material_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialType extends BaseEntity {

    @Column(nullable = false, unique = true, length = 50)
    private String code;         // IMITATION_TIMBER, PLASTER, BRICK, PAINT

    @Column(nullable = false)
    private String name;         // "Имитация бруса"

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private MaterialCategory category;

    @Column(name = "description", length = 1000)
    private String description;

    // === Поведенческие флаги ===
    @Column(name = "has_direction", nullable = false)
    private boolean hasDirection;     // можно ли крутить направление

    @Column(name = "has_profile", nullable = false)
    private boolean hasProfile;       // есть ли рельеф (normal map)

    @Column(name = "allows_color_tinting", nullable = false)
    private boolean allowsColorTinting; // можно ли перекрашивать

    @Column(name = "is_tiled", nullable = false)
    private boolean isTiled;          // тайлится ли текстура (для repeat)

    @Column(nullable = false)
    private boolean active = true;
}
