CREATE TABLE known_spells_table
(id                INTEGER NOT NULL,
 name              VARCHAR(15),
 PRIMARY KEY (id)
);

INSERT INTO known_spells_table VALUES ( 1, 'Bard' );
INSERT INTO known_spells_table VALUES ( 2, 'Sorcerer' );
INSERT INTO known_spells_table VALUES ( 3, 'Unlimited' );
INSERT INTO known_spells_table VALUES ( 4, 'Assassin' );


CREATE TABLE known_spells_level
(id                       INTEGER NOT NULL,
 known_spells_table_id    INTEGER NOT NULL,
 spellcaster_level        INTEGER NOT NULL,
 level_0                  INTEGER NOT NULL DEFAULT 0,
 level_1                  INTEGER NOT NULL DEFAULT 0,
 level_2                  INTEGER NOT NULL DEFAULT 0,
 level_3                  INTEGER NOT NULL DEFAULT 0,
 level_4                  INTEGER NOT NULL DEFAULT 0,
 level_5                  INTEGER NOT NULL DEFAULT 0,
 level_6                  INTEGER NOT NULL DEFAULT 0,
 level_7                  INTEGER NOT NULL DEFAULT 0,
 level_8                  INTEGER NOT NULL DEFAULT 0,
 level_9                  INTEGER NOT NULL DEFAULT 0,
 PRIMARY KEY(id)
 FOREIGN KEY (known_spells_table_id) REFERENCES known_spells_table(id) ON DELETE CASCADE
);

