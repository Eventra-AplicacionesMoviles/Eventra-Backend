-- Insertar datos iniciales en tickets si no existen
INSERT INTO tickets (eventID, price, total_available, category, description)
SELECT 1, 50.0, 100, 'Concert', 'Description of concert ticket'
    WHERE NOT EXISTS (SELECT 1 FROM tickets WHERE eventID = 1);

INSERT INTO tickets (eventID, price, total_available, category, description)
SELECT 2, 25.0, 50, 'Theater', 'Description of theater ticket'
    WHERE NOT EXISTS (SELECT 1 FROM tickets WHERE eventID = 2);

INSERT INTO tickets (eventID, price, total_available, category, description)
SELECT 3, 20.0, 200, 'Sports', 'Description of sports ticket'
    WHERE NOT EXISTS (SELECT 1 FROM tickets WHERE eventID = 3);