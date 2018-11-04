CREATE TABLE die
(id             INTEGER,
 name           VARCHAR(15),
 PRIMARY KEY(id)
);
INSERT INTO "die" VALUES (0, "D2");
INSERT INTO "die" VALUES (1, "D3");
INSERT INTO "die" VALUES (2, "D4");
INSERT INTO "die" VALUES (3, "D6");
INSERT INTO "die" VALUES (4, "D8");
INSERT INTO "die" VALUES (5, "D10");
INSERT INTO "die" VALUES (6, "D12");
INSERT INTO "die" VALUES (7, "D20");
INSERT INTO "die" VALUES (8, "D100");


CREATE TABLE weapon_type
(id       INTEGER,
 name     VARCHAR(6) NOT NULL,
 PRIMARY KEY (id)
);
INSERT INTO weapon_type VALUES(0,'Simple');
INSERT INTO weapon_type VALUES(1,'Martial');
INSERT INTO weapon_type VALUES(2,'Exotic');
INSERT INTO weapon_type VALUES(3,'Ammo');


CREATE TABLE armor_type
(id       INTEGER,
 name     VARCHAR(6) NOT NULL,
 PRIMARY KEY (id)
);
INSERT INTO armor_type VALUES(0,'Light');
INSERT INTO armor_type VALUES(1,'Medium');
INSERT INTO armor_type VALUES(2,'Heavy');
INSERT INTO armor_type VALUES(3,'Shield');


CREATE TABLE good_type
(id       INTEGER,
 name     VARCHAR(8) NOT NULL,
 PRIMARY KEY (id)
);
INSERT INTO good_type VALUES(0,'Adventuring Gear');
INSERT INTO good_type VALUES(1,'Special Substance');
INSERT INTO good_type VALUES(2,'Special Item');
INSERT INTO good_type VALUES(3,'Tool Kit');
INSERT INTO good_type VALUES(4,'Skill Kit');
INSERT INTO good_type VALUES(5,'Clothing');
INSERT INTO good_type VALUES(6,'Mount');
INSERT INTO good_type VALUES(7,'Mount Gear');


CREATE TABLE weapon
(id               INTEGER,
 cost             FLOAT,
 weight           FLOAT,
 number_of_dice   INTEGER,
 die_id           INTEGER,
 bonus            INTEGER,
 critical_hit     INTEGER,
 critical_mod     INTEGER,
 weapon_type_id   INTEGER,
 PRIMARY KEY (id),
 FOREIGN KEY (die_id) REFERENCES die(id),
 FOREIGN KEY (weapon_type_id) REFERENCES weapon_type(id)
);

CREATE TABLE weapon_text
(weapon_id        INTEGER,
 language_id      INTEGER,
 name             VARCHAR(30),
 PRIMARY KEY (weapon_id, language_id),
 FOREIGN KEY (weapon_id) REFERENCES weapon(id) ON DELETE CASCADE,
 FOREIGN KEY (language_id) REFERENCES language(id) ON DELETE CASCADE
);

CREATE TABLE charakter_weapon
(id             INTEGER,
 charakter_id   INTEGER,
 weapon_id      INTEGER,
 number         INTEGER,
 PRIMARY KEY (id),
 FOREIGN KEY (charakter_id) REFERENCES charakter(id),
 FOREIGN KEY (weapon_id) REFERENCES weapon(id)
);

INSERT INTO weapon VALUES ( 0, 2, 1, 1, 1, 0, 20, 2, 0 );
INSERT INTO weapon VALUES ( 1, 2, 1, 1, 2, 0, 19, 2, 0 );
INSERT INTO weapon VALUES ( 2, 2, 1, 1, 2, 0, 20, 3, 0 );
INSERT INTO weapon VALUES ( 3, 5, 1, 1, 2, 0, 20, 2, 0 );
INSERT INTO weapon VALUES ( 4, 5, 4, 1, 3, 0, 20, 2, 0 );
INSERT INTO weapon VALUES ( 5, 6, 2, 1, 3, 0, 20, 2, 0 );
INSERT INTO weapon VALUES ( 6, 0, 3, 1, 3, 0, 20, 2, 0 );
INSERT INTO weapon VALUES ( 7, 12, 8, 1, 4, 0, 20, 2, 0 );
INSERT INTO weapon VALUES ( 8, 8, 6, 1, 4, 0, 20, 2, 0 );
INSERT INTO weapon VALUES ( 9, 1, 3, 1, 3, 0, 20, 2, 0 );
INSERT INTO weapon VALUES ( 10, 5, 9, 1, 4, 0, 20, 3, 0 );
INSERT INTO weapon VALUES ( 11, 0, 4, 1, 3, 0, 20, 2, 0 );
INSERT INTO weapon VALUES ( 12, 2, 6, 1, 4, 0, 20, 3, 0 );
INSERT INTO weapon VALUES ( 13, 50, 8, 1, 5, 0, 19, 2, 0 );
INSERT INTO weapon VALUES ( 14, 35, 4, 1, 4, 0, 19, 2, 0 );
INSERT INTO weapon VALUES ( 15, 5, 0.5, 1, 2, 0, 20, 2, 0 );
INSERT INTO weapon VALUES ( 16, 1, 2, 1, 3, 0, 20, 2, 0 );
INSERT INTO weapon VALUES ( 17, 0, 0, 1, 2, 0, 20, 2, 0 );
INSERT INTO weapon VALUES ( 18, 8, 2, 1, 3, 0, 20, 2, 1 );
INSERT INTO weapon VALUES ( 19, 1, 2, 1, 2, 0, 20, 2, 1 );
INSERT INTO weapon VALUES ( 20, 6, 3, 1, 3, 0, 20, 3, 1 );
INSERT INTO weapon VALUES ( 21, 8, 2, 1, 2, 0, 18, 2, 1 );
INSERT INTO weapon VALUES ( 22, 4, 3, 1, 2, 0, 20, 4, 1 );
INSERT INTO weapon VALUES ( 23, 1, 2, 1, 3, 0, 20, 2, 1 );
INSERT INTO weapon VALUES ( 24, 10, 2, 1, 3, 0, 19, 2, 1 );
INSERT INTO weapon VALUES ( 25, 10, 6, 1, 4, 0, 20, 3, 1 );
INSERT INTO weapon VALUES ( 26, 8, 5, 1, 4, 0, 20, 2, 1 );
INSERT INTO weapon VALUES ( 27, 15, 4, 1, 4, 0, 19, 2, 1 );
INSERT INTO weapon VALUES ( 28, 8, 6, 1, 3, 0, 20, 4, 1 );
INSERT INTO weapon VALUES ( 29, 20, 2, 1, 3, 0, 18, 2, 1 );
INSERT INTO weapon VALUES ( 30, 15, 4, 1, 3, 0, 18, 2, 1 );
INSERT INTO weapon VALUES ( 31, 15, 4, 1, 4, 0, 20, 2, 1 );
INSERT INTO weapon VALUES ( 32, 12, 5, 1, 4, 0, 20, 3, 1 );
INSERT INTO weapon VALUES ( 33, 75, 8, 2, 2, 0, 18, 2, 1 );
INSERT INTO weapon VALUES ( 34, 8, 10, 1, 5, 0, 20, 3, 1 );
INSERT INTO weapon VALUES ( 35, 20, 12, 1, 6, 0, 20, 3, 1 );
INSERT INTO weapon VALUES ( 36, 5, 8, 1, 5, 0, 20, 2, 1 );
INSERT INTO weapon VALUES ( 37, 15, 10, 1, 5, 0, 19, 2, 1 );
INSERT INTO weapon VALUES ( 38, 50, 8, 2, 3, 0, 19, 2, 1 );
INSERT INTO weapon VALUES ( 39, 9, 12, 2, 2, 0, 20, 3, 1 );
INSERT INTO weapon VALUES ( 40, 10, 12, 1, 5, 0, 20, 3, 1 );
INSERT INTO weapon VALUES ( 41, 10, 10, 1, 4, 0, 20, 3, 1 );
INSERT INTO weapon VALUES ( 42, 10, 12, 2, 2, 0, 20, 3, 1 );
INSERT INTO weapon VALUES ( 43, 18, 10, 2, 2, 0, 20, 4, 1 );
INSERT INTO weapon VALUES ( 44, 75, 3, 1, 4, 0, 20, 3, 1 );
INSERT INTO weapon VALUES ( 45, 100, 3, 1, 4, 0, 20, 3, 1 );
INSERT INTO weapon VALUES ( 46, 30, 2, 1, 3, 0, 20, 3, 1 );
INSERT INTO weapon VALUES ( 47, 75, 2, 1, 3, 0, 20, 3, 1 );
INSERT INTO weapon VALUES ( 48, 2, 2, 1, 3, 0, 20, 2, 2 );
INSERT INTO weapon VALUES ( 49, 2, 2, 1, 3, 0, 20, 2, 2 );
INSERT INTO weapon VALUES ( 50, 1, 1, 1, 2, 0, 20, 2, 2 );
INSERT INTO weapon VALUES ( 51, 3, 1, 1, 3, 0, 20, 2, 2 );
INSERT INTO weapon VALUES ( 52, 35, 6, 1, 5, 0, 19, 2, 2 );
INSERT INTO weapon VALUES ( 53, 30, 8, 1, 5, 0, 20, 3, 2 );
INSERT INTO weapon VALUES ( 54, 1, 2, 1, 1, 0, 20, 2, 2 );
INSERT INTO weapon VALUES ( 55, 60, 15, 1, 4, 0, 20, 3, 2 );
INSERT INTO weapon VALUES ( 56, 25, 10, 2, 2, 0, 20, 2, 2 );
INSERT INTO weapon VALUES ( 57, 90, 10, 1, 4, 0, 20, 2, 2 );
INSERT INTO weapon VALUES ( 58, 20, 6, 1, 4, 0, 20, 3, 2 );
INSERT INTO weapon VALUES ( 59, 100, 10, 1, 4, 0, 19, 2, 2 );
INSERT INTO weapon VALUES ( 60, 50, 12, 1, 4, 0, 20, 3, 2 );
INSERT INTO weapon VALUES ( 61, 5, 2, 1, 2, 0, 20, 2, 2 );
INSERT INTO weapon VALUES ( 62, 100, 2, 1, 2, 0, 19, 2, 2 );
INSERT INTO weapon VALUES ( 63, 400, 12, 1, 5, 0, 19, 2, 2 );
INSERT INTO weapon VALUES ( 64, 250, 6, 1, 4, 0, 19, 2, 2 );
INSERT INTO weapon VALUES ( 65, 0.2, 0.1, 1, 0, 0, 20, 2, 2 );
INSERT INTO weapon VALUES ( 66, 0.1, 0.1, 0, 0, 0, 0, 0, 3 );
INSERT INTO weapon VALUES ( 67, 0.01, 0.5, 0, 0, 0, 0, 0, 3 );
INSERT INTO weapon VALUES ( 68, 0.05, 0.15, 0, 0, 0, 0, 0, 3 );

