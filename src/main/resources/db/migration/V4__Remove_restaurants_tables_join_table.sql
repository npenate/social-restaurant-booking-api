DROP TABLE restaurants_tables;

ALTER TABLE tables
    ADD COLUMN restaurant_id uuid NOT NULL,
    ADD CONSTRAINT restaurant_fk FOREIGN KEY (restaurant_id) REFERENCES restaurants (id);