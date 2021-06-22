CREATE TABLE class_starter_pack_box
(id            INTEGER,
 class_id      INTEGER,
 name          VARCHAR(32),
 PRIMARY KEY(id),
 FOREIGN KEY (class_id) REFERENCES class(id) ON DELETE CASCADE
);


CREATE TABLE class_starter_pack_option_query
(id                          INTEGER,
 starter_pack_box_id         INTEGER,
 starter_pack_box_option_id  INTEGER,
 equipment_type_id           INTEGER,
 type_id                     INTEGER,
 combat_id                   INTEGER,
 item_id                     INTEGER,
 quantity                    INTEGER,
 PRIMARY KEY(id),
 FOREIGN KEY (starter_pack_box_id) REFERENCES class_starter_pack_box(id) ON DELETE CASCADE
);


CREATE TABLE equipment_pack
(id               INTEGER,
 name             VARCHAR(30),
 PRIMARY KEY (id)
);


CREATE TABLE equipment_pack_item_group
(id                INTEGER,
 equipment_pack_id INTEGER,
 good_id           INTEGER,
 quantity          INTEGER,
 PRIMARY KEY (id),
 FOREIGN KEY (equipment_pack_id) REFERENCES equipment_pack(id)
 FOREIGN KEY (good_id) REFERENCES good(id)
);


INSERT INTO class_starter_pack_box VALUES (1, 1, 'Primary Hand' );
INSERT INTO class_starter_pack_box VALUES (2, 1, 'Secondary Hand' );
INSERT INTO class_starter_pack_box VALUES (3, 1, 'Range Weapon' );
INSERT INTO class_starter_pack_box VALUES (4, 1, 'Equipment' );
INSERT INTO class_starter_pack_box VALUES (5, 2, 'Primary Hand' );
INSERT INTO class_starter_pack_box VALUES (6, 2, 'Equipment' );
INSERT INTO class_starter_pack_box VALUES (7, 2, 'Music Instrument' );
INSERT INTO class_starter_pack_box VALUES (8, 2, 'Armor' );
INSERT INTO class_starter_pack_box VALUES (9, 3, 'Primary Hand' );
INSERT INTO class_starter_pack_box VALUES (10, 3, 'Armor' );
INSERT INTO class_starter_pack_box VALUES (11, 3, 'Secondary Hand' );
INSERT INTO class_starter_pack_box VALUES (12, 3, 'Equipment' );
INSERT INTO class_starter_pack_box VALUES (13, 3, 'Holy Symbol' );
INSERT INTO class_starter_pack_box VALUES (14, 4, 'Primary Hand' );
INSERT INTO class_starter_pack_box VALUES (15, 4, 'Secondary Hand' );
INSERT INTO class_starter_pack_box VALUES (16, 4, 'Armor' );
INSERT INTO class_starter_pack_box VALUES (17, 4, 'Equipment' );
INSERT INTO class_starter_pack_box VALUES (18, 4, 'Druidic Focus' );
INSERT INTO class_starter_pack_box VALUES (19, 5, 'Armor' );
INSERT INTO class_starter_pack_box VALUES (20, 5, 'Primary Hand' );
INSERT INTO class_starter_pack_box VALUES (21, 5, 'Secondary Hand' );
INSERT INTO class_starter_pack_box VALUES (22, 5, 'Range Weapon' );
INSERT INTO class_starter_pack_box VALUES (23, 5, 'Equipment' );
INSERT INTO class_starter_pack_box VALUES (24, 6, 'Primary Hand' );
INSERT INTO class_starter_pack_box VALUES (25, 6, 'Equipment' );
INSERT INTO class_starter_pack_box VALUES (26, 6, 'Range Weapon' );
INSERT INTO class_starter_pack_box VALUES (27, 7, 'Primary Hand' );
INSERT INTO class_starter_pack_box VALUES (28, 7, 'Secondary Hand' );
INSERT INTO class_starter_pack_box VALUES (29, 7, 'Range Weapon' );
INSERT INTO class_starter_pack_box VALUES (30, 7, 'Equipment' );
INSERT INTO class_starter_pack_box VALUES (31, 7, 'Armor' );
INSERT INTO class_starter_pack_box VALUES (32, 7, 'Holy Symbol' );
INSERT INTO class_starter_pack_box VALUES (33, 8, 'Armor' );
INSERT INTO class_starter_pack_box VALUES (34, 8, 'Primary Hand' );
INSERT INTO class_starter_pack_box VALUES (35, 8, 'Secondary Hand' );
INSERT INTO class_starter_pack_box VALUES (36, 8, 'Equipment' );
INSERT INTO class_starter_pack_box VALUES (37, 8, 'Range Weapon' );
INSERT INTO class_starter_pack_box VALUES (38, 9, 'Primary Hand' );
INSERT INTO class_starter_pack_box VALUES (39, 9, 'Range Weapon' );
INSERT INTO class_starter_pack_box VALUES (40, 9, 'Equipment' );
INSERT INTO class_starter_pack_box VALUES (41, 9, 'Armor' );
INSERT INTO class_starter_pack_box VALUES (42, 10, 'Primary Hand' );
INSERT INTO class_starter_pack_box VALUES (43, 10, 'Equipment' );
INSERT INTO class_starter_pack_box VALUES (44, 10, 'Range Weapon' );
INSERT INTO class_starter_pack_box VALUES (45, 12, 'Primary Hand' );
INSERT INTO class_starter_pack_box VALUES (46, 12, 'Equipment' );
INSERT INTO class_starter_pack_box VALUES (47, 12, 'Spellbook' );


