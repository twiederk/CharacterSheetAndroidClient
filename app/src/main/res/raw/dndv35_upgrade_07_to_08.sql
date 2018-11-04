ALTER TABLE skill RENAME TO skill_old;
ALTER TABLE charakter_class RENAME TO charakter_class_old;

CREATE TABLE save
(id             INTEGER,
 name           VARCHAR(15),
 PRIMARY KEY(id)
);
INSERT INTO "save" VALUES (0,"Fortitude");
INSERT INTO "save" VALUES (1,"Reflex");
INSERT INTO "save" VALUES (2,"Will");

CREATE TABLE skill
(id           INTEGER,
 ability_id   INTEGER,
 untrained    INTEGER,
 PRIMARY KEY(id),
 FOREIGN KEY (ability_id) REFERENCES ability(id)
);
INSERT INTO "skill" (id, ability_id, untrained) SELECT id, ability_id, untrained FROM skill_old;

CREATE TABLE charakter_class
(id                       INTEGER,
 base_attack_bonus        INTEGER,
 skill_points_per_level   INTEGER,
 PRIMARY KEY (id)
);
INSERT INTO "charakter_class" VALUES (0, 2, 4);
INSERT INTO "charakter_class" VALUES (1, 1, 6);
INSERT INTO "charakter_class" VALUES (2, 1, 2);
INSERT INTO "charakter_class" VALUES (3, 1, 4);
INSERT INTO "charakter_class" VALUES (4, 2, 2);
INSERT INTO "charakter_class" VALUES (5, 1, 4);
INSERT INTO "charakter_class" VALUES (6, 2, 2);
INSERT INTO "charakter_class" VALUES (7, 2, 6);
INSERT INTO "charakter_class" VALUES (8, 1, 8);
INSERT INTO "charakter_class" VALUES (9, 0, 2);
INSERT INTO "charakter_class" VALUES (10, 0, 2);
INSERT INTO "charakter_class" VALUES (11, 2, 4);
INSERT INTO "charakter_class" VALUES (12, 0, 4);
INSERT INTO "charakter_class" VALUES (13, 1, 2);
INSERT INTO "charakter_class" VALUES (14, 1, 4);
INSERT INTO "charakter_class" VALUES (15, 2, 2);
INSERT INTO "charakter_class" VALUES (16, 0, 2);
INSERT INTO "charakter_class" VALUES (17, 2, 4);
INSERT INTO "charakter_class" VALUES (18, 2, 2);
INSERT INTO "charakter_class" VALUES (19, 2, 2);
INSERT INTO "charakter_class" VALUES (20, 0, 2);
INSERT INTO "charakter_class" VALUES (21, 2, 4);
INSERT INTO "charakter_class" VALUES (22, 1, 4);
INSERT INTO "charakter_class" VALUES (23, 0, 2);
INSERT INTO "charakter_class" VALUES (24, 0, 2);
INSERT INTO "charakter_class" VALUES (25, 1, 6);
INSERT INTO "charakter_class" VALUES (26, 0, 2);


CREATE TABLE charakter_class_save
(charakter_class_id       INTEGER,
 save_id                  INTEGER,
 PRIMARY KEY(charakter_class_id, save_id),
 FOREIGN KEY (charakter_class_id) REFERENCES charakter_class(id) ON DELETE CASCADE,
 FOREIGN KEY (save_id) REFERENCES save(id) ON DELETE CASCADE
);
INSERT INTO charakter_class_save VALUES (0, 0);
INSERT INTO charakter_class_save VALUES (1, 1);
INSERT INTO charakter_class_save VALUES (1, 2);
INSERT INTO charakter_class_save VALUES (2, 0);
INSERT INTO charakter_class_save VALUES (2, 2);
INSERT INTO charakter_class_save VALUES (3, 0);
INSERT INTO charakter_class_save VALUES (3, 2);
INSERT INTO charakter_class_save VALUES (4, 0);
INSERT INTO charakter_class_save VALUES (5, 0);
INSERT INTO charakter_class_save VALUES (5, 1);
INSERT INTO charakter_class_save VALUES (5, 2);
INSERT INTO charakter_class_save VALUES (6, 0);
INSERT INTO charakter_class_save VALUES (7, 0);
INSERT INTO charakter_class_save VALUES (7, 1);
INSERT INTO charakter_class_save VALUES (8, 1);
INSERT INTO charakter_class_save VALUES (9, 2);
INSERT INTO charakter_class_save VALUES (10, 2);
INSERT INTO charakter_class_save VALUES (11, 0);
INSERT INTO charakter_class_save VALUES (11, 1);
INSERT INTO charakter_class_save VALUES (12, 1);
INSERT INTO charakter_class_save VALUES (12, 2);
INSERT INTO charakter_class_save VALUES (13, 2);
INSERT INTO charakter_class_save VALUES (14, 1);
INSERT INTO charakter_class_save VALUES (15, 0);
INSERT INTO charakter_class_save VALUES (16, 2);
INSERT INTO charakter_class_save VALUES (17, 1);
INSERT INTO charakter_class_save VALUES (18, 0);
INSERT INTO charakter_class_save VALUES (18, 2);
INSERT INTO charakter_class_save VALUES (19, 0);
INSERT INTO charakter_class_save VALUES (20, 0);
INSERT INTO charakter_class_save VALUES (20, 2);
INSERT INTO charakter_class_save VALUES (21, 0);
INSERT INTO charakter_class_save VALUES (22, 2);
INSERT INTO charakter_class_save VALUES (23, 2);
INSERT INTO charakter_class_save VALUES (24, 2);
INSERT INTO charakter_class_save VALUES (25, 1);
INSERT INTO charakter_class_save VALUES (26, 2);


