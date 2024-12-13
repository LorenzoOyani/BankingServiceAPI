-- Insert mock account for an existing user
INSERT INTO account_id (id, -- Optional if the database auto-generates it via sequence
                        account_number,
                        account_type,
                        account_status,
                        available_balance,
                        pin,
                        user_id)
VALUES (NEXTVAL('account_sequence'), -- Auto-generate ID using sequence
        '00214553307000', -- Unique account number
        'SAVINGS', -- Account Type (Enum)
        'ACTIVE', -- Account Status (Enum)
        1500.00, -- Available Balance
        '1234', -- PIN
        1 -- User ID (replace with valid User ID from Bank_users)
       );

-- Verify the inserted record
SELECT *
FROM account_id
WHERE account_number = '00214553307000';
