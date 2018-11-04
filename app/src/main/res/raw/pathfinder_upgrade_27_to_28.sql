UPDATE ability SET classname = 'ExtraSkillPointsAbility' where id = 40;
INSERT INTO ability_property VALUES (40, 'number_of_skill_points', '1' );

CREATE TABLE charakter_ability
(id                 INTEGER,
 charakter_id       INTEGER,
 class_id           INTEGER,
 ability_id         INTEGER,
 owned              INTEGER DEFAULT 1,
 PRIMARY KEY (id),
 FOREIGN KEY (charakter_id) REFERENCES charakter (id),
 FOREIGN KEY (class_id) REFERENCES charakter_class (id),
 FOREIGN KEY (ability_id) REFERENCES ability (id)
);

INSERT INTO charakter_ability (charakter_id, class_id, ability_id) SELECT c.id, ccl.charakter_class_id, ca.ability_id FROM charakter c, charakter_class_level ccl, class_ability ca WHERE c.id = ccl.charakter_id AND ccl.charakter_class_id = ca.class_id;

CREATE TABLE charakter_class_level_temp
(id                     INTEGER,
 charakter_id           INTEGER,
 charakter_class_id     INTEGER,
 level                  INTEGER,
 PRIMARY KEY (id),
 FOREIGN KEY (charakter_id) REFERENCES charakter(id) ON DELETE CASCADE,
 FOREIGN KEY (charakter_class_id) REFERENCES charakter_class(id) ON DELETE CASCADE
);

INSERT INTO charakter_class_level_temp (charakter_id, charakter_class_id, level) SELECT charakter_id, charakter_class_id, level FROM charakter_class_level;
DROP TABLE charakter_class_level;
ALTER TABLE charakter_class_level_temp RENAME TO charakter_class_level;

