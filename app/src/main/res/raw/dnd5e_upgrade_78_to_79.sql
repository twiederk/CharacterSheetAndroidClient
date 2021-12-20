DROP TABLE save;


UPDATE weapon SET weight = 0.1 WHERE id = 16;


CREATE TABLE charakter_new
(id 			      INTEGER,
 player 		    VARCHAR(30) NOT NULL,
 name 			    VARCHAR(30) NOT NULL,
 race_id       	INTEGER NOT NULL,
 sex_id         INTEGER NOT NULL,
 alignment_id  	INTEGER NOT NULL,
 xp_table_id    INTEGER NOT NULL DEFAULT 1,
 experience 	  INTEGER NOT NULL,
 strength   	  INTEGER NOT NULL,
 dexterity  	  INTEGER NOT NULL,
 constitution 	INTEGER NOT NULL,
 intelligence	  INTEGER NOT NULL,
 wisdom			    INTEGER NOT NULL,
 charisma		    INTEGER NOT NULL,
 hitpoints		  INTEGER NOT NULL,
 max_hitpoints	INTEGER NOT NULL,
 armor      	  INTEGER NOT NULL,
 ini_misc_mod   INTEGER NOT NULL,
 cmb_mod    INTEGER NOT NULL DEFAULT 0,
 cmd_mod    INTEGER NOT NULL DEFAULT 0,
 save_str_misc_mod  INTEGER NOT NULL DEFAULT 0,
 save_dex_misc_mod  INTEGER NOT NULL DEFAULT 0,
 save_con_misc_mod  INTEGER NOT NULL DEFAULT 0,
 save_int_misc_mod  INTEGER NOT NULL DEFAULT 0,
 save_wis_misc_mod  INTEGER NOT NULL DEFAULT 0,
 save_cha_misc_mod  INTEGER NOT NULL DEFAULT 0,
 image_id       INTEGER,
 thumb_image_id INTEGER,
 platinum       INTEGER NOT NULL DEFAULT 0,
 gold           INTEGER NOT NULL DEFAULT 0,
 silver         INTEGER NOT NULL DEFAULT 0,
 copper         INTEGER NOT NULL DEFAULT 0,
 PRIMARY KEY (id),
 FOREIGN KEY (race_id) REFERENCES race(id) ON DELETE CASCADE,
 FOREIGN KEY (sex_id) REFERENCES sex(id) ON DELETE CASCADE,
 FOREIGN KEY (alignment_id) REFERENCES alignment(id) ON DELETE CASCADE,
 FOREIGN KEY (image_id) REFERENCES image(id) ON DELETE CASCADE,
 FOREIGN KEY (thumb_image_id) REFERENCES image(id) ON DELETE CASCADE
);


INSERT INTO charakter_new (id, player, name, race_id, sex_id, alignment_id, xp_table_id, experience, strength, dexterity, constitution, intelligence, wisdom, charisma, hitpoints, max_hitpoints, armor, ini_misc_mod, cmb_mod, cmd_mod, save_str_misc_mod, save_dex_misc_mod, save_con_misc_mod, image_id, thumb_image_id, platinum, gold, silver, copper)
SELECT                     id, player, name, race_id, sex_id, alignment_id, xp_table_id, experience, strength, dexterity, constitution, intelligence, wisdom, charisma, hitpoints, max_hitpoints, armor, ini_misc_mod, cmb_mod, cmd_mod, fort_misc_mod,     ref_misc_mod,      will_misc_mod,     image_id, thumb_image_id, platinum, gold, silver, copper FROM charakter;


DROP TABLE charakter;


ALTER TABLE charakter_new RENAME TO charakter;


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
