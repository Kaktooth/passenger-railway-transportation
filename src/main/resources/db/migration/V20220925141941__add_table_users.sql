CREATE TABLE users
(
    id         UUID PRIMARY KEY,
    email      VARCHAR NOT NULL,
    password   VARCHAR NOT NULL,
    name       VARCHAR NOT NULL,
    surname    VARCHAR NOT NULL,
    patronymic VARCHAR NOT NULL
);