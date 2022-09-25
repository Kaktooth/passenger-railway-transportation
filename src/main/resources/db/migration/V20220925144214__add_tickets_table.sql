CREATE TABLE tickets
(
    id                UUID PRIMARY KEY,
    wagon_id          UUID REFERENCES wagons (id),
    place_number      INTEGER NOT NULL,
    first_station_id  UUID REFERENCES stations (id),
    second_station_id UUID REFERENCES stations (id),
    price             BIGINT  NOT NULL
);