INSERT INTO weapon_text VALUES ( 0, 0, 'Gauntlet' );
INSERT INTO weapon_text VALUES ( 1, 0, 'Dagger' );
INSERT INTO weapon_text VALUES ( 2, 0, 'Punshing Dagger' );
INSERT INTO weapon_text VALUES ( 3, 0, 'Spiked Gauntlet' );
INSERT INTO weapon_text VALUES ( 4, 0, 'Light Mace' );
INSERT INTO weapon_text VALUES ( 5, 0, 'Sickle' );
INSERT INTO weapon_text VALUES ( 6, 0, 'Club' );
INSERT INTO weapon_text VALUES ( 7, 0, 'Heavy Mace' );
INSERT INTO weapon_text VALUES ( 8, 0, 'Morningstar' );
INSERT INTO weapon_text VALUES ( 9, 0, 'Shortspear' );
INSERT INTO weapon_text VALUES ( 10, 0, 'Longspear' );
INSERT INTO weapon_text VALUES ( 11, 0, 'Quarterstaff' );
INSERT INTO weapon_text VALUES ( 12, 0, 'Spear' );
INSERT INTO weapon_text VALUES ( 13, 0, 'Heavy Crossbow' );
INSERT INTO weapon_text VALUES ( 14, 0, 'Light Crossbow' );
INSERT INTO weapon_text VALUES ( 15, 0, 'Dart' );
INSERT INTO weapon_text VALUES ( 16, 0, 'Javelin' );
INSERT INTO weapon_text VALUES ( 17, 0, 'Sling' );
INSERT INTO weapon_text VALUES ( 18, 0, 'Throwing Axe' );
INSERT INTO weapon_text VALUES ( 19, 0, 'Light Hammer' );
INSERT INTO weapon_text VALUES ( 20, 0, 'Handaxe' );
INSERT INTO weapon_text VALUES ( 21, 0, 'Kukri' );
INSERT INTO weapon_text VALUES ( 22, 0, 'Light Pick' );
INSERT INTO weapon_text VALUES ( 23, 0, 'Sap' );
INSERT INTO weapon_text VALUES ( 24, 0, 'Short Sword' );
INSERT INTO weapon_text VALUES ( 25, 0, 'Battleaxe' );
INSERT INTO weapon_text VALUES ( 26, 0, 'Flail' );
INSERT INTO weapon_text VALUES ( 27, 0, 'Longsword' );
INSERT INTO weapon_text VALUES ( 28, 0, 'Heavy Pick' );
INSERT INTO weapon_text VALUES ( 29, 0, 'Rapier' );
INSERT INTO weapon_text VALUES ( 30, 0, 'Scimitar' );
INSERT INTO weapon_text VALUES ( 31, 0, 'Trident' );
INSERT INTO weapon_text VALUES ( 32, 0, 'Warhammer' );
INSERT INTO weapon_text VALUES ( 33, 0, 'Falchion' );
INSERT INTO weapon_text VALUES ( 34, 0, 'Glaive' );
INSERT INTO weapon_text VALUES ( 35, 0, 'Greataxe' );
INSERT INTO weapon_text VALUES ( 36, 0, 'Greatclub' );
INSERT INTO weapon_text VALUES ( 37, 0, 'Heavy Flail' );
INSERT INTO weapon_text VALUES ( 38, 0, 'Greatsword' );
INSERT INTO weapon_text VALUES ( 39, 0, 'Guisarme' );
INSERT INTO weapon_text VALUES ( 40, 0, 'Halberd' );
INSERT INTO weapon_text VALUES ( 41, 0, 'Lance' );
INSERT INTO weapon_text VALUES ( 42, 0, 'Ranseur' );
INSERT INTO weapon_text VALUES ( 43, 0, 'Scythe' );
INSERT INTO weapon_text VALUES ( 44, 0, 'Longbow' );
INSERT INTO weapon_text VALUES ( 45, 0, 'Composite Longbow' );
INSERT INTO weapon_text VALUES ( 46, 0, 'Shortbow' );
INSERT INTO weapon_text VALUES ( 47, 0, 'Composite Shortbow' );
INSERT INTO weapon_text VALUES ( 48, 0, 'Kama' );
INSERT INTO weapon_text VALUES ( 49, 0, 'Nunchaku' );
INSERT INTO weapon_text VALUES ( 50, 0, 'Sai' );
INSERT INTO weapon_text VALUES ( 51, 0, 'Siangham' );
INSERT INTO weapon_text VALUES ( 52, 0, 'Bastard Sword' );
INSERT INTO weapon_text VALUES ( 53, 0, 'Dwarven Waraxe' );
INSERT INTO weapon_text VALUES ( 54, 0, 'Whip' );
INSERT INTO weapon_text VALUES ( 55, 0, 'Orc Double Axe' );
INSERT INTO weapon_text VALUES ( 56, 0, 'Spiked Chain' );
INSERT INTO weapon_text VALUES ( 57, 0, 'Dire Flail' );
INSERT INTO weapon_text VALUES ( 58, 0, 'Gnome Hooked Hammer' );
INSERT INTO weapon_text VALUES ( 59, 0, 'Two-Bladed Sword' );
INSERT INTO weapon_text VALUES ( 60, 0, 'Dwarven Urgrosh' );
INSERT INTO weapon_text VALUES ( 61, 0, 'Bolas' );
INSERT INTO weapon_text VALUES ( 62, 0, 'Hand Crossbow' );
INSERT INTO weapon_text VALUES ( 63, 0, 'Heavy Repeating Crossbow' );
INSERT INTO weapon_text VALUES ( 64, 0, 'Light Repeating Crossbow' );
INSERT INTO weapon_text VALUES ( 65, 0, 'Shuriken' );
INSERT INTO weapon_text VALUES ( 66, 0, 'Crossbow Bolt' );
INSERT INTO weapon_text VALUES ( 67, 0, 'Sling Bullet' );
INSERT INTO weapon_text VALUES ( 68, 0, 'Bow Arrow' );

