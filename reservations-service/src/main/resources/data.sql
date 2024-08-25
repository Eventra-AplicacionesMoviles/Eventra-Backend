-- Insertar datos iniciales en reservations si no existen
INSERT INTO reservations (userid, ticketid, quantity, reservation_date)
SELECT 1, 1, 2, '2024-05-01 10:00:00'
    WHERE NOT EXISTS (SELECT 1 FROM reservations WHERE userid = 1 AND ticketid = 1);

INSERT INTO reservations (userid, ticketid, quantity, reservation_date)
SELECT 2, 3, 1, '2024-05-02 12:00:00'
    WHERE NOT EXISTS (SELECT 1 FROM reservations WHERE userid = 2 AND ticketid = 3);

INSERT INTO reservations (userid, ticketid, quantity, reservation_date)
SELECT 3, 2, 3, '2024-05-03 15:00:00'
    WHERE NOT EXISTS (SELECT 1 FROM reservations WHERE userid = 3 AND ticketid = 2);
