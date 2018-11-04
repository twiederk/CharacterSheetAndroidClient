CREATE TABLE class
(id                       INTEGER,
 name                     VARCHAR(30),
 saves                    INTEGER DEFAULT 0,
 alignments               INTEGER DEFAULT 0,
 base_attack_bonus        INTEGER,
 skill_points_per_level   INTEGER,
 hit_die_id               INTEGER,
 PRIMARY KEY (id)
);

INSERT INTO class (id, name, saves, alignments, base_attack_bonus, skill_points_per_level, hit_die_id) SELECT id, name, saves, alignments, base_attack_bonus, skill_points_per_level, hit_die_id FROM charakter_class;
DROP TABLE charakter_class;


CREATE TABLE class_skill
(class_id              INTEGER,
 skill_id              INTEGER,
 PRIMARY KEY (class_id, skill_id),
 FOREIGN KEY (class_id) REFERENCES class(id) ON DELETE CASCADE,
 FOREIGN KEY (skill_id) REFERENCES skill(id) ON DELETE CASCADE
);

INSERT INTO class_skill (class_id, skill_id) SELECT charakter_class_id, skill_id FROM charakter_class_skill;
DROP TABLE charakter_class_skill;


CREATE TABLE temp_charakter_class_level
(id                     INTEGER,
 charakter_id           INTEGER,
 class_id		            INTEGER,
 level			            INTEGER,
 PRIMARY KEY (id),
 FOREIGN KEY (charakter_id) REFERENCES charakter(id) ON DELETE CASCADE,
 FOREIGN KEY (class_id) REFERENCES class(id) ON DELETE CASCADE
);

INSERT INTO temp_charakter_class_level (id, charakter_id, class_id, level) SELECT id, charakter_id, charakter_class_id, level FROM charakter_class_level;
DROP TABLE charakter_class_level;
ALTER TABLE temp_charakter_class_level RENAME TO charakter_class_level;


CREATE TABLE temp_charakter_ability
(id                 INTEGER,
 charakter_id       INTEGER,
 class_id           INTEGER,
 ability_id         INTEGER,
 owned              INTEGER DEFAULT 1,
 PRIMARY KEY (id),
 FOREIGN KEY (charakter_id) REFERENCES charakter (id),
 FOREIGN KEY (class_id) REFERENCES class (id),
 FOREIGN KEY (ability_id) REFERENCES ability (id)
);

INSERT INTO temp_charakter_ability (id, charakter_id, class_id, ability_id, owned) SELECT id, charakter_id, class_id, ability_id, owned FROM charakter_ability;
DROP TABLE charakter_ability;
ALTER TABLE temp_charakter_ability RENAME TO charakter_ability;


CREATE TABLE temp_class_ability
(class_id      INTEGER,
 ability_id    INTEGER,
 level         INTEGER,
 PRIMARY KEY(class_id, ability_id),
 FOREIGN KEY (class_id) REFERENCES charakter_class(id) ON DELETE CASCADE,
 FOREIGN KEY (ability_id) REFERENCES ability(id) ON DELETE CASCADE
);

INSERT INTO temp_class_ability (class_id, ability_id, level) SELECT class_id, ability_id, level FROM class_ability;
DROP TABLE class_ability;
ALTER TABLE temp_class_ability RENAME TO class_ability;

ALTER TABLE weapon_attack RENAME TO charakter_weapon_attack;
ALTER TABLE note RENAME TO charakter_note;