INSERT INTO weapon_text VALUES ( 0, 1, 'Panzerhandschuh' );
INSERT INTO weapon_text VALUES ( 1, 1, 'Dolch' );
INSERT INTO weapon_text VALUES ( 2, 1, 'Stossdolch' );
INSERT INTO weapon_text VALUES ( 3, 1, 'Stachelhandschuh' );
INSERT INTO weapon_text VALUES ( 4, 1, 'Leichter Streitkolben' );
INSERT INTO weapon_text VALUES ( 5, 1, 'Sichel' );
INSERT INTO weapon_text VALUES ( 6, 1, 'Keule' );
INSERT INTO weapon_text VALUES ( 7, 1, 'Schwerer Streitkolben' );
INSERT INTO weapon_text VALUES ( 8, 1, 'Morgenstern' );
INSERT INTO weapon_text VALUES ( 9, 1, 'Kurzspeer' );
INSERT INTO weapon_text VALUES ( 10, 1, 'Langspeer' );
INSERT INTO weapon_text VALUES ( 11, 1, 'Kampfstab' );
INSERT INTO weapon_text VALUES ( 12, 1, 'Speer' );
INSERT INTO weapon_text VALUES ( 13, 1, 'Schwere Armbrust' );
INSERT INTO weapon_text VALUES ( 14, 1, 'Leichte Armbrust' );
INSERT INTO weapon_text VALUES ( 15, 1, 'Wurfpfeil' );
INSERT INTO weapon_text VALUES ( 16, 1, 'Wurfspeer' );
INSERT INTO weapon_text VALUES ( 17, 1, 'Schleuder' );
INSERT INTO weapon_text VALUES ( 18, 1, 'Wurfbeil' );
INSERT INTO weapon_text VALUES ( 19, 1, 'Leichter Hammer' );
INSERT INTO weapon_text VALUES ( 20, 1, 'Beil' );
INSERT INTO weapon_text VALUES ( 21, 1, 'Kukri' );
INSERT INTO weapon_text VALUES ( 22, 1, 'Leichte Kriegshacke' );
INSERT INTO weapon_text VALUES ( 23, 1, 'Totschlaeger' );
INSERT INTO weapon_text VALUES ( 24, 1, 'Kurzschwert' );
INSERT INTO weapon_text VALUES ( 25, 1, 'Streitaxt' );
INSERT INTO weapon_text VALUES ( 26, 1, 'Streitflegel' );
INSERT INTO weapon_text VALUES ( 27, 1, 'Langschwert' );
INSERT INTO weapon_text VALUES ( 28, 1, 'Schwere Kriegshacke' );
INSERT INTO weapon_text VALUES ( 29, 1, 'Rapier' );
INSERT INTO weapon_text VALUES ( 30, 1, 'Krummsaebel' );
INSERT INTO weapon_text VALUES ( 31, 1, 'Dreizack' );
INSERT INTO weapon_text VALUES ( 32, 1, 'Streithammer' );
INSERT INTO weapon_text VALUES ( 33, 1, 'Krummschwert' );
INSERT INTO weapon_text VALUES ( 34, 1, 'Glefe' );
INSERT INTO weapon_text VALUES ( 35, 1, 'Zweihaendige Axt' );
INSERT INTO weapon_text VALUES ( 36, 1, 'Zweihaendige Keule' );
INSERT INTO weapon_text VALUES ( 37, 1, 'Schwerer Streitflegel' );
INSERT INTO weapon_text VALUES ( 38, 1, 'Zweihaender' );
INSERT INTO weapon_text VALUES ( 39, 1, 'Guisarme' );
INSERT INTO weapon_text VALUES ( 40, 1, 'Hellebarde' );
INSERT INTO weapon_text VALUES ( 41, 1, 'Lanze' );
INSERT INTO weapon_text VALUES ( 42, 1, 'Ranseur' );
INSERT INTO weapon_text VALUES ( 43, 1, 'Sense' );
INSERT INTO weapon_text VALUES ( 44, 1, 'Langbogen' );
INSERT INTO weapon_text VALUES ( 45, 1, 'Kompositlangbogen' );
INSERT INTO weapon_text VALUES ( 46, 1, 'Kurzbogen' );
INSERT INTO weapon_text VALUES ( 47, 1, 'Kompositkurzbogen' );
INSERT INTO weapon_text VALUES ( 48, 1, 'Kama' );
INSERT INTO weapon_text VALUES ( 49, 1, 'Nunchaku' );
INSERT INTO weapon_text VALUES ( 50, 1, 'Sai' );
INSERT INTO weapon_text VALUES ( 51, 1, 'Siangham' );
INSERT INTO weapon_text VALUES ( 52, 1, 'Bastardschwert' );
INSERT INTO weapon_text VALUES ( 53, 1, 'Zwergische Streitaxt' );
INSERT INTO weapon_text VALUES ( 54, 1, 'Peitsche' );
INSERT INTO weapon_text VALUES ( 55, 1, 'Orkische Doppelaxt' );
INSERT INTO weapon_text VALUES ( 56, 1, 'Stachelkettte' );
INSERT INTO weapon_text VALUES ( 57, 1, 'Schreckensflegel' );
INSERT INTO weapon_text VALUES ( 58, 1, 'Gnomischer Hakenhammer' );
INSERT INTO weapon_text VALUES ( 59, 1, 'Doppelklingenschwert' );
INSERT INTO weapon_text VALUES ( 60, 1, 'Zwergische Urgrosch' );
INSERT INTO weapon_text VALUES ( 61, 1, 'Bolas' );
INSERT INTO weapon_text VALUES ( 62, 1, 'Handarmbrust' );
INSERT INTO weapon_text VALUES ( 63, 1, 'Schwere Repetierarmbrust' );
INSERT INTO weapon_text VALUES ( 64, 1, 'Leichte Repetierarmbrust' );
INSERT INTO weapon_text VALUES ( 65, 1, 'Shuriken' );
INSERT INTO weapon_text VALUES ( 66, 1, 'Armbrustbolzen' );
INSERT INTO weapon_text VALUES ( 67, 1, 'Schleuderkugel' );
INSERT INTO weapon_text VALUES ( 68, 1, 'Pfeil' );

