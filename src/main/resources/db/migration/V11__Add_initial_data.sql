INSERT INTO public.food_classifications(id, name, description, created_at, updated_at)
  VALUES ('ef87ef8c-d912-4f56-9114-7ab224eae120', 'FOOD', '', now(), now());
INSERT INTO public.food_classifications(id, name, description, created_at, updated_at)
  VALUES ('87e75661-8c19-4582-b415-2d213f28832d', 'PALEO', '', now(), now());
INSERT INTO public.food_classifications(id, name, description, created_at, updated_at)
  VALUES ('9b31ccf1-f2bf-4b3c-a2cc-f7613c9d1daf', 'VEGAN', '', now(), now());
INSERT INTO public.food_classifications(id, name, description, created_at, updated_at)
  VALUES ('8509fd40-dea1-41d3-9d2d-f0058d975269', 'VEGETARIAN', '', now(), now());

INSERT INTO public.diners(id, name, zip_code, lat, lon, created_at, updated_at)
  VALUES ('57fb94c1-ea81-4213-ba9a-f8ca00b5efd5', 'Nelson', '06600', 19.4204502, -99.165441, now(), now());
INSERT INTO public.diners(id, name, zip_code, lat, lon, created_at, updated_at)
  VALUES ('427d3ad2-3b84-46d8-a6a1-75fe0cc54035', 'Vegonia', '06600', 19.4204501, -99.165442, now(), now());
INSERT INTO public.diners(id, name, zip_code, lat, lon, created_at, updated_at)
  VALUES ('2bc7981d-d3de-4f88-827e-733bdecd71dd', 'Patricio', '06600', 19.4204503, -99.165444, now(), now());

INSERT INTO public.diners_food_classifications(diner_id, food_classification_id)
  VALUES ('57fb94c1-ea81-4213-ba9a-f8ca00b5efd5', 'ef87ef8c-d912-4f56-9114-7ab224eae120');
INSERT INTO public.diners_food_classifications(diner_id, food_classification_id)
  VALUES ('427d3ad2-3b84-46d8-a6a1-75fe0cc54035', 'ef87ef8c-d912-4f56-9114-7ab224eae120');
INSERT INTO public.diners_food_classifications(diner_id, food_classification_id)
  VALUES ('427d3ad2-3b84-46d8-a6a1-75fe0cc54035', '87e75661-8c19-4582-b415-2d213f28832d');
INSERT INTO public.diners_food_classifications(diner_id, food_classification_id)
  VALUES ('2bc7981d-d3de-4f88-827e-733bdecd71dd', 'ef87ef8c-d912-4f56-9114-7ab224eae120');
INSERT INTO public.diners_food_classifications(diner_id, food_classification_id)
  VALUES ('2bc7981d-d3de-4f88-827e-733bdecd71dd', '9b31ccf1-f2bf-4b3c-a2cc-f7613c9d1daf');

INSERT INTO public.restaurants(id, name, created_at, updated_at, zip_code, lat, lon)
  VALUES ('f7e8896c-cd75-4023-b07b-2c31108e4250', 'El Pescadito', now(), now(),  '06600', 19.425926,-99.1650147);

INSERT INTO public.restaurants_food_classifications(restaurant_id, food_classification_id)
  VALUES ('f7e8896c-cd75-4023-b07b-2c31108e4250', 'ef87ef8c-d912-4f56-9114-7ab224eae120');
INSERT INTO public.restaurants_food_classifications(restaurant_id, food_classification_id)
  VALUES ('f7e8896c-cd75-4023-b07b-2c31108e4250', '87e75661-8c19-4582-b415-2d213f28832d');
INSERT INTO public.restaurants_food_classifications(restaurant_id, food_classification_id)
  VALUES ('f7e8896c-cd75-4023-b07b-2c31108e4250', '9b31ccf1-f2bf-4b3c-a2cc-f7613c9d1daf');

INSERT INTO public.tables(id, capacity, description, created_at, updated_at, restaurant_id, zip_code, lat, lon)
  VALUES ('7b9f0009-5c1c-4f18-8441-e1a43bff4a00', 2, '', now(), now(), 'f7e8896c-cd75-4023-b07b-2c31108e4250', '06600',  19.425926,-99.1650147);
INSERT INTO public.tables(id, capacity, description, created_at, updated_at, restaurant_id, zip_code, lat, lon)
  VALUES ('5d853459-1af9-4111-bc70-01a3e4bbcda9', 4, '', now(), now(), 'f7e8896c-cd75-4023-b07b-2c31108e4250', '06600',  19.425926,-99.1650147);
INSERT INTO public.tables(id, capacity, description, created_at, updated_at, restaurant_id, zip_code, lat, lon)
  VALUES ('8ee58eb8-4171-441e-a1ed-8aeb63f06b08', 6, '', now(), now(), 'f7e8896c-cd75-4023-b07b-2c31108e4250', '06600',  19.425926,-99.1650147);

INSERT INTO public.reservations(id, table_id, scheduled_at, created_at, updated_at)
  VALUES ('e04be4e5-4e8b-4fa8-bc50-f20b143df233', '7b9f0009-5c1c-4f18-8441-e1a43bff4a00' , now(), now(), now());

INSERT INTO public.reservations_diners(reservation_id, diner_id)
  VALUES ('e04be4e5-4e8b-4fa8-bc50-f20b143df233', '57fb94c1-ea81-4213-ba9a-f8ca00b5efd5');

INSERT INTO public.restaurants(id, name, created_at, updated_at, zip_code, lat, lon)
  VALUES ('df7c0ce8-4a53-4e6b-9d43-55e06580d89a', 'Don Tonno', now(), now(),  '06600', 19.425926,-99.1650147);

INSERT INTO public.restaurants_food_classifications(restaurant_id, food_classification_id)
  VALUES ('df7c0ce8-4a53-4e6b-9d43-55e06580d89a', 'ef87ef8c-d912-4f56-9114-7ab224eae120');

INSERT INTO public.tables(id, capacity, description, created_at, updated_at, restaurant_id, zip_code, lat, lon)
  VALUES ('1fbffc61-99c9-4487-92cb-6179efa83f11', 2, '', now(), now(), 'df7c0ce8-4a53-4e6b-9d43-55e06580d89a', '06600',  19.425926,-99.1650147);
INSERT INTO public.tables(id, capacity, description, created_at, updated_at, restaurant_id, zip_code, lat, lon)
  VALUES ('8740f754-b746-4b91-81a9-926c1460a1c4', 4, '', now(), now(), 'df7c0ce8-4a53-4e6b-9d43-55e06580d89a', '06600',  19.425926,-99.1650147);
INSERT INTO public.tables(id, capacity, description, created_at, updated_at, restaurant_id, zip_code, lat, lon)
  VALUES ('3c699128-7784-4616-84df-e343aae8f578', 1, '', now(), now(), 'df7c0ce8-4a53-4e6b-9d43-55e06580d89a', '06600',  19.425926,-99.1650147);

-- FOR RESET
--delete from reservations_diners;
--delete from reservations;
--delete from tables;
--delete from restaurants_food_classifications;
--delete from diners_food_classifications;
--delete from diners;
--delete from food_classifications;
--delete from restaurants
--delete from flyway_schema_history WHERE installed_rank = 11
