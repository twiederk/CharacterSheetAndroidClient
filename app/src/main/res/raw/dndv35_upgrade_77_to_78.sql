UPDATE image SET id = id + 36 WHERE id > 39;
UPDATE charakter SET image_id = image_id + 36 WHERE image_id > 39;
UPDATE charakter SET thumb_image_id = thumb_image_id + 36 WHERE thumb_image_id > 39;
ALTER TABLE class ADD COLUMN image_id INTEGER NOT NULL DEFAULT 0;


UPDATE class SET image_id = 68 WHERE id = 0;
UPDATE class SET image_id = 50 WHERE id = 1;
UPDATE class SET image_id = 55 WHERE id = 2;
UPDATE class SET image_id = 45 WHERE id = 3;
UPDATE class SET image_id = 43 WHERE id = 4;
UPDATE class SET image_id = 59 WHERE id = 5;
UPDATE class SET image_id = 63 WHERE id = 6;
UPDATE class SET image_id = 58 WHERE id = 7;
UPDATE class SET image_id = 47 WHERE id = 8;
UPDATE class SET image_id = 57 WHERE id = 9;
UPDATE class SET image_id = 54 WHERE id = 10;
UPDATE class SET image_id = 41 WHERE id = 11;
UPDATE class SET image_id = 48 WHERE id = 12;
UPDATE class SET image_id = 51 WHERE id = 13;
UPDATE class SET image_id = 53 WHERE id = 14;
UPDATE class SET image_id = 75 WHERE id = 15;
UPDATE class SET image_id = 61 WHERE id = 16;
UPDATE class SET image_id = 46 WHERE id = 17;
UPDATE class SET image_id = 42 WHERE id = 18;
UPDATE class SET image_id = 62 WHERE id = 19;
UPDATE class SET image_id = 52 WHERE id = 20;
UPDATE class SET image_id = 49 WHERE id = 21;
UPDATE class SET image_id = 60 WHERE id = 22;
UPDATE class SET image_id = 66 WHERE id = 23;
UPDATE class SET image_id = 64 WHERE id = 24;
UPDATE class SET image_id = 44 WHERE id = 25;
UPDATE class SET image_id = 70 WHERE id = 26;