CREATE TABLE armor
(id               INTEGER,
 cost             FLOAT,
 weight           FLOAT,
 armor_bonus      INTEGER,
 armor_penalty    INTEGER,
 armor_type_id    INTEGER,
 PRIMARY KEY (id),
 FOREIGN KEY (armor_type_id) REFERENCES armor_type(id)
);

CREATE TABLE armor_text
(armor_id        INTEGER,
 language_id      INTEGER,
 name             VARCHAR(30),
 PRIMARY KEY (armor_id, language_id),
 FOREIGN KEY (armor_id) REFERENCES armor(id) ON DELETE CASCADE,
 FOREIGN KEY (language_id) REFERENCES language(id) ON DELETE CASCADE
);

CREATE TABLE charakter_armor
(id             INTEGER,
 charakter_id   INTEGER,
 armor_id       INTEGER,
 number         INTEGER,
 PRIMARY KEY (id),
 FOREIGN KEY (charakter_id) REFERENCES charakter(id),
 FOREIGN KEY (armor_id) REFERENCES armor(id)
);

INSERT INTO armor VALUES ( 0, 5, 10, 1, 0, 0 );
INSERT INTO armor VALUES ( 1, 10, 15, 2, 0, 0 );
INSERT INTO armor VALUES ( 2, 25, 20, 3, -1, 0 );
INSERT INTO armor VALUES ( 3, 100, 25, 4, -2, 0 );
INSERT INTO armor VALUES ( 4, 15, 25, 3, -3, 1 );
INSERT INTO armor VALUES ( 5, 50, 30, 4, -4, 1 );
INSERT INTO armor VALUES ( 6, 150, 40, 5, -5, 1 );
INSERT INTO armor VALUES ( 7, 200, 30, 5, -4, 1 );
INSERT INTO armor VALUES ( 8, 200, 45, 6, -7, 2 );
INSERT INTO armor VALUES ( 9, 250, 35, 6, -6, 2 );
INSERT INTO armor VALUES ( 10, 600, 50, 7, -7, 2 );
INSERT INTO armor VALUES ( 11, 1500, 50, 8, -6, 2 );
INSERT INTO armor VALUES ( 12, 15, 5, 1, -1, 3 );
INSERT INTO armor VALUES ( 13, 3, 5, 1, -1, 3 );
INSERT INTO armor VALUES ( 14, 9, 6, 1, -1, 3 );
INSERT INTO armor VALUES ( 15, 7, 10, 2, -2, 3 );
INSERT INTO armor VALUES ( 16, 20, 15, 2, -2, 3 );
INSERT INTO armor VALUES ( 17, 30, 45, 4, -10, 3 );

INSERT INTO armor_text VALUES ( 0, 0, 'Padded' );
INSERT INTO armor_text VALUES ( 1, 0, 'Leather' );
INSERT INTO armor_text VALUES ( 2, 0, 'Studded Leather' );
INSERT INTO armor_text VALUES ( 3, 0, 'Chain Shirt' );
INSERT INTO armor_text VALUES ( 4, 0, 'Hide' );
INSERT INTO armor_text VALUES ( 5, 0, 'Scale Mail' );
INSERT INTO armor_text VALUES ( 6, 0, 'Chain Mail' );
INSERT INTO armor_text VALUES ( 7, 0, 'Breastplate' );
INSERT INTO armor_text VALUES ( 8, 0, 'Splint Mail' );
INSERT INTO armor_text VALUES ( 9, 0, 'Banded Mail' );
INSERT INTO armor_text VALUES ( 10, 0, 'Half-Plate' );
INSERT INTO armor_text VALUES ( 11, 0, 'Full Plate' );
INSERT INTO armor_text VALUES ( 12, 0, 'Buckler' );
INSERT INTO armor_text VALUES ( 13, 0, 'Light Wooden Shield' );
INSERT INTO armor_text VALUES ( 14, 0, 'Light Steel Shield' );
INSERT INTO armor_text VALUES ( 15, 0, 'Heavy Wooden Shield' );
INSERT INTO armor_text VALUES ( 16, 0, 'Heavy Steel Shield' );
INSERT INTO armor_text VALUES ( 17, 0, 'Tower Shield' );

INSERT INTO armor_text VALUES ( 0, 1, 'Waffenrock' );
INSERT INTO armor_text VALUES ( 1, 1, 'Lederruestung' );
INSERT INTO armor_text VALUES ( 2, 1, 'Beschlagene Lederruestung' );
INSERT INTO armor_text VALUES ( 3, 1, 'Kettenhemd' );
INSERT INTO armor_text VALUES ( 4, 1, 'Fellruestung' );
INSERT INTO armor_text VALUES ( 5, 1, 'Schuppenpanzer' );
INSERT INTO armor_text VALUES ( 6, 1, 'Kettenpanzer' );
INSERT INTO armor_text VALUES ( 7, 1, 'Brustplatte' );
INSERT INTO armor_text VALUES ( 8, 1, 'Schienenpanzer' );
INSERT INTO armor_text VALUES ( 9, 1, 'Baenderpanzer' );
INSERT INTO armor_text VALUES ( 10, 1, 'Plattenpanzer' );
INSERT INTO armor_text VALUES ( 11, 1, 'Ritterruestung' );
INSERT INTO armor_text VALUES ( 12, 1, 'Tartsche' );
INSERT INTO armor_text VALUES ( 13, 1, 'Leicher Holzschild' );
INSERT INTO armor_text VALUES ( 14, 1, 'Leicher Stahlschild' );
INSERT INTO armor_text VALUES ( 15, 1, 'Schwerer Holzschild' );
INSERT INTO armor_text VALUES ( 16, 1, 'Schwerer Stahlschild' );
INSERT INTO armor_text VALUES ( 17, 1, 'Turmschild' );

CREATE TABLE good
(id               INTEGER,
 cost             FLOAT,
 weight           FLOAT,
 good_type_id    INTEGER,
 PRIMARY KEY (id),
 FOREIGN KEY (good_type_id) REFERENCES good_type(id)
);

CREATE TABLE good_text
(good_id        INTEGER,
 language_id      INTEGER,
 name             VARCHAR(30),
 PRIMARY KEY (good_id, language_id),
 FOREIGN KEY (good_id) REFERENCES good(id) ON DELETE CASCADE,
 FOREIGN KEY (language_id) REFERENCES language(id) ON DELETE CASCADE
);

CREATE TABLE charakter_good
(id             INTEGER,
 charakter_id   INTEGER,
 good_id        INTEGER,
 number         INTEGER,
 PRIMARY KEY (id),
 FOREIGN KEY (charakter_id) REFERENCES charakter(id),
 FOREIGN KEY (good_id) REFERENCES good(id)
);

