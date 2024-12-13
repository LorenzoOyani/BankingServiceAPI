INSERT INTO Bank_users (id, -- Optional: Leave this out if it's auto-generated
                        first_name,
                        last_name,
                        password,
                        email,
                        phone_number,
                        address,
                        country_code,
                        role)
VALUES (NEXTVAL('bank_users_id_seq'), -- Use your sequence name; for SEQUENCE auto-increment
        'John', -- First Name
        'Doe', -- Last Name
        'securepassword123', -- Password (hash in production if applicable)
        'john.doe@example.com', -- Email (unique)
        '+1234567890', -- Phone Number (unique)
        '123 Elm Street, Springfield', -- Address
        'US', -- Country Code
        'CUSTOMER' -- Role (Enum type)
       );

-- Verify the inserted record
SELECT *
FROM Bank_users
WHERE email = 'john.doe@example.com';
