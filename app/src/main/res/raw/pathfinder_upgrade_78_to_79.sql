DROP TABLE save;


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


