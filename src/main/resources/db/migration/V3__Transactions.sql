-- Create the sequence for the transaction ID
CREATE SEQUENCE transactions_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create the Transactions table
CREATE TABLE Transactions
(
    id                BIGINT NOT NULL PRIMARY KEY,
    transactions      TIMESTAMP,
    source_account_id BIGINT,
    target_account_id BIGINT,
    transaction_type  VARCHAR(255),

    -- Foreign key constraints
    CONSTRAINT fk_source_account FOREIGN KEY (source_account_id) REFERENCES account_id (id),
    CONSTRAINT fk_target_account FOREIGN KEY (target_account_id) REFERENCES account_id (id)
);