INSERT INTO good VALUES (0, 2, 2, 0 );
INSERT INTO good VALUES (1, 2, 30, 0 );
INSERT INTO good VALUES (2, 0.4, 1, 0 );
INSERT INTO good VALUES (3, 0.1, 5, 0 );
INSERT INTO good VALUES (4, 1, 0, 0 );
INSERT INTO good VALUES (5, 0.5, 3, 0 );
INSERT INTO good VALUES (6, 5, 5, 0 );
INSERT INTO good VALUES (7, 2, 0, 0 );
INSERT INTO good VALUES (8, 0.5, 2, 0 );
INSERT INTO good VALUES (9, 1, 2, 0 );
INSERT INTO good VALUES (10, 0.01, 0, 0 );
INSERT INTO good VALUES (11, 0.1, 1, 0 );
INSERT INTO good VALUES (12, 1, 0.5, 0 );
INSERT INTO good VALUES (13, 1, 0.5, 0 );
INSERT INTO good VALUES (14, 30, 2, 0 );
INSERT INTO good VALUES (15, 0.01, 0, 0 );
INSERT INTO good VALUES (16, 2, 25, 0 );
INSERT INTO good VALUES (17, 2, 5, 0 );
INSERT INTO good VALUES (18, 0.01, 20, 0 );
INSERT INTO good VALUES (19, 0.1, 0, 0 );
INSERT INTO good VALUES (20, 4, 5, 0 );
INSERT INTO good VALUES (21, 0.03, 1.5, 0 );
INSERT INTO good VALUES (22, 1, 0, 0 );
INSERT INTO good VALUES (23, 1, 4, 0 );
INSERT INTO good VALUES (24, 0.5, 2, 0 );
INSERT INTO good VALUES (25, 8, 0, 0 );
INSERT INTO good VALUES (26, 0.1, 0, 0 );
INSERT INTO good VALUES (27, 0.03, 9, 0 );
INSERT INTO good VALUES (28, 0.05, 20, 0 );
INSERT INTO good VALUES (29, 0.1, 1, 0 );
INSERT INTO good VALUES (30, 12, 3, 0 );
INSERT INTO good VALUES (31, 7, 2, 0 );
INSERT INTO good VALUES (32, 20, 1, 0 );
INSERT INTO good VALUES (33, 40, 1, 0 );
INSERT INTO good VALUES (34, 80, 1, 0 );
INSERT INTO good VALUES (35, 150, 1, 0 );
INSERT INTO good VALUES (36, 15, 2, 0 );
INSERT INTO good VALUES (37, 50, 2, 0 );
INSERT INTO good VALUES (38, 10, 0.5, 0 );
INSERT INTO good VALUES (39, 0.02, 1, 0 );
INSERT INTO good VALUES (40, 0.02, 1, 0 );
INSERT INTO good VALUES (41, 0.1, 1, 0 );
INSERT INTO good VALUES (42, 0.4, 0, 0 );
INSERT INTO good VALUES (43, 0.2, 0, 0 );
INSERT INTO good VALUES (44, 3, 10, 0 );
INSERT INTO good VALUES (45, 0.02, 5, 0 );
INSERT INTO good VALUES (46, 0.1, 0.5, 0 );
INSERT INTO good VALUES (47, 0.2, 8, 0 );
INSERT INTO good VALUES (48, 0.5, 10, 0 );
INSERT INTO good VALUES (49, 1, 0.5, 0 );
INSERT INTO good VALUES (50, 10, 20, 0 );
INSERT INTO good VALUES (51, 0.5, 1, 0 );
INSERT INTO good VALUES (52, 1, 10, 0 );
INSERT INTO good VALUES (53, 10, 5, 0 );
INSERT INTO good VALUES (54, 0.1, 0.5, 0 );
INSERT INTO good VALUES (55, 1, 1, 0 );
INSERT INTO good VALUES (56, 0.5, 0, 0 );
INSERT INTO good VALUES (57, 0.8, 0, 0 );
INSERT INTO good VALUES (58, 5, 0, 0 );
INSERT INTO good VALUES (59, 1, 10, 0 );
INSERT INTO good VALUES (60, 0.5, 1, 0 );
INSERT INTO good VALUES (61, 2, 8, 0 );
INSERT INTO good VALUES (62, 2, 8, 0 );
INSERT INTO good VALUES (63, 1000, 1, 0 );
INSERT INTO good VALUES (64, 10, 20, 0 );
INSERT INTO good VALUES (65, 0.01, 1, 0 );
INSERT INTO good VALUES (66, 1, 0, 0 );
INSERT INTO good VALUES (67, 1, 0, 0 );
INSERT INTO good VALUES (68, 1, 4, 0 );
INSERT INTO good VALUES (69, 0.02, 1, 2 );
INSERT INTO good VALUES (70, 10, 1, 1 );
INSERT INTO good VALUES (71, 20, 1, 1 );
INSERT INTO good VALUES (72, 50, 0, 1 );
INSERT INTO good VALUES (73, 110, 1, 2 );
INSERT INTO good VALUES (74, 25, 1, 1 );
INSERT INTO good VALUES (75, 20, 0.5, 2 );
INSERT INTO good VALUES (76, 2, 1, 2 );
INSERT INTO good VALUES (77, 50, 4, 2 );
INSERT INTO good VALUES (78, 30, 1, 2 );
INSERT INTO good VALUES (79, 1, 0, 2 );
INSERT INTO good VALUES (80, 500, 40, 3 );
INSERT INTO good VALUES (81, 5, 5, 3 );
INSERT INTO good VALUES (82, 55, 5, 3 );
INSERT INTO good VALUES (83, 80, 5, 4 );
INSERT INTO good VALUES (84, 50, 8, 4 );
INSERT INTO good VALUES (85, 50, 1, 4 );
INSERT INTO good VALUES (86, 0, 0, 3 );
INSERT INTO good VALUES (87, 1, 0, 3 );
INSERT INTO good VALUES (88, 25, 1, 3 );
INSERT INTO good VALUES (89, 25, 1, 3 );
INSERT INTO good VALUES (90, 100, 0, 3 );
INSERT INTO good VALUES (91, 5, 3, 3 );
INSERT INTO good VALUES (92, 100, 3, 3 );
INSERT INTO good VALUES (93, 2, 1, 3 );
INSERT INTO good VALUES (94, 5, 2, 3 );
INSERT INTO good VALUES (95, 15, 3, 3 );
INSERT INTO good VALUES (96, 30, 1, 3 );
INSERT INTO good VALUES (97, 100, 2, 3 );
INSERT INTO good VALUES (98, 50, 1, 3 );
INSERT INTO good VALUES (99, 1000, 200, 3 );
INSERT INTO good VALUES (100, 1, 4, 5 );
INSERT INTO good VALUES (101, 5, 6, 5 );
INSERT INTO good VALUES (102, 8, 7, 5 );
INSERT INTO good VALUES (103, 30, 6, 5 );
INSERT INTO good VALUES (104, 3, 4, 5 );
INSERT INTO good VALUES (105, 10, 8, 5 );
INSERT INTO good VALUES (106, 5, 2, 5 );
INSERT INTO good VALUES (107, 75, 10, 5 );
INSERT INTO good VALUES (108, 0.1, 2, 5 );
INSERT INTO good VALUES (109, 200, 15, 5 );
INSERT INTO good VALUES (110, 5, 6, 5 );
INSERT INTO good VALUES (111, 1, 5, 5 );
INSERT INTO good VALUES (112, 2, 1, 7 );
INSERT INTO good VALUES (113, 25, 0, 6 );
INSERT INTO good VALUES (114, 150, 0, 6 );
INSERT INTO good VALUES (115, 8, 0, 6 );
INSERT INTO good VALUES (116, 8, 0, 6 );
INSERT INTO good VALUES (117, 0.05, 10, 7 );
INSERT INTO good VALUES (118, 200, 0, 6 );
INSERT INTO good VALUES (119, 75, 0, 6 );
INSERT INTO good VALUES (120, 30, 0, 6 );
INSERT INTO good VALUES (121, 400, 0, 6 );
INSERT INTO good VALUES (122, 150, 0, 6 );
INSERT INTO good VALUES (123, 100, 0, 6 );
INSERT INTO good VALUES (124, 20, 30, 7 );
INSERT INTO good VALUES (125, 5, 15, 7 );
INSERT INTO good VALUES (126, 10, 25, 7 );
INSERT INTO good VALUES (127, 60, 40, 7 );
INSERT INTO good VALUES (128, 15, 20, 7 );
INSERT INTO good VALUES (129, 30, 30, 7 );
INSERT INTO good VALUES (130, 4, 8, 7 );