CREATE TABLE charakter_class_alignment
(charakter_class_id       INTEGER,
 alignment_id             INTEGER,
 PRIMARY KEY(charakter_class_id, alignment_id),
 FOREIGN KEY (charakter_class_id) REFERENCES charakter_class(id) ON DELETE CASCADE,
 FOREIGN KEY (alignment_id) REFERENCES alignment(id) ON DELETE CASCADE
);
INSERT INTO "charakter_class_alignment" VALUES (0, 1);
INSERT INTO "charakter_class_alignment" VALUES (0, 2);
INSERT INTO "charakter_class_alignment" VALUES (0, 4);
INSERT INTO "charakter_class_alignment" VALUES (0, 5);
INSERT INTO "charakter_class_alignment" VALUES (0, 7);
INSERT INTO "charakter_class_alignment" VALUES (0, 8);

INSERT INTO "charakter_class_alignment" VALUES (1, 1);
INSERT INTO "charakter_class_alignment" VALUES (1, 2);
INSERT INTO "charakter_class_alignment" VALUES (1, 4);
INSERT INTO "charakter_class_alignment" VALUES (1, 5);
INSERT INTO "charakter_class_alignment" VALUES (1, 7);
INSERT INTO "charakter_class_alignment" VALUES (1, 8);

INSERT INTO "charakter_class_alignment" VALUES (2, 0);
INSERT INTO "charakter_class_alignment" VALUES (2, 1);
INSERT INTO "charakter_class_alignment" VALUES (2, 2);
INSERT INTO "charakter_class_alignment" VALUES (2, 3);
INSERT INTO "charakter_class_alignment" VALUES (2, 4);
INSERT INTO "charakter_class_alignment" VALUES (2, 5);
INSERT INTO "charakter_class_alignment" VALUES (2, 6);
INSERT INTO "charakter_class_alignment" VALUES (2, 7);
INSERT INTO "charakter_class_alignment" VALUES (2, 8);

INSERT INTO "charakter_class_alignment" VALUES (3, 1);
INSERT INTO "charakter_class_alignment" VALUES (3, 3);
INSERT INTO "charakter_class_alignment" VALUES (3, 4);
INSERT INTO "charakter_class_alignment" VALUES (3, 5);
INSERT INTO "charakter_class_alignment" VALUES (3, 7);

INSERT INTO "charakter_class_alignment" VALUES (4, 0);
INSERT INTO "charakter_class_alignment" VALUES (4, 1);
INSERT INTO "charakter_class_alignment" VALUES (4, 2);
INSERT INTO "charakter_class_alignment" VALUES (4, 3);
INSERT INTO "charakter_class_alignment" VALUES (4, 4);
INSERT INTO "charakter_class_alignment" VALUES (4, 5);
INSERT INTO "charakter_class_alignment" VALUES (4, 6);
INSERT INTO "charakter_class_alignment" VALUES (4, 7);
INSERT INTO "charakter_class_alignment" VALUES (4, 8);

INSERT INTO "charakter_class_alignment" VALUES (5, 0);
INSERT INTO "charakter_class_alignment" VALUES (5, 3);
INSERT INTO "charakter_class_alignment" VALUES (5, 6);

INSERT INTO "charakter_class_alignment" VALUES (6, 0);

INSERT INTO "charakter_class_alignment" VALUES (7, 0);
INSERT INTO "charakter_class_alignment" VALUES (7, 1);
INSERT INTO "charakter_class_alignment" VALUES (7, 2);
INSERT INTO "charakter_class_alignment" VALUES (7, 3);
INSERT INTO "charakter_class_alignment" VALUES (7, 4);
INSERT INTO "charakter_class_alignment" VALUES (7, 5);
INSERT INTO "charakter_class_alignment" VALUES (7, 6);
INSERT INTO "charakter_class_alignment" VALUES (7, 7);
INSERT INTO "charakter_class_alignment" VALUES (7, 8);

INSERT INTO "charakter_class_alignment" VALUES (8, 0);
INSERT INTO "charakter_class_alignment" VALUES (8, 1);
INSERT INTO "charakter_class_alignment" VALUES (8, 2);
INSERT INTO "charakter_class_alignment" VALUES (8, 3);
INSERT INTO "charakter_class_alignment" VALUES (8, 4);
INSERT INTO "charakter_class_alignment" VALUES (8, 5);
INSERT INTO "charakter_class_alignment" VALUES (8, 6);
INSERT INTO "charakter_class_alignment" VALUES (8, 7);
INSERT INTO "charakter_class_alignment" VALUES (8, 8);

INSERT INTO "charakter_class_alignment" VALUES (9, 0);
INSERT INTO "charakter_class_alignment" VALUES (9, 1);
INSERT INTO "charakter_class_alignment" VALUES (9, 2);
INSERT INTO "charakter_class_alignment" VALUES (9, 3);
INSERT INTO "charakter_class_alignment" VALUES (9, 4);
INSERT INTO "charakter_class_alignment" VALUES (9, 5);
INSERT INTO "charakter_class_alignment" VALUES (9, 6);
INSERT INTO "charakter_class_alignment" VALUES (9, 7);
INSERT INTO "charakter_class_alignment" VALUES (9, 8);

