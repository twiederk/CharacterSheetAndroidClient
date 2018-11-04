ALTER TABLE charakter ADD ini_misc_mod INTEGER NOT NULL DEFAULT 0;

CREATE TABLE language
(id             INTEGER,
 language       VARCHAR(15),
 PRIMARY KEY (id)
);
INSERT INTO "language" VALUES (0,"English");
INSERT INTO "language" VALUES (1,"Deutsch");

CREATE TABLE ability
(id            INTEGER,
 name          VARCHAR(15),
 PRIMARY KEY (id)
);
INSERT INTO "ability" VALUES (0,"Strength");
INSERT INTO "ability" VALUES (1,"Dexterity");
INSERT INTO "ability" VALUES (2,"Constitution");
INSERT INTO "ability" VALUES (3,"Intelligence");
INSERT INTO "ability" VALUES (4,"Wisdom");
INSERT INTO "ability" VALUES (5,"Charisma");


CREATE TABLE skill
(id             INTEGER,
 ability_id     INTEGER,
 untrained      INTEGER,
 barbarian      INTEGER,
 bard           INTEGER,
 cleric         INTEGER,
 druid          INTEGER,
 fighter        INTEGER,
 monk           INTEGER,
 paladin        INTEGER,
 ranger         INTEGER,
 rogue          INTEGER,
 sorcerer       INTEGER,
 wizard         INTEGER,
 PRIMARY KEY(id)
);
INSERT INTO "skill" VALUES (1,3,1,0,1,0,0,0,0,0,0,1,0,0);
INSERT INTO "skill" VALUES (2,1,1,0,1,0,0,0,1,0,0,1,0,0);
INSERT INTO "skill" VALUES (3,5,1,0,1,0,0,0,0,0,0,1,1,0);
INSERT INTO "skill" VALUES (4,0,1,1,1,0,0,1,1,0,1,1,0,0);
INSERT INTO "skill" VALUES (5,2,1,0,1,1,1,0,1,1,1,0,1,1);
INSERT INTO "skill" VALUES (6,3,1,1,1,1,1,1,1,1,1,1,1,1);
INSERT INTO "skill" VALUES (7,3,0,0,1,0,0,0,0,0,0,1,0,1);
INSERT INTO "skill" VALUES (8,5,1,0,1,1,1,0,1,1,0,1,0,0);
INSERT INTO "skill" VALUES (9,3,0,0,0,0,0,0,0,0,0,1,0,0);
INSERT INTO "skill" VALUES (10,5,1,0,1,0,0,0,0,0,0,1,0,0);
INSERT INTO "skill" VALUES (11,1,1,0,1,0,0,0,0,0,0,1,0,0);
INSERT INTO "skill" VALUES (12,3,1,0,0,0,0,0,0,0,0,1,0,0);
INSERT INTO "skill" VALUES (13,5,1,0,1,0,0,0,0,0,0,1,0,0);
INSERT INTO "skill" VALUES (14,5,0,1,0,0,1,1,0,1,1,0,0,0);
INSERT INTO "skill" VALUES (15,4,1,0,0,1,1,0,0,1,1,0,0,0);
INSERT INTO "skill" VALUES (16,1,1,0,1,0,0,0,1,0,1,1,0,0);
INSERT INTO "skill" VALUES (17,5,1,1,0,0,0,1,0,0,0,1,0,0);
INSERT INTO "skill" VALUES (18,0,1,1,1,0,0,1,1,0,1,1,0,0);
INSERT INTO "skill" VALUES (19,3,0,0,1,1,0,0,1,0,0,0,1,1);
INSERT INTO "skill" VALUES (20,3,0,0,1,0,0,0,0,0,0,0,0,1);
INSERT INTO "skill" VALUES (21,3,0,0,1,0,0,0,0,0,1,0,0,1);
INSERT INTO "skill" VALUES (22,3,0,0,1,0,0,0,0,0,1,0,0,1);
INSERT INTO "skill" VALUES (23,3,0,0,1,1,0,0,0,0,0,0,0,1);
INSERT INTO "skill" VALUES (24,3,0,0,1,0,0,0,0,0,0,0,0,1);
INSERT INTO "skill" VALUES (25,3,0,0,1,0,1,0,0,0,1,0,0,1);
INSERT INTO "skill" VALUES (26,3,0,0,1,0,0,0,0,1,0,0,0,1);
INSERT INTO "skill" VALUES (27,3,0,0,1,1,0,0,1,1,0,0,0,1);
INSERT INTO "skill" VALUES (28,3,0,0,1,1,0,0,0,0,0,0,0,1);
INSERT INTO "skill" VALUES (29,4,1,1,1,0,1,0,1,0,1,1,0,0);
INSERT INTO "skill" VALUES (30,1,1,0,1,0,0,0,1,0,1,1,0,0);
INSERT INTO "skill" VALUES (31,1,0,0,0,0,0,0,0,0,0,1,0,0);
INSERT INTO "skill" VALUES (32,5,0,0,1,0,0,0,1,0,0,1,0,0);
INSERT INTO "skill" VALUES (33,4,0,0,1,1,1,0,1,1,1,1,1,1);
INSERT INTO "skill" VALUES (34,1,1,1,0,0,1,1,0,1,1,0,0,0);
INSERT INTO "skill" VALUES (35,3,1,0,0,0,0,0,0,0,1,1,0,0);
INSERT INTO "skill" VALUES (36,4,1,0,1,0,0,0,1,1,0,1,0,0);
INSERT INTO "skill" VALUES (37,1,0,0,1,0,0,0,0,0,0,1,0,0);
INSERT INTO "skill" VALUES (39,3,0,0,1,1,1,0,0,0,0,0,1,1);
INSERT INTO "skill" VALUES (40,4,1,0,0,0,1,0,1,0,1,1,0,0);
INSERT INTO "skill" VALUES (41,4,1,1,0,0,1,0,0,0,1,0,0,0);
INSERT INTO "skill" VALUES (42,0,1,1,1,0,1,1,1,0,1,1,0,0);
INSERT INTO "skill" VALUES (43,1,0,0,1,0,0,0,1,0,0,1,0,0);
INSERT INTO "skill" VALUES (44,5,0,0,1,0,0,0,0,0,0,1,0,0);
INSERT INTO "skill" VALUES (45,1,1,0,0,0,0,0,0,0,1,1,0,0);

