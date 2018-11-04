ALTER TABLE charakter_class ADD hit_die INTEGER;

UPDATE charakter_class set hit_die = 2 WHERE id IN (9, 10, 12, 13, 22, 23, 24, 26);
UPDATE charakter_class set hit_die = 3 WHERE id IN (1, 8, 14, 19);
UPDATE charakter_class set hit_die = 4 WHERE id IN (2, 3, 5, 7, 11, 20, 21, 25);
UPDATE charakter_class set hit_die = 5 WHERE id IN (4, 6, 15, 17);
UPDATE charakter_class set hit_die = 6 WHERE id IN (0, 16, 18);
