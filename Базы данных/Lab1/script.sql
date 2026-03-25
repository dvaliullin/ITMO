--BEGIN;

DO $$
BEGIN
IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'FOSSILTYPE') THEN
CREATE TYPE FOSSILTYPE AS ENUM (
    'MINERAL',
    'METAL',
    'OIL',
    'GAS'
    );
END IF;
END $$;

DO $$
BEGIN
IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'COMPUTERTYPE') THEN
CREATE TYPE COMPUTERTYPE AS ENUM (
    'DESKTOP',
    'LAPTOP',
    'TABLET'
    );
END IF;
END $$;

DO $$
BEGIN
IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'COVERAGE') THEN
CREATE TYPE COVERAGE AS ENUM (
    'TARP',
    'LEATHER',
    'TWILL'
    );
END IF;
END $$;

DO $$
BEGIN
IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'BULLETTYPE') THEN
CREATE TYPE BULLETTYPE AS ENUM (
    'SOFT',
    'SPHERICAL',
    'ARMOR-PIERCING'
    );
END IF;
END $$;

CREATE TABLE IF NOT EXISTS excavation_site (
    id SERIAL PRIMARY KEY,
    coverage COVERAGE,
    perimeter INT,
    area INT
    );

CREATE TABLE IF NOT EXISTS fossil (
    id SERIAL PRIMARY KEY,
    excavation_site_id INT REFERENCES excavation_site,
    finding_date DATE,
    type FOSSILTYPE,
    age INT,
    size INT
    );

CREATE TABLE IF NOT EXISTS computer (
    id SERIAL PRIMARY KEY,
    type COMPUTERTYPE,
    screen_image TEXT
    );

CREATE TABLE IF NOT EXISTS CAT (
    computer_id INT REFERENCES computer(id),
    fossil_id INT REFERENCES fossil,
    is_successfully BOOL,
    PRIMARY KEY(computer_id, fossil_id)
    );

CREATE TABLE IF NOT EXISTS water_inflow (
    id SERIAL PRIMARY KEY,
    length INT,
    water_volume BIGINT
    );

CREATE TABLE IF NOT EXISTS ditch (
    id SERIAL PRIMARY KEY,
    water_level INT,
    length INT,
    width INT,
    depth INT
    );

CREATE TABLE IF NOT EXISTS flooding (
    water_inflow_id INT REFERENCES water_inflow,
    excavation_site_id INT REFERENCES excavation_site,
    ditch_id INT REFERENCES ditch,
    speed INT,
    PRIMARY KEY (water_inflow_id, excavation_site_id, ditch_id)
    );

CREATE TABLE IF NOT EXISTS gun (
    id SERIAL PRIMARY KEY,
    bullet_type BULLETTYPE,
    shot_range NUMERIC(8,4)
    );

CREATE TABLE IF NOT EXISTS blast_waves (
    id SERIAL PRIMARY KEY,
    gun_id INT REFERENCES gun,
    power BIGINT,
    speed BIGINT
    );

CREATE TABLE IF NOT EXISTS equipment (
    excavation_site_id INT REFERENCES excavation_site,
    gun_id INT REFERENCES gun,
    quantity INT,
    PRIMARY KEY (excavation_site_id, gun_id)
    );

INSERT INTO excavation_site (coverage, perimeter, area) VALUES
('TARP', 40, 100),
('LEATHER', 100, 625),
('TWILL', 80, 400);

INSERT INTO fossil (finding_date, type, age, size) VALUES
('2026-02-25', 'MINERAL', 100, 20),
('2007-09-29', 'METAL', 10, 200),
('2000-01-01', 'OIL', 5, 500),
('1800-05-20', 'GAS', 1, 1000);

INSERT INTO computer (type, screen_image) VALUES
('DESKTOP', 'Склон в разрезе'),
('LAPTOP', 'Неопознанный объект'),
('TABLET', 'Ничего не видно');

INSERT INTO CAT (computer_id, fossil_id, is_successfully) VALUES
(1, 2, 'TRUE'),
(2, 1, 'FALSE')
ON CONFLICT (computer_id, fossil_id) DO NOTHING;

INSERT INTO water_inflow (length, water_volume) VALUES
(50, 500),
(100, 1000),
(150, 1500);

INSERT INTO ditch (water_level, length, width, depth) VALUES
(10, 100, 20, 15),
(15, 50, 50, 30),
(5, 10, 15, 20);

INSERT INTO flooding (water_inflow_id, excavation_site_id, ditch_id, speed) VALUES
(1, 2, 3, 5),
(2, 1, 3, 10),
(3, 2, 1, 30)
ON CONFLICT (water_inflow_id, excavation_site_id, ditch_id) DO NOTHING;

INSERT INTO gun (bullet_type, shot_range) VALUES
('SOFT', 100.5),
('SPHERICAL', 500.25),
('ARMOR-PIERCING', 1000.125);

INSERT INTO equipment (excavation_site_id, gun_id, quantity) VALUES
(1, 2, 1),
(2, 1, 5),
(3, 2, 10)
ON CONFLICT (excavation_site_id, gun_id) DO NOTHING;

INSERT INTO blast_waves (gun_id, power, speed) VALUES
(1, 100, 1000),
(2, 500, 50000);
--ON CONFLICT (gun_id) DO NOTHING;

--COMMIT;

