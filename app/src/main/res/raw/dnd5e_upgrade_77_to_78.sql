UPDATE image SET id = id + 36 WHERE id > 39;
UPDATE charakter SET image_id = image_id + 36 WHERE image_id > 39;
UPDATE charakter SET thumb_image_id = thumb_image_id + 36 WHERE thumb_image_id > 39;
ALTER TABLE class ADD COLUMN image_id INTEGER NOT NULL DEFAULT 0;


UPDATE class SET image_id = 68 WHERE id = 1;
UPDATE class SET image_id = 50 WHERE id = 2;
UPDATE class SET image_id = 55 WHERE id = 3;
UPDATE class SET image_id = 45 WHERE id = 4;
UPDATE class SET image_id = 43 WHERE id = 5;
UPDATE class SET image_id = 59 WHERE id = 6;
UPDATE class SET image_id = 63 WHERE id = 7;
UPDATE class SET image_id = 58 WHERE id = 8;
UPDATE class SET image_id = 47 WHERE id = 9;
UPDATE class SET image_id = 57 WHERE id = 10;
UPDATE class SET image_id = 51 WHERE id = 11;
UPDATE class SET image_id = 54 WHERE id = 12;

