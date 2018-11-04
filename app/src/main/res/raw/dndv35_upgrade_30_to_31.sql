CREATE TABLE charakter_spell
(id                INTEGER NOT NULL,
 charakter_id      INTEGER NOT NULL,
 spell_id          INTEGER NOT NULL,
 PRIMARY KEY(id),
 FOREIGN KEY (charakter_id) REFERENCES charakter(id),
 FOREIGN KEY (spell_id) REFERENCES spell(id)
);

CREATE TABLE xp_table
(id             INTEGER,
 name           VARCHAR(15),
 PRIMARY KEY(id)
);

CREATE TABLE xp_level
(id             INTEGER,
 xp_table_id    INTEGER,
 level          INTEGER NOT NULL,
 xp             INTEGER NOT NULL DEFAULT 0,
 PRIMARY KEY(id)
 FOREIGN KEY (xp_table_id) REFERENCES xp_table(id) ON DELETE CASCADE
);

INSERT INTO xp_table VALUES (1, 'Normal');

INSERT INTO xp_level VALUES (1, 1, 1, 0);
INSERT INTO xp_level VALUES (2, 1, 2, 1000);
INSERT INTO xp_level VALUES (3, 1, 3, 3000);
INSERT INTO xp_level VALUES (4, 1, 4, 6000);
INSERT INTO xp_level VALUES (5, 1, 5, 10000);
INSERT INTO xp_level VALUES (6, 1, 6, 15000);
INSERT INTO xp_level VALUES (7, 1, 7, 21000);
INSERT INTO xp_level VALUES (8, 1, 8, 28000);
INSERT INTO xp_level VALUES (9, 1, 9, 36000);
INSERT INTO xp_level VALUES (10, 1, 10, 45000);
INSERT INTO xp_level VALUES (11, 1, 11, 55000);
INSERT INTO xp_level VALUES (12, 1, 12, 66000);
INSERT INTO xp_level VALUES (13, 1, 13, 78000);
INSERT INTO xp_level VALUES (14, 1, 14, 91000);
INSERT INTO xp_level VALUES (15, 1, 15, 105000);
INSERT INTO xp_level VALUES (16, 1, 16, 120000);
INSERT INTO xp_level VALUES (17, 1, 17, 136000);
INSERT INTO xp_level VALUES (18, 1, 18, 153000);
INSERT INTO xp_level VALUES (19, 1, 19, 171000);
INSERT INTO xp_level VALUES (20, 1, 20, 190000);

CREATE TABLE charakter_temp
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
 fort_misc_mod  INTEGER NOT NULL,
 ref_misc_mod   INTEGER NOT NULL,
 will_misc_mod  INTEGER NOT NULL,
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

INSERT INTO charakter_temp (id, player, name, race_id, sex_id, alignment_id, xp_table_id, experience, strength, dexterity, constitution, intelligence, wisdom, charisma, hitpoints, max_hitpoints, armor, ini_misc_mod, cmb_mod, cmd_mod, fort_misc_mod, ref_misc_mod, will_misc_mod, image_id, thumb_image_id, platinum, gold, silver, copper) SELECT id, player, name, race_id, sex_id, alignment_id, 1, experience, strength, dexterity, constitution, intelligence, wisdom, charisma, hitpoints, max_hitpoints, armor, ini_misc_mod, grapple_mod, 0, fort_misc_mod, ref_misc_mod, will_misc_mod, image_id, thumb_image_id, platinum, gold, silver, copper FROM charakter;
DROP TABLE charakter;
ALTER TABLE charakter_temp RENAME TO charakter;


