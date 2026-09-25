CREATE TABLE material_categories (
                                     id          UUID PRIMARY KEY,
                                     code        VARCHAR(50) NOT NULL UNIQUE,
                                     name        VARCHAR(255) NOT NULL,
                                     sort_order  INTEGER NOT NULL DEFAULT 0,
                                     active      BOOLEAN NOT NULL DEFAULT TRUE,
                                     created_at  TIMESTAMPTZ NOT NULL,
                                     updated_at  TIMESTAMPTZ NOT NULL,
                                     version     BIGINT
);

CREATE TABLE material_types (
                                id                      UUID PRIMARY KEY,
                                code                    VARCHAR(50) NOT NULL UNIQUE,
                                name                    VARCHAR(255) NOT NULL,
                                category_id             UUID NOT NULL REFERENCES material_categories(id),
                                description             VARCHAR(1000),
                                has_direction           BOOLEAN NOT NULL DEFAULT FALSE,
                                has_profile             BOOLEAN NOT NULL DEFAULT FALSE,
                                allows_color_tinting    BOOLEAN NOT NULL DEFAULT FALSE,
                                is_tiled                BOOLEAN NOT NULL DEFAULT FALSE,
                                active                  BOOLEAN NOT NULL DEFAULT TRUE,
                                created_at              TIMESTAMPTZ NOT NULL,
                                updated_at              TIMESTAMPTZ NOT NULL,
                                version                 BIGINT
);

CREATE TABLE materials (
                           id                          UUID PRIMARY KEY,
                           company_id                  UUID NOT NULL REFERENCES companies(id),
                           type_id                     UUID NOT NULL REFERENCES material_types(id),
                           name                        VARCHAR(255) NOT NULL,
                           description                 VARCHAR(1000),
                           supplier_name               VARCHAR(255),
                           price_per_unit              NUMERIC(12, 2) NOT NULL,
                           price_unit                  VARCHAR(20) NOT NULL,
                           unit_width_mm               INTEGER,
                           unit_length_mm              INTEGER,
                           unit_thickness_mm           INTEGER,
                           albedo_url                  VARCHAR(500),
                           normal_url                  VARCHAR(500),
                           roughness_url               VARCHAR(500),
                           ao_url                      VARCHAR(500),
                           waste_coefficient_horizontal NUMERIC(5, 3) DEFAULT 1.05,
                           waste_coefficient_vertical   NUMERIC(5, 3) DEFAULT 1.15,
                           active                      BOOLEAN NOT NULL DEFAULT TRUE,
                           created_at                  TIMESTAMPTZ NOT NULL,
                           updated_at                  TIMESTAMPTZ NOT NULL,
                           version                     BIGINT
);

CREATE TABLE material_variants (
                                   id              UUID PRIMARY KEY,
                                   material_id     UUID NOT NULL REFERENCES materials(id) ON DELETE CASCADE,
                                   name            VARCHAR(255) NOT NULL,
                                   color_hex       VARCHAR(7),
                                   ral_code        VARCHAR(20),
                                   albedo_url      VARCHAR(500),
                                   price_modifier  NUMERIC(12, 2) DEFAULT 0,
                                   active          BOOLEAN NOT NULL DEFAULT TRUE,
                                   created_at      TIMESTAMPTZ NOT NULL,
                                   updated_at      TIMESTAMPTZ NOT NULL,
                                   version         BIGINT
);

CREATE INDEX idx_materials_company ON materials(company_id);
CREATE INDEX idx_materials_type ON materials(type_id);
CREATE INDEX idx_materials_company_active ON materials(company_id, active);
CREATE INDEX idx_variants_material ON material_variants(material_id);
CREATE INDEX idx_types_category ON material_types(category_id);