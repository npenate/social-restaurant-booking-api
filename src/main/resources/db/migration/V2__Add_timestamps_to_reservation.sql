ALTER TABLE reservations
    ADD COLUMN created_at timestamp without time zone NOT NULL,
    ADD COLUMN updated_at timestamp without time zone NOT NULL;