INSERT INTO good_text VALUES (0, 0, 'Backpack' );
INSERT INTO good_text VALUES (1, 0, 'Barrel' );
INSERT INTO good_text VALUES (2, 0, 'Basket' );
INSERT INTO good_text VALUES (3, 0, 'Bedroll' );
INSERT INTO good_text VALUES (4, 0, 'Bell' );
INSERT INTO good_text VALUES (5, 0, 'Winter Blanket' );
INSERT INTO good_text VALUES (6, 0, 'Block and Tackle' );
INSERT INTO good_text VALUES (7, 0, 'Wine Bottle' );
INSERT INTO good_text VALUES (8, 0, 'Bucket' );
INSERT INTO good_text VALUES (9, 0, 'Caltrops' );
INSERT INTO good_text VALUES (10, 0, 'Candle' );
INSERT INTO good_text VALUES (11, 0, 'Canvas (1 sq. yd.)' );
INSERT INTO good_text VALUES (12, 0, 'Map Case' );
INSERT INTO good_text VALUES (13, 0, 'Scroll Case' );
INSERT INTO good_text VALUES (14, 0, 'Chain (10 ft.)' );
INSERT INTO good_text VALUES (15, 0, 'Chalk' );
INSERT INTO good_text VALUES (16, 0, 'Chest' );
INSERT INTO good_text VALUES (17, 0, 'Crowbar' );
INSERT INTO good_text VALUES (18, 0, 'Firewood (per day)' );
INSERT INTO good_text VALUES (19, 0, 'Fishhook' );
INSERT INTO good_text VALUES (20, 0, 'Fishing Net (25 sq. ft.)' );
INSERT INTO good_text VALUES (21, 0, 'Flask' );
INSERT INTO good_text VALUES (22, 0, 'Flint and Steel' );
INSERT INTO good_text VALUES (23, 0, 'Grappling Hook' );
INSERT INTO good_text VALUES (24, 0, 'Hammer' );
INSERT INTO good_text VALUES (25, 0, 'Vial of Ink' );
INSERT INTO good_text VALUES (26, 0, 'Inkpen' );
INSERT INTO good_text VALUES (27, 0, 'Clay Jug' );
INSERT INTO good_text VALUES (28, 0, 'Ladder (10-foot)' );
INSERT INTO good_text VALUES (29, 0, 'Common Lamp' );
INSERT INTO good_text VALUES (30, 0, 'Bullseye Lantern' );
INSERT INTO good_text VALUES (31, 0, 'Hooded Lantern' );
INSERT INTO good_text VALUES (32, 0, 'Lock, very simple' );
INSERT INTO good_text VALUES (33, 0, 'Lock, average' );
INSERT INTO good_text VALUES (34, 0, 'Lock, good' );
INSERT INTO good_text VALUES (35, 0, 'Lock, amazing' );
INSERT INTO good_text VALUES (36, 0, 'Manacles' );
INSERT INTO good_text VALUES (37, 0, 'Manacles [Masterwork]' );
INSERT INTO good_text VALUES (38, 0, 'Small Steel Mirror' );
INSERT INTO good_text VALUES (39, 0, 'Clay Mug' );
INSERT INTO good_text VALUES (40, 0, 'Clay Tankard' );
INSERT INTO good_text VALUES (41, 0, 'Flask of Oil' );
INSERT INTO good_text VALUES (42, 0, 'Paper (Sheet)' );
INSERT INTO good_text VALUES (43, 0, 'Parchment (Sheet)' );
INSERT INTO good_text VALUES (44, 0, 'Miner Pick' );
INSERT INTO good_text VALUES (45, 0, 'Clay Pitcher' );
INSERT INTO good_text VALUES (46, 0, 'Piton' );
INSERT INTO good_text VALUES (47, 0, 'Pole (10-foot)' );
INSERT INTO good_text VALUES (48, 0, 'Iron Pot' );
INSERT INTO good_text VALUES (49, 0, 'Belt Pouch' );
INSERT INTO good_text VALUES (50, 0, 'Portable Ram' );
INSERT INTO good_text VALUES (51, 0, 'Trail Rations (per Day)' );
INSERT INTO good_text VALUES (52, 0, 'Rope, Hempen' );
INSERT INTO good_text VALUES (53, 0, 'Rope, Silk' );
INSERT INTO good_text VALUES (54, 0, 'Sack' );
INSERT INTO good_text VALUES (55, 0, 'Sealing Wax' );
INSERT INTO good_text VALUES (56, 0, 'Needle Sewing' );
INSERT INTO good_text VALUES (57, 0, 'Signal Whistle' );
INSERT INTO good_text VALUES (58, 0, 'Signet Ring' );
INSERT INTO good_text VALUES (59, 0, 'Sledge' );
INSERT INTO good_text VALUES (60, 0, 'Soap' );
INSERT INTO good_text VALUES (61, 0, 'Shovel' );
INSERT INTO good_text VALUES (62, 0, 'Spade' );
INSERT INTO good_text VALUES (63, 0, 'Spyglass' );
INSERT INTO good_text VALUES (64, 0, 'Tent' );
INSERT INTO good_text VALUES (65, 0, 'Torch' );
INSERT INTO good_text VALUES (66, 0, 'Vial' );
INSERT INTO good_text VALUES (67, 0, 'Potion' );
INSERT INTO good_text VALUES (68, 0, 'Waterskin' );
INSERT INTO good_text VALUES (69, 0, 'Whetstone' );
INSERT INTO good_text VALUES (70, 0, 'Acid (flask)' );
INSERT INTO good_text VALUES (71, 0, 'Alchemist Fire (flask)' );
INSERT INTO good_text VALUES (72, 0, 'Antitoxin (vial)' );
INSERT INTO good_text VALUES (73, 0, 'Everburning Torch' );
INSERT INTO good_text VALUES (74, 0, 'Holy Water (flask)' );
INSERT INTO good_text VALUES (75, 0, 'Smokestick' );
INSERT INTO good_text VALUES (76, 0, 'Sunrod' );
INSERT INTO good_text VALUES (77, 0, 'Tanglefoot Bag' );
INSERT INTO good_text VALUES (78, 0, 'Thunderstone' );
INSERT INTO good_text VALUES (79, 0, 'Tindertwig' );
INSERT INTO good_text VALUES (80, 0, 'Alchemist Lab' );
INSERT INTO good_text VALUES (81, 0, 'Artisan Tools' );
INSERT INTO good_text VALUES (82, 0, 'Artisan Tools [Masterwork]' );
INSERT INTO good_text VALUES (83, 0, 'Climber Kit' );
INSERT INTO good_text VALUES (84, 0, 'Disguise Kit' );
INSERT INTO good_text VALUES (85, 0, 'Healer Kit' );
INSERT INTO good_text VALUES (86, 0, 'Holly and Mistletoe' );
INSERT INTO good_text VALUES (87, 0, 'Holy Symbol, wooden' );
INSERT INTO good_text VALUES (88, 0, 'Holy Symbol, silver' );
INSERT INTO good_text VALUES (89, 0, 'Hourglass' );
INSERT INTO good_text VALUES (90, 0, 'Magnifying Glass' );
INSERT INTO good_text VALUES (91, 0, 'Musical Instrument, common' );
INSERT INTO good_text VALUES (92, 0, 'Musical Instrument [Masterwork]' );
INSERT INTO good_text VALUES (93, 0, 'Scale Merchant' );
INSERT INTO good_text VALUES (94, 0, 'Spell Component Pouch' );
INSERT INTO good_text VALUES (95, 0, 'Spellbook' );
INSERT INTO good_text VALUES (96, 0, 'Thieves Tools' );
INSERT INTO good_text VALUES (97, 0, 'Thieves Tools [Masterwork]' );
INSERT INTO good_text VALUES (98, 0, 'Tools [Masterwork]' );
INSERT INTO good_text VALUES (99, 0, 'Water clock' );
INSERT INTO good_text VALUES (100, 0, 'Outfit Artisan' );
INSERT INTO good_text VALUES (101, 0, 'Cleric Vestments' );
INSERT INTO good_text VALUES (102, 0, 'Outfit Cold Weather' );
INSERT INTO good_text VALUES (103, 0, 'Outfit Courtier' );
INSERT INTO good_text VALUES (104, 0, 'Outfit Entertainer' );
INSERT INTO good_text VALUES (105, 0, 'Outfit Explorer' );
INSERT INTO good_text VALUES (106, 0, 'Outfit Monk' );
INSERT INTO good_text VALUES (107, 0, 'Outfit Noble' );
INSERT INTO good_text VALUES (108, 0, 'Outfit Peasant' );
INSERT INTO good_text VALUES (109, 0, 'Outfit Royal' );
INSERT INTO good_text VALUES (110, 0, 'Outfit Scholar' );
INSERT INTO good_text VALUES (111, 0, 'Outfit Traveler' );
INSERT INTO good_text VALUES (112, 0, 'Bit and bridle' );
INSERT INTO good_text VALUES (113, 0, 'Dog, guard' );
INSERT INTO good_text VALUES (114, 0, 'Dog, riding' );
INSERT INTO good_text VALUES (115, 0, 'Donkey' );
INSERT INTO good_text VALUES (116, 0, 'Mule' );
INSERT INTO good_text VALUES (117, 0, 'Feed (per day)' );
INSERT INTO good_text VALUES (118, 0, 'Horse, heavy' );
INSERT INTO good_text VALUES (119, 0, 'Horse, light' );
INSERT INTO good_text VALUES (120, 0, 'Pony' );
INSERT INTO good_text VALUES (121, 0, 'Warhorse, heavy' );
INSERT INTO good_text VALUES (122, 0, 'Warhorse, light' );
INSERT INTO good_text VALUES (123, 0, 'Warpony' );
INSERT INTO good_text VALUES (124, 0, 'Saddle, military' );
INSERT INTO good_text VALUES (125, 0, 'Saddle, pack' );
INSERT INTO good_text VALUES (126, 0, 'Saddle, riding' );
INSERT INTO good_text VALUES (127, 0, 'Saddle, exotic, Military' );
INSERT INTO good_text VALUES (128, 0, 'Saddle, exotic, pack' );
INSERT INTO good_text VALUES (129, 0, 'Saddle, exotic, riding' );
INSERT INTO good_text VALUES (130, 0, 'Saddlebags' );