INSERT INTO "charakter_class_alignment" VALUES (10, 0);
INSERT INTO "charakter_class_alignment" VALUES (10, 1);
INSERT INTO "charakter_class_alignment" VALUES (10, 2);
INSERT INTO "charakter_class_alignment" VALUES (10, 3);
INSERT INTO "charakter_class_alignment" VALUES (10, 4);
INSERT INTO "charakter_class_alignment" VALUES (10, 5);
INSERT INTO "charakter_class_alignment" VALUES (10, 6);
INSERT INTO "charakter_class_alignment" VALUES (10, 7);
INSERT INTO "charakter_class_alignment" VALUES (10, 8);

INSERT INTO "charakter_class_alignment" VALUES (11, 0);
INSERT INTO "charakter_class_alignment" VALUES (11, 1);
INSERT INTO "charakter_class_alignment" VALUES (11, 2);
INSERT INTO "charakter_class_alignment" VALUES (11, 3);
INSERT INTO "charakter_class_alignment" VALUES (11, 4);
INSERT INTO "charakter_class_alignment" VALUES (11, 5);
INSERT INTO "charakter_class_alignment" VALUES (11, 6);
INSERT INTO "charakter_class_alignment" VALUES (11, 7);
INSERT INTO "charakter_class_alignment" VALUES (11, 8);

INSERT INTO "charakter_class_alignment" VALUES (12, 1);
INSERT INTO "charakter_class_alignment" VALUES (12, 2);
INSERT INTO "charakter_class_alignment" VALUES (12, 4);
INSERT INTO "charakter_class_alignment" VALUES (12, 5);
INSERT INTO "charakter_class_alignment" VALUES (12, 7);
INSERT INTO "charakter_class_alignment" VALUES (12, 8);

INSERT INTO "charakter_class_alignment" VALUES (13, 0);
INSERT INTO "charakter_class_alignment" VALUES (13, 1);
INSERT INTO "charakter_class_alignment" VALUES (13, 2);
INSERT INTO "charakter_class_alignment" VALUES (13, 3);
INSERT INTO "charakter_class_alignment" VALUES (13, 4);
INSERT INTO "charakter_class_alignment" VALUES (13, 5);
INSERT INTO "charakter_class_alignment" VALUES (13, 6);
INSERT INTO "charakter_class_alignment" VALUES (13, 7);
INSERT INTO "charakter_class_alignment" VALUES (13, 8);

INSERT INTO "charakter_class_alignment" VALUES (14, 6);
INSERT INTO "charakter_class_alignment" VALUES (14, 7);
INSERT INTO "charakter_class_alignment" VALUES (14, 8);

INSERT INTO "charakter_class_alignment" VALUES (15, 6);
INSERT INTO "charakter_class_alignment" VALUES (15, 7);
INSERT INTO "charakter_class_alignment" VALUES (15, 8);

INSERT INTO "charakter_class_alignment" VALUES (16, 0);
INSERT INTO "charakter_class_alignment" VALUES (16, 1);
INSERT INTO "charakter_class_alignment" VALUES (16, 2);
INSERT INTO "charakter_class_alignment" VALUES (16, 3);
INSERT INTO "charakter_class_alignment" VALUES (16, 4);
INSERT INTO "charakter_class_alignment" VALUES (16, 5);
INSERT INTO "charakter_class_alignment" VALUES (16, 6);
INSERT INTO "charakter_class_alignment" VALUES (16, 7);
INSERT INTO "charakter_class_alignment" VALUES (16, 8);

INSERT INTO "charakter_class_alignment" VALUES (17, 0);
INSERT INTO "charakter_class_alignment" VALUES (17, 1);
INSERT INTO "charakter_class_alignment" VALUES (17, 2);
INSERT INTO "charakter_class_alignment" VALUES (17, 3);
INSERT INTO "charakter_class_alignment" VALUES (17, 4);
INSERT INTO "charakter_class_alignment" VALUES (17, 5);
INSERT INTO "charakter_class_alignment" VALUES (17, 6);
INSERT INTO "charakter_class_alignment" VALUES (17, 7);
INSERT INTO "charakter_class_alignment" VALUES (17, 8);

INSERT INTO "charakter_class_alignment" VALUES (18, 0);
INSERT INTO "charakter_class_alignment" VALUES (18, 3);
INSERT INTO "charakter_class_alignment" VALUES (18, 6);

INSERT INTO "charakter_class_alignment" VALUES (19, 0);
INSERT INTO "charakter_class_alignment" VALUES (19, 1);
INSERT INTO "charakter_class_alignment" VALUES (19, 2);
INSERT INTO "charakter_class_alignment" VALUES (19, 3);
INSERT INTO "charakter_class_alignment" VALUES (19, 4);
INSERT INTO "charakter_class_alignment" VALUES (19, 5);
INSERT INTO "charakter_class_alignment" VALUES (19, 6);
INSERT INTO "charakter_class_alignment" VALUES (19, 7);
INSERT INTO "charakter_class_alignment" VALUES (19, 8);

INSERT INTO "charakter_class_alignment" VALUES (20, 0);
INSERT INTO "charakter_class_alignment" VALUES (20, 1);
INSERT INTO "charakter_class_alignment" VALUES (20, 2);
INSERT INTO "charakter_class_alignment" VALUES (20, 3);
INSERT INTO "charakter_class_alignment" VALUES (20, 4);
INSERT INTO "charakter_class_alignment" VALUES (20, 5);
INSERT INTO "charakter_class_alignment" VALUES (20, 6);
INSERT INTO "charakter_class_alignment" VALUES (20, 7);
INSERT INTO "charakter_class_alignment" VALUES (20, 8);

