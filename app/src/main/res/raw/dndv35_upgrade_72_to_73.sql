UPDATE ability SET name = 'Ability Score Increase (Con +2, Cha -2)' WHERE id = 226;
UPDATE ability SET name = 'Ability Score Increase (Con +2, Str -2)' WHERE id = 243;
UPDATE ability SET name = 'Ability Score Increase (Con +2, Cha -4)' WHERE id = 267;
UPDATE ability SET name = 'Ability Score Increase (Dex +2, Con -2)' WHERE id = 238;
UPDATE ability SET name = 'Ability Score Increase (Dex +2, Int -2)' WHERE id = 273;
UPDATE ability SET name = 'Ability Score Increase (Dex +2, Int -2)' WHERE id = 283;
UPDATE ability SET name = 'Ability Score Increase (Dex +2, Str -2)' WHERE id = 257;
UPDATE ability SET name = 'Ability Score Increase (Int +2, Cha +2)' WHERE id = 277;
UPDATE ability SET name = 'Ability Score Increase (Int +2, Str -2)' WHERE id = 282;
UPDATE ability SET name = 'Ability Score Increase (Str +2, Int -2)' WHERE id = 284;
UPDATE ability SET name = 'Ability Score Increase (Str +2, Int -2, Cha -2)' WHERE id = 255;
UPDATE ability SET name = 'Ability Score Increase (Str -2, Dex +2, Wis +2, Cha -4)' WHERE id = 285;


UPDATE ability SET description = 'Constitution +2, Charisma -2.' WHERE id = 226;
UPDATE ability SET description = 'Constitution +2, Strength -2.' WHERE id = 243;
UPDATE ability SET description = 'Constitution +2, Charisma -4.' WHERE id = 267;
UPDATE ability SET description = 'Dexterity +2, Constitution -2.' WHERE id = 238;
UPDATE ability SET description = 'Dexterity +2, Intelligence -2.' WHERE id = 273;
UPDATE ability SET description = 'Dexterity +2, Intelligence -2.' WHERE id = 283;
UPDATE ability SET description = 'Dexterity +2, Strength -2.' WHERE id = 257;
UPDATE ability SET description = 'Intelligence +2, Charisma +2.' WHERE id = 277;
UPDATE ability SET description = 'Intelligence +2, Strength -2.' WHERE id = 282;
UPDATE ability SET description = 'Strength +2, Intelligence -2.' WHERE id = 284;
UPDATE ability SET description = 'Strength +2, Intelligence -2, Charisma -2.' WHERE id = 255;
UPDATE ability SET description = 'Strength -2, Dexterity +2, Wisdom +2, Charisma -4.' WHERE id = 285;

UPDATE image SET id = id + 36 WHERE id > 3;
UPDATE charakter SET image_id = image_id + 36 WHERE image_id > 3;
UPDATE charakter SET thumb_image_id = thumb_image_id + 36 WHERE thumb_image_id > 3;
ALTER TABLE race ADD COLUMN image_id INTEGER NOT NULL DEFAULT 0;


UPDATE race SET image_id = 22 WHERE id = 0;
UPDATE race SET image_id = 6 WHERE id = 1;
UPDATE race SET image_id = 10 WHERE id = 2;
UPDATE race SET image_id = 12 WHERE id = 3;
UPDATE race SET image_id = 16 WHERE id = 4;
UPDATE race SET image_id = 32 WHERE id = 5;
UPDATE race SET image_id = 14 WHERE id = 6;
UPDATE race SET image_id = 7 WHERE id = 7;
UPDATE race SET image_id = 4 WHERE id = 8;
UPDATE race SET image_id = 7 WHERE id = 9;
UPDATE race SET image_id = 9 WHERE id = 10;
UPDATE race SET image_id = 11 WHERE id = 11;
UPDATE race SET image_id = 10 WHERE id = 12;
UPDATE race SET image_id = 9 WHERE id = 13;
UPDATE race SET image_id = 8 WHERE id = 14;
UPDATE race SET image_id = 15 WHERE id = 15;
UPDATE race SET image_id = 12 WHERE id = 16;
UPDATE race SET image_id = 14 WHERE id = 17;
UPDATE race SET image_id = 13 WHERE id = 18;
