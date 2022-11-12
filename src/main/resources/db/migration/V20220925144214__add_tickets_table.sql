CREATE TABLE tickets
(
    id                UUID PRIMARY KEY,
    wagon_id          UUID REFERENCES wagons (id),
    user_id           UUID REFERENCES users (id),
    place_number      INTEGER NOT NULL,
    transportation_id UUID REFERENCES transportation (id),
    price             BIGINT  NOT NULL
);