INSERT INTO "charakter_class_alignment" VALUES (21, 0);
INSERT INTO "charakter_class_alignment" VALUES (21, 1);
INSERT INTO "charakter_class_alignment" VALUES (21, 2);
INSERT INTO "charakter_class_alignment" VALUES (21, 3);
INSERT INTO "charakter_class_alignment" VALUES (21, 4);
INSERT INTO "charakter_class_alignment" VALUES (21, 5);
INSERT INTO "charakter_class_alignment" VALUES (21, 6);
INSERT INTO "charakter_class_alignment" VALUES (21, 7);
INSERT INTO "charakter_class_alignment" VALUES (21, 8);

INSERT INTO "charakter_class_alignment" VALUES (22, 0);
INSERT INTO "charakter_class_alignment" VALUES (22, 1);
INSERT INTO "charakter_class_alignment" VALUES (22, 2);
INSERT INTO "charakter_class_alignment" VALUES (22, 3);
INSERT INTO "charakter_class_alignment" VALUES (22, 4);
INSERT INTO "charakter_class_alignment" VALUES (22, 5);
INSERT INTO "charakter_class_alignment" VALUES (22, 6);
INSERT INTO "charakter_class_alignment" VALUES (22, 7);
INSERT INTO "charakter_class_alignment" VALUES (22, 8);

INSERT INTO "charakter_class_alignment" VALUES (23, 0);
INSERT INTO "charakter_class_alignment" VALUES (23, 1);
INSERT INTO "charakter_class_alignment" VALUES (23, 2);
INSERT INTO "charakter_class_alignment" VALUES (23, 3);
INSERT INTO "charakter_class_alignment" VALUES (23, 4);
INSERT INTO "charakter_class_alignment" VALUES (23, 5);
INSERT INTO "charakter_class_alignment" VALUES (23, 6);
INSERT INTO "charakter_class_alignment" VALUES (23, 7);
INSERT INTO "charakter_class_alignment" VALUES (23, 8);

INSERT INTO "charakter_class_alignment" VALUES (24, 0);
INSERT INTO "charakter_class_alignment" VALUES (24, 1);
INSERT INTO "charakter_class_alignment" VALUES (24, 2);
INSERT INTO "charakter_class_alignment" VALUES (24, 3);
INSERT INTO "charakter_class_alignment" VALUES (24, 4);
INSERT INTO "charakter_class_alignment" VALUES (24, 5);
INSERT INTO "charakter_class_alignment" VALUES (24, 6);
INSERT INTO "charakter_class_alignment" VALUES (24, 7);
INSERT INTO "charakter_class_alignment" VALUES (24, 8);

INSERT INTO "charakter_class_alignment" VALUES (25, 0);
INSERT INTO "charakter_class_alignment" VALUES (25, 1);
INSERT INTO "charakter_class_alignment" VALUES (25, 2);
INSERT INTO "charakter_class_alignment" VALUES (25, 3);
INSERT INTO "charakter_class_alignment" VALUES (25, 4);
INSERT INTO "charakter_class_alignment" VALUES (25, 5);
INSERT INTO "charakter_class_alignment" VALUES (25, 6);
INSERT INTO "charakter_class_alignment" VALUES (25, 7);
INSERT INTO "charakter_class_alignment" VALUES (25, 8);

INSERT INTO "charakter_class_alignment" VALUES (26, 0);
INSERT INTO "charakter_class_alignment" VALUES (26, 1);
INSERT INTO "charakter_class_alignment" VALUES (26, 2);
INSERT INTO "charakter_class_alignment" VALUES (26, 3);
INSERT INTO "charakter_class_alignment" VALUES (26, 4);
INSERT INTO "charakter_class_alignment" VALUES (26, 5);
INSERT INTO "charakter_class_alignment" VALUES (26, 6);
INSERT INTO "charakter_class_alignment" VALUES (26, 7);
INSERT INTO "charakter_class_alignment" VALUES (26, 8);


CREATE TABLE charakter_class_skill
(charakter_class_id    INTEGER,
 skill_id              INTEGER,
 PRIMARY KEY (charakter_class_id, skill_id),
 FOREIGN KEY (charakter_class_id) REFERENCES charakter_class(id) ON DELETE CASCADE,
 FOREIGN KEY (skill_id) REFERENCES skill(id) ON DELETE CASCADE
);
INSERT INTO charakter_class_skill (charakter_class_id, skill_id) SELECT 0, id FROM skill_old WHERE barbarian = 1;
INSERT INTO charakter_class_skill (charakter_class_id, skill_id) SELECT 1, id FROM skill_old WHERE bard = 1;
INSERT INTO charakter_class_skill (charakter_class_id, skill_id) SELECT 2, id FROM skill_old WHERE cleric = 1;
INSERT INTO charakter_class_skill (charakter_class_id, skill_id) SELECT 3, id FROM skill_old WHERE druid = 1;
INSERT INTO charakter_class_skill (charakter_class_id, skill_id) SELECT 4, id FROM skill_old WHERE fighter = 1;
INSERT INTO charakter_class_skill (charakter_class_id, skill_id) SELECT 5, id FROM skill_old WHERE monk = 1;
INSERT INTO charakter_class_skill (charakter_class_id, skill_id) SELECT 6, id FROM skill_old WHERE paladin = 1;
INSERT INTO charakter_class_skill (charakter_class_id, skill_id) SELECT 7, id FROM skill_old WHERE ranger = 1;
INSERT INTO charakter_class_skill (charakter_class_id, skill_id) SELECT 8, id FROM skill_old WHERE rogue = 1;
INSERT INTO charakter_class_skill (charakter_class_id, skill_id) SELECT 9, id FROM skill_old WHERE sorcerer = 1;
INSERT INTO charakter_class_skill (charakter_class_id, skill_id) SELECT 10, id FROM skill_old WHERE wizard = 1;

