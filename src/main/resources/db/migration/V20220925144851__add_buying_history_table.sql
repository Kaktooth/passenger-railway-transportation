CREATE TABLE buying_history
(
    id        UUID PRIMARY KEY,
    user_id   UUID REFERENCES users (id),
    ticket_id UUID REFERENCES tickets (id),
    date      TIMESTAMP NOT NULL
);