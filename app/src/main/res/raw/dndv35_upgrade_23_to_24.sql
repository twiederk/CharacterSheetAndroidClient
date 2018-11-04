UPDATE ability_type SET name = 'Natural' WHERE id = 0;

CREATE TABLE charakter_class_new
(id                       INTEGER,
 base_attack_bonus        INTEGER,
 skill_points_per_level   INTEGER,
 hit_die_id               INTEGER,
 PRIMARY KEY (id)
);

INSERT INTO charakter_class_new (id, base_attack_bonus, skill_points_per_level, hit_die_id) SELECT id, base_attack_bonus, skill_points_per_level, hit_die FROM charakter_class;
DROP TABLE charakter_class;
ALTER TABLE charakter_class_new RENAME TO charakter_class;

UPDATE ability_charakter_class SET id = id + 1000 WHERE id > 223;
UPDATE ability_charakter_class_text SET ability_charakter_class_id = ability_charakter_class_id + 1000 WHERE ability_charakter_class_id > 223;
UPDATE charakter_class_ability SET ability_charakter_class_id = ability_charakter_class_id + 1000 WHERE ability_charakter_class_id > 223;

UPDATE ability SET id = id + 2000 WHERE id > 69;
UPDATE ability_text SET ability_id = ability_id + 2000 WHERE ability_id > 69;
UPDATE race_ability SET ability_id = ability_id + 2000 WHERE ability_id > 69;

UPDATE ability SET id = id + 224 WHERE id < 70;
UPDATE ability_text SET ability_id = ability_id + 224 WHERE ability_id < 70;
UPDATE race_ability SET ability_id = ability_id + 224 WHERE ability_id < 70;

CREATE TABLE new_ability
(id                INTEGER,
 ability_type_id   INTEGER,
 classname         VARCHAR(50) DEFAULT 'Ability',
 PRIMARY KEY(id),
 FOREIGN KEY (ability_type_id) REFERENCES ability_type(id) ON DELETE CASCADE
);

CREATE TABLE new_ability_text
(ability_id      INTEGER,
 language_id   	 INTEGER,
 name            VARCHAR(64),
 description     VARCHAR(4096),
 PRIMARY KEY(ability_id, language_id),
 FOREIGN KEY (ability_id) REFERENCES ability(id) ON DELETE CASCADE,
 FOREIGN KEY (language_id) REFERENCES language(id) ON DELETE CASCADE
);

CREATE TABLE class_ability
(class_id      INTEGER,
 ability_id    INTEGER,
 level         INTEGER,
 PRIMARY KEY(class_id, ability_id),
 FOREIGN KEY (class_id) REFERENCES charakter_class(id) ON DELETE CASCADE,
 FOREIGN KEY (ability_id) REFERENCES ability_(id) ON DELETE CASCADE
);

INSERT INTO new_ability (id, ability_type_id) SELECT id, ability_type_id FROM ability_charakter_class;
INSERT INTO new_ability_text (ability_id, language_id, name, description) SELECT ability_charakter_class_id, language_id, name, description FROM ability_charakter_class_text;
INSERT INTO new_ability (id, ability_type_id) SELECT id, ability_type_id FROM ability;
INSERT INTO new_ability_text (ability_id, language_id, name, description) SELECT ability_id, language_id, name, description FROM ability_text;
INSERT INTO class_ability (class_id, ability_id, level) SELECT charakter_class_id, ability_charakter_class_id, level FROM charakter_class_ability;

DROP TABLE charakter_class_ability;
DROP TABLE ability_charakter_class_text;
DROP TABLE ability_charakter_class ;
DROP TABLE ability_text;
DROP TABLE ability;

ALTER TABLE new_ability RENAME TO ability;
ALTER TABLE new_ability_text RENAME TO ability_text;

UPDATE ability SET classname = 'SpelllistAbility' WHERE id IN (11, 23, 24, 26, 29, 72, 83, 103, 105, 140, 146, 175);
UPDATE ability SET classname = 'ExtraFeatsAbility' WHERE id IN (224);
UPDATE ability SET classname = 'ExtraSkillPointsAbility' WHERE id IN (225);