INSERT INTO class_starter_pack_option_query VALUES (1, 1, 1, 0, -1, -1, 26, 1 );
INSERT INTO class_starter_pack_option_query VALUES (2, 1, 2, 0, 0, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (3, 2, 1, 0, -1, -1, 27, 2 );
INSERT INTO class_starter_pack_option_query VALUES (4, 2, 2, 0, 0, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (5, 3, 1, 0, -1, -1, 22, 4 );
INSERT INTO class_starter_pack_option_query VALUES (6, 4, 1, 3, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (7, 5, 1, 0, -1, -1, 37, 1 );
INSERT INTO class_starter_pack_option_query VALUES (8, 5, 2, 0, -1, -1, 28, 1 );
INSERT INTO class_starter_pack_option_query VALUES (9, 5, 3, 0, 0, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (10, 6, 1, 3, -1, -1, 2, 1 );
INSERT INTO class_starter_pack_option_query VALUES (11, 6, 2, 3, -1, -1, 4, 1 );
INSERT INTO class_starter_pack_option_query VALUES (12, 7, 1, 2, -1, -1, 89, 1 );
INSERT INTO class_starter_pack_option_query VALUES (13, 8, 1, 1, -1, -1, 2, 1 );
INSERT INTO class_starter_pack_option_query VALUES (14, 8, 1, 0, -1, -1, 3, 1 );
INSERT INTO class_starter_pack_option_query VALUES (15, 9, 1, 0, -1, -1, 6, 1 );
INSERT INTO class_starter_pack_option_query VALUES (16, 9, 2, 0, -1, -1, 40, 1 );
INSERT INTO class_starter_pack_option_query VALUES (17, 10, 1, 1, -1, -1, 6, 1 );
INSERT INTO class_starter_pack_option_query VALUES (18, 10, 1, 1, -1, -1, 15, 1 );
INSERT INTO class_starter_pack_option_query VALUES (19, 10, 2, 1, -1, -1, 2, 1 );
INSERT INTO class_starter_pack_option_query VALUES (20, 10, 2, 1, -1, -1, 15, 1 );
INSERT INTO class_starter_pack_option_query VALUES (21, 10, 3, 1, -1, -1, 7, 1 );
INSERT INTO class_starter_pack_option_query VALUES (22, 10, 3, 1, -1, -1, 15, 1 );
INSERT INTO class_starter_pack_option_query VALUES (23, 11, 1, 0, -1, -1, 19, 1 );
INSERT INTO class_starter_pack_option_query VALUES (24, 11, 1, 0, -1, -1, 20, 1 );
INSERT INTO class_starter_pack_option_query VALUES (25, 11, 2, 0, 0, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (26, 12, 1, 3, -1, -1, 6, 1 );
INSERT INTO class_starter_pack_option_query VALUES (27, 12, 2, 3, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (28, 13, 1, 2, -1, -1, 86, 1 );
INSERT INTO class_starter_pack_option_query VALUES (29, 13, 2, 2, -1, -1, 87, 1 );
INSERT INTO class_starter_pack_option_query VALUES (30, 14, 1, 1, -1, -1, 14, 1 );
INSERT INTO class_starter_pack_option_query VALUES (31, 14, 2, 0, 0, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (32, 15, 1, 0, -1, -1, 38, 1 );
INSERT INTO class_starter_pack_option_query VALUES (33, 15, 2, 0, 0, 0, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (34, 16, 1, 1, -1, -1, 2, 1 );
INSERT INTO class_starter_pack_option_query VALUES (35, 17, 1, 3, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (36, 18, 1, 2, -1, -1, 85, 1 );
INSERT INTO class_starter_pack_option_query VALUES (37, 19, 1, 1, -1, -1, 7, 1 );
INSERT INTO class_starter_pack_option_query VALUES (38, 19, 2, 1, -1, -1, 2, 1 );
INSERT INTO class_starter_pack_option_query VALUES (39, 19, 2, 0, -1, -1, 52, 1 );
INSERT INTO class_starter_pack_option_query VALUES (40, 19, 2, 0, -1, -1, 53, 1 );
INSERT INTO class_starter_pack_option_query VALUES (41, 20, 1, 0, 1, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (42, 21, 1, 0, 1, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (43, 21, 2, 1, -1, -1, 15, 1 );
INSERT INTO class_starter_pack_option_query VALUES (44, 22, 1, 0, -1, -1, 19, 1 );
INSERT INTO class_starter_pack_option_query VALUES (45, 22, 1, 0, -1, -1, 20, 1 );
INSERT INTO class_starter_pack_option_query VALUES (46, 22, 2, 0, -1, -1, 27, 2 );
INSERT INTO class_starter_pack_option_query VALUES (47, 23, 1, 3, -1, -1, 3, 1 );
INSERT INTO class_starter_pack_option_query VALUES (48, 23, 2, 3, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (49, 24, 1, 0, -1, -1, 32, 1 );
INSERT INTO class_starter_pack_option_query VALUES (50, 24, 2, 0, 0, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (51, 25, 1, 3, -1, -1, 3, 1 );
INSERT INTO class_starter_pack_option_query VALUES (52, 25, 2, 3, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (53, 26, 1, 0, -1, -1, 21, 10 );
INSERT INTO class_starter_pack_option_query VALUES (54, 27, 1, 0, 1, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (55, 28, 1, 0, 1, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (56, 28, 2, 1, -1, -1, 15, 1 );
INSERT INTO class_starter_pack_option_query VALUES (57, 29, 1, 0, -1, -1, 22, 5 );
INSERT INTO class_starter_pack_option_query VALUES (58, 29, 2, 0, 0, 0, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (59, 30, 1, 3, -1, -1, 6, 1 );
INSERT INTO class_starter_pack_option_query VALUES (60, 30, 2, 3, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (61, 31, 1, 1, -1, -1, 7, 1 );
INSERT INTO class_starter_pack_option_query VALUES (62, 32, 1, 2, -1, -1, 86, 1 );
INSERT INTO class_starter_pack_option_query VALUES (63, 32, 2, 2, -1, -1, 87, 1 );
INSERT INTO class_starter_pack_option_query VALUES (64, 33, 1, 1, -1, -1, 6, 1 );
INSERT INTO class_starter_pack_option_query VALUES (65, 33, 2, 1, -1, -1, 2, 1 );
INSERT INTO class_starter_pack_option_query VALUES (66, 34, 1, 0, -1, -1, 32, 1 );
INSERT INTO class_starter_pack_option_query VALUES (67, 34, 2, 0, 0, 0, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (68, 35, 1, 0, -1, -1, 32, 1 );
INSERT INTO class_starter_pack_option_query VALUES (69, 35, 2, 0, 0, 0, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (70, 36, 1, 3, -1, -1, 3, 1 );
INSERT INTO class_starter_pack_option_query VALUES (71, 36, 2, 3, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (72, 37, 1, 0, -1, -1, 52, 1 );
INSERT INTO class_starter_pack_option_query VALUES (73, 37, 1, 0, -1, -1, 53, 1 );
INSERT INTO class_starter_pack_option_query VALUES (74, 38, 1, 0, -1, -1, 37, 1 );
INSERT INTO class_starter_pack_option_query VALUES (75, 38, 2, 0, -1, -1, 32, 1 );
INSERT INTO class_starter_pack_option_query VALUES (76, 39, 1, 0, -1, -1, 56, 1 );
INSERT INTO class_starter_pack_option_query VALUES (77, 39, 1, 0, -1, -1, 57, 1 );
INSERT INTO class_starter_pack_option_query VALUES (78, 39, 2, 0, -1, -1, 32, 1 );
INSERT INTO class_starter_pack_option_query VALUES (79, 40, 1, 3, -1, -1, 1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (80, 40, 2, 3, -1, -1, 3, 1 );
INSERT INTO class_starter_pack_option_query VALUES (81, 40, 3, 3, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (82, 41, 1, 1, -1, -1, 2, 1 );
INSERT INTO class_starter_pack_option_query VALUES (83, 41, 1, 0, -1, -1, 3, 2 );
INSERT INTO class_starter_pack_option_query VALUES (84, 41, 1, 2, -1, -1, 94, 1 );
INSERT INTO class_starter_pack_option_query VALUES (85, 42, 1, 0, -1, -1, 19, 1 );
INSERT INTO class_starter_pack_option_query VALUES (86, 42, 1, 0, -1, -1, 20, 1 );
INSERT INTO class_starter_pack_option_query VALUES (87, 42, 2, 0, 0, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (88, 43, 1, 3, -1, -1, 3, 1 );
INSERT INTO class_starter_pack_option_query VALUES (89, 43, 2, 3, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (90, 44, 1, 0, -1, -1, 3, 2 );
INSERT INTO class_starter_pack_option_query VALUES (91, 45, 1, 0, -1, -1, 13, 1 );
INSERT INTO class_starter_pack_option_query VALUES (92, 45, 2, 0, -1, -1, 3, 1 );
INSERT INTO class_starter_pack_option_query VALUES (93, 46, 1, 3, -1, -1, 7, 1 );
INSERT INTO class_starter_pack_option_query VALUES (94, 46, 2, 3, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (95, 47, 1, 2, -1, -1, 93, 1 );


INSERT INTO equipment_pack VALUES (1, 'Burglar''s Pack' );
INSERT INTO equipment_pack VALUES (2, 'Diplomat''s Pack' );
INSERT INTO equipment_pack VALUES (3, 'Dungeoneer''s Pack' );
INSERT INTO equipment_pack VALUES (4, 'Entertainer''s Pack' );
INSERT INTO equipment_pack VALUES (5, 'Explorer''s Pack' );
INSERT INTO equipment_pack VALUES (6, 'Priest''s Pack' );
INSERT INTO equipment_pack VALUES (7, 'Scholar''s Pack' );


INSERT INTO equipment_pack_item_group VALUES (1, 1, 1, 1 );
INSERT INTO equipment_pack_item_group VALUES (2, 1, 10, 1 );
INSERT INTO equipment_pack_item_group VALUES (3, 1, 5, 1 );
INSERT INTO equipment_pack_item_group VALUES (4, 1, 11, 5 );
INSERT INTO equipment_pack_item_group VALUES (5, 1, 17, 1 );
INSERT INTO equipment_pack_item_group VALUES (6, 1, 24, 1 );
INSERT INTO equipment_pack_item_group VALUES (7, 1, 46, 10 );
INSERT INTO equipment_pack_item_group VALUES (8, 1, 32, 1 );
INSERT INTO equipment_pack_item_group VALUES (9, 1, 41, 2 );
INSERT INTO equipment_pack_item_group VALUES (10, 1, 51, 5 );
INSERT INTO equipment_pack_item_group VALUES (11, 1, 22, 1 );
INSERT INTO equipment_pack_item_group VALUES (12, 1, 67, 1 );
INSERT INTO equipment_pack_item_group VALUES (13, 1, 52, 1 );
INSERT INTO equipment_pack_item_group VALUES (14, 1, 97, 1 );
INSERT INTO equipment_pack_item_group VALUES (15, 2, 16, 1 );
INSERT INTO equipment_pack_item_group VALUES (16, 2, 13, 2 );
INSERT INTO equipment_pack_item_group VALUES (17, 2, 100, 1 );
INSERT INTO equipment_pack_item_group VALUES (18, 2, 26, 1 );
INSERT INTO equipment_pack_item_group VALUES (19, 2, 27, 1 );
INSERT INTO equipment_pack_item_group VALUES (20, 2, 30, 1 );
INSERT INTO equipment_pack_item_group VALUES (21, 2, 41, 2 );
INSERT INTO equipment_pack_item_group VALUES (22, 2, 42, 5 );
INSERT INTO equipment_pack_item_group VALUES (23, 2, 55, 1 );
INSERT INTO equipment_pack_item_group VALUES (24, 2, 61, 1 );
INSERT INTO equipment_pack_item_group VALUES (25, 3, 1, 1 );
INSERT INTO equipment_pack_item_group VALUES (26, 3, 17, 1 );
INSERT INTO equipment_pack_item_group VALUES (27, 3, 24, 1 );
INSERT INTO equipment_pack_item_group VALUES (28, 3, 46, 10 );
INSERT INTO equipment_pack_item_group VALUES (29, 3, 64, 10 );
INSERT INTO equipment_pack_item_group VALUES (30, 3, 22, 1 );
INSERT INTO equipment_pack_item_group VALUES (31, 3, 51, 10 );
INSERT INTO equipment_pack_item_group VALUES (32, 3, 67, 1 );
INSERT INTO equipment_pack_item_group VALUES (33, 3, 52, 1 );
INSERT INTO equipment_pack_item_group VALUES (34, 3, 108, 1 );
INSERT INTO equipment_pack_item_group VALUES (35, 4, 1, 1 );
INSERT INTO equipment_pack_item_group VALUES (36, 4, 4, 1 );
INSERT INTO equipment_pack_item_group VALUES (37, 4, 11, 5 );
INSERT INTO equipment_pack_item_group VALUES (38, 4, 51, 5 );
INSERT INTO equipment_pack_item_group VALUES (39, 4, 67, 1 );
INSERT INTO equipment_pack_item_group VALUES (40, 4, 83, 1 );
INSERT INTO equipment_pack_item_group VALUES (41, 4, 101, 1 );
INSERT INTO equipment_pack_item_group VALUES (42, 5, 1, 1 );
INSERT INTO equipment_pack_item_group VALUES (43, 5, 4, 1 );
INSERT INTO equipment_pack_item_group VALUES (44, 5, 22, 1 );
INSERT INTO equipment_pack_item_group VALUES (45, 5, 64, 10 );
INSERT INTO equipment_pack_item_group VALUES (46, 5, 51, 10 );
INSERT INTO equipment_pack_item_group VALUES (47, 5, 67, 1 );
INSERT INTO equipment_pack_item_group VALUES (48, 5, 52, 1 );
INSERT INTO equipment_pack_item_group VALUES (49, 5, 102, 1 );
INSERT INTO equipment_pack_item_group VALUES (50, 6, 1, 1 );
INSERT INTO equipment_pack_item_group VALUES (51, 6, 6, 1 );
INSERT INTO equipment_pack_item_group VALUES (52, 6, 11, 10 );
INSERT INTO equipment_pack_item_group VALUES (53, 6, 22, 1 );
INSERT INTO equipment_pack_item_group VALUES (54, 6, 51, 1 );
INSERT INTO equipment_pack_item_group VALUES (55, 6, 67, 1 );
INSERT INTO equipment_pack_item_group VALUES (56, 6, 98, 1 );
INSERT INTO equipment_pack_item_group VALUES (57, 7, 1, 1 );
INSERT INTO equipment_pack_item_group VALUES (58, 7, 26, 1 );
INSERT INTO equipment_pack_item_group VALUES (59, 7, 27, 1 );
INSERT INTO equipment_pack_item_group VALUES (60, 7, 43, 10 );
INSERT INTO equipment_pack_item_group VALUES (61, 7, 107, 1 );