INSERT INTO "charakter_class_skill" VALUES (11, 6);
INSERT INTO "charakter_class_skill" VALUES (11, 16);
INSERT INTO "charakter_class_skill" VALUES (11, 29);
INSERT INTO "charakter_class_skill" VALUES (11, 30);
INSERT INTO "charakter_class_skill" VALUES (11, 34);
INSERT INTO "charakter_class_skill" VALUES (11, 40);
INSERT INTO "charakter_class_skill" VALUES (11, 41);
INSERT INTO "charakter_class_skill" VALUES (11, 45);

INSERT INTO "charakter_class_skill" VALUES (12, 5);
INSERT INTO "charakter_class_skill" VALUES (12, 6);
INSERT INTO "charakter_class_skill" VALUES (12, 7);
INSERT INTO "charakter_class_skill" VALUES (12, 8);
INSERT INTO "charakter_class_skill" VALUES (12, 9);
INSERT INTO "charakter_class_skill" VALUES (12, 10);
INSERT INTO "charakter_class_skill" VALUES (12, 11);
INSERT INTO "charakter_class_skill" VALUES (12, 13);
INSERT INTO "charakter_class_skill" VALUES (12, 16);
INSERT INTO "charakter_class_skill" VALUES (12, 18);
INSERT INTO "charakter_class_skill" VALUES (12, 19);
INSERT INTO "charakter_class_skill" VALUES (12, 20);
INSERT INTO "charakter_class_skill" VALUES (12, 21);
INSERT INTO "charakter_class_skill" VALUES (12, 22);
INSERT INTO "charakter_class_skill" VALUES (12, 23);
INSERT INTO "charakter_class_skill" VALUES (12, 24);
INSERT INTO "charakter_class_skill" VALUES (12, 25);
INSERT INTO "charakter_class_skill" VALUES (12, 26);
INSERT INTO "charakter_class_skill" VALUES (12, 27);
INSERT INTO "charakter_class_skill" VALUES (12, 28);
INSERT INTO "charakter_class_skill" VALUES (12, 29);
INSERT INTO "charakter_class_skill" VALUES (12, 30);
INSERT INTO "charakter_class_skill" VALUES (12, 31);
INSERT INTO "charakter_class_skill" VALUES (12, 33);
INSERT INTO "charakter_class_skill" VALUES (12, 36);
INSERT INTO "charakter_class_skill" VALUES (12, 35);
INSERT INTO "charakter_class_skill" VALUES (12, 37);
INSERT INTO "charakter_class_skill" VALUES (12, 39);
INSERT INTO "charakter_class_skill" VALUES (12, 42);
INSERT INTO "charakter_class_skill" VALUES (12, 43);
INSERT INTO "charakter_class_skill" VALUES (12, 45);

INSERT INTO "charakter_class_skill" VALUES (13, 5);
INSERT INTO "charakter_class_skill" VALUES (13, 6);
INSERT INTO "charakter_class_skill" VALUES (13, 19);
INSERT INTO "charakter_class_skill" VALUES (13, 20);
INSERT INTO "charakter_class_skill" VALUES (13, 21);
INSERT INTO "charakter_class_skill" VALUES (13, 22);
INSERT INTO "charakter_class_skill" VALUES (13, 23);
INSERT INTO "charakter_class_skill" VALUES (13, 24);
INSERT INTO "charakter_class_skill" VALUES (13, 25);
INSERT INTO "charakter_class_skill" VALUES (13, 26);
INSERT INTO "charakter_class_skill" VALUES (13, 27);
INSERT INTO "charakter_class_skill" VALUES (13, 28);
INSERT INTO "charakter_class_skill" VALUES (13, 33);
INSERT INTO "charakter_class_skill" VALUES (13, 35);
INSERT INTO "charakter_class_skill" VALUES (13, 39);

INSERT INTO "charakter_class_skill" VALUES (14, 2);
INSERT INTO "charakter_class_skill" VALUES (14, 3);
INSERT INTO "charakter_class_skill" VALUES (14, 4);
INSERT INTO "charakter_class_skill" VALUES (14, 6);
INSERT INTO "charakter_class_skill" VALUES (14, 7);
INSERT INTO "charakter_class_skill" VALUES (14, 8);
INSERT INTO "charakter_class_skill" VALUES (14, 9);
INSERT INTO "charakter_class_skill" VALUES (14, 10);
INSERT INTO "charakter_class_skill" VALUES (14, 11);
INSERT INTO "charakter_class_skill" VALUES (14, 12);
INSERT INTO "charakter_class_skill" VALUES (14, 13);
INSERT INTO "charakter_class_skill" VALUES (14, 16);
INSERT INTO "charakter_class_skill" VALUES (14, 17);
INSERT INTO "charakter_class_skill" VALUES (14, 18);
INSERT INTO "charakter_class_skill" VALUES (14, 29);
INSERT INTO "charakter_class_skill" VALUES (14, 30);
INSERT INTO "charakter_class_skill" VALUES (14, 31);
INSERT INTO "charakter_class_skill" VALUES (14, 35);
INSERT INTO "charakter_class_skill" VALUES (14, 36);
INSERT INTO "charakter_class_skill" VALUES (14, 37);
INSERT INTO "charakter_class_skill" VALUES (14, 40);
INSERT INTO "charakter_class_skill" VALUES (14, 42);
INSERT INTO "charakter_class_skill" VALUES (14, 43);
INSERT INTO "charakter_class_skill" VALUES (14, 44);
INSERT INTO "charakter_class_skill" VALUES (14, 45);

