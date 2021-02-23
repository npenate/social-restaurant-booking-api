CREATE TABLE restaurants_food_classifications
(
    restaurant_id          uuid NOT NULL,
    food_classification_id uuid NOT NULL,

    PRIMARY KEY (restaurant_id, food_classification_id),
    FOREIGN KEY (food_classification_id) REFERENCES food_classifications(id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);

ALTER TABLE restaurants
    DROP COLUMN endorsements;