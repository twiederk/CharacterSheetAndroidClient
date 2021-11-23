DROP TABLE save;


UPDATE weapon SET weight = 0.1 WHERE id = 16;


ALTER TABLE charakter ADD COLUMN save_str_misc_mod INTEGER NOT NULL DEFAULT 0;
ALTER TABLE charakter ADD COLUMN save_dex_misc_mod INTEGER NOT NULL DEFAULT 0;
ALTER TABLE charakter ADD COLUMN save_con_misc_mod INTEGER NOT NULL DEFAULT 0;
ALTER TABLE charakter ADD COLUMN save_int_misc_mod INTEGER NOT NULL DEFAULT 0;
ALTER TABLE charakter ADD COLUMN save_wis_misc_mod INTEGER NOT NULL DEFAULT 0;
ALTER TABLE charakter ADD COLUMN save_cha_misc_mod INTEGER NOT NULL DEFAULT 0;


UPDATE charakter SET save_str_misc_mod = fort_misc_mod;
UPDATE charakter SET save_dex_misc_mod = ref_misc_mod;
UPDATE charakter SET save_con_misc_mod = will_misc_mod;


ALTER TABLE charakter DROP COLUMN fort_misc_mod;
ALTER TABLE charakter DROP COLUMN ref_misc_mod;
ALTER TABLE charakter DROP COLUMN will_misc_mod;


UPDATE class SET saves = 5 WHERE id = 1;
UPDATE class SET saves = 34 WHERE id = 2;
UPDATE class SET saves = 48 WHERE id = 3;
UPDATE class SET saves = 24 WHERE id = 4;
UPDATE class SET saves = 5 WHERE id = 5;
UPDATE class SET saves = 3 WHERE id = 6;
UPDATE class SET saves = 48 WHERE id = 7;
UPDATE class SET saves = 3 WHERE id = 8;
UPDATE class SET saves = 10 WHERE id = 9;
UPDATE class SET saves = 36 WHERE id = 10;
UPDATE class SET saves = 48 WHERE id = 11;
UPDATE class SET saves = 24 WHERE id = 12;
