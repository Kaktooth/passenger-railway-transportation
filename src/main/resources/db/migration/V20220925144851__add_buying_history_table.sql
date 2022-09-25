CREATE TABLE buying_history
(
    user_id   UUID REFERENCES users (id),
    ticket_id UUID REFERENCES tickets (id),
    date      TIMESTAMP NOT NULL
);