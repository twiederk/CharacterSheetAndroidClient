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

INSERT INTO xp_table VALUES (1, 'Slow');
INSERT INTO xp_table VALUES (2, 'Medium');
INSERT INTO xp_table VALUES (3, 'Fast');


INSERT INTO xp_level VALUES (1, 1, 1, 0);
INSERT INTO xp_level VALUES (2, 1, 2, 3000);
INSERT INTO xp_level VALUES (3, 1, 3, 7500);
INSERT INTO xp_level VALUES (4, 1, 4, 14000);
INSERT INTO xp_level VALUES (5, 1, 5, 23000);
INSERT INTO xp_level VALUES (6, 1, 6, 35000);
INSERT INTO xp_level VALUES (7, 1, 7, 53000);
INSERT INTO xp_level VALUES (8, 1, 8, 77000);
INSERT INTO xp_level VALUES (9, 1, 9, 115000);
INSERT INTO xp_level VALUES (10, 1, 10, 160000);
INSERT INTO xp_level VALUES (11, 1, 11, 235000);
INSERT INTO xp_level VALUES (12, 1, 12, 330000);
INSERT INTO xp_level VALUES (13, 1, 13, 475000);
INSERT INTO xp_level VALUES (14, 1, 14, 665000);
INSERT INTO xp_level VALUES (15, 1, 15, 955000);
INSERT INTO xp_level VALUES (16, 1, 16, 1350000);
INSERT INTO xp_level VALUES (17, 1, 17, 1900000);
INSERT INTO xp_level VALUES (18, 1, 18, 2700000);
INSERT INTO xp_level VALUES (19, 1, 19, 3850000);
INSERT INTO xp_level VALUES (20, 1, 20, 5350000);
INSERT INTO xp_level VALUES (21, 2, 1, 0);
INSERT INTO xp_level VALUES (22, 2, 2, 2000);
INSERT INTO xp_level VALUES (23, 2, 3, 5000);
INSERT INTO xp_level VALUES (24, 2, 4, 9000);
INSERT INTO xp_level VALUES (25, 2, 5, 15000);
INSERT INTO xp_level VALUES (26, 2, 6, 23000);
INSERT INTO xp_level VALUES (27, 2, 7, 35000);
INSERT INTO xp_level VALUES (28, 2, 8, 51000);
INSERT INTO xp_level VALUES (29, 2, 9, 75000);
INSERT INTO xp_level VALUES (30, 2, 10, 105000);
INSERT INTO xp_level VALUES (31, 2, 11, 155000);
INSERT INTO xp_level VALUES (32, 2, 12, 220000);
INSERT INTO xp_level VALUES (33, 2, 13, 315000);
INSERT INTO xp_level VALUES (34, 2, 14, 445000);
INSERT INTO xp_level VALUES (35, 2, 15, 635000);
INSERT INTO xp_level VALUES (36, 2, 16, 890000);
INSERT INTO xp_level VALUES (37, 2, 17, 1300000);
INSERT INTO xp_level VALUES (38, 2, 18, 1800000);
INSERT INTO xp_level VALUES (39, 2, 19, 2550000);
INSERT INTO xp_level VALUES (40, 2, 20, 3600000);
INSERT INTO xp_level VALUES (41, 3, 1, 0);
INSERT INTO xp_level VALUES (42, 3, 2, 1300);
INSERT INTO xp_level VALUES (43, 3, 3, 3300);
INSERT INTO xp_level VALUES (44, 3, 4, 6000);
INSERT INTO xp_level VALUES (45, 3, 5, 10000);
INSERT INTO xp_level VALUES (46, 3, 6, 15000);
INSERT INTO xp_level VALUES (47, 3, 7, 23000);
INSERT INTO xp_level VALUES (48, 3, 8, 34000);
INSERT INTO xp_level VALUES (49, 3, 9, 50000);
INSERT INTO xp_level VALUES (50, 3, 10, 71000);
INSERT INTO xp_level VALUES (51, 3, 11, 105000);
INSERT INTO xp_level VALUES (52, 3, 12, 145000);
INSERT INTO xp_level VALUES (53, 3, 13, 210000);
INSERT INTO xp_level VALUES (54, 3, 14, 295000);
INSERT INTO xp_level VALUES (55, 3, 15, 425000);
INSERT INTO xp_level VALUES (56, 3, 16, 600000);
INSERT INTO xp_level VALUES (57, 3, 17, 850000);
INSERT INTO xp_level VALUES (58, 3, 18, 1200000);
INSERT INTO xp_level VALUES (59, 3, 19, 1700000);
INSERT INTO xp_level VALUES (60, 3, 20, 2400000);

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

UPDATE good SET weight = 1 WHERE id = 51 AND name = 'Rations, trail (per day)' AND weight = 11;

