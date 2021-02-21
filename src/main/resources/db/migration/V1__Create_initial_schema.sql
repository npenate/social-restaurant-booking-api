CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE food_classifications
(
    id          uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    name        text NOT NULL,
    description text,
    created_at  timestamp without time zone NOT NULL,
    updated_at  timestamp without time zone NOT NULL
);

CREATE TABLE diners
(
    id           uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    name         text NOT NULL,
    restrictions uuid[],
    created_at   timestamp without time zone NOT NULL,
    updated_at   timestamp without time zone NOT NULL
);

CREATE TABLE tables
(
    id          uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    capacity    smallint NOT NULL,
    description text,
    created_at  timestamp without time zone NOT NULL,
    updated_at  timestamp without time zone NOT NULL
);

CREATE TABLE restaurants
(
    id         uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    name       text NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);

CREATE TABLE restaurants_food_classifications
(
    restaurant_id          uuid NOT NULL,
    food_classification_id uuid NOT NULL,

    PRIMARY KEY (restaurant_id, food_classification_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE,
    FOREIGN KEY (food_classification_id) REFERENCES food_classifications(id) ON DELETE CASCADE
);

CREATE TABLE restaurants_tables
(
    restaurant_id uuid NOT NULL,
    table_id      uuid NOT NULL,

    PRIMARY KEY (restaurant_id, table_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE,
    FOREIGN KEY (table_id) REFERENCES tables(id) ON DELETE CASCADE
);

CREATE TABLE reservations
(
    id           uuid PRIMARY KEY,
    table_id     uuid NOT NULL,
    scheduled_at timestamp NOT NULL,

    FOREIGN KEY (table_id) REFERENCES tables(id)
);

CREATE TABLE reservations_diners
(
    reservation_id uuid NOT NULL,
    diner_id       uuid NOT NULL,

    PRIMARY KEY (reservation_id, diner_id),
    FOREIGN KEY (reservation_id) REFERENCES reservations(id) ON DELETE CASCADE,
    FOREIGN KEY (diner_id) REFERENCES diners(id) ON DELETE CASCADE
);
