CREATE TABLE race_temp
(id          INTEGER,
 name              VARCHAR(30),
 size_id           INTEGER,
 base_land_speed   INTEGER,
 fav_class_id      INTEGER,
 PRIMARY KEY (id)
);

INSERT INTO race_temp (id, name, size_id, base_land_speed, fav_class_id) SELECT id, name, size_id, base_land_speed, fav_class_id FROM race, race_text WHERE race.id = race_text.race_id AND language_id = 0;

DROP TABLE race;
DROP TABLE race_text;
ALTER TABLE race_temp RENAME TO race;



CREATE TABLE charakter_class_temp
(id                       INTEGER,
 name                     VARCHAR(30),
 saves                    INTEGER DEFAULT 0,
 alignments               INTEGER DEFAULT 0,
 base_attack_bonus        INTEGER,
 skill_points_per_level   INTEGER,
 hit_die_id               INTEGER,
 PRIMARY KEY (id)
);

INSERT INTO charakter_class_temp (id, name, base_attack_bonus, skill_points_per_level, hit_die_id) SELECT id, name, base_attack_bonus, skill_points_per_level, hit_die_id FROM charakter_class, charakter_class_text WHERE charakter_class.id = charakter_class_text.charakter_class_id AND language_id = 0;

UPDATE charakter_class_temp SET name = 'Barbarian' WHERE name = 'Barbar';
UPDATE charakter_class_temp SET name = 'Bard' WHERE name = 'Barde';
UPDATE charakter_class_temp SET name = 'Cleric' WHERE name = 'Kleriker';
UPDATE charakter_class_temp SET name = 'Druid' WHERE name = 'Druide';
UPDATE charakter_class_temp SET name = 'Fighter' WHERE name = 'Kaempfer';
UPDATE charakter_class_temp SET name = 'Monk' WHERE name = 'Moench';
UPDATE charakter_class_temp SET name = 'Paladin' WHERE name = 'Paladin';
UPDATE charakter_class_temp SET name = 'Ranger' WHERE name = 'Waldlaeufer';
UPDATE charakter_class_temp SET name = 'Rogue' WHERE name = 'Schurke';
UPDATE charakter_class_temp SET name = 'Sorcerer' WHERE name = 'Hexenmeister';
UPDATE charakter_class_temp SET name = 'Wizard' WHERE name = 'Magier';
UPDATE charakter_class_temp SET name = 'Arcane Archer' WHERE name = 'Arkaner Bogenschuetze';
UPDATE charakter_class_temp SET name = 'Arcane Trickster' WHERE name = 'Arkaner Betrueger';
UPDATE charakter_class_temp SET name = 'Archmage' WHERE name = 'Erzmagier';
UPDATE charakter_class_temp SET name = 'Assassin' WHERE name = 'Assassine';
UPDATE charakter_class_temp SET name = 'Blackguard' WHERE name = 'Finsterer Streiter';
UPDATE charakter_class_temp SET name = 'Dragon Disciple' WHERE name = 'Drachenjuenger';
UPDATE charakter_class_temp SET name = 'Duelist' WHERE name = 'Duellant';
UPDATE charakter_class_temp SET name = 'Dwarven Defender' WHERE name = 'Zwergischer Verteidiger';
UPDATE charakter_class_temp SET name = 'Eldritch Knight' WHERE name = 'Mystischer Ritter';
UPDATE charakter_class_temp SET name = 'Hierophant' WHERE name = 'Hierophant';
UPDATE charakter_class_temp SET name = 'Horizon Walker' WHERE name = 'Weltengaenger';
UPDATE charakter_class_temp SET name = 'Loremaster' WHERE name = 'Wissenshueter';
UPDATE charakter_class_temp SET name = 'Mystic Theurge' WHERE name = 'Mystischer Theurg';
UPDATE charakter_class_temp SET name = 'Red Wizard' WHERE name = 'Roter Magier';
UPDATE charakter_class_temp SET name = 'Shadowdancer' WHERE name = 'Schattentaenzer';
UPDATE charakter_class_temp SET name = 'Thaumaturgist' WHERE name = 'Invokant';

