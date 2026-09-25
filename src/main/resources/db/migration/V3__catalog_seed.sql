-- Категории
INSERT INTO material_categories (id, code, name, sort_order, active, created_at, updated_at)
VALUES
    (uuid_generate_v4(), 'FACADE',   'Фасадная отделка', 1, TRUE, now(), now()),
    (uuid_generate_v4(), 'INTERIOR', 'Внутренняя отделка', 2, TRUE, now(), now()),
    (uuid_generate_v4(), 'ROOF',     'Кровля', 3, TRUE, now(), now()),
    (uuid_generate_v4(), 'FLOOR',    'Полы', 4, TRUE, now(), now());

-- Типы (привязка по code категории)
INSERT INTO material_types (id, code, name, category_id, has_direction, has_profile, allows_color_tinting, is_tiled, active, created_at, updated_at)
SELECT uuid_generate_v4(), 'IMITATION_TIMBER', 'Имитация бруса', id, TRUE, TRUE, TRUE, TRUE, TRUE, now(), now()
FROM material_categories WHERE code = 'FACADE';

INSERT INTO material_types (id, code, name, category_id, has_direction, has_profile, allows_color_tinting, is_tiled, active, created_at, updated_at)
SELECT uuid_generate_v4(), 'PLASTER', 'Штукатурка', id, FALSE, TRUE, TRUE, FALSE, TRUE, now(), now()
FROM material_categories WHERE code = 'FACADE';

INSERT INTO material_types (id, code, name, category_id, has_direction, has_profile, allows_color_tinting, is_tiled, active, created_at, updated_at)
SELECT uuid_generate_v4(), 'BRICK', 'Кирпич', id, FALSE, TRUE, FALSE, TRUE, TRUE, now(), now()
FROM material_categories WHERE code = 'FACADE';

INSERT INTO material_types (id, code, name, category_id, has_direction, has_profile, allows_color_tinting, is_tiled, active, created_at, updated_at)
SELECT uuid_generate_v4(), 'PAINT', 'Краска', id, FALSE, FALSE, TRUE, FALSE, TRUE, now(), now()
FROM material_categories WHERE code = 'INTERIOR';

INSERT INTO material_types (id, code, name, category_id, has_direction, has_profile, allows_color_tinting, is_tiled, active, created_at, updated_at)
SELECT uuid_generate_v4(), 'WALLPAPER', 'Обои', id, FALSE, TRUE, FALSE, TRUE, TRUE, now(), now()
FROM material_categories WHERE code = 'INTERIOR';

INSERT INTO material_types (id, code, name, category_id, has_direction, has_profile, allows_color_tinting, is_tiled, active, created_at, updated_at)
SELECT uuid_generate_v4(), 'METAL_TILE', 'Металлочерепица', id, FALSE, TRUE, FALSE, TRUE, TRUE, now(), now()
FROM material_categories WHERE code = 'ROOF';