CREATE TABLE skill_text
(skill_id            INTEGER,
 language_id         INTEGER,
 name                VARCHAR(30),
 description         VARCHAR(150),
 PRIMARY KEY (skill_id, language_id),
 FOREIGN KEY (skill_id) REFERENCES skill(id) ON DELETE CASCADE,
 FOREIGN KEY (language_id) REFERENCES language(id)
);

INSERT INTO "skill_text" VALUES (1,0,"Appraise","");
INSERT INTO "skill_text" VALUES (32,1,"Auftreten","");
INSERT INTO "skill_text" VALUES (2,0,"Balance","");
INSERT INTO "skill_text" VALUES (2,1,"Balancieren","");
INSERT INTO "skill_text" VALUES (33,1,"Beruf","");
INSERT INTO "skill_text" VALUES (3,0,"Bluff","");
INSERT INTO "skill_text" VALUES (3,1,"Bluffen","");
INSERT INTO "skill_text" VALUES (4,0,"Climb","");
INSERT INTO "skill_text" VALUES (5,0,"Concentration","");
INSERT INTO "skill_text" VALUES (6,0,"Craft","");
INSERT INTO "skill_text" VALUES (7,0,"Decipher Script","");
INSERT INTO "skill_text" VALUES (8,0,"Diplomacy","");
INSERT INTO "skill_text" VALUES (8,1,"Diplomatie","");
INSERT INTO "skill_text" VALUES (9,0,"Disable Device","");
INSERT INTO "skill_text" VALUES (10,0,"Disguise","");
INSERT INTO "skill_text" VALUES (17,1,"Einschuechtern","");
INSERT INTO "skill_text" VALUES (40,1,"Entdecken","");
INSERT INTO "skill_text" VALUES (11,1,"Entfesselungskunst","");
INSERT INTO "skill_text" VALUES (11,0,"Escape Artist","");
INSERT INTO "skill_text" VALUES (12,1,"Faelschen","");
INSERT INTO "skill_text" VALUES (37,1,"Fingerfertigkeit","");
INSERT INTO "skill_text" VALUES (12,0,"Forgery","");
INSERT INTO "skill_text" VALUES (13,0,"Gather Information","");
INSERT INTO "skill_text" VALUES (14,0,"Handle Animal","");
INSERT INTO "skill_text" VALUES (6,1,"Handwerk","");
INSERT INTO "skill_text" VALUES (15,0,"Heal","");
INSERT INTO "skill_text" VALUES (15,1,"Heilkunde","");
INSERT INTO "skill_text" VALUES (16,0,"Hide","");
INSERT INTO "skill_text" VALUES (13,1,"Informationen sammeln","");
INSERT INTO "skill_text" VALUES (17,0,"Intimidate","");
INSERT INTO "skill_text" VALUES (18,0,"Jump","");
INSERT INTO "skill_text" VALUES (4,1,"Klettern","");
INSERT INTO "skill_text" VALUES (19,0,"Knowledge (arcana)","");
INSERT INTO "skill_text" VALUES (20,0,"Knowledge (architecture and enginieering)","");
INSERT INTO "skill_text" VALUES (21,0,"Knowledge (dungeoneering)","");
INSERT INTO "skill_text" VALUES (22,0,"Knowledge (geography)","");
INSERT INTO "skill_text" VALUES (23,0,"Knowledge (history)","");
INSERT INTO "skill_text" VALUES (24,0,"Knowledge (local)","");
INSERT INTO "skill_text" VALUES (25,0,"Knowledge (nature)","");
INSERT INTO "skill_text" VALUES (26,0,"Knowledge (nobility and royality)","");
INSERT INTO "skill_text" VALUES (27,0,"Knowledge (religion)","");
INSERT INTO "skill_text" VALUES (28,0,"Knowledge (the planes)","");
INSERT INTO "skill_text" VALUES (5,1,"Konzentration","");
INSERT INTO "skill_text" VALUES (29,1,"Lauschen","");
INSERT INTO "skill_text" VALUES (30,1,"Leise bewegen","");
INSERT INTO "skill_text" VALUES (29,0,"Listen","");
INSERT INTO "skill_text" VALUES (44,1,"Magischen Gegenstand benutzen","");
INSERT INTO "skill_text" VALUES (9,1,"Mechanismus ausschalten","");
INSERT INTO "skill_text" VALUES (14,1,"Mit Tieren umgehen","");
INSERT INTO "skill_text" VALUES (36,1,"Motiv erkennen","");
INSERT INTO "skill_text" VALUES (30,0,"Move Silently","");
INSERT INTO "skill_text" VALUES (31,0,"Open Lock","");
INSERT INTO "skill_text" VALUES (32,0,"Perform","");
INSERT INTO "skill_text" VALUES (33,0,"Profession","");
INSERT INTO "skill_text" VALUES (34,1,"Reiten","");
INSERT INTO "skill_text" VALUES (34,0,"Ride","");
INSERT INTO "skill_text" VALUES (1,1,"Schaetzen","");
INSERT INTO "skill_text" VALUES (31,1,"Schloesser oeffnen","");
INSERT INTO "skill_text" VALUES (7,1,"Schriftzeichen entschluesseln","");
INSERT INTO "skill_text" VALUES (42,1,"Schwimmen","");
INSERT INTO "skill_text" VALUES (35,0,"Search","");
INSERT INTO "skill_text" VALUES (45,1,"Seil benutzen","");
INSERT INTO "skill_text" VALUES (36,0,"Sense Motive","");
INSERT INTO "skill_text" VALUES (37,0,"Sleight of Hand","");
INSERT INTO "skill_text" VALUES (39,0,"Spellcraft","");
INSERT INTO "skill_text" VALUES (40,0,"Spot","");
INSERT INTO "skill_text" VALUES (18,1,"Springen","");
INSERT INTO "skill_text" VALUES (35,1,"Suchen","");
INSERT INTO "skill_text" VALUES (41,0,"Survival","");
INSERT INTO "skill_text" VALUES (42,0,"Swim","");
INSERT INTO "skill_text" VALUES (43,0,"Tumble","");
INSERT INTO "skill_text" VALUES (43,1,"Turnen","");
INSERT INTO "skill_text" VALUES (41,1,"Ueberlebenskunst","");
INSERT INTO "skill_text" VALUES (44,0,"Use Magic Device","");
INSERT INTO "skill_text" VALUES (45,0,"Use Rope","");
INSERT INTO "skill_text" VALUES (10,1,"Verkleiden","");
INSERT INTO "skill_text" VALUES (16,1,"Verstecken","");
INSERT INTO "skill_text" VALUES (26,1,"Wissen (Adel und Koenigshaeuser)","");
INSERT INTO "skill_text" VALUES (20,1,"Wissen (Architektur und Baukunst)","");
INSERT INTO "skill_text" VALUES (19,1,"Wissen (Arkanes)","");
INSERT INTO "skill_text" VALUES (28,1,"Wissen (Ebenen)","");
INSERT INTO "skill_text" VALUES (22,1,"Wissen (Geographie)","");
INSERT INTO "skill_text" VALUES (23,1,"Wissen (Geschichte)","");
INSERT INTO "skill_text" VALUES (21,1,"Wissen (Gewoelbekunde)","");
INSERT INTO "skill_text" VALUES (24,1,"Wissen (Lokales)","");
INSERT INTO "skill_text" VALUES (25,1,"Wissen (Natur)","");
INSERT INTO "skill_text" VALUES (27,1,"Wissen (Religion)","");
INSERT INTO "skill_text" VALUES (39,1,"Zauberkunde","");

