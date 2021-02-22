DROP TABLE restaurants_food_classifications;

ALTER TABLE restaurants
    ADD COLUMN endorsements uuid[] NOT NULL;

CREATE INDEX restaurants_endorsements_idx ON restaurants USING gin (endorsements);
