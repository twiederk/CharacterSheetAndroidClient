UPDATE trait_text SET name = 'Extra Feat' WHERE trait_id = 0 AND language_id = 0;

CREATE TABLE note
(id               INTEGER,
 charakter_id     INTEGER,
 date             VARCHAR(25),
 text             INTEGER,
 PRIMARY KEY (id),
 FOREIGN KEY (charakter_id) REFERENCES charakter(id)
);
