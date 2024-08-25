-- Insertar datos iniciales en typeofuser si no existen
INSERT INTO typeofuser (description) SELECT 'Admin' WHERE NOT EXISTS (SELECT * FROM typeofuser WHERE description = 'Admin');
INSERT INTO typeofuser (description) SELECT 'User' WHERE NOT EXISTS (SELECT * FROM typeofuser WHERE description = 'User');

-- Insertar datos iniciales en users si no existen
INSERT INTO users (first_name, last_name, email, password, url, type_id)
SELECT 'John', 'Doe', 'john@example.com', 'password', 'url', 1
    WHERE NOT EXISTS (SELECT * FROM users WHERE email = 'john@example.com');

INSERT INTO users (first_name, last_name, email, password, url, type_id)
SELECT 'Jane', 'Doe', 'jane@example.com', 'password', 'url', 2
    WHERE NOT EXISTS (SELECT * FROM users WHERE email = 'jane@example.com');
