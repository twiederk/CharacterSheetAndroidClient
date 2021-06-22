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
INSERT INTO class_starter_pack_box VALUES (43, 10, 'Arcane Focus' );
INSERT INTO class_starter_pack_box VALUES (44, 10, 'Equipment' );
INSERT INTO class_starter_pack_box VALUES (45, 10, 'Range Weapon' );
INSERT INTO class_starter_pack_box VALUES (46, 11, 'Primary Hand' );
INSERT INTO class_starter_pack_box VALUES (47, 11, 'Secondary Hand' );
INSERT INTO class_starter_pack_box VALUES (48, 11, 'Arcane Focus' );
INSERT INTO class_starter_pack_box VALUES (49, 11, 'Equipment' );
INSERT INTO class_starter_pack_box VALUES (50, 11, 'Armor' );
INSERT INTO class_starter_pack_box VALUES (51, 12, 'Primary Hand' );
INSERT INTO class_starter_pack_box VALUES (52, 12, 'Arcane Focus' );
INSERT INTO class_starter_pack_box VALUES (53, 12, 'Equipment' );
INSERT INTO class_starter_pack_box VALUES (54, 12, 'Spellbook' );


INSERT INTO class_starter_pack_option_query VALUES (1, 1, 1, 0, -1, -1, 21, 1 );
INSERT INTO class_starter_pack_option_query VALUES (2, 1, 2, 0, 0, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (3, 2, 1, 0, -1, -1, 4, 2 );
INSERT INTO class_starter_pack_option_query VALUES (4, 2, 2, 0, 0, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (5, 3, 1, 0, -1, -1, 5, 4 );
INSERT INTO class_starter_pack_option_query VALUES (6, 4, 1, 3, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (7, 5, 1, 0, -1, -1, 30, 1 );
INSERT INTO class_starter_pack_option_query VALUES (8, 5, 2, 0, -1, -1, 25, 1 );
INSERT INTO class_starter_pack_option_query VALUES (9, 5, 3, 0, 0, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (10, 6, 1, 3, -1, -1, 2, 1 );
INSERT INTO class_starter_pack_option_query VALUES (11, 6, 2, 3, -1, -1, 4, 1 );
INSERT INTO class_starter_pack_option_query VALUES (12, 7, 1, 2, 8, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (13, 8, 1, 1, -1, -1, 2, 1 );
INSERT INTO class_starter_pack_option_query VALUES (14, 8, 1, 0, -1, -1, 2, 1 );
INSERT INTO class_starter_pack_option_query VALUES (15, 9, 1, 0, -1, -1, 7, 1 );
INSERT INTO class_starter_pack_option_query VALUES (16, 9, 2, 0, -1, -1, 36, 1 );
INSERT INTO class_starter_pack_option_query VALUES (17, 10, 1, 1, -1, -1, 6, 1 );
INSERT INTO class_starter_pack_option_query VALUES (18, 10, 1, 1, -1, -1, 13, 1 );
INSERT INTO class_starter_pack_option_query VALUES (19, 10, 2, 1, -1, -1, 2, 1 );
INSERT INTO class_starter_pack_option_query VALUES (20, 10, 2, 1, -1, -1, 13, 1 );
INSERT INTO class_starter_pack_option_query VALUES (21, 10, 3, 1, -1, -1, 10, 1 );
INSERT INTO class_starter_pack_option_query VALUES (22, 10, 3, 1, -1, -1, 13, 1 );
INSERT INTO class_starter_pack_option_query VALUES (23, 11, 1, 0, -1, -1, 13, 1 );
INSERT INTO class_starter_pack_option_query VALUES (24, 11, 1, 0, -1, -1, 46, 1 );
INSERT INTO class_starter_pack_option_query VALUES (25, 11, 2, 0, 0, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (26, 12, 1, 3, -1, -1, 6, 1 );
INSERT INTO class_starter_pack_option_query VALUES (27, 12, 2, 3, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (28, 13, 1, 2, -1, -1, 45, 1 );
INSERT INTO class_starter_pack_option_query VALUES (29, 13, 2, 2, -1, -1, 46, 1 );
INSERT INTO class_starter_pack_option_query VALUES (30, 13, 3, 2, -1, -1, 47, 1 );
INSERT INTO class_starter_pack_option_query VALUES (31, 14, 1, 1, -1, -1, 13, 1 );
INSERT INTO class_starter_pack_option_query VALUES (32, 14, 2, 0, 0, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (33, 15, 1, 0, -1, -1, 31, 1 );
INSERT INTO class_starter_pack_option_query VALUES (34, 15, 2, 0, 0, 0, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (35, 16, 1, 1, -1, -1, 2, 1 );
INSERT INTO class_starter_pack_option_query VALUES (36, 17, 1, 3, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (37, 18, 1, 2, -1, -1, 35, 1 );
INSERT INTO class_starter_pack_option_query VALUES (38, 18, 2, 2, -1, -1, 36, 1 );
INSERT INTO class_starter_pack_option_query VALUES (39, 18, 3, 2, -1, -1, 37, 1 );
INSERT INTO class_starter_pack_option_query VALUES (40, 18, 4, 2, -1, -1, 38, 1 );
INSERT INTO class_starter_pack_option_query VALUES (41, 19, 1, 1, -1, -1, 10, 1 );
INSERT INTO class_starter_pack_option_query VALUES (42, 19, 2, 1, -1, -1, 2, 1 );
INSERT INTO class_starter_pack_option_query VALUES (43, 19, 2, 0, -1, -1, 42, 1 );
INSERT INTO class_starter_pack_option_query VALUES (44, 19, 2, 0, -1, -1, 44, 1 );
INSERT INTO class_starter_pack_option_query VALUES (45, 20, 1, 0, 1, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (46, 21, 1, 0, 1, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (47, 21, 2, 1, -1, -1, 13, 1 );
INSERT INTO class_starter_pack_option_query VALUES (48, 22, 1, 0, -1, -1, 13, 1 );
INSERT INTO class_starter_pack_option_query VALUES (49, 22, 1, 0, -1, -1, 46, 1 );
INSERT INTO class_starter_pack_option_query VALUES (50, 22, 2, 0, -1, -1, 4, 2 );
INSERT INTO class_starter_pack_option_query VALUES (51, 23, 1, 3, -1, -1, 3, 1 );
INSERT INTO class_starter_pack_option_query VALUES (52, 23, 2, 3, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (53, 24, 1, 0, -1, -1, 32, 1 );
INSERT INTO class_starter_pack_option_query VALUES (54, 24, 2, 0, 0, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (55, 25, 1, 3, -1, -1, 3, 1 );
INSERT INTO class_starter_pack_option_query VALUES (56, 25, 2, 3, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (57, 26, 1, 0, -1, -1, 14, 10 );
INSERT INTO class_starter_pack_option_query VALUES (58, 27, 1, 0, 1, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (59, 28, 1, 0, 1, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (60, 28, 2, 1, -1, -1, 13, 1 );
INSERT INTO class_starter_pack_option_query VALUES (61, 29, 1, 0, -1, -1, 5, 5 );
INSERT INTO class_starter_pack_option_query VALUES (62, 29, 2, 0, 0, 0, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (63, 30, 1, 3, -1, -1, 6, 1 );
INSERT INTO class_starter_pack_option_query VALUES (64, 30, 2, 3, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (65, 31, 1, 1, -1, -1, 10, 1 );
INSERT INTO class_starter_pack_option_query VALUES (66, 32, 1, 2, -1, -1, 45, 1 );
INSERT INTO class_starter_pack_option_query VALUES (67, 32, 2, 2, -1, -1, 46, 1 );
INSERT INTO class_starter_pack_option_query VALUES (68, 32, 3, 2, -1, -1, 47, 1 );
INSERT INTO class_starter_pack_option_query VALUES (69, 33, 1, 1, -1, -1, 6, 1 );
INSERT INTO class_starter_pack_option_query VALUES (70, 33, 2, 1, -1, -1, 2, 1 );
INSERT INTO class_starter_pack_option_query VALUES (71, 34, 1, 0, -1, -1, 32, 1 );
INSERT INTO class_starter_pack_option_query VALUES (72, 34, 2, 0, 0, 0, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (73, 35, 1, 0, -1, -1, 32, 1 );
INSERT INTO class_starter_pack_option_query VALUES (74, 35, 2, 0, 0, 0, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (75, 36, 1, 3, -1, -1, 3, 1 );
INSERT INTO class_starter_pack_option_query VALUES (76, 36, 2, 3, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (77, 37, 1, 0, -1, -1, 42, 1 );
INSERT INTO class_starter_pack_option_query VALUES (78, 37, 1, 0, -1, -1, 44, 1 );
INSERT INTO class_starter_pack_option_query VALUES (79, 38, 1, 0, -1, -1, 30, 1 );
INSERT INTO class_starter_pack_option_query VALUES (80, 38, 2, 0, -1, -1, 32, 1 );
INSERT INTO class_starter_pack_option_query VALUES (81, 39, 1, 0, -1, -1, 15, 1 );
INSERT INTO class_starter_pack_option_query VALUES (82, 39, 1, 0, -1, -1, 44, 1 );
INSERT INTO class_starter_pack_option_query VALUES (83, 39, 2, 0, -1, -1, 32, 1 );
INSERT INTO class_starter_pack_option_query VALUES (84, 40, 1, 3, -1, -1, 1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (85, 40, 2, 3, -1, -1, 3, 1 );
INSERT INTO class_starter_pack_option_query VALUES (86, 40, 3, 3, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (87, 41, 1, 1, -1, -1, 2, 1 );
INSERT INTO class_starter_pack_option_query VALUES (88, 41, 1, 0, -1, -1, 2, 2 );
INSERT INTO class_starter_pack_option_query VALUES (89, 41, 1, 2, -1, -1, 132, 1 );
INSERT INTO class_starter_pack_option_query VALUES (90, 42, 1, 0, -1, -1, 13, 1 );
INSERT INTO class_starter_pack_option_query VALUES (91, 42, 1, 0, -1, -1, 46, 1 );
INSERT INTO class_starter_pack_option_query VALUES (92, 42, 2, 0, 0, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (93, 43, 1, 2, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (94, 43, 2, 2, -1, -1, 6, 1 );
INSERT INTO class_starter_pack_option_query VALUES (95, 43, 3, 2, -1, -1, 7, 1 );
INSERT INTO class_starter_pack_option_query VALUES (96, 43, 4, 2, -1, -1, 8, 1 );
INSERT INTO class_starter_pack_option_query VALUES (97, 43, 5, 2, -1, -1, 9, 1 );
INSERT INTO class_starter_pack_option_query VALUES (98, 43, 6, 2, -1, -1, 33, 1 );
INSERT INTO class_starter_pack_option_query VALUES (99, 44, 1, 3, -1, -1, 3, 1 );
INSERT INTO class_starter_pack_option_query VALUES (100, 44, 2, 3, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (101, 45, 1, 0, -1, -1, 2, 2 );
INSERT INTO class_starter_pack_option_query VALUES (102, 46, 1, 0, -1, -1, 13, 1 );
INSERT INTO class_starter_pack_option_query VALUES (103, 46, 1, 0, -1, -1, 46, 1 );
INSERT INTO class_starter_pack_option_query VALUES (104, 46, 2, 0, 0, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (105, 47, 1, 0, 0, -1, -1, 1 );
INSERT INTO class_starter_pack_option_query VALUES (106, 48, 1, 2, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (107, 48, 2, 2, -1, -1, 6, 1 );
INSERT INTO class_starter_pack_option_query VALUES (108, 48, 3, 2, -1, -1, 7, 1 );
INSERT INTO class_starter_pack_option_query VALUES (109, 48, 4, 2, -1, -1, 8, 1 );
INSERT INTO class_starter_pack_option_query VALUES (110, 48, 5, 2, -1, -1, 9, 1 );
INSERT INTO class_starter_pack_option_query VALUES (111, 48, 6, 2, -1, -1, 33, 1 );
INSERT INTO class_starter_pack_option_query VALUES (112, 49, 1, 3, -1, -1, 7, 1 );
INSERT INTO class_starter_pack_option_query VALUES (113, 49, 2, 3, -1, -1, 3, 1 );
INSERT INTO class_starter_pack_option_query VALUES (114, 50, 1, 1, -1, -1, 2, 1 );
INSERT INTO class_starter_pack_option_query VALUES (115, 50, 1, 0, -1, -1, 2, 2 );
INSERT INTO class_starter_pack_option_query VALUES (116, 51, 1, 0, -1, -1, 8, 1 );
INSERT INTO class_starter_pack_option_query VALUES (117, 51, 2, 0, -1, -1, 2, 1 );
INSERT INTO class_starter_pack_option_query VALUES (118, 52, 1, 2, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (119, 52, 2, 2, -1, -1, 6, 1 );
INSERT INTO class_starter_pack_option_query VALUES (120, 52, 3, 2, -1, -1, 7, 1 );
INSERT INTO class_starter_pack_option_query VALUES (121, 52, 4, 2, -1, -1, 8, 1 );
INSERT INTO class_starter_pack_option_query VALUES (122, 52, 5, 2, -1, -1, 9, 1 );
INSERT INTO class_starter_pack_option_query VALUES (123, 52, 6, 2, -1, -1, 33, 1 );
INSERT INTO class_starter_pack_option_query VALUES (124, 53, 1, 3, -1, -1, 7, 1 );
INSERT INTO class_starter_pack_option_query VALUES (125, 53, 2, 3, -1, -1, 5, 1 );
INSERT INTO class_starter_pack_option_query VALUES (126, 54, 1, 2, -1, -1, 87, 1 );


INSERT INTO equipment_pack VALUES (1, 'Burglar''s Pack' );
INSERT INTO equipment_pack VALUES (2, 'Diplomat''s Pack' );
INSERT INTO equipment_pack VALUES (3, 'Dungeoneer''s Pack' );
INSERT INTO equipment_pack VALUES (4, 'Entertainer''s Pack' );
INSERT INTO equipment_pack VALUES (5, 'Explorer''s Pack' );
INSERT INTO equipment_pack VALUES (6, 'Priest''s Pack' );
INSERT INTO equipment_pack VALUES (7, 'Scholar''s Pack' );


INSERT INTO equipment_pack_item_group VALUES (1, 1, 10, 1 );
INSERT INTO equipment_pack_item_group VALUES (2, 1, 11, 1 );
INSERT INTO equipment_pack_item_group VALUES (3, 1, 15, 1 );
INSERT INTO equipment_pack_item_group VALUES (4, 1, 22, 5 );
INSERT INTO equipment_pack_item_group VALUES (5, 1, 34, 1 );
INSERT INTO equipment_pack_item_group VALUES (6, 1, 42, 1 );
INSERT INTO equipment_pack_item_group VALUES (7, 1, 68, 10 );
INSERT INTO equipment_pack_item_group VALUES (8, 1, 57, 1 );
INSERT INTO equipment_pack_item_group VALUES (9, 1, 63, 2 );
INSERT INTO equipment_pack_item_group VALUES (10, 1, 76, 5 );
INSERT INTO equipment_pack_item_group VALUES (11, 1, 91, 1 );
INSERT INTO equipment_pack_item_group VALUES (12, 1, 94, 1 );
INSERT INTO equipment_pack_item_group VALUES (13, 1, 78, 1 );
INSERT INTO equipment_pack_item_group VALUES (14, 2, 27, 1 );
INSERT INTO equipment_pack_item_group VALUES (15, 2, 24, 2 );
INSERT INTO equipment_pack_item_group VALUES (16, 2, 31, 1 );
INSERT INTO equipment_pack_item_group VALUES (17, 2, 51, 1 );
INSERT INTO equipment_pack_item_group VALUES (18, 2, 52, 1 );
INSERT INTO equipment_pack_item_group VALUES (19, 2, 55, 1 );
INSERT INTO equipment_pack_item_group VALUES (20, 2, 63, 2 );
INSERT INTO equipment_pack_item_group VALUES (21, 2, 64, 5 );
INSERT INTO equipment_pack_item_group VALUES (22, 2, 66, 1 );
INSERT INTO equipment_pack_item_group VALUES (23, 2, 82, 1 );
INSERT INTO equipment_pack_item_group VALUES (24, 2, 86, 1 );
INSERT INTO equipment_pack_item_group VALUES (25, 3, 10, 1 );
INSERT INTO equipment_pack_item_group VALUES (26, 3, 34, 1 );
INSERT INTO equipment_pack_item_group VALUES (27, 3, 42, 1 );
INSERT INTO equipment_pack_item_group VALUES (28, 3, 68, 10 );
INSERT INTO equipment_pack_item_group VALUES (29, 3, 92, 10 );
INSERT INTO equipment_pack_item_group VALUES (30, 3, 91, 1 );
INSERT INTO equipment_pack_item_group VALUES (31, 3, 76, 10 );
INSERT INTO equipment_pack_item_group VALUES (32, 3, 94, 1 );
INSERT INTO equipment_pack_item_group VALUES (33, 3, 78, 1 );
INSERT INTO equipment_pack_item_group VALUES (34, 4, 10, 1 );
INSERT INTO equipment_pack_item_group VALUES (35, 4, 14, 1 );
INSERT INTO equipment_pack_item_group VALUES (36, 4, 22, 5 );
INSERT INTO equipment_pack_item_group VALUES (37, 4, 76, 5 );
INSERT INTO equipment_pack_item_group VALUES (38, 4, 94, 1 );
INSERT INTO equipment_pack_item_group VALUES (39, 4, 127, 1 );
INSERT INTO equipment_pack_item_group VALUES (40, 5, 10, 1 );
INSERT INTO equipment_pack_item_group VALUES (41, 5, 14, 1 );
INSERT INTO equipment_pack_item_group VALUES (42, 5, 61, 1 );
INSERT INTO equipment_pack_item_group VALUES (43, 5, 91, 1 );
INSERT INTO equipment_pack_item_group VALUES (44, 5, 92, 10 );
INSERT INTO equipment_pack_item_group VALUES (45, 5, 76, 10 );
INSERT INTO equipment_pack_item_group VALUES (46, 5, 94, 1 );
INSERT INTO equipment_pack_item_group VALUES (47, 5, 78, 1 );
INSERT INTO equipment_pack_item_group VALUES (48, 6, 10, 1 );
INSERT INTO equipment_pack_item_group VALUES (49, 6, 16, 1 );
INSERT INTO equipment_pack_item_group VALUES (50, 6, 22, 10 );
INSERT INTO equipment_pack_item_group VALUES (51, 6, 91, 1 );
INSERT INTO equipment_pack_item_group VALUES (52, 6, 76, 1 );
INSERT INTO equipment_pack_item_group VALUES (53, 6, 94, 1 );
INSERT INTO equipment_pack_item_group VALUES (54, 7, 10, 1 );
INSERT INTO equipment_pack_item_group VALUES (55, 7, 18, 1 );
INSERT INTO equipment_pack_item_group VALUES (56, 7, 51, 1 );
INSERT INTO equipment_pack_item_group VALUES (57, 7, 52, 1 );
INSERT INTO equipment_pack_item_group VALUES (58, 7, 65, 10 );
