CREATE TABLE diners_food_classifications
(
    diner_id       uuid NOT NULL,
    food_classification_id uuid NOT NULL,

    PRIMARY KEY (diner_id, food_classification_id),
    FOREIGN KEY (food_classification_id) REFERENCES food_classifications(id) ON DELETE CASCADE,
    FOREIGN KEY (diner_id) REFERENCES diners(id) ON DELETE CASCADE
);