INSERT INTO "charakter_class_skill" VALUES (15, 5);
INSERT INTO "charakter_class_skill" VALUES (15, 6);
INSERT INTO "charakter_class_skill" VALUES (15, 8);
INSERT INTO "charakter_class_skill" VALUES (15, 14);
INSERT INTO "charakter_class_skill" VALUES (15, 15);
INSERT INTO "charakter_class_skill" VALUES (15, 16);
INSERT INTO "charakter_class_skill" VALUES (15, 17);
INSERT INTO "charakter_class_skill" VALUES (15, 27);
INSERT INTO "charakter_class_skill" VALUES (15, 33);
INSERT INTO "charakter_class_skill" VALUES (15, 34);

INSERT INTO "charakter_class_skill" VALUES (16, 5);
INSERT INTO "charakter_class_skill" VALUES (16, 6);
INSERT INTO "charakter_class_skill" VALUES (16, 8);
INSERT INTO "charakter_class_skill" VALUES (16, 11);
INSERT INTO "charakter_class_skill" VALUES (16, 13);
INSERT INTO "charakter_class_skill" VALUES (16, 19);
INSERT INTO "charakter_class_skill" VALUES (16, 20);
INSERT INTO "charakter_class_skill" VALUES (16, 21);
INSERT INTO "charakter_class_skill" VALUES (16, 22);
INSERT INTO "charakter_class_skill" VALUES (16, 23);
INSERT INTO "charakter_class_skill" VALUES (16, 24);
INSERT INTO "charakter_class_skill" VALUES (16, 25);
INSERT INTO "charakter_class_skill" VALUES (16, 26);
INSERT INTO "charakter_class_skill" VALUES (16, 27);
INSERT INTO "charakter_class_skill" VALUES (16, 28);
INSERT INTO "charakter_class_skill" VALUES (16, 29);
INSERT INTO "charakter_class_skill" VALUES (16, 33);
INSERT INTO "charakter_class_skill" VALUES (16, 35);
INSERT INTO "charakter_class_skill" VALUES (16, 39);
INSERT INTO "charakter_class_skill" VALUES (16, 40);

INSERT INTO "charakter_class_skill" VALUES (17, 2);
INSERT INTO "charakter_class_skill" VALUES (17, 3);
INSERT INTO "charakter_class_skill" VALUES (17, 11);
INSERT INTO "charakter_class_skill" VALUES (17, 18);
INSERT INTO "charakter_class_skill" VALUES (17, 29);
INSERT INTO "charakter_class_skill" VALUES (17, 32);
INSERT INTO "charakter_class_skill" VALUES (17, 36);
INSERT INTO "charakter_class_skill" VALUES (17, 40);
INSERT INTO "charakter_class_skill" VALUES (17, 43);

INSERT INTO "charakter_class_skill" VALUES (18, 6);
INSERT INTO "charakter_class_skill" VALUES (18, 29);
INSERT INTO "charakter_class_skill" VALUES (18, 36);
INSERT INTO "charakter_class_skill" VALUES (18, 40);

INSERT INTO "charakter_class_skill" VALUES (19, 5);
INSERT INTO "charakter_class_skill" VALUES (19, 6);
INSERT INTO "charakter_class_skill" VALUES (19, 7);
INSERT INTO "charakter_class_skill" VALUES (19, 18);
INSERT INTO "charakter_class_skill" VALUES (19, 19);
INSERT INTO "charakter_class_skill" VALUES (19, 26);
INSERT INTO "charakter_class_skill" VALUES (19, 34);
INSERT INTO "charakter_class_skill" VALUES (19, 36);
INSERT INTO "charakter_class_skill" VALUES (19, 39);
INSERT INTO "charakter_class_skill" VALUES (19, 42);

INSERT INTO "charakter_class_skill" VALUES (20, 5);
INSERT INTO "charakter_class_skill" VALUES (20, 6);
INSERT INTO "charakter_class_skill" VALUES (20, 8);
INSERT INTO "charakter_class_skill" VALUES (20, 15);
INSERT INTO "charakter_class_skill" VALUES (20, 19);
INSERT INTO "charakter_class_skill" VALUES (20, 37);
INSERT INTO "charakter_class_skill" VALUES (20, 33);
INSERT INTO "charakter_class_skill" VALUES (20, 39);

INSERT INTO "charakter_class_skill" VALUES (21, 2);
INSERT INTO "charakter_class_skill" VALUES (21, 4);
INSERT INTO "charakter_class_skill" VALUES (21, 8);
INSERT INTO "charakter_class_skill" VALUES (21, 14);
INSERT INTO "charakter_class_skill" VALUES (21, 16);
INSERT INTO "charakter_class_skill" VALUES (21, 22);
INSERT INTO "charakter_class_skill" VALUES (21, 29);
INSERT INTO "charakter_class_skill" VALUES (21, 30);
INSERT INTO "charakter_class_skill" VALUES (21, 33);
INSERT INTO "charakter_class_skill" VALUES (21, 34);
INSERT INTO "charakter_class_skill" VALUES (21, 40);
INSERT INTO "charakter_class_skill" VALUES (21, 41);

INSERT INTO "charakter_class_skill" VALUES (22, 1);
INSERT INTO "charakter_class_skill" VALUES (22, 5);
INSERT INTO "charakter_class_skill" VALUES (22, 6);
INSERT INTO "charakter_class_skill" VALUES (22, 7);
INSERT INTO "charakter_class_skill" VALUES (22, 13);
INSERT INTO "charakter_class_skill" VALUES (22, 14);
INSERT INTO "charakter_class_skill" VALUES (22, 15);
INSERT INTO "charakter_class_skill" VALUES (22, 19);
INSERT INTO "charakter_class_skill" VALUES (22, 20);
INSERT INTO "charakter_class_skill" VALUES (22, 21);
INSERT INTO "charakter_class_skill" VALUES (22, 22);
INSERT INTO "charakter_class_skill" VALUES (22, 23);
INSERT INTO "charakter_class_skill" VALUES (22, 24);
INSERT INTO "charakter_class_skill" VALUES (22, 25);
INSERT INTO "charakter_class_skill" VALUES (22, 26);
INSERT INTO "charakter_class_skill" VALUES (22, 27);
INSERT INTO "charakter_class_skill" VALUES (22, 28);
INSERT INTO "charakter_class_skill" VALUES (22, 32);
INSERT INTO "charakter_class_skill" VALUES (22, 33);
INSERT INTO "charakter_class_skill" VALUES (22, 39);
INSERT INTO "charakter_class_skill" VALUES (22, 44);

INSERT INTO "charakter_class_skill" VALUES (23, 5);
INSERT INTO "charakter_class_skill" VALUES (23, 6);
INSERT INTO "charakter_class_skill" VALUES (23, 7);
INSERT INTO "charakter_class_skill" VALUES (23, 19);
INSERT INTO "charakter_class_skill" VALUES (23, 27);
INSERT INTO "charakter_class_skill" VALUES (23, 33);
INSERT INTO "charakter_class_skill" VALUES (23, 36);
INSERT INTO "charakter_class_skill" VALUES (23, 39);

INSERT INTO "charakter_class_skill" VALUES (24, 3);
INSERT INTO "charakter_class_skill" VALUES (24, 5);
INSERT INTO "charakter_class_skill" VALUES (24, 6);
INSERT INTO "charakter_class_skill" VALUES (24, 17);
INSERT INTO "charakter_class_skill" VALUES (24, 19);
INSERT INTO "charakter_class_skill" VALUES (24, 20);
INSERT INTO "charakter_class_skill" VALUES (24, 21);
INSERT INTO "charakter_class_skill" VALUES (24, 22);
INSERT INTO "charakter_class_skill" VALUES (24, 23);
INSERT INTO "charakter_class_skill" VALUES (24, 24);
INSERT INTO "charakter_class_skill" VALUES (24, 25);
INSERT INTO "charakter_class_skill" VALUES (24, 26);
INSERT INTO "charakter_class_skill" VALUES (24, 27);
INSERT INTO "charakter_class_skill" VALUES (24, 28);
INSERT INTO "charakter_class_skill" VALUES (24, 33);
INSERT INTO "charakter_class_skill" VALUES (24, 39);

INSERT INTO "charakter_class_skill" VALUES (25, 2);
INSERT INTO "charakter_class_skill" VALUES (25, 3);
INSERT INTO "charakter_class_skill" VALUES (25, 7);
INSERT INTO "charakter_class_skill" VALUES (25, 10);
INSERT INTO "charakter_class_skill" VALUES (25, 11);
INSERT INTO "charakter_class_skill" VALUES (25, 16);
INSERT INTO "charakter_class_skill" VALUES (25, 18);
INSERT INTO "charakter_class_skill" VALUES (25, 29);
INSERT INTO "charakter_class_skill" VALUES (25, 30);
INSERT INTO "charakter_class_skill" VALUES (25, 32);
INSERT INTO "charakter_class_skill" VALUES (25, 33);
INSERT INTO "charakter_class_skill" VALUES (25, 35);
INSERT INTO "charakter_class_skill" VALUES (25, 37);
INSERT INTO "charakter_class_skill" VALUES (25, 40);
INSERT INTO "charakter_class_skill" VALUES (25, 43);
INSERT INTO "charakter_class_skill" VALUES (25, 45);

INSERT INTO "charakter_class_skill" VALUES (26, 5);
INSERT INTO "charakter_class_skill" VALUES (26, 6);
INSERT INTO "charakter_class_skill" VALUES (26, 8);
INSERT INTO "charakter_class_skill" VALUES (26, 27);
INSERT INTO "charakter_class_skill" VALUES (26, 28);
INSERT INTO "charakter_class_skill" VALUES (26, 33);
INSERT INTO "charakter_class_skill" VALUES (26, 36);
INSERT INTO "charakter_class_skill" VALUES (26, 39);

DELETE FROM charakter_class_skill
WHERE skill_id NOT IN (SELECT id FROM skill);


