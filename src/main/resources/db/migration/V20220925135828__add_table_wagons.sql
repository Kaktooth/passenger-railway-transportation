CREATE TABLE wagons
(
    id       UUID PRIMARY KEY,
    type_id  INTEGER REFERENCES wagon_types (id) NOT NULL,
    train_id UUID REFERENCES trains (id)         NOT NULL
);