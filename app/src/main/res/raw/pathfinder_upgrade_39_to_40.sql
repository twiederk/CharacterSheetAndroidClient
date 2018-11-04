CREATE TABLE known_spells_table
(id                INTEGER NOT NULL,
 name              VARCHAR(15),
 PRIMARY KEY (id)
);

INSERT INTO known_spells_table VALUES ( 1, 'Bard' );
INSERT INTO known_spells_table VALUES ( 2, 'Sorcerer' );
INSERT INTO known_spells_table VALUES ( 3, 'Unlimited' );


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

INSERT INTO known_spells_level VALUES ( 1, 1, 1, 4, 2, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 2, 1, 2, 5, 3, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 3, 1, 3, 6, 4, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 4, 1, 4, 6, 4, 2, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 5, 1, 5, 6, 4, 3, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 6, 1, 6, 6, 4, 4, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 7, 1, 7, 6, 5, 4, 2, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 8, 1, 8, 6, 5, 4, 3, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 9, 1, 9, 6, 5, 4, 4, -1, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 10, 1, 10, 6, 5, 5, 4, 2, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 11, 1, 11, 6, 6, 5, 4, 3, -1, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 12, 1, 12, 6, 6, 5, 4, 4, 4, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 13, 1, 13, 6, 6, 5, 5, 4, 2, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 14, 1, 14, 6, 6, 6, 5, 4, 3, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 15, 1, 15, 6, 6, 6, 5, 4, 4, -1, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 16, 1, 16, 6, 6, 6, 5, 5, 4, 2, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 17, 1, 17, 6, 6, 6, 6, 5, 4, 3, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 18, 1, 18, 6, 6, 6, 6, 5, 4, 4, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 19, 1, 19, 6, 6, 6, 6, 5, 5, 4, -1, -1, -1 );
INSERT INTO known_spells_level VALUES ( 20, 1, 20, 6, 6, 6, 6, 6, 5, 5, -1, -1, -1 );
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

CREATE TABLE spells_per_day_table
(id                INTEGER NOT NULL,
 name              VARCHAR(15),
 PRIMARY KEY (id)
);

INSERT INTO spells_per_day_table VALUES ( 1, 'Bard' );
INSERT INTO spells_per_day_table VALUES ( 2, 'Ranger' );
INSERT INTO spells_per_day_table VALUES ( 3, 'Sorcerer' );
INSERT INTO spells_per_day_table VALUES ( 4, 'Wizard' );
INSERT INTO spells_per_day_table VALUES ( 5, 'Domain' );
INSERT INTO spells_per_day_table VALUES ( 6, 'Specialization' );

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