UPDATE charakter_class_temp SET saves = 1 WHERE charakter_class_temp.id IN (SELECT charakter_class_id FROM charakter_class_save WHERE save_id = 0);
UPDATE charakter_class_temp SET saves = saves + 2 WHERE charakter_class_temp.id IN (SELECT charakter_class_id FROM charakter_class_save WHERE save_id = 1);
UPDATE charakter_class_temp SET saves = saves + 4 WHERE charakter_class_temp.id IN (SELECT charakter_class_id FROM charakter_class_save WHERE save_id = 2);

UPDATE charakter_class_temp SET alignments = 1 WHERE charakter_class_temp.id IN (SELECT charakter_class_id FROM charakter_class_alignment WHERE alignment_id = 0);
UPDATE charakter_class_temp SET alignments = alignments + 2 WHERE charakter_class_temp.id IN (SELECT charakter_class_id FROM charakter_class_alignment WHERE alignment_id = 1);
UPDATE charakter_class_temp SET alignments = alignments + 4 WHERE charakter_class_temp.id IN (SELECT charakter_class_id FROM charakter_class_alignment WHERE alignment_id = 2);
UPDATE charakter_class_temp SET alignments = alignments + 8 WHERE charakter_class_temp.id IN (SELECT charakter_class_id FROM charakter_class_alignment WHERE alignment_id = 3);
UPDATE charakter_class_temp SET alignments = alignments + 16 WHERE charakter_class_temp.id IN (SELECT charakter_class_id FROM charakter_class_alignment WHERE alignment_id = 4);
UPDATE charakter_class_temp SET alignments = alignments + 32 WHERE charakter_class_temp.id IN (SELECT charakter_class_id FROM charakter_class_alignment WHERE alignment_id = 5);
UPDATE charakter_class_temp SET alignments = alignments + 64 WHERE charakter_class_temp.id IN (SELECT charakter_class_id FROM charakter_class_alignment WHERE alignment_id = 6);
UPDATE charakter_class_temp SET alignments = alignments + 128 WHERE charakter_class_temp.id IN (SELECT charakter_class_id FROM charakter_class_alignment WHERE alignment_id = 7);
UPDATE charakter_class_temp SET alignments = alignments + 256 WHERE charakter_class_temp.id IN (SELECT charakter_class_id FROM charakter_class_alignment WHERE alignment_id = 8);

DROP TABLE charakter_class;
DROP TABLE charakter_class_text;
ALTER TABLE charakter_class_temp RENAME TO charakter_class;
DROP TABLE charakter_class_save;
DROP TABLE charakter_class_alignment;



CREATE TABLE ability_temp
(id                INTEGER,
 name              VARCHAR(64),
 description       VARCHAR(4096),
 ability_type_id   INTEGER,
 classname         VARCHAR(50) DEFAULT 'Ability',
 PRIMARY KEY(id),
 FOREIGN KEY (ability_type_id) REFERENCES ability_type(id) ON DELETE CASCADE
);

INSERT INTO ability_temp (id, name, description, ability_type_id, classname) SELECT id, name, description, ability_type_id, classname FROM ability, ability_text WHERE ability.id = ability_text.ability_id AND language_id = 0;

DROP TABLE ability;
DROP TABLE ability_text;
ALTER TABLE ability_temp RENAME TO ability;



CREATE TABLE skill_temp
(id           INTEGER,
 name         VARCHAR(50),
 description  VARCHAR(10000),
 attribute_id INTEGER,
 untrained    INTEGER,
 PRIMARY KEY(id),
 FOREIGN KEY (attribute_id) REFERENCES attribute(id)
);

INSERT INTO skill_temp (id, name, description, attribute_id, untrained) SELECT id, name, description, attribute_id, untrained FROM skill, skill_text WHERE skill.id = skill_text.skill_id AND language_id = 0;

DROP TABLE skill;
DROP TABLE skill_text;
ALTER TABLE skill_temp RENAME TO skill;



