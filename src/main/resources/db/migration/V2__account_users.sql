CREATE SEQUENCE account_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE account_id
(
    id                BIGINT       NOT NULL,
    account_number    VARCHAR(255) NOT NULL,
    account_type      VARCHAR(255),
    account_status    VARCHAR(255),
    available_balance DECIMAL(19, 2),
    pin               VARCHAR(255),
    user_id           BIGINT,
    PRIMARY KEY (id),
    UNIQUE (account_number)
);

ALTER SEQUENCE account_sequence
    OWNED BY account_id.id;

CREATE INDEX idx_user_id ON account_id (user_id);

-- Foreign key constraints
ALTER TABLE account_id
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES bank_users (id);