INSERT INTO good_text VALUES (0, 1, 'Rucksack' );
INSERT INTO good_text VALUES (1, 1, 'Fass' );
INSERT INTO good_text VALUES (2, 1, 'Korb' );
INSERT INTO good_text VALUES (3, 1, 'Schlafset' );
INSERT INTO good_text VALUES (4, 1, 'Glocke' );
INSERT INTO good_text VALUES (5, 1, 'Winterdecke' );
INSERT INTO good_text VALUES (6, 1, 'Flaschenzug' );
INSERT INTO good_text VALUES (7, 1, 'Weinflasche' );
INSERT INTO good_text VALUES (8, 1, 'Eimer' );
INSERT INTO good_text VALUES (9, 1, 'Kraehenfuesse' );
INSERT INTO good_text VALUES (10, 1, 'Kerze' );
INSERT INTO good_text VALUES (11, 1, 'Plane (1 qm)' );
INSERT INTO good_text VALUES (12, 1, 'Kartenbehaelter' );
INSERT INTO good_text VALUES (13, 1, 'Schriftrollenbehaelter' );
INSERT INTO good_text VALUES (14, 1, 'Kette (3 m)' );
INSERT INTO good_text VALUES (15, 1, 'Kreide' );
INSERT INTO good_text VALUES (16, 1, 'Truhe' );
INSERT INTO good_text VALUES (17, 1, 'Brecheisen' );
INSERT INTO good_text VALUES (18, 1, 'Feuerholz (pro Tag)' );
INSERT INTO good_text VALUES (19, 1, 'Angelhaken' );
INSERT INTO good_text VALUES (20, 1, 'Fischernetz (2,25 qm)' );
INSERT INTO good_text VALUES (21, 1, 'Flaeschchen' );
INSERT INTO good_text VALUES (22, 1, 'Feuerstein und Stahl' );
INSERT INTO good_text VALUES (23, 1, 'Wurfhaken' );
INSERT INTO good_text VALUES (24, 1, 'Hammer' );
INSERT INTO good_text VALUES (25, 1, 'Tinte (30 ml)' );
INSERT INTO good_text VALUES (26, 1, 'Tintenschreiber' );
INSERT INTO good_text VALUES (27, 1, 'Tonkrug' );
INSERT INTO good_text VALUES (28, 1, 'Leiter (3 m)' );
INSERT INTO good_text VALUES (29, 1, 'Gewoehnliche Lampe' );
INSERT INTO good_text VALUES (30, 1, 'Blendlaterne' );
INSERT INTO good_text VALUES (31, 1, 'Abdeckbare Laterne' );
INSERT INTO good_text VALUES (32, 1, 'Schloss, sehr einfach' );
INSERT INTO good_text VALUES (33, 1, 'Schloss, durchschnittlich' );
INSERT INTO good_text VALUES (34, 1, 'Schloss, gut' );
INSERT INTO good_text VALUES (35, 1, 'Schloss, hervorragend' );
INSERT INTO good_text VALUES (36, 1, 'Handschellen' );
INSERT INTO good_text VALUES (37, 1, 'Handschellen [Meisterarbeit]' );
INSERT INTO good_text VALUES (38, 1, 'Kleiner Stahlspiegel' );
INSERT INTO good_text VALUES (39, 1, 'Tonbecher' );
INSERT INTO good_text VALUES (40, 1, 'Tonkrug' );
INSERT INTO good_text VALUES (41, 1, 'Oel (1/2 Liter)' );
INSERT INTO good_text VALUES (42, 1, 'Papier (Blatt)' );
INSERT INTO good_text VALUES (43, 1, 'Pergament (Blatt)' );
INSERT INTO good_text VALUES (44, 1, 'Spitzhacke' );
INSERT INTO good_text VALUES (45, 1, 'Tonhumpen' );
INSERT INTO good_text VALUES (46, 1, 'Kletterhaken' );
INSERT INTO good_text VALUES (47, 1, 'Stab (3 m)' );
INSERT INTO good_text VALUES (48, 1, 'Eisentopf' );
INSERT INTO good_text VALUES (49, 1, 'Guerteltasche' );
INSERT INTO good_text VALUES (50, 1, 'Tragbarer Rammbock' );
INSERT INTO good_text VALUES (51, 1, 'Wegrationen (pro Tag)' );
INSERT INTO good_text VALUES (52, 1, 'Seil, Hanf' );
INSERT INTO good_text VALUES (53, 1, 'Seil, Seide' );
INSERT INTO good_text VALUES (54, 1, 'Sack' );
INSERT INTO good_text VALUES (55, 1, 'Siegelwachs' );
INSERT INTO good_text VALUES (56, 1, 'Naehnadel' );
INSERT INTO good_text VALUES (57, 1, 'Signalpfeife' );
INSERT INTO good_text VALUES (58, 1, 'Siegelring' );
INSERT INTO good_text VALUES (59, 1, 'Vorschlaghammer' );
INSERT INTO good_text VALUES (60, 1, 'Seife' );
INSERT INTO good_text VALUES (61, 1, 'Schaufel' );
INSERT INTO good_text VALUES (62, 1, 'Spaten' );
INSERT INTO good_text VALUES (63, 1, 'Fernrohr' );
INSERT INTO good_text VALUES (64, 1, 'Zelt' );
INSERT INTO good_text VALUES (65, 1, 'Fackel' );
INSERT INTO good_text VALUES (66, 1, 'Phiole' );
INSERT INTO good_text VALUES (67, 1, 'Trank' );
INSERT INTO good_text VALUES (68, 1, 'Wasserschlauch' );
INSERT INTO good_text VALUES (69, 1, 'Wetzstein' );
INSERT INTO good_text VALUES (70, 1, 'Saeure (Flaeschchen)' );
INSERT INTO good_text VALUES (71, 1, 'Alchemistenfeuer (Flaeschchen)' );
INSERT INTO good_text VALUES (72, 1, 'Gegengift (Phiole)' );
INSERT INTO good_text VALUES (73, 1, 'Ewige Fackel' );
INSERT INTO good_text VALUES (74, 1, 'Weihwasser (Flaeschchen)' );
INSERT INTO good_text VALUES (75, 1, 'Rauchstab' );
INSERT INTO good_text VALUES (76, 1, 'Sonnenzepter' );
INSERT INTO good_text VALUES (77, 1, 'Verstrickungsbeutel' );
INSERT INTO good_text VALUES (78, 1, 'Donnerstein' );
INSERT INTO good_text VALUES (79, 1, 'Zuendholz' );
INSERT INTO good_text VALUES (80, 1, 'Alchemistisches Labor' );
INSERT INTO good_text VALUES (81, 1, 'Werkzeug eines Handwerkers' );
INSERT INTO good_text VALUES (82, 1, 'Werkzeug eines Handwerkers [Meisterarbeit]' );
INSERT INTO good_text VALUES (83, 1, 'Kletterzeug' );
INSERT INTO good_text VALUES (84, 1, 'Schminkset' );
INSERT INTO good_text VALUES (85, 1, 'Heilertasche' );
INSERT INTO good_text VALUES (86, 1, 'Stechpalmen und Misteln' );
INSERT INTO good_text VALUES (87, 1, 'Heiliges Symbol aus Holz' );
INSERT INTO good_text VALUES (88, 1, 'Heiliges Symbol aus Silber' );
INSERT INTO good_text VALUES (89, 1, 'Stundenglass' );
INSERT INTO good_text VALUES (90, 1, 'Vergroesserungsglass' );
INSERT INTO good_text VALUES (91, 1, 'Musikinstrument (gewoehnliches)' );
INSERT INTO good_text VALUES (92, 1, 'Musikinstrument [Meisterarbeit]' );
INSERT INTO good_text VALUES (93, 1, 'Kaufmannswaage' );
INSERT INTO good_text VALUES (94, 1, 'Taeschchen fuer Materialkomponenten' );
INSERT INTO good_text VALUES (95, 1, 'Zauberbuch' );
INSERT INTO good_text VALUES (96, 1, 'Diebeswerkzeug' );
INSERT INTO good_text VALUES (97, 1, 'Diebeswerkzeug [Meisterarbeit]' );
INSERT INTO good_text VALUES (98, 1, 'Werkzeug [Meisterarbeit]' );
INSERT INTO good_text VALUES (99, 1, 'Wasseruhr' );
INSERT INTO good_text VALUES (100, 1, 'Handwerkskleidung' );
INSERT INTO good_text VALUES (101, 1, 'Klerikale Gewaender' );
INSERT INTO good_text VALUES (102, 1, 'Kleidung fuer kaltes Wetter' );
INSERT INTO good_text VALUES (103, 1, 'Hoeflingskleidung' );
INSERT INTO good_text VALUES (104, 1, 'Kleidung eines Unterhalters' );
INSERT INTO good_text VALUES (105, 1, 'Kleidung eines Entdeckers' );
INSERT INTO good_text VALUES (106, 1, 'Moenchskleidung' );
INSERT INTO good_text VALUES (107, 1, 'Kleidung eines Adligen' );
INSERT INTO good_text VALUES (108, 1, 'Bauernkleidung' );
INSERT INTO good_text VALUES (109, 1, 'Koenigliche Kleidung' );
INSERT INTO good_text VALUES (110, 1, 'Kleidung eines Gelehrten' );
INSERT INTO good_text VALUES (111, 1, 'Kleidung eines Reisenden' );
INSERT INTO good_text VALUES (112, 1, 'Zaumzeug' );
INSERT INTO good_text VALUES (113, 1, 'Hund, Wachhund' );
INSERT INTO good_text VALUES (114, 1, 'Hund, Reithund' );
INSERT INTO good_text VALUES (115, 1, 'Esel' );
INSERT INTO good_text VALUES (116, 1, 'Maultier' );
INSERT INTO good_text VALUES (117, 1, 'Futter (pro Tag)' );
INSERT INTO good_text VALUES (118, 1, 'Reitpferd, schweres' );
INSERT INTO good_text VALUES (119, 1, 'Reitpferd, leichtes ' );
INSERT INTO good_text VALUES (120, 1, 'Pony' );
INSERT INTO good_text VALUES (121, 1, 'Streitross, schweres' );
INSERT INTO good_text VALUES (122, 1, 'Streitross, leichtes' );
INSERT INTO good_text VALUES (123, 1, 'Kriegspony' );
INSERT INTO good_text VALUES (124, 1, 'Sattel, Militaersattel' );
INSERT INTO good_text VALUES (125, 1, 'Sattel, Packsattel' );
INSERT INTO good_text VALUES (126, 1, 'Sattel, Reitsattel' );
INSERT INTO good_text VALUES (127, 1, 'Sattel, exotischer Militaersattel' );
INSERT INTO good_text VALUES (128, 1, 'Sattel, exotischer Packsattel' );
INSERT INTO good_text VALUES (129, 1, 'Sattel, exotischer Reitsattel' );
INSERT INTO good_text VALUES (130, 1, 'Statteltaschen' );