CREATE TABLE feat_temp
(id       INTEGER,
 name           VARCHAR(30),
 prerequisit    VARCHAR(150),
 benefit        VARCHAR(150),
 feat_type_id   INTEGER not null,
 fighter        INTEGER,
 multiple       INTEGER,
 stack          INTEGER,
 PRIMARY KEY(id),
 FOREIGN KEY (feat_type_id) REFERENCES feat_type(id) ON DELETE CASCADE
);

INSERT INTO feat_temp (id, name, prerequisit, benefit, feat_type_id, fighter, multiple, stack) SELECT id, name, prerequisit, benefit, feat_type_id, fighter, multiple, stack FROM feat, feat_text WHERE feat.id = feat_text.feat_id AND language_id = 0;

DROP TABLE feat;
DROP TABLE feat_text;
ALTER TABLE feat_temp RENAME TO feat;



CREATE TABLE weapon_temp
(id               INTEGER,
 name             VARCHAR(30),
 description      VARCHAR(2048),
 cost             FLOAT,
 weight           FLOAT,
 number_of_dice   INTEGER,
 die_id           INTEGER,
 bonus            INTEGER,
 critical_hit     INTEGER,
 critical_mod     INTEGER,
 weapon_type_id   INTEGER,
 quality_type_id  INTEGER DEFAULT 0,
 PRIMARY KEY (id),
 FOREIGN KEY (die_id) REFERENCES die(id),
 FOREIGN KEY (weapon_type_id) REFERENCES weapon_type(id)
 FOREIGN KEY (quality_type_id) REFERENCES quality_type(id)
);

INSERT INTO weapon_temp (id, name, description, cost, weight, number_of_dice, die_id, bonus, critical_hit, critical_mod, weapon_type_id, quality_type_id) SELECT id, name, description, cost, weight, number_of_dice, die_id, bonus, critical_hit, critical_mod, weapon_type_id, quality_type_id FROM weapon, weapon_text WHERE weapon.id = weapon_text.weapon_id AND language_id = 0;

DROP TABLE weapon;
DROP TABLE weapon_text;
ALTER TABLE weapon_temp RENAME TO weapon;



CREATE TABLE armor_temp
(id               INTEGER,
 name             VARCHAR(30),
 description      VARCHAR(2048),
 cost             FLOAT,
 weight           FLOAT,
 armor_bonus      INTEGER,
 armor_penalty    INTEGER,
 armor_type_id    INTEGER,
 quality_type_id  INTEGER DEFAULT 0,
 PRIMARY KEY (id),
 FOREIGN KEY (armor_type_id) REFERENCES armor_type(id)
 FOREIGN KEY (quality_type_id) REFERENCES quality_type(id)
);

INSERT INTO armor_temp (id, name, description, cost, weight, armor_bonus, armor_penalty, armor_type_id, quality_type_id) SELECT id, name, description, cost, weight, armor_bonus, armor_penalty, armor_type_id, quality_type_id FROM armor, armor_text WHERE armor.id = armor_text.armor_id AND language_id = 0;

DROP TABLE armor;
DROP TABLE armor_text;
ALTER TABLE armor_temp RENAME TO armor;



CREATE TABLE good_temp
(id               INTEGER,
 name             VARCHAR(30),
 description      VARCHAR(2048),
 cost             FLOAT,
 weight           FLOAT,
 good_type_id     INTEGER,
 quality_type_id  INTEGER DEFAULT 0,
 PRIMARY KEY (id),
 FOREIGN KEY (good_type_id) REFERENCES good_type(id)
 FOREIGN KEY (quality_type_id) REFERENCES quality_type(id)
);

INSERT INTO good_temp (id, name, description, cost, weight, good_type_id, quality_type_id) SELECT id, name, description, cost, weight, good_type_id, quality_type_id FROM good, good_text WHERE good.id = good_text.good_id AND language_id = 0;

DROP TABLE good;
DROP TABLE good_text;
ALTER TABLE good_temp RENAME TO good;

DROP TABLE language;