INSERT INTO spells_per_day_level VALUES ( 1, 1, 1, 4, 1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 2, 1, 2, 5, 2, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 3, 1, 3, 6, 3, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 4, 1, 4, 6, 3, 1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 5, 1, 5, 6, 4, 2, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 6, 1, 6, 6, 4, 3, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 7, 1, 7, 6, 4, 3, 1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 8, 1, 8, 6, 4, 4, 2, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 9, 1, 9, 6, 5, 4, 3, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 10, 1, 10, 6, 5, 4, 3, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 11, 1, 11, 6, 5, 4, 4, 2, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 12, 1, 12, 6, 5, 5, 4, 3, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 13, 1, 13, 6, 5, 5, 4, 3, 1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 14, 1, 14, 6, 5, 5, 4, 4, 2, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 15, 1, 15, 6, 5, 5, 5, 4, 3, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 16, 1, 16, 6, 5, 5, 5, 4, 3, 1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 17, 1, 17, 6, 5, 5, 5, 4, 4, 2, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 18, 1, 18, 6, 5, 5, 5, 5, 4, 3, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 19, 1, 19, 6, 5, 5, 5, 5, 5, 4, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 20, 1, 20, 6, 5, 5, 5, 5, 5, 5, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 21, 2, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 22, 2, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 23, 2, 3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 24, 2, 4, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 25, 2, 5, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 26, 2, 6, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 27, 2, 7, -1, 1, 0, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 28, 2, 8, -1, 1, 1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 29, 2, 9, -1, 2, 1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 30, 2, 10, -1, 2, 1, 0, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 31, 2, 11, -1, 2, 1, 1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 32, 2, 12, -1, 2, 2, 1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 33, 2, 13, -1, 3, 2, 1, 0, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 34, 2, 14, -1, 3, 2, 1, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 35, 2, 15, -1, 3, 2, 2, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 36, 2, 16, -1, 3, 3, 2, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 37, 2, 17, -1, 4, 3, 2, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 38, 2, 18, -1, 4, 3, 2, 2, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 39, 2, 19, -1, 4, 3, 3, 2, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 40, 2, 20, -1, 4, 4, 3, 3, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 41, 3, 1, 4, 3, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 42, 3, 2, 5, 4, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 43, 3, 3, 5, 5, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 44, 3, 4, 6, 6, 3, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 45, 3, 5, 6, 6, 4, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 46, 3, 6, 7, 6, 5, 3, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 47, 3, 7, 7, 6, 6, 4, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 48, 3, 8, 8, 6, 6, 5, 3, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 49, 3, 9, 8, 6, 6, 6, 4, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 50, 3, 10, 9, 6, 6, 6, 5, 3, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 51, 3, 11, 9, 6, 6, 6, 6, 4, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 52, 3, 12, 9, 6, 6, 6, 6, 5, 3, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 53, 3, 13, 9, 6, 6, 6, 6, 6, 4, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 54, 3, 14, 9, 6, 6, 6, 6, 6, 5, 3, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 55, 3, 15, 9, 6, 6, 6, 6, 6, 6, 4, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 56, 3, 16, 9, 6, 6, 6, 6, 6, 6, 5, 3, -1 );
INSERT INTO spells_per_day_level VALUES ( 57, 3, 17, 9, 6, 6, 6, 6, 6, 6, 6, 4, -1 );
INSERT INTO spells_per_day_level VALUES ( 58, 3, 18, 9, 6, 6, 6, 6, 6, 6, 6, 5, 3 );
INSERT INTO spells_per_day_level VALUES ( 59, 3, 19, 9, 6, 6, 6, 6, 6, 6, 6, 6, 4 );
INSERT INTO spells_per_day_level VALUES ( 60, 3, 20, 9, 6, 6, 6, 6, 6, 6, 6, 6, 6 );
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
INSERT INTO spells_per_day_level VALUES ( 81, 5, 1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 82, 5, 2, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 83, 5, 3, -1, 1, 1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 84, 5, 4, -1, 1, 1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 85, 5, 5, -1, 1, 1, 1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 86, 5, 6, -1, 1, 1, 1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 87, 5, 7, -1, 1, 1, 1, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 88, 5, 8, -1, 1, 1, 1, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 89, 5, 9, -1, 1, 1, 1, 1, 1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 90, 5, 10, -1, 1, 1, 1, 1, 1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 91, 5, 11, -1, 1, 1, 1, 1, 1, 1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 92, 5, 12, -1, 1, 1, 1, 1, 1, 1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 93, 5, 13, -1, 1, 1, 1, 1, 1, 1, 1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 94, 5, 14, -1, 1, 1, 1, 1, 1, 1, 1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 95, 5, 15, -1, 1, 1, 1, 1, 1, 1, 1, 1, -1 );
INSERT INTO spells_per_day_level VALUES ( 96, 5, 16, -1, 1, 1, 1, 1, 1, 1, 1, 1, -1 );
INSERT INTO spells_per_day_level VALUES ( 97, 5, 17, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1 );
INSERT INTO spells_per_day_level VALUES ( 98, 5, 18, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1 );
INSERT INTO spells_per_day_level VALUES ( 99, 5, 19, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1 );
INSERT INTO spells_per_day_level VALUES ( 100, 5, 20, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1 );
INSERT INTO spells_per_day_level VALUES ( 101, 6, 1, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 102, 6, 2, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 103, 6, 3, 1, 1, 1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 104, 6, 4, 1, 1, 1, -1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 105, 6, 5, 1, 1, 1, 1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 106, 6, 6, 1, 1, 1, 1, -1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 107, 6, 7, 1, 1, 1, 1, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 108, 6, 8, 1, 1, 1, 1, 1, -1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 109, 6, 9, 1, 1, 1, 1, 1, 1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 110, 6, 10, 1, 1, 1, 1, 1, 1, -1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 111, 6, 11, 1, 1, 1, 1, 1, 1, 1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 112, 6, 12, 1, 1, 1, 1, 1, 1, 1, -1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 113, 6, 13, 1, 1, 1, 1, 1, 1, 1, 1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 114, 6, 14, 1, 1, 1, 1, 1, 1, 1, 1, -1, -1 );
INSERT INTO spells_per_day_level VALUES ( 115, 6, 15, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1 );
INSERT INTO spells_per_day_level VALUES ( 116, 6, 16, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1 );
INSERT INTO spells_per_day_level VALUES ( 117, 6, 17, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 );
INSERT INTO spells_per_day_level VALUES ( 118, 6, 18, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 );
INSERT INTO spells_per_day_level VALUES ( 119, 6, 19, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 );
INSERT INTO spells_per_day_level VALUES ( 120, 6, 20, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 );


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


