CREATE SCHEMA city_storage;

CREATE TABLE city_storage.cities(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL UNIQUE,
    info VARCHAR(8000) NOT NULL
);

INSERT INTO city_storage.cities (name, info) VALUES
    ('Moscow', 'Visit Kremlin and Gorky Park'),
    ('London', 'Visit Trafalgar Square and Big Ben'),
    ('Paris', 'Visit The louvre museum and Elysian Fields. Do not forget look at the Eiffel Tower!');


