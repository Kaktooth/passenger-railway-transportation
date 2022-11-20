CREATE TABLE wagon_types
(
    id   UUID PRIMARY KEY,
    type VARCHAR NOT NULL,
    seat_number INTEGER NOT NULL,
    seat_price INTEGER NOT NULL
);