CREATE TABLE charakter_class_text
(charakter_class_id   INTEGER,
 language_id          INTEGER,
 name                 VARCHAR(30),
 PRIMARY KEY (charakter_class_id, language_id),
 FOREIGN KEY (charakter_class_id) REFERENCES charakter_class(id) ON DELETE CASCADE,
 FOREIGN KEY (language_id) REFERENCES language(id) ON DELETE CASCADE
);
INSERT INTO charakter_class_text VALUES (0, 0, "Barbarian");
INSERT INTO charakter_class_text VALUES (1, 0, "Bard");
INSERT INTO charakter_class_text VALUES (2, 0, "Cleric");
INSERT INTO charakter_class_text VALUES (3, 0, "Druid");
INSERT INTO charakter_class_text VALUES (4, 0, "Fighter");
INSERT INTO charakter_class_text VALUES (5, 0, "Monk");
INSERT INTO charakter_class_text VALUES (6, 0, "Paladin");
INSERT INTO charakter_class_text VALUES (7, 0, "Ranger");
INSERT INTO charakter_class_text VALUES (8, 0, "Rogue");
INSERT INTO charakter_class_text VALUES (9, 0, "Sorcerer");
INSERT INTO charakter_class_text VALUES (10, 0, "Wizard");
INSERT INTO charakter_class_text VALUES (11, 0, "Arcane Archer");
INSERT INTO charakter_class_text VALUES (12, 0, "Arcane Trickster");
INSERT INTO charakter_class_text VALUES (13, 0, "Archmage");
INSERT INTO charakter_class_text VALUES (14, 0, "Assassin");
INSERT INTO charakter_class_text VALUES (15, 0, "Blackguard");
INSERT INTO charakter_class_text VALUES (16, 0, "Dragon Disciple");
INSERT INTO charakter_class_text VALUES (17, 0, "Duelist");
INSERT INTO charakter_class_text VALUES (18, 0, "Dwarven Defender");
INSERT INTO charakter_class_text VALUES (19, 0, "Eldritch Knight");
INSERT INTO charakter_class_text VALUES (20, 0, "Hierophant");
INSERT INTO charakter_class_text VALUES (21, 0, "Horizon Walker");
INSERT INTO charakter_class_text VALUES (22, 0, "Loremaster");
INSERT INTO charakter_class_text VALUES (23, 0, "Mystic Theurge");
INSERT INTO charakter_class_text VALUES (24, 0, "Red Wizard");
INSERT INTO charakter_class_text VALUES (25, 0, "Shadowdancer");
INSERT INTO charakter_class_text VALUES (26, 0, "Thaumaturgist");

INSERT INTO charakter_class_text VALUES (0, 1, "Barbar");
INSERT INTO charakter_class_text VALUES (1, 1, "Barde");
INSERT INTO charakter_class_text VALUES (2, 1, "Kleriker");
INSERT INTO charakter_class_text VALUES (3, 1, "Druide");
INSERT INTO charakter_class_text VALUES (4, 1, "Kaempfer");
INSERT INTO charakter_class_text VALUES (5, 1, "Moench");
INSERT INTO charakter_class_text VALUES (6, 1, "Paladin");
INSERT INTO charakter_class_text VALUES (7, 1, "Waldlaeufer");
INSERT INTO charakter_class_text VALUES (8, 1, "Schurke");
INSERT INTO charakter_class_text VALUES (9, 1, "Hexenmeister");
INSERT INTO charakter_class_text VALUES (10, 1, "Magier");
INSERT INTO charakter_class_text VALUES (11, 1, "Arkaner Bogenschuetze");
INSERT INTO charakter_class_text VALUES (12, 1, "Arkaner Betrueger");
INSERT INTO charakter_class_text VALUES (13, 1, "Erzmagier");
INSERT INTO charakter_class_text VALUES (14, 1, "Assassine");
INSERT INTO charakter_class_text VALUES (15, 1, "Finsterer Streiter");
INSERT INTO charakter_class_text VALUES (16, 1, "Drachenjuenger");
INSERT INTO charakter_class_text VALUES (17, 1, "Duellant");
INSERT INTO charakter_class_text VALUES (18, 1, "Zwergischer Verteidiger");
INSERT INTO charakter_class_text VALUES (19, 1, "Mystischer Ritter");
INSERT INTO charakter_class_text VALUES (20, 1, "Hierophant");
INSERT INTO charakter_class_text VALUES (21, 1, "Weltengaenger");
INSERT INTO charakter_class_text VALUES (22, 1, "Wissenshueter");
INSERT INTO charakter_class_text VALUES (23, 1, "Mystischer Theurg");
INSERT INTO charakter_class_text VALUES (24, 1, "Roter Magier");
INSERT INTO charakter_class_text VALUES (25, 1, "Schattentaenzer");
INSERT INTO charakter_class_text VALUES (26, 1, "Invokant");


CREATE TABLE charakter_class_level
(charakter_id         INTEGER,
 charakter_class_id   INTEGER,
 level                INTEGER,
 PRIMARY KEY (charakter_id, charakter_class_id),
 FOREIGN KEY (charakter_id) REFERENCES charakter(id) ON DELETE CASCADE,
 FOREIGN KEY (charakter_class_id) REFERENCES charakter_class(id) ON DELETE CASCADE
);
INSERT INTO charakter_class_level (charakter_id, charakter_class_id, level) SELECT charakter_id, class_id, level FROM charakter_class_old;

DROP TABLE skill_old;
DROP TABLE charakter_class_old;
DROP TABLE class;
