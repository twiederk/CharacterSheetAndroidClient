ALTER TABLE ability RENAME TO attribute;

CREATE TABLE skill_new
(id           INTEGER,
 attribute_id INTEGER,
 untrained    INTEGER,
 PRIMARY KEY (id),
 FOREIGN KEY (attribute_id) REFERENCES attribute(id)
);
INSERT INTO skill_new (id, attribute_id, untrained) SELECT id, ability_id, untrained FROM skill;
DROP TABLE skill;
ALTER TABLE skill_new RENAME TO skill;

ALTER TABLE trait_type RENAME TO ability_type;

CREATE TABLE ability
(id              INTEGER,
 ability_type_id   INTEGER,
 PRIMARY KEY (id),
 FOREIGN KEY (ability_type_id) REFERENCES ability_type(id) ON DELETE CASCADE
);
INSERT INTO ability (id, ability_type_id) SELECT id, trait_type_id FROM trait;
DROP TABLE trait;

CREATE TABLE ability_text
(ability_id    INTEGER,
 language_id   INTEGER,
 name          VARCHAR(30),
 description   VARCHAR(255),
 PRIMARY KEY (ability_id, language_id),
 FOREIGN KEY (ability_id) REFERENCES ability(id) ON DELETE CASCADE,
 FOREIGN KEY (language_id) REFERENCES language(id) ON DELETE CASCADE
);
INSERT INTO ability_text (ability_id, language_id, name, description) SELECT trait_id, language_id, name, description FROM trait_text;
DROP TABLE trait_text;

CREATE TABLE race_ability
(race_id     INTEGER,
 ability_id  INTEGER,
 PRIMARY KEY (race_id, ability_id),
 FOREIGN KEY (race_id) REFERENCES race(id) ON DELETE CASCADE,
 FOREIGN KEY (ability_id) REFERENCES ability(id) ON DELETE CASCADE
);
INSERT INTO race_ability (race_id, ability_id) SELECT race_id, trait_id FROM race_trait;
DROP TABLE race_trait;





CREATE TABLE quality_type
(id        INTEGER,
 name      VARCHAR(30),
 PRIMARY KEY(id)
);
INSERT INTO quality_type VALUES (0, 'Normal');
INSERT INTO quality_type VALUES (1, 'Masterwork');
INSERT INTO quality_type VALUES (2, 'Magic');


CREATE TABLE weapon_new
(id               INTEGER,
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
INSERT INTO weapon_new (id, cost, weight, number_of_dice, die_id, bonus, critical_hit, critical_mod, weapon_type_id, quality_type_id)
SELECT id, cost, weight, number_of_dice, die_id, bonus, critical_hit, critical_mod, weapon_type_id, magic FROM weapon;
DROP TABLE weapon;
ALTER TABLE weapon_new RENAME TO weapon;

UPDATE weapon SET quality_type_id = 2 WHERE quality_type_id = 1;
UPDATE weapon SET quality_type_id = 1 WHERE id IN (85, 92);
UPDATE weapon_text SET name = 'Cold Iron Longsword' WHERE weapon_id = 85 AND language_id = 0;
UPDATE weapon_text SET name = 'Langschwert aus kalt geschmiedetem Eisen' WHERE weapon_id = 85 AND language_id = 1;
UPDATE weapon_text SET name = 'Silver Dagger' WHERE weapon_id = 92 AND language_id = 0;
UPDATE weapon_text SET name = 'Silberdolch' WHERE weapon_id = 92 AND language_id = 1;



CREATE TABLE armor_new
(id               INTEGER,
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
INSERT INTO armor_new (id, cost, weight, armor_bonus, armor_penalty, armor_type_id, quality_type_id)
SELECT id, cost, weight, armor_bonus, armor_penalty, armor_type_id, magic FROM armor;
DROP TABLE armor;
ALTER TABLE armor_new RENAME TO armor;
UPDATE armor SET quality_type_id = 2 WHERE quality_type_id = 1;



CREATE TABLE good_new
(id               INTEGER,
 cost             FLOAT,
 weight           FLOAT,
 good_type_id     INTEGER,
 quality_type_id  INTEGER DEFAULT 0,
 PRIMARY KEY (id),
 FOREIGN KEY (good_type_id) REFERENCES good_type(id)
 FOREIGN KEY (quality_type_id) REFERENCES quality_type(id)
);
INSERT INTO good_new (id, cost, weight, good_type_id, quality_type_id)
SELECT id, cost, weight, good_type_id, magic FROM good;
DROP TABLE good;
ALTER TABLE good_new RENAME TO good;
UPDATE good SET quality_type_id = 2 WHERE quality_type_id = 1;
UPDATE good SET quality_type_id = 1 WHERE id IN (37, 82, 92, 97, 98);

UPDATE good_text SET name = 'Manacles' WHERE good_id = 37 AND language_id = 0;
UPDATE good_text SET name = 'Handschellen' WHERE good_id = 37 AND language_id = 1;

UPDATE good_text SET name = 'Artisan Tools' WHERE good_id = 82 AND language_id = 0;
UPDATE good_text SET name = 'Werkzeug eines Handwerkers' WHERE good_id = 82 AND language_id = 1;

UPDATE good_text SET name = 'Musical Instrument' WHERE good_id = 92 AND language_id = 0;
UPDATE good_text SET name = 'Musikinstrument' WHERE good_id = 92 AND language_id = 1;

UPDATE good_text SET name = 'Thieves Tools' WHERE good_id = 97 AND language_id = 0;
UPDATE good_text SET name = 'Diebeswerkzeug' WHERE good_id = 97 AND language_id = 1;

UPDATE good_text SET name = 'Tools' WHERE good_id = 98 AND language_id = 0;
UPDATE good_text SET name = 'Werkzeug' WHERE good_id = 98 AND language_id = 1;
