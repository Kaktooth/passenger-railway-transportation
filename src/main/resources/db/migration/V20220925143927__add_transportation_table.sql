CREATE TABLE transportation
(
    id                UUID PRIMARY KEY,
    train_id          UUID REFERENCES trains (id),
    first_station_id  UUID REFERENCES stations (id),
    second_station_id UUID REFERENCES stations (id),
    arrival_time      TIMESTAMP NOT NULL
);