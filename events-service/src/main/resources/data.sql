-- Inicialización de la tabla category_event si no existen
INSERT INTO category_event (name_category)
SELECT 'Music'
    WHERE NOT EXISTS (SELECT 1 FROM category_event WHERE name_category = 'Music');

INSERT INTO category_event (name_category)
SELECT 'Sports'
    WHERE NOT EXISTS (SELECT 1 FROM category_event WHERE name_category = 'Sports');

INSERT INTO category_event (name_category)
SELECT 'Education'
    WHERE NOT EXISTS (SELECT 1 FROM category_event WHERE name_category = 'Education');

INSERT INTO category_event (name_category)
SELECT 'Technology'
    WHERE NOT EXISTS (SELECT 1 FROM category_event WHERE name_category = 'Technology');

-- Inicialización de la tabla events si no existen
INSERT INTO events (title, description, start_date, end_date, location, id_organizer, url, id_category)
SELECT 'Concert of the Year', 'A spectacular music event featuring top artists.', '2024-06-01 18:00:00', '2024-06-01 22:00:00', 'City Arena', 1, 'url', 1
    WHERE NOT EXISTS (SELECT 1 FROM events WHERE title = 'Concert of the Year');

INSERT INTO events (title, description, start_date, end_date, location, id_organizer, url, id_category)
SELECT 'Marathon 2024', 'Join the city marathon and challenge yourself!', '2024-07-15 06:00:00', '2024-07-15 12:00:00', 'Downtown Streets', 2, 'url', 2
    WHERE NOT EXISTS (SELECT 1 FROM events WHERE title = 'Marathon 2024');

INSERT INTO events (title, description, start_date, end_date, location, id_organizer, url, id_category)
SELECT 'Tech Conference', 'A conference on the latest advancements in technology.', '2024-08-20 09:00:00', '2024-08-20 17:00:00', 'Tech Hub Convention Center', 1, 'url', 4
    WHERE NOT EXISTS (SELECT 1 FROM events WHERE title = 'Tech Conference');

INSERT INTO events (title, description, start_date, end_date, location, id_organizer, url, id_category)
SELECT 'Educational Workshop', 'A workshop focused on innovative teaching methods.', '2024-09-10 10:00:00', '2024-09-10 14:00:00', 'Community Hall', 2, 'url', 3
    WHERE NOT EXISTS (SELECT 1 FROM events WHERE title = 'Educational Workshop');