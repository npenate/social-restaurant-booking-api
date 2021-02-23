ALTER TABLE restaurants
    DROP COLUMN location,
    ADD COLUMN lat real NOT NULL,
    ADD COLUMN lon real NOT NULL;

ALTER TABLE tables
    DROP COLUMN location,
    ADD COLUMN lat real NOT NULL,
    ADD COLUMN lon real NOT NULL;

ALTER TABLE diners
    DROP COLUMN location,
    ADD COLUMN lat real NOT NULL,
    ADD COLUMN lon real NOT NULL;