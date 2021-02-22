ALTER TABLE tables
    ADD COLUMN zip_code text NOT NULL,
    ADD COLUMN location point NOT NULL;

CREATE INDEX zip_code_tables_idx ON tables(zip_code);

ALTER TABLE restaurants
    ADD COLUMN zip_code text NOT NULL,
    ADD COLUMN location point NOT NULL;

CREATE INDEX zip_code_restaurants_idx ON restaurants(zip_code);

ALTER TABLE diners
    ADD COLUMN zip_code text NOT NULL,
    ADD COLUMN location point NOT NULL;