UPDATE ability SET classname = 'SpelllistAbility' WHERE id IN (342, 346, 350, 354, 358, 362, 366, 370, 374);
INSERT INTO ability_property VALUES (81, 'spell_attribute_id', '5' );
INSERT INTO ability_property VALUES (81, 'casting_type_id', '0' );
INSERT INTO ability_property VALUES (81, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (81, 'spells_known_table_id', '1' );
INSERT INTO ability_property VALUES (81, 'spells_per_day_table_id', '1' );
INSERT INTO ability_property VALUES (81, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (81, 'school_id', '-1' );
INSERT INTO ability_property VALUES (103, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (103, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (103, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (103, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (103, 'spells_per_day_table_id', '4' );
INSERT INTO ability_property VALUES (103, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (103, 'school_id', '-1' );
INSERT INTO ability_property VALUES (110, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (110, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (110, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (110, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (110, 'spells_per_day_table_id', '4' );
INSERT INTO ability_property VALUES (110, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (110, 'school_id', '-1' );
INSERT INTO ability_property VALUES (163, 'spell_attribute_id', '5' );
INSERT INTO ability_property VALUES (163, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (163, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (163, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (163, 'spells_per_day_table_id', '2' );
INSERT INTO ability_property VALUES (163, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (163, 'school_id', '-1' );
INSERT INTO ability_property VALUES (179, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (179, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (179, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (179, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (179, 'spells_per_day_table_id', '2' );
INSERT INTO ability_property VALUES (179, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (179, 'school_id', '-1' );
INSERT INTO ability_property VALUES (220, 'spell_attribute_id', '5' );
INSERT INTO ability_property VALUES (220, 'casting_type_id', '0' );
INSERT INTO ability_property VALUES (220, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (220, 'spells_known_table_id', '2' );
INSERT INTO ability_property VALUES (220, 'spells_per_day_table_id', '3' );
INSERT INTO ability_property VALUES (220, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (220, 'school_id', '-1' );
INSERT INTO ability_property VALUES (334, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (334, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (334, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (334, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (334, 'spells_per_day_table_id', '4' );
INSERT INTO ability_property VALUES (334, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (334, 'school_id', '-1' );
INSERT INTO ability_property VALUES (342, 'spelllist_id', '2' );
INSERT INTO ability_property VALUES (342, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (342, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (342, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (342, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (342, 'spells_per_day_table_id', '6' );
INSERT INTO ability_property VALUES (342, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (342, 'school_id', '0' );
INSERT INTO ability_property VALUES (346, 'spelllist_id', '2' );
INSERT INTO ability_property VALUES (346, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (346, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (346, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (346, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (346, 'spells_per_day_table_id', '6' );
INSERT INTO ability_property VALUES (346, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (346, 'school_id', '1' );
INSERT INTO ability_property VALUES (350, 'spelllist_id', '2' );
INSERT INTO ability_property VALUES (350, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (350, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (350, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (350, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (350, 'spells_per_day_table_id', '6' );
INSERT INTO ability_property VALUES (350, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (350, 'school_id', '2' );
INSERT INTO ability_property VALUES (354, 'spelllist_id', '2' );
INSERT INTO ability_property VALUES (354, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (354, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (354, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (354, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (354, 'spells_per_day_table_id', '6' );
INSERT INTO ability_property VALUES (354, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (354, 'school_id', '3' );
INSERT INTO ability_property VALUES (358, 'spelllist_id', '2' );
INSERT INTO ability_property VALUES (358, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (358, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (358, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (358, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (358, 'spells_per_day_table_id', '6' );
INSERT INTO ability_property VALUES (358, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (358, 'school_id', '4' );
INSERT INTO ability_property VALUES (362, 'spelllist_id', '2' );
INSERT INTO ability_property VALUES (362, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (362, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (362, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (362, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (362, 'spells_per_day_table_id', '6' );
INSERT INTO ability_property VALUES (362, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (362, 'school_id', '5' );
INSERT INTO ability_property VALUES (366, 'spelllist_id', '2' );
INSERT INTO ability_property VALUES (366, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (366, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (366, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (366, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (366, 'spells_per_day_table_id', '6' );
INSERT INTO ability_property VALUES (366, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (366, 'school_id', '6' );
INSERT INTO ability_property VALUES (370, 'spelllist_id', '2' );
INSERT INTO ability_property VALUES (370, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (370, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (370, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (370, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (370, 'spells_per_day_table_id', '6' );
INSERT INTO ability_property VALUES (370, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (370, 'school_id', '7' );
INSERT INTO ability_property VALUES (374, 'spelllist_id', '2' );
INSERT INTO ability_property VALUES (374, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (374, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (374, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (374, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (374, 'spells_per_day_table_id', '6' );
INSERT INTO ability_property VALUES (374, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (374, 'school_id', '8' );
INSERT INTO ability_property VALUES (469, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (469, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (469, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (469, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (469, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (469, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (469, 'school_id', '-1' );
INSERT INTO ability_property VALUES (471, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (471, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (471, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (471, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (471, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (471, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (471, 'school_id', '-1' );
INSERT INTO ability_property VALUES (473, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (473, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (473, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (473, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (473, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (473, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (473, 'school_id', '-1' );
INSERT INTO ability_property VALUES (475, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (475, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (475, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (475, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (475, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (475, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (475, 'school_id', '-1' );
INSERT INTO ability_property VALUES (477, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (477, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (477, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (477, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (477, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (477, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (477, 'school_id', '-1' );
INSERT INTO ability_property VALUES (479, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (479, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (479, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (479, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (479, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (479, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (479, 'school_id', '-1' );
INSERT INTO ability_property VALUES (481, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (481, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (481, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (481, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (481, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (481, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (481, 'school_id', '-1' );
INSERT INTO ability_property VALUES (483, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (483, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (483, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (483, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (483, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (483, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (483, 'school_id', '-1' );
INSERT INTO ability_property VALUES (485, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (485, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (485, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (485, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (485, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (485, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (485, 'school_id', '-1' );
INSERT INTO ability_property VALUES (487, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (487, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (487, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (487, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (487, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (487, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (487, 'school_id', '-1' );
INSERT INTO ability_property VALUES (489, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (489, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (489, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (489, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (489, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (489, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (489, 'school_id', '-1' );
INSERT INTO ability_property VALUES (491, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (491, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (491, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (491, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (491, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (491, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (491, 'school_id', '-1' );
INSERT INTO ability_property VALUES (493, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (493, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (493, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (493, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (493, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (493, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (493, 'school_id', '-1' );
INSERT INTO ability_property VALUES (495, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (495, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (495, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (495, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (495, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (495, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (495, 'school_id', '-1' );
INSERT INTO ability_property VALUES (497, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (497, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (497, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (497, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (497, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (497, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (497, 'school_id', '-1' );
INSERT INTO ability_property VALUES (499, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (499, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (499, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (499, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (499, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (499, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (499, 'school_id', '-1' );
INSERT INTO ability_property VALUES (501, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (501, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (501, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (501, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (501, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (501, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (501, 'school_id', '-1' );
INSERT INTO ability_property VALUES (503, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (503, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (503, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (503, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (503, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (503, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (503, 'school_id', '-1' );
INSERT INTO ability_property VALUES (505, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (505, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (505, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (505, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (505, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (505, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (505, 'school_id', '-1' );
INSERT INTO ability_property VALUES (507, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (507, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (507, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (507, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (507, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (507, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (507, 'school_id', '-1' );
INSERT INTO ability_property VALUES (509, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (509, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (509, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (509, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (509, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (509, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (509, 'school_id', '-1' );
INSERT INTO ability_property VALUES (511, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (511, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (511, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (511, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (511, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (511, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (511, 'school_id', '-1' );
INSERT INTO ability_property VALUES (513, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (513, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (513, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (513, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (513, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (513, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (513, 'school_id', '-1' );
INSERT INTO ability_property VALUES (515, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (515, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (515, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (515, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (515, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (515, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (515, 'school_id', '-1' );
INSERT INTO ability_property VALUES (517, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (517, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (517, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (517, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (517, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (517, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (517, 'school_id', '-1' );
INSERT INTO ability_property VALUES (519, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (519, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (519, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (519, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (519, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (519, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (519, 'school_id', '-1' );
INSERT INTO ability_property VALUES (521, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (521, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (521, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (521, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (521, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (521, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (521, 'school_id', '-1' );
INSERT INTO ability_property VALUES (523, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (523, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (523, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (523, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (523, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (523, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (523, 'school_id', '-1' );
INSERT INTO ability_property VALUES (525, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (525, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (525, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (525, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (525, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (525, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (525, 'school_id', '-1' );
INSERT INTO ability_property VALUES (527, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (527, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (527, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (527, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (527, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (527, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (527, 'school_id', '-1' );
INSERT INTO ability_property VALUES (529, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (529, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (529, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (529, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (529, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (529, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (529, 'school_id', '-1' );
INSERT INTO ability_property VALUES (531, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (531, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (531, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (531, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (531, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (531, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (531, 'school_id', '-1' );
INSERT INTO ability_property VALUES (533, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (533, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (533, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (533, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (533, 'spells_per_day_table_id', '5' );
INSERT INTO ability_property VALUES (533, 'attribute_bonus_spell_slots', 'false' );
INSERT INTO ability_property VALUES (533, 'school_id', '-1' );
INSERT INTO ability_property VALUES (582, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (582, 'casting_type_id', '0' );
INSERT INTO ability_property VALUES (582, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (582, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (582, 'spells_per_day_table_id', '1' );
INSERT INTO ability_property VALUES (582, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (582, 'school_id', '-1' );
INSERT INTO ability_property VALUES (620, 'spell_attribute_id', '4' );
INSERT INTO ability_property VALUES (620, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (620, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (620, 'spells_known_table_id', '1' );
INSERT INTO ability_property VALUES (620, 'spells_per_day_table_id', '1' );
INSERT INTO ability_property VALUES (620, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (620, 'school_id', '-1' );
INSERT INTO ability_property VALUES (650, 'spell_attribute_id', '5' );
INSERT INTO ability_property VALUES (650, 'casting_type_id', '0' );
INSERT INTO ability_property VALUES (650, 'spell_source_id', '0' );
INSERT INTO ability_property VALUES (650, 'spells_known_table_id', '2' );
INSERT INTO ability_property VALUES (650, 'spells_per_day_table_id', '3' );
INSERT INTO ability_property VALUES (650, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (650, 'school_id', '-1' );
INSERT INTO ability_property VALUES (776, 'spell_attribute_id', '5' );
INSERT INTO ability_property VALUES (776, 'casting_type_id', '0' );
INSERT INTO ability_property VALUES (776, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (776, 'spells_known_table_id', '2' );
INSERT INTO ability_property VALUES (776, 'spells_per_day_table_id', '3' );
INSERT INTO ability_property VALUES (776, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (776, 'school_id', '-1' );
INSERT INTO ability_property VALUES (799, 'spell_attribute_id', '3' );
INSERT INTO ability_property VALUES (799, 'casting_type_id', '1' );
INSERT INTO ability_property VALUES (799, 'spell_source_id', '1' );
INSERT INTO ability_property VALUES (799, 'spells_known_table_id', '3' );
INSERT INTO ability_property VALUES (799, 'spells_per_day_table_id', '4' );
INSERT INTO ability_property VALUES (799, 'attribute_bonus_spell_slots', 'true' );
INSERT INTO ability_property VALUES (799, 'school_id', '-1' );


UPDATE ability SET classname = 'Ability' WHERE id IN (660, 673, 686, 699, 712, 725, 738, 751, 764, 800, 957, 984, 996, 997, 998, 999, 1000, 1001, 1002, 1003, 1004, 1005, 1006);
DELETE FROM ability_property WHERE ability_id IN (660, 673, 686, 699, 712, 725, 738, 751, 764, 800, 957, 984, 996, 997, 998, 999, 1000, 1001, 1002, 1003, 1004, 1005, 1006);
DELETE FROM spelllist WHERE id IN (44, 45, 46, 47, 48, 49, 50, 51, 52, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68);
DELETE FROM spelllist_spell WHERE spelllist_id IN (44, 45, 46, 47, 48, 49, 50, 51, 52, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68);
DELETE FROM charakter_known_spell WHERE spelllist_id IN (44, 45, 46, 47, 48, 49, 50, 51, 52, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68);


CREATE TABLE spelllist_temp
(id             INTEGER,
 name           VARCHAR(20),
 shortname      VARCHAR(20),
 domain         INTEGER DEFAULT 0,
 min_level      INTEGER DEFAULT 0,
 max_level      INTEGER DEFAULT 9,
 PRIMARY KEY (id)
);
INSERT INTO spelllist_temp VALUES ( 1, 'Sorcerer', 'Sor', 0, 0, 9 );
INSERT INTO spelllist_temp VALUES ( 2, 'Wizard', 'Wiz', 0, 0, 9 );
INSERT INTO spelllist_temp VALUES ( 3, 'Cleric', 'Cleric', 0, 0, 9 );
INSERT INTO spelllist_temp VALUES ( 4, 'Druid', 'Druid', 0, 0, 9 );
INSERT INTO spelllist_temp VALUES ( 5, 'Ranger', 'Ranger', 0, 1, 4 );
INSERT INTO spelllist_temp VALUES ( 6, 'Bard', 'Bard', 0, 0, 6 );
INSERT INTO spelllist_temp VALUES ( 7, 'Paladin', 'Paladin', 0, 1, 4 );
INSERT INTO spelllist_temp VALUES ( 8, 'Air', 'Air', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 9, 'Animal', 'Animal', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 10, 'Artifice', 'Artifice', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 11, 'Chaos', 'Chaos', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 12, 'Charm', 'Charm', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 13, 'Community', 'Community', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 14, 'Darkness', 'Darkness', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 15, 'Death', 'Death', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 16, 'Destruction', 'Destruction', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 17, 'Earth', 'Earth', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 18, 'Evil', 'Evil', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 19, 'Fire', 'Fire', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 20, 'Glory', 'Glory', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 21, 'Good', 'Good', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 22, 'Healing', 'Healing', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 23, 'Knowledge', 'Knowledge', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 24, 'Law', 'Law', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 25, 'Liberation', 'Liberation', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 26, 'Luck', 'Luck', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 27, 'Madness', 'Madness', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 28, 'Magic', 'Magic', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 29, 'Nobility', 'Nobility', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 30, 'Plant', 'Plant', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 31, 'Protection', 'Protection', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 32, 'Repose', 'Repose', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 33, 'Rune', 'Rune', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 34, 'Strength', 'Strength', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 35, 'Sun', 'Sun', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 36, 'Travel', 'Travel', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 37, 'Trickery', 'Trickery', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 38, 'War', 'War', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 39, 'Water', 'Water', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 40, 'Weather', 'Weather', 1, 1, 9 );
INSERT INTO spelllist_temp VALUES ( 41, 'Alchemist Formulae', 'Alchemist Formulae', 0, 1, 6 );
INSERT INTO spelllist_temp VALUES ( 42, 'Inquisitor', 'Inquisitor', 0, 0, 6 );
INSERT INTO spelllist_temp VALUES ( 43, 'Oracle', 'Oracle', 0, 0, 9 );
INSERT INTO spelllist_temp VALUES ( 53, 'Summoner', 'Summoner', 0, 1, 6 );
INSERT INTO spelllist_temp VALUES ( 54, 'Witch', 'Witch', 0, 0, 9 );
INSERT INTO spelllist_temp (id, name, shortname, domain, min_level, max_level) SELECT id, name, shortname, 0, 0, 9 FROM spelllist WHERE id > 68;
DROP TABLE spelllist;
ALTER TABLE spelllist_temp RENAME TO spelllist;


ALTER TABLE feat ADD COLUMN spell_slot INTEGER DEFAULT 0;
UPDATE feat SET spell_slot = 2 WHERE id = 168;
UPDATE feat SET spell_slot = 1 WHERE id = 169;
UPDATE feat SET spell_slot = 1 WHERE id = 170;
UPDATE feat SET spell_slot = 3 WHERE id = 172;
UPDATE feat SET spell_slot = 4 WHERE id = 173;
UPDATE feat SET spell_slot = 1 WHERE id = 174;
UPDATE feat SET spell_slot = 1 WHERE id = 175;
UPDATE feat SET spell_slot = 3 WHERE id = 176;
UPDATE feat SET spell_slot = 1 WHERE id = 315;
UPDATE feat SET spell_slot = 3 WHERE id = 316;
UPDATE feat SET spell_slot = 1 WHERE id = 317;
UPDATE feat SET spell_slot = 1 WHERE id = 318;
UPDATE feat SET spell_slot = 1 WHERE id = 319;
UPDATE feat SET spell_slot = 1 WHERE id = 320;
UPDATE feat SET spell_slot = 1 WHERE id = 321;
UPDATE feat SET spell_slot = 1 WHERE id = 322;
UPDATE feat SET spell_slot = 2 WHERE id = 324;
UPDATE feat SET spell_slot = 1 WHERE id = 325;
UPDATE feat SET spell_slot = 1 WHERE id = 326;
UPDATE feat SET spell_slot = 2 WHERE id = 327;
UPDATE feat SET spell_slot = 2 WHERE id = 328;


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