CREATE TABLE charakter_skill
(skill_id          INTEGER,
 charakter_id      INTEGER,
 rank              FLOAT,
 misc_modifier     INTEGER,
 favorite          INTEGER,
 PRIMARY KEY (skill_id, charakter_id),
 FOREIGN KEY (skill_id) REFERENCES skill(id),
 FOREIGN KEY (charakter_id) REFERENCES charakter(id)
);
INSERT INTO "charakter_skill" VALUES (4,1,6,0,1);
INSERT INTO "charakter_skill" VALUES (5,0,8,0,0);
INSERT INTO "charakter_skill" VALUES (7,1,6,2,0);
INSERT INTO "charakter_skill" VALUES (8,1,0,2,1);
INSERT INTO "charakter_skill" VALUES (9,1,6,2,1);
INSERT INTO "charakter_skill" VALUES (13,1,6,0,1);
INSERT INTO "charakter_skill" VALUES (14,1,7,0,1);
INSERT INTO "charakter_skill" VALUES (16,1,6,0,1);
INSERT INTO "charakter_skill" VALUES (18,1,6,0,1);
INSERT INTO "charakter_skill" VALUES (19,0,7,0,0);
INSERT INTO "charakter_skill" VALUES (23,0,7,0,0);
INSERT INTO "charakter_skill" VALUES (23,1,2,0,0);
INSERT INTO "charakter_skill" VALUES (25,1,7,2,0);
INSERT INTO "charakter_skill" VALUES (27,0,2,0,0);
INSERT INTO "charakter_skill" VALUES (29,0,0,2,1);
INSERT INTO "charakter_skill" VALUES (29,1,6,0,1);
INSERT INTO "charakter_skill" VALUES (30,1,6,0,1);
INSERT INTO "charakter_skill" VALUES (31,1,6,2,1);
INSERT INTO "charakter_skill" VALUES (34,1,0,2,1);
INSERT INTO "charakter_skill" VALUES (35,0,4,2,1);
INSERT INTO "charakter_skill" VALUES (36,1,7,0,1);
INSERT INTO "charakter_skill" VALUES (39,0,8,2,1);
INSERT INTO "charakter_skill" VALUES (40,0,4,2,1);
INSERT INTO "charakter_skill" VALUES (40,1,7,0,1);
INSERT INTO "charakter_skill" VALUES (41,0,0,2,0);
INSERT INTO "charakter_skill" VALUES (41,1,5,2,1);
INSERT INTO "charakter_skill" VALUES (44,0,0,2,0);
INSERT INTO "charakter_skill" VALUES (44,1,6,0,0);
