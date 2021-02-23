SELECT * INTO reservations_history FROM reservations LIMIT 0;


ALTER TABLE reservations_history
  ADD COLUMN deleted boolean DEFAULT false,
  ADD PRIMARY KEY (id);
