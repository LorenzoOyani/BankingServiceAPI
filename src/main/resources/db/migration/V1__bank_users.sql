CREATE TABLE Bank_users
(
    id           BIGSERIAL PRIMARY KEY,
    first_name   VARCHAR(255)        NOT NULL,
    last_name    VARCHAR(255)        NOT NULL,
    password     VARCHAR(255)        NOT NULL,
    email        VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(20) UNIQUE  NOT NULL,
    address      VARCHAR(255)        NOT NULL,
    country_code VARCHAR(10)         NOT NULL,
    role         VARCHAR(50),
    CONSTRAINT user_email_unique UNIQUE (email),
    CONSTRAINT user_phone_unique UNIQUE (phone_number)
);

CREATE SEQUENCE user_req
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE INDEX idx_id_email ON bank_users(id, email);