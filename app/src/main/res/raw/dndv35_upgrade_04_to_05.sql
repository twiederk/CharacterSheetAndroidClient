UPDATE feat_text SET name = 'Zaubereffekt maximieren' WHERE feat_id = 66 AND language_id = 1;
UPDATE feat_text SET name = 'Schnell zaubern' WHERE feat_id = 78 AND language_id = 1;
UPDATE feat_text SET benefit = 'Bonus von +2 auf Wuerfe fuer Heilkunde und Ueberlebenskunst' WHERE feat_id = 84 AND language_id = 1;
UPDATE skill SET monk = 1 WHERE id = 11;

ALTER TABLE charakter ADD platinum INTEGER NOT NULL DEFAULT 0;
ALTER TABLE charakter ADD gold     INTEGER NOT NULL DEFAULT 0;
ALTER TABLE charakter ADD silver   INTEGER NOT NULL DEFAULT 0;
ALTER TABLE charakter ADD copper   INTEGER NOT NULL DEFAULT 0;