INSERT INTO known_spells_level VALUES ( 1, 1, 1, 4, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 2, 1, 2, 5, 2, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 3, 1, 3, 6, 3, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 4, 1, 4, 6, 3, 2, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 5, 1, 5, 6, 4, 3, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 6, 1, 6, 6, 4, 3, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 7, 1, 7, 6, 4, 4, 2, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 8, 1, 8, 6, 4, 4, 3, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 9, 1, 9, 6, 4, 4, 3, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 10, 1, 10, 6, 4, 4, 4, 2, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 11, 1, 11, 6, 4, 4, 4, 3, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 12, 1, 12, 6, 4, 4, 4, 3, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 13, 1, 13, 6, 4, 4, 4, 4, 2, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 14, 1, 14, 6, 4, 4, 4, 4, 3, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 15, 1, 15, 6, 4, 4, 4, 4, 3, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 16, 1, 16, 6, 5, 4, 4, 4, 4, 2, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 17, 1, 17, 6, 5, 5, 4, 4, 4, 3, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 18, 1, 18, 6, 5, 5, 5, 4, 4, 3, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 19, 1, 19, 6, 5, 5, 5, 5, 4, 4, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 20, 1, 20, 6, 5, 5, 5, 5, 5, 4, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 21, 2, 1, 4, 2, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 22, 2, 2, 5, 2, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 23, 2, 3, 5, 3, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 24, 2, 4, 6, 3, 1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 25, 2, 5, 6, 4, 2, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 26, 2, 6, 7, 4, 2, 1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 27, 2, 7, 7, 5, 3, 2, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 28, 2, 8, 8, 5, 3, 2, 1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 29, 2, 9, 8, 5, 4, 3, 2, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 30, 2, 10, 9, 5, 4, 3, 2, 1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 31, 2, 11, 9, 5, 5, 4, 3, 2, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 32, 2, 12, 9, 5, 5, 4, 3, 2, 1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 33, 2, 13, 9, 5, 5, 4, 4, 3, 2, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 34, 2, 14, 9, 5, 5, 4, 4, 3, 2, 1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 35, 2, 15, 9, 5, 5, 4, 4, 4, 3, 2, -1, -1 );
INSERT INTO known_spells_level VALUES ( 36, 2, 16, 9, 5, 5, 4, 4, 4, 3, 2, 1, -1 );
INSERT INTO known_spells_level VALUES ( 37, 2, 17, 9, 5, 5, 4, 4, 4, 3, 3, 2, -1 );
INSERT INTO known_spells_level VALUES ( 38, 2, 18, 9, 5, 5, 4, 4, 4, 3, 3, 2, 1 );
INSERT INTO known_spells_level VALUES ( 39, 2, 19, 9, 5, 5, 4, 4, 4, 3, 3, 3, 2 );
INSERT INTO known_spells_level VALUES ( 40, 2, 20, 9, 5, 5, 4, 4, 4, 3, 3, 3, 3 );
INSERT INTO known_spells_level VALUES ( 41, 3, 1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
INSERT INTO known_spells_level VALUES ( 42, 3, 2, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
INSERT INTO known_spells_level VALUES ( 43, 3, 3, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
INSERT INTO known_spells_level VALUES ( 44, 3, 4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
INSERT INTO known_spells_level VALUES ( 45, 3, 5, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
INSERT INTO known_spells_level VALUES ( 46, 3, 6, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
INSERT INTO known_spells_level VALUES ( 47, 3, 7, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
INSERT INTO known_spells_level VALUES ( 48, 3, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
INSERT INTO known_spells_level VALUES ( 49, 3, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
INSERT INTO known_spells_level VALUES ( 50, 3, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
INSERT INTO known_spells_level VALUES ( 51, 3, 11, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
INSERT INTO known_spells_level VALUES ( 52, 3, 12, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
INSERT INTO known_spells_level VALUES ( 53, 3, 13, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
INSERT INTO known_spells_level VALUES ( 54, 3, 14, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
INSERT INTO known_spells_level VALUES ( 55, 3, 15, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
INSERT INTO known_spells_level VALUES ( 56, 3, 16, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
INSERT INTO known_spells_level VALUES ( 57, 3, 17, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
INSERT INTO known_spells_level VALUES ( 58, 3, 18, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
INSERT INTO known_spells_level VALUES ( 59, 3, 19, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
INSERT INTO known_spells_level VALUES ( 60, 3, 20, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
INSERT INTO known_spells_level VALUES ( 61, 4, 1, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 62, 4, 2, -1, 3, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 63, 4, 3, -1, 3, 2, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 64, 4, 4, -1, 4, 3, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 65, 4, 5, -1, 4, 3, 2, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 66, 4, 6, -1, 4, 4, 3, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 67, 4, 7, -1, 4, 4, 3, 2, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 68, 4, 8, -1, 4, 4, 4, 3, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 69, 4, 9, -1, 4, 4, 4, 3, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 70, 4, 10, -1, 4, 4, 4, 4, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 71, 4, 11, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 72, 4, 12, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 73, 4, 13, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 74, 4, 14, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 75, 4, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 76, 4, 16, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 77, 4, 17, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 78, 4, 18, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 79, 4, 19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 80, 4, 20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );


CREATE TABLE spells_per_day_table
(id                INTEGER NOT NULL,
 name              VARCHAR(15),
 PRIMARY KEY (id)
);

INSERT INTO spells_per_day_table VALUES ( 1, 'Bard' );
INSERT INTO spells_per_day_table VALUES ( 2, 'Ranger' );
INSERT INTO spells_per_day_table VALUES ( 3, 'Sorcerer' );
INSERT INTO spells_per_day_table VALUES ( 4, 'Wizard' );
INSERT INTO spells_per_day_table VALUES ( 5, 'Assassin' );
INSERT INTO spells_per_day_table VALUES ( 6, 'Blackguard' );
INSERT INTO spells_per_day_table VALUES ( 7, 'Domain' );
INSERT INTO spells_per_day_table VALUES ( 8, 'Specialization' );


CREATE TABLE spells_per_day_level
(id                       INTEGER NOT NULL,
 spells_per_day_table_id  INTEGER NOT NULL,
 spellcaster_level        INTEGER NOT NULL,
 level_0                  INTEGER NOT NULL DEFAULT 0,
 level_1                  INTEGER NOT NULL DEFAULT 0,
 level_2                  INTEGER NOT NULL DEFAULT 0,
 level_3                  INTEGER NOT NULL DEFAULT 0,
 level_4                  INTEGER NOT NULL DEFAULT 0,
 level_5                  INTEGER NOT NULL DEFAULT 0,
 level_6                  INTEGER NOT NULL DEFAULT 0,
 level_7                  INTEGER NOT NULL DEFAULT 0,
 level_8                  INTEGER NOT NULL DEFAULT 0,
 level_9                  INTEGER NOT NULL DEFAULT 0,
 PRIMARY KEY(id)
 FOREIGN KEY (spells_per_day_table_id) REFERENCES spells_per_day_table(id) ON DELETE CASCADE
);

INSERT INTO spells_per_day_level VALUES ( 1, 1, 1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 2, 1, 2, 3, 0, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 3, 1, 3, 3, 1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 4, 1, 4, 3, 2, 0, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 5, 1, 5, 3, 3, 1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 6, 1, 6, 3, 3, 2, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 7, 1, 7, 3, 3, 2, 0, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 8, 1, 8, 3, 3, 3, 1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 9, 1, 9, 3, 3, 3, 2, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 10, 1, 10, 3, 3, 3, 2, 0, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 11, 1, 11, 3, 3, 3, 3, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 12, 1, 12, 3, 3, 3, 3, 2, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 13, 1, 13, 3, 3, 3, 3, 2, 0, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 14, 1, 14, 4, 3, 3, 3, 3, 1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 15, 1, 15, 4, 4, 3, 3, 3, 2, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 16, 1, 16, 4, 4, 4, 3, 3, 2, 0, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 17, 1, 17, 4, 4, 4, 4, 3, 3, 1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 18, 1, 18, 4, 4, 4, 4, 4, 3, 2, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 19, 1, 19, 4, 4, 4, 4, 4, 4, 3, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 20, 1, 20, 4, 4, 4, 4, 4, 4, 4, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 21, 2, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 22, 2, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 23, 2, 3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 24, 2, 4, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 25, 2, 5, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 26, 2, 6, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 27, 2, 7, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 28, 2, 8, -1, 1, 0, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 29, 2, 9, -1, 1, 0, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 30, 2, 10, -1, 1, 1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 31, 2, 11, -1, 1, 1, 0, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 32, 2, 12, -1, 1, 1, 1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 33, 2, 13, -1, 1, 1, 1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 34, 2, 14, -1, 2, 1, 1, 0, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 35, 2, 15, -1, 2, 1, 1, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 36, 2, 16, -1, 2, 2, 1, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 37, 2, 17, -1, 2, 2, 2, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 38, 2, 18, -1, 3, 2, 2, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 39, 2, 19, -1, 3, 3, 3, 2, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 40, 2, 20, -1, 3, 3, 3, 3, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 41, 3, 1, 5, 3, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 42, 3, 2, 6, 4, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 43, 3, 3, 6, 5, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 44, 3, 4, 6, 6, 3, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 45, 3, 5, 6, 6, 4, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 46, 3, 6, 6, 6, 5, 3, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 47, 3, 7, 6, 6, 6, 4, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 48, 3, 8, 6, 6, 6, 5, 3, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 49, 3, 9, 6, 6, 6, 6, 4, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 50, 3, 10, 6, 6, 6, 6, 5, 3, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 51, 3, 11, 6, 6, 6, 6, 6, 4, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 52, 3, 12, 6, 6, 6, 6, 6, 5, 3, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 53, 3, 13, 6, 6, 6, 6, 6, 6, 4, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 54, 3, 14, 6, 6, 6, 6, 6, 6, 5, 3, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 55, 3, 15, 6, 6, 6, 6, 6, 6, 6, 4, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 56, 3, 16, 6, 6, 6, 6, 6, 6, 6, 5, 3, -1 );
INSERT INTO spells_per_day_level VALUES ( 57, 3, 17, 6, 6, 6, 6, 6, 6, 6, 6, 4, -1 );
INSERT INTO spells_per_day_level VALUES ( 58, 3, 18, 6, 6, 6, 6, 6, 6, 6, 6, 5, 3 );
INSERT INTO spells_per_day_level VALUES ( 59, 3, 19, 6, 6, 6, 6, 6, 6, 6, 6, 6, 4 );
INSERT INTO spells_per_day_level VALUES ( 60, 3, 20, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6 );
INSERT INTO spells_per_day_level VALUES ( 61, 4, 1, 3, 1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 62, 4, 2, 4, 2, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 63, 4, 3, 4, 2, 1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 64, 4, 4, 4, 3, 2, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 65, 4, 5, 4, 3, 2, 1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 66, 4, 6, 4, 3, 3, 2, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 67, 4, 7, 4, 4, 3, 2, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 68, 4, 8, 4, 4, 3, 3, 2, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 69, 4, 9, 4, 4, 4, 3, 2, 1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 70, 4, 10, 4, 4, 4, 3, 3, 2, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 71, 4, 11, 4, 4, 4, 4, 3, 2, 1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 72, 4, 12, 4, 4, 4, 4, 3, 3, 2, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 73, 4, 13, 4, 4, 4, 4, 4, 3, 2, 1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 74, 4, 14, 4, 4, 4, 4, 4, 3, 3, 2, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 75, 4, 15, 4, 4, 4, 4, 4, 4, 3, 2, 1, -1 );
INSERT INTO spells_per_day_level VALUES ( 76, 4, 16, 4, 4, 4, 4, 4, 4, 3, 3, 2, -1 );
INSERT INTO spells_per_day_level VALUES ( 77, 4, 17, 4, 4, 4, 4, 4, 4, 4, 3, 2, 1 );
INSERT INTO spells_per_day_level VALUES ( 78, 4, 18, 4, 4, 4, 4, 4, 4, 4, 3, 3, 2 );
INSERT INTO spells_per_day_level VALUES ( 79, 4, 19, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3 );
INSERT INTO spells_per_day_level VALUES ( 80, 4, 20, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 );
INSERT INTO spells_per_day_level VALUES ( 81, 5, 1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 82, 5, 2, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 83, 5, 3, -1, 2, 0, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 84, 5, 4, -1, 3, 1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 85, 5, 5, -1, 3, 2, 0, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 86, 5, 6, -1, 3, 3, 1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 87, 5, 7, -1, 3, 3, 2, 0, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 88, 5, 8, -1, 3, 3, 3, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 89, 5, 9, -1, 3, 3, 3, 2, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 90, 5, 10, -1, 3, 3, 3, 3, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 91, 5, 11, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 92, 5, 12, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 93, 5, 13, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 94, 5, 14, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 95, 5, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 96, 5, 16, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 97, 5, 17, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 98, 5, 18, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 99, 5, 19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 100, 5, 20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 101, 6, 1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 102, 6, 2, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 103, 6, 3, -1, 1, 0, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 104, 6, 4, -1, 1, 1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 105, 6, 5, -1, 1, 1, 0, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 106, 6, 6, -1, 1, 1, 1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 107, 6, 7, -1, 2, 1, 1, 0, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 108, 6, 8, -1, 2, 1, 1, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 109, 6, 9, -1, 2, 2, 1, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 110, 6, 10, -1, 2, 2, 2, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 111, 6, 11, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 112, 6, 12, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 113, 6, 13, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 114, 6, 14, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 115, 6, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 116, 6, 16, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 117, 6, 17, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 118, 6, 18, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 119, 6, 19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 120, 6, 20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 121, 7, 1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 122, 7, 2, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 123, 7, 3, -1, 1, 1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 124, 7, 4, -1, 1, 1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 125, 7, 5, -1, 1, 1, 1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 126, 7, 6, -1, 1, 1, 1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 127, 7, 7, -1, 1, 1, 1, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 128, 7, 8, -1, 1, 1, 1, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 129, 7, 9, -1, 1, 1, 1, 1, 1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 130, 7, 10, -1, 1, 1, 1, 1, 1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 131, 7, 11, -1, 1, 1, 1, 1, 1, 1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 132, 7, 12, -1, 1, 1, 1, 1, 1, 1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 133, 7, 13, -1, 1, 1, 1, 1, 1, 1, 1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 134, 7, 14, -1, 1, 1, 1, 1, 1, 1, 1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 135, 7, 15, -1, 1, 1, 1, 1, 1, 1, 1, 1, -1 );
INSERT INTO spells_per_day_level VALUES ( 136, 7, 16, -1, 1, 1, 1, 1, 1, 1, 1, 1, -1 );
INSERT INTO spells_per_day_level VALUES ( 137, 7, 17, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1 );
INSERT INTO spells_per_day_level VALUES ( 138, 7, 18, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1 );
INSERT INTO spells_per_day_level VALUES ( 139, 7, 19, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1 );
INSERT INTO spells_per_day_level VALUES ( 140, 7, 20, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1 );
INSERT INTO spells_per_day_level VALUES ( 141, 8, 1, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 142, 8, 2, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 143, 8, 3, 1, 1, 1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 144, 8, 4, 1, 1, 1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 145, 8, 5, 1, 1, 1, 1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 146, 8, 6, 1, 1, 1, 1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 147, 8, 7, 1, 1, 1, 1, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 148, 8, 8, 1, 1, 1, 1, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 149, 8, 9, 1, 1, 1, 1, 1, 1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 150, 8, 10, 1, 1, 1, 1, 1, 1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 151, 8, 11, 1, 1, 1, 1, 1, 1, 1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 152, 8, 12, 1, 1, 1, 1, 1, 1, 1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 153, 8, 13, 1, 1, 1, 1, 1, 1, 1, 1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 154, 8, 14, 1, 1, 1, 1, 1, 1, 1, 1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 155, 8, 15, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1 );
INSERT INTO spells_per_day_level VALUES ( 156, 8, 16, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1 );
INSERT INTO spells_per_day_level VALUES ( 157, 8, 17, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 );
INSERT INTO spells_per_day_level VALUES ( 158, 8, 18, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 );
INSERT INTO spells_per_day_level VALUES ( 159, 8, 19, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 );
INSERT INTO spells_per_day_level VALUES ( 160, 8, 20, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 );


CREATE TABLE spelllist_temp
(id             INTEGER,
 name           VARCHAR(20),
 shortname      VARCHAR(20)
);
INSERT INTO spelllist_temp (id, name, shortname)  SELECT id, name, shortname FROM spelllist;
UPDATE spelllist_temp SET name = 'Wizard', shortname = 'Wiz' WHERE id = 5;
UPDATE spelllist_temp SET id = id + 1 WHERE id > 5;
INSERT INTO spelllist_temp VALUES ( 6, 'Sorcerer', 'Sor' );
DELETE FROM spelllist;
INSERT INTO spelllist (id, name, shortname)  SELECT id, name, shortname FROM spelllist_temp;
DROP TABLE spelllist_temp;


CREATE TABLE spelllist_spell_temp
(spelllist_id  INTEGER,
 spell_id      INTEGER,
 level         INTEGER
);
INSERT INTO spelllist_spell_temp (spelllist_id, spell_id, level)  SELECT spelllist_id, spell_id, level FROM spelllist_spell;
UPDATE spelllist_spell_temp SET spelllist_id = spelllist_id + 1 WHERE spelllist_id > 5;
INSERT INTO spelllist_spell_temp VALUES ( 6, 1, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 2, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 3, 0 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 6, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 8, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 9, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 10, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 14, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 17, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 19, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 20, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 22, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 23, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 24, 0 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 25, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 26, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 27, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 31, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 33, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 35, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 36, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 37, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 38, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 39, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 45, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 46, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 47, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 48, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 49, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 50, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 51, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 52, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 57, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 58, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 59, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 60, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 64, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 65, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 66, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 68, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 69, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 70, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 71, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 73, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 74, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 75, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 79, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 82, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 83, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 84, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 87, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 88, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 89, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 90, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 92, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 93, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 94, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 97, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 98, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 101, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 102, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 113, 0 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 114, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 115, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 116, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 117, 0 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 118, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 122, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 125, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 126, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 134, 0 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 135, 0 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 136, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 137, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 139, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 140, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 142, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 143, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 144, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 147, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 148, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 149, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 150, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 155, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 156, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 157, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 158, 0 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 164, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 165, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 167, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 168, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 169, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 172, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 173, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 174, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 175, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 176, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 180, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 181, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 182, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 183, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 184, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 185, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 186, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 188, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 189, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 190, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 191, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 192, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 195, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 197, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 199, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 200, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 201, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 204, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 205, 0 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 206, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 207, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 208, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 209, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 211, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 212, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 213, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 214, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 215, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 216, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 218, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 219, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 220, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 221, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 222, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 223, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 224, 0 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 225, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 228, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 229, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 230, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 235, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 236, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 238, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 240, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 242, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 243, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 245, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 252, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 253, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 256, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 258, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 259, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 260, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 261, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 262, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 267, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 268, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 269, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 270, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 271, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 272, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 273, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 276, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 277, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 287, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 289, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 290, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 291, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 292, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 293, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 295, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 296, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 298, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 299, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 300, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 301, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 303, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 304, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 305, 0 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 306, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 307, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 309, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 310, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 313, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 314, 0 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 315, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 316, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 318, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 319, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 320, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 321, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 322, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 323, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 324, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 325, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 328, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 329, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 330, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 333, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 334, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 335, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 336, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 339, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 341, 0 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 342, 0 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 343, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 344, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 345, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 346, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 347, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 349, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 350, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 351, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 352, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 355, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 356, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 357, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 359, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 360, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 361, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 362, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 363, 0 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 365, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 366, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 367, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 368, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 370, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 371, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 372, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 373, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 374, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 375, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 376, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 380, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 381, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 382, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 383, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 386, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 387, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 388, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 389, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 390, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 391, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 393, 0 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 394, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 395, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 396, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 398, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 399, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 400, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 401, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 402, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 403, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 404, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 405, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 406, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 407, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 408, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 410, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 412, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 413, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 415, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 416, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 417, 0 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 418, 0 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 420, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 421, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 422, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 426, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 433, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 434, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 435, 0 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 436, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 441, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 443, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 446, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 447, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 448, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 449, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 450, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 451, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 454, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 455, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 456, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 457, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 458, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 459, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 460, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 461, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 462, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 463, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 464, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 465, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 466, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 467, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 469, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 470, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 471, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 476, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 477, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 478, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 479, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 481, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 482, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 484, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 485, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 486, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 489, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 491, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 496, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 501, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 502, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 506, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 508, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 509, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 510, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 512, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 514, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 515, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 517, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 518, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 519, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 520, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 521, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 522, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 523, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 524, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 525, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 535, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 537, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 538, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 539, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 540, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 541, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 542, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 543, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 544, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 545, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 547, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 548, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 549, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 550, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 551, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 552, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 553, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 554, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 555, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 556, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 557, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 558, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 559, 0 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 560, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 561, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 563, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 564, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 566, 8 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 570, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 571, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 572, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 577, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 578, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 579, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 580, 1 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 582, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 583, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 584, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 585, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 586, 4 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 587, 6 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 588, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 591, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 593, 7 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 594, 5 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 595, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 596, 9 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 598, 2 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 600, 3 );
INSERT INTO spelllist_spell_temp VALUES ( 6, 601, 9 );
DELETE FROM spelllist_spell;
INSERT INTO spelllist_spell (spelllist_id, spell_id, level) SELECT spelllist_id, spell_id, level FROM spelllist_spell_temp;
DROP TABLE spelllist_spell_temp;

UPDATE ability_property SET property_value = CAST((CAST (property_value as INTEGER) + 1) as text) WHERE property_key = 'spelllist_id' AND CAST(property_value AS INTEGER) > 5;
UPDATE ability_property SET property_value = '6' WHERE ability_id = 103 AND property_key = 'spelllist_id';


CREATE TABLE charakter_known_spell
(id                INTEGER NOT NULL,
 charakter_id      INTEGER NOT NULL,
 spelllist_id      INTEGER NOT NULL,
 spell_id          INTEGER NOT NULL,
 PRIMARY KEY(id),
 FOREIGN KEY (charakter_id) REFERENCES charakter(id),
 FOREIGN KEY (spelllist_id) REFERENCES spelllist(id),
 FOREIGN KEY (spell_id) REFERENCES spell(id)
);
INSERT INTO charakter_known_spell (id, charakter_id, spelllist_id, spell_id) SELECT id, charakter_id, (SELECT spelllist_id FROM spelllist_spell WHERE spell_id = charakter_spell.spell_id AND spelllist_id IN (SELECT CAST(ap.property_value as Integer) FROM charakter_spell cs, charakter_ability ca, ability_property ap WHERE cs.charakter_id = ca.charakter_id  AND ca.owned = 1 AND ca.ability_id = ap.ability_id  AND ap.property_key = 'spelllist_id' AND cs.spell_id = charakter_spell.spell_id)), spell_id FROM charakter_spell;
DROP TABLE charakter_spell;


UPDATE ability SET classname = 'SpelllistAbility' WHERE id IN (111, 112, 113, 114, 115, 116, 117, 118, 119);
INSERT INTO ability_property VALUES (11, 'spell_attribute_id', '5' );
INSERT INTO ability_property VALUES (11, 'casting_type_id', '0' );
INSERT INTO ability_property VALUES (11, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (11, 'spells_known_table_id', '1' );
INSERT INTO ability_property VALUES (11, 'spells_per_day_table_id', '1' );
INSERT INTO ability_property VALUES (11, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (11, 'school_id', '-1' );
INSERT INTO ability_property VALUES (23, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (23, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (23, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (23, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (23, 'spells_per_day_table_id', '4' );
INSERT INTO ability_property VALUES (23, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (23, 'school_id', '-1' );
INSERT INTO ability_property VALUES (29, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (29, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (29, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (29, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (29, 'spells_per_day_table_id', '4' );
INSERT INTO ability_property VALUES (29, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (29, 'school_id', '-1' );
INSERT INTO ability_property VALUES (72, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (72, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (72, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (72, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (72, 'spells_per_day_table_id', '2' );
INSERT INTO ability_property VALUES (72, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (72, 'school_id', '-1' );
INSERT INTO ability_property VALUES (83, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (83, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (83, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (83, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (83, 'spells_per_day_table_id', '2' );
INSERT INTO ability_property VALUES (83, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (83, 'school_id', '-1' );
INSERT INTO ability_property VALUES (103, 'spell_attribute_id', '5' );
INSERT INTO ability_property VALUES (103, 'casting_type_id', '0' );
INSERT INTO ability_property VALUES (103, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (103, 'spells_known_table_id', '2' );
INSERT INTO ability_property VALUES (103, 'spells_per_day_table_id', '3' );
INSERT INTO ability_property VALUES (103, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (103, 'school_id', '-1' );
INSERT INTO ability_property VALUES (105, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (105, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (105, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (105, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (105, 'spells_per_day_table_id', '4' );
INSERT INTO ability_property VALUES (105, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (105, 'school_id', '-1' );
INSERT INTO ability_property VALUES (111, 'spelllist_id', '5' );
INSERT INTO ability_property VALUES (111, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (111, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (111, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (111, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (111, 'spells_per_day_table_id', '8' );
INSERT INTO ability_property VALUES (111, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (111, 'school_id', '0' );
INSERT INTO ability_property VALUES (112, 'spelllist_id', '5' );
INSERT INTO ability_property VALUES (112, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (112, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (112, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (112, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (112, 'spells_per_day_table_id', '8' );
INSERT INTO ability_property VALUES (112, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (112, 'school_id', '1' );
INSERT INTO ability_property VALUES (113, 'spelllist_id', '5' );
INSERT INTO ability_property VALUES (113, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (113, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (113, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (113, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (113, 'spells_per_day_table_id', '8' );
INSERT INTO ability_property VALUES (113, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (113, 'school_id', '2' );
INSERT INTO ability_property VALUES (114, 'spelllist_id', '5' );
INSERT INTO ability_property VALUES (114, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (114, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (114, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (114, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (114, 'spells_per_day_table_id', '8' );
INSERT INTO ability_property VALUES (114, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (114, 'school_id', '3' );
INSERT INTO ability_property VALUES (115, 'spelllist_id', '5' );
INSERT INTO ability_property VALUES (115, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (115, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (115, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (115, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (115, 'spells_per_day_table_id', '8' );
INSERT INTO ability_property VALUES (115, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (115, 'school_id', '4' );
INSERT INTO ability_property VALUES (116, 'spelllist_id', '5' );
INSERT INTO ability_property VALUES (116, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (116, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (116, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (116, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (116, 'spells_per_day_table_id', '8' );
INSERT INTO ability_property VALUES (116, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (116, 'school_id', '5' );
INSERT INTO ability_property VALUES (117, 'spelllist_id', '5' );
INSERT INTO ability_property VALUES (117, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (117, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (117, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (117, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (117, 'spells_per_day_table_id', '8' );
INSERT INTO ability_property VALUES (117, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (117, 'school_id', '6' );
INSERT INTO ability_property VALUES (118, 'spelllist_id', '5' );
INSERT INTO ability_property VALUES (118, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (118, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (118, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (118, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (118, 'spells_per_day_table_id', '8' );
INSERT INTO ability_property VALUES (118, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (118, 'school_id', '7' );
INSERT INTO ability_property VALUES (119, 'spelllist_id', '5' );
INSERT INTO ability_property VALUES (119, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (119, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (119, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (119, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (119, 'spells_per_day_table_id', '8' );
INSERT INTO ability_property VALUES (119, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (119, 'school_id', '8' );
INSERT INTO ability_property VALUES (140, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (140, 'casting_type_id', '0' );
INSERT INTO ability_property VALUES (140, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (140, 'spells_known_table_id', '4' );
INSERT INTO ability_property VALUES (140, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (140, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (140, 'school_id', '-1' );
INSERT INTO ability_property VALUES (146, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (146, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (146, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (146, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (146, 'spells_per_day_table_id', '6' );
INSERT INTO ability_property VALUES (146, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (146, 'school_id', '-1' );
INSERT INTO ability_property VALUES (294, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (294, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (294, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (294, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (294, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (294, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (294, 'school_id', '-1' );
INSERT INTO ability_property VALUES (295, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (295, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (295, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (295, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (295, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (295, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (295, 'school_id', '-1' );
INSERT INTO ability_property VALUES (296, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (296, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (296, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (296, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (296, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (296, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (296, 'school_id', '-1' );
INSERT INTO ability_property VALUES (297, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (297, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (297, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (297, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (297, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (297, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (297, 'school_id', '-1' );
INSERT INTO ability_property VALUES (298, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (298, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (298, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (298, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (298, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (298, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (298, 'school_id', '-1' );
INSERT INTO ability_property VALUES (299, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (299, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (299, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (299, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (299, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (299, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (299, 'school_id', '-1' );
INSERT INTO ability_property VALUES (300, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (300, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (300, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (300, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (300, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (300, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (300, 'school_id', '-1' );
INSERT INTO ability_property VALUES (301, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (301, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (301, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (301, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (301, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (301, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (301, 'school_id', '-1' );
INSERT INTO ability_property VALUES (302, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (302, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (302, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (302, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (302, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (302, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (302, 'school_id', '-1' );
INSERT INTO ability_property VALUES (303, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (303, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (303, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (303, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (303, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (303, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (303, 'school_id', '-1' );
INSERT INTO ability_property VALUES (304, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (304, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (304, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (304, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (304, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (304, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (304, 'school_id', '-1' );
INSERT INTO ability_property VALUES (305, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (305, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (305, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (305, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (305, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (305, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (305, 'school_id', '-1' );
INSERT INTO ability_property VALUES (306, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (306, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (306, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (306, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (306, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (306, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (306, 'school_id', '-1' );
INSERT INTO ability_property VALUES (307, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (307, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (307, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (307, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (307, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (307, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (307, 'school_id', '-1' );
INSERT INTO ability_property VALUES (308, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (308, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (308, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (308, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (308, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (308, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (308, 'school_id', '-1' );
INSERT INTO ability_property VALUES (309, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (309, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (309, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (309, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (309, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (309, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (309, 'school_id', '-1' );
INSERT INTO ability_property VALUES (310, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (310, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (310, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (310, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (310, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (310, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (310, 'school_id', '-1' );
INSERT INTO ability_property VALUES (311, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (311, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (311, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (311, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (311, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (311, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (311, 'school_id', '-1' );
INSERT INTO ability_property VALUES (312, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (312, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (312, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (312, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (312, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (312, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (312, 'school_id', '-1' );
INSERT INTO ability_property VALUES (313, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (313, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (313, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (313, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (313, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (313, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (313, 'school_id', '-1' );
INSERT INTO ability_property VALUES (314, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (314, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (314, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (314, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (314, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (314, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (314, 'school_id', '-1' );
INSERT INTO ability_property VALUES (315, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (315, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (315, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (315, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (315, 'spells_per_day_table_id', '7' );
INSERT INTO ability_property VALUES (315, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (315, 'school_id', '-1' );


CREATE TABLE spelllist_temp
(id             INTEGER,
 name           VARCHAR(20),
 shortname      VARCHAR(20),
 domain         INTEGER DEFAULT 0,
 min_level      INTEGER DEFAULT 0,
 max_level      INTEGER DEFAULT 9,
 PRIMARY KEY (id)
);
INSERT INTO spelllist_temp VALUES ( 1, 'Cleric', 'Clr', 0, 0, 9 );
INSERT INTO spelllist_temp VALUES ( 2, 'Druid', 'Drd', 0, 0, 9 );
INSERT INTO spelllist_temp VALUES ( 3, 'Paladin', 'Pal', 0, 1, 4 );
INSERT INTO spelllist_temp VALUES ( 4, 'Ranger', 'Rgr', 0, 1, 4 );
INSERT INTO spelllist_temp VALUES ( 5, 'Wizard', 'Wiz', 0, 0, 9 );
INSERT INTO spelllist_temp VALUES ( 6, 'Sorcerer', 'Sor', 0, 0, 9 );
INSERT INTO spelllist_temp VALUES ( 7, 'Bard', 'Brd', 0, 0, 6 );
INSERT INTO spelllist_temp VALUES ( 8, 'Fire', 'Fire', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 9, 'Air', 'Air', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 10, 'Good', 'Good', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 11, 'Healing', 'Healing', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 12, 'Knowledge', 'Knowledge', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 13, 'Law', 'Law', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 14, 'Luck', 'Luck', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 15, 'Magic', 'Magic', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 16, 'Plant', 'Plant', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 17, 'Protection', 'Protection', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 18, 'Strength', 'Strength', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 19, 'Sun', 'Sun', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 20, 'Travel', 'Travel', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 21, 'Trickery', 'Trickery', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 22, 'War', 'War', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 23, 'Water', 'Water', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 24, 'Evil', 'Evil', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 25, 'Earth', 'Earth', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 26, 'Destruction', 'Destruction', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 27, 'Death', 'Death', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 28, 'Chaos', 'Chaos', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 29, 'Animal', 'Animal', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 30, 'Assassine', 'Asn', 0, 1, 4 );
INSERT INTO spelllist_temp VALUES ( 31, 'Blackguard', 'Bld', 0, 1, 4 );
INSERT INTO spelllist_temp (id, name, shortname, domain, min_level, max_level) SELECT id, name, shortname, 0, 0, 9 FROM spelllist WHERE id > 31;
DROP TABLE spelllist;
ALTER TABLE spelllist_temp RENAME TO spelllist;


ALTER TABLE feat ADD COLUMN spell_slot INTEGER DEFAULT 0;
UPDATE feat SET spell_slot = 2 WHERE id = 27;
UPDATE feat SET spell_slot = 1 WHERE id = 29;
UPDATE feat SET spell_slot = 1 WHERE id = 32;
UPDATE feat SET spell_slot = 3 WHERE id = 66;
UPDATE feat SET spell_slot = 4 WHERE id = 78;
UPDATE feat SET spell_slot = 1 WHERE id = 87;
UPDATE feat SET spell_slot = 1 WHERE id = 97;
UPDATE feat SET spell_slot = 3 WHERE id = 109;


CREATE TABLE charakter_spell_slot
(id             INTEGER NOT NULL,
 charakter_id   INTEGER NOT NULL,
 spell_id       INTEGER NOT NULL,
 level          INTEGER NOT NULL,
 cast           INTEGER NOT NULL,
 PRIMARY KEY(id)
 FOREIGN KEY (charakter_id) REFERENCES charakter(id) ON DELETE CASCADE
 FOREIGN KEY (spell_id) REFERENCES spell(id) ON DELETE CASCADE
);


CREATE TABLE charakter_spell_slot_ability
(id             INTEGER NOT NULL,
 spell_slot_id  INTEGER NOT NULL,
 ability_id     INTEGER NOT NULL,
 PRIMARY KEY(id)
 FOREIGN KEY (spell_slot_id) REFERENCES spell_slot(id) ON DELETE CASCADE
 FOREIGN KEY (ability_id) REFERENCES ability(id) ON DELETE CASCADE
);


CREATE TABLE charakter_spell_slot_feat
(id             INTEGER NOT NULL,
 spell_slot_id  INTEGER NOT NULL,
 feat_id        INTEGER NOT NULL,
 PRIMARY KEY(id)
 FOREIGN KEY (spell_slot_id) REFERENCES spell_slot(id) ON DELETE CASCADE
 FOREIGN KEY (feat_id) REFERENCES feat(id) ON DELETE CASCADE
);
