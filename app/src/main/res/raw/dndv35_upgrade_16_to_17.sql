UPDATE good_text SET name = 'Tonhumpen' WHERE good_id = 40 AND language_id = 1;
UPDATE good_text SET name = 'Tonkanne' WHERE good_id = 45 AND language_id = 1;
UPDATE feat_text SET name = 'Verbessertes Ueberrennen' WHERE name = 'Verbessertes ueberrennen';

CREATE TABLE size
(id             INTEGER,
 name           VARCHAR(15),
 PRIMARY KEY(id)
);
INSERT INTO size VALUES (0,'Fine');
INSERT INTO size VALUES (1,'Diminutive');
INSERT INTO size VALUES (2,'Tiny');
INSERT INTO size VALUES (3,'Small');
INSERT INTO size VALUES (4,'Medium');
INSERT INTO size VALUES (5,'Large (tall)');
INSERT INTO size VALUES (6,'Large (long)');
INSERT INTO size VALUES (7,'Huge (tall)');
INSERT INTO size VALUES (8,'Huge (long)');
INSERT INTO size VALUES (9,'Gargantuan (tall)');
INSERT INTO size VALUES (10,'Gargantuan (long)');
INSERT INTO size VALUES (11,'Colossal (tall)');
INSERT INTO size VALUES (12,'Colossal (long)');

DROP TABLE race;

CREATE TABLE race
(id          INTEGER,
 size_id           INTEGER,
 base_land_speed   INTEGER,
 fav_class_id      INTEGER,
 PRIMARY KEY (id)
);
INSERT INTO race VALUES (0, 4, 30, -1);
INSERT INTO race VALUES (1, 4, 20, 4);
INSERT INTO race VALUES (2, 4, 30, 10);
INSERT INTO race VALUES (3, 3, 20, 1);
INSERT INTO race VALUES (4, 4, 30, -1);
INSERT INTO race VALUES (5, 4, 30, 0);
INSERT INTO race VALUES (6, 3, 20, 8);
INSERT INTO race VALUES (7, 4, 20, 4);
INSERT INTO race VALUES (8, 4, 20, 4);
INSERT INTO race VALUES (9, 4, 20, 4);
INSERT INTO race VALUES (10, 4, 40, 4);
INSERT INTO race VALUES (11, 4, 30, 10);
INSERT INTO race VALUES (12, 4, 30, 10);
INSERT INTO race VALUES (13, 4, 30, 9);
INSERT INTO race VALUES (14, 4, 30, 10);
INSERT INTO race VALUES (15, 3, 20, 1);
INSERT INTO race VALUES (16, 3, 20, 1);
INSERT INTO race VALUES (17, 3, 20, 8);
INSERT INTO race VALUES (18, 3, 20, 8);

CREATE TABLE race_text
(race_id            INTEGER,
 language_id         INTEGER,
 name                VARCHAR(30),
 PRIMARY KEY (race_id, language_id),
 FOREIGN KEY (race_id) REFERENCES race(id) ON DELETE CASCADE,
 FOREIGN KEY (language_id) REFERENCES language(id) ON DELETE CASCADE
);

INSERT INTO race_text VALUES (0, 0, 'Human');
INSERT INTO race_text VALUES (1, 0, 'Hill dwarf');
INSERT INTO race_text VALUES (2, 0, 'High Elf');
INSERT INTO race_text VALUES (3, 0, 'Rock Gnome');
INSERT INTO race_text VALUES (4, 0, 'Half-Elf');
INSERT INTO race_text VALUES (5, 0, 'Half-Orc');
INSERT INTO race_text VALUES (6, 0, 'Lightfeet');
INSERT INTO race_text VALUES (7, 0, 'Deep dwarf');
INSERT INTO race_text VALUES (8, 0, 'Mountain dwarf');
INSERT INTO race_text VALUES (9, 0, 'Duergar');
INSERT INTO race_text VALUES (10, 0, 'Aquatic Elf');
INSERT INTO race_text VALUES (11, 0, 'Drow');
INSERT INTO race_text VALUES (12, 0, 'Gray Elf');
INSERT INTO race_text VALUES (13, 0, 'Wild Elf');
INSERT INTO race_text VALUES (14, 0, 'Wood Elf');
INSERT INTO race_text VALUES (15, 0, 'Svirfneblin');
INSERT INTO race_text VALUES (16, 0, 'Forest Gnome');
INSERT INTO race_text VALUES (17, 0, 'Tallfellows');
INSERT INTO race_text VALUES (18, 0, 'Deep Halfling');

INSERT INTO race_text VALUES (0, 1, 'Mensch');
INSERT INTO race_text VALUES (1, 1, 'Huegelzwerg');
INSERT INTO race_text VALUES (2, 1, 'Hochelf');
INSERT INTO race_text VALUES (3, 1, 'Felsengnom');
INSERT INTO race_text VALUES (4, 1, 'Halb-Elf');
INSERT INTO race_text VALUES (5, 1, 'Halb-Ork');
INSERT INTO race_text VALUES (6, 1, 'Leichtfuss');
INSERT INTO race_text VALUES (7, 1, 'Tiefenzwerg');
INSERT INTO race_text VALUES (8, 1, 'Bergzwerg');
INSERT INTO race_text VALUES (9, 1, 'Grauzwerg');
INSERT INTO race_text VALUES (10, 1, 'Meerelf');
INSERT INTO race_text VALUES (11, 1, 'Dunkelelf');
INSERT INTO race_text VALUES (12, 1, 'Grauelf');
INSERT INTO race_text VALUES (13, 1, 'Wildelf');
INSERT INTO race_text VALUES (14, 1, 'Waldelf');
INSERT INTO race_text VALUES (15, 1, 'Tiefengnom');
INSERT INTO race_text VALUES (16, 1, 'Waldgnom');
INSERT INTO race_text VALUES (17, 1, 'Grossgefaehrte');
INSERT INTO race_text VALUES (18, 1, 'Tiefenhalbling');

CREATE TABLE trait_type
(id        INTEGER,
 name      VARCHAR(30),
 PRIMARY KEY(id)
);
INSERT INTO trait_type VALUES (0, 'Extraordinary');
INSERT INTO trait_type VALUES (1, 'Spell-like');
INSERT INTO trait_type VALUES (2, 'Supernatural');

CREATE TABLE trait
(id              INTEGER,
 trait_type_id   INTEGER,
 PRIMARY KEY(id),
 FOREIGN KEY (trait_type_id) REFERENCES trait_type(id) ON DELETE CASCADE
);
INSERT INTO trait VALUES (0, 0);
INSERT INTO trait VALUES (1, 0);
INSERT INTO trait VALUES (2, 0);
INSERT INTO trait VALUES (3, 0);
INSERT INTO trait VALUES (4, 0);
INSERT INTO trait VALUES (5, 0);
INSERT INTO trait VALUES (6, 0);
INSERT INTO trait VALUES (7, 0);
INSERT INTO trait VALUES (8, 0);
INSERT INTO trait VALUES (9, 0);
INSERT INTO trait VALUES (10, 0);
INSERT INTO trait VALUES (11, 0);
INSERT INTO trait VALUES (12, 0);
INSERT INTO trait VALUES (13, 0);
INSERT INTO trait VALUES (14, 0);
INSERT INTO trait VALUES (15, 0);
INSERT INTO trait VALUES (16, 0);
INSERT INTO trait VALUES (17, 0);
INSERT INTO trait VALUES (18, 0);
INSERT INTO trait VALUES (19, 0);
INSERT INTO trait VALUES (20, 0);
INSERT INTO trait VALUES (21, 0);
INSERT INTO trait VALUES (22, 0);
INSERT INTO trait VALUES (23, 0);
INSERT INTO trait VALUES (24, 0);
INSERT INTO trait VALUES (25, 0);
INSERT INTO trait VALUES (26, 1);
INSERT INTO trait VALUES (27, 1);
INSERT INTO trait VALUES (28, 0);
INSERT INTO trait VALUES (29, 0);
INSERT INTO trait VALUES (30, 0);
INSERT INTO trait VALUES (31, 0);
INSERT INTO trait VALUES (32, 0);
INSERT INTO trait VALUES (33, 0);
INSERT INTO trait VALUES (34, 0);
INSERT INTO trait VALUES (35, 0);
INSERT INTO trait VALUES (36, 0);
INSERT INTO trait VALUES (37, 0);
INSERT INTO trait VALUES (38, 0);
INSERT INTO trait VALUES (39, 0);
INSERT INTO trait VALUES (40, 0);
INSERT INTO trait VALUES (41, 0);
INSERT INTO trait VALUES (42, 0);
INSERT INTO trait VALUES (43, 0);
INSERT INTO trait VALUES (44, 0);
INSERT INTO trait VALUES (45, 0);
INSERT INTO trait VALUES (46, 1);
INSERT INTO trait VALUES (47, 0);
INSERT INTO trait VALUES (48, 0);
INSERT INTO trait VALUES (49, 0);
INSERT INTO trait VALUES (50, 0);
INSERT INTO trait VALUES (51, 0);
INSERT INTO trait VALUES (52, 0);
INSERT INTO trait VALUES (53, 0);
INSERT INTO trait VALUES (54, 0);
INSERT INTO trait VALUES (55, 1);
INSERT INTO trait VALUES (56, 0);
INSERT INTO trait VALUES (57, 0);
INSERT INTO trait VALUES (58, 0);
INSERT INTO trait VALUES (59, 0);
INSERT INTO trait VALUES (60, 0);
INSERT INTO trait VALUES (61, 0);
INSERT INTO trait VALUES (62, 0);
INSERT INTO trait VALUES (63, 1);
INSERT INTO trait VALUES (64, 2);
INSERT INTO trait VALUES (65, 0);
INSERT INTO trait VALUES (66, 2);
INSERT INTO trait VALUES (67, 0);
INSERT INTO trait VALUES (68, 0);
INSERT INTO trait VALUES (69, 0);

CREATE TABLE trait_text
(trait_id      INTEGER,
 language_id   INTEGER,
 name          VARCHAR(30),
 description   VARCHAR(255),
 PRIMARY KEY(trait_id, language_id),
 FOREIGN KEY (trait_id) REFERENCES trait(id) ON DELETE CASCADE,
 FOREIGN KEY (language_id) REFERENCES language(id) ON DELETE CASCADE
);
INSERT INTO trait_text VALUES (0, 0, 'Extra Feet', '1 extra feat at 1st level');
INSERT INTO trait_text VALUES (1, 0, 'Extra Skill Points', '4 extra skill points at 1st level and 1 extra skill point at each additional level');
INSERT INTO trait_text VALUES (2, 0, 'Ability Adjustment', '+2 Constitution, -2 Charisma');
INSERT INTO trait_text VALUES (3, 0, 'Darkvision (60 feet)', 'See up to 60 feet in the dark. Darkvision is black and white only, but it is otherwise like normal sight, can function just fine with no light at all.');
INSERT INTO trait_text VALUES (4, 0, 'Carry heavy load', 'Dwarves can move at normal speed even when wearing medium or heavy armor or when carrying a medium or heavy load (unlike other creatures, whose speed is reduced in such situations)');
INSERT INTO trait_text VALUES (5, 0, 'Stonecunning', '+2 racial bonus on Search checks to notice unusual stonework. Within 10 feet of unusual stonework make a Search check. Can use the Search skill to find stonework traps. Can also intuit depth, sensing his approx. depth underground.');
INSERT INTO trait_text VALUES (6, 0, 'Weapon Familiarity', 'Dwarves may treat dwarven waraxes and dwarven urgroshes as martial weapons, rather than exotic weapons.');
INSERT INTO trait_text VALUES (7, 0, 'Stability', '+4 bonus on ability checks made to resist being bull rushed or tripped when standing on the ground (but not when climbing, flying, riding, or otherwise not standing firmly on the ground).');
INSERT INTO trait_text VALUES (8, 0, 'Resist poison', '+2 racial bonus on saving throws against poison.');
INSERT INTO trait_text VALUES (9, 0, 'Resist spells', '+2 racial bonus on saving throws against spells and spell-like effects.');
INSERT INTO trait_text VALUES (10, 0, 'Animosity', '+1 racial bonus on attack rolls against orcs and goblinoids.');
INSERT INTO trait_text VALUES (11, 0, 'Armor bonus against Giants', '+4 dodge bonus to Armor Class against monsters of the giant type. Any time a creature loses its Dexterity bonus (if any) to Armor Class, such as when it is caught flat-footed, it loses its dodge bonus, too. ');
INSERT INTO trait_text VALUES (12, 0, 'Skill Bonus', '+2 racial bonus on Appraise checks that are related to stone or metal items.');
INSERT INTO trait_text VALUES (13, 0, 'Skill Bonus', '+2 racial bonus on Craft checks that are related to stone or metal.');
INSERT INTO trait_text VALUES (14, 0, 'Ability Adjustment', '+2 Dexterity, -2 Constitution.');
INSERT INTO trait_text VALUES (15, 0, 'Resist Sleep', 'Immunity to magic sleep effects, and a +2 racial saving throw bonus against enchantment spells or effects.');
INSERT INTO trait_text VALUES (16, 0, 'Low-Light Vision', 'See twice as far as a human in starlight, moonlight, torchlight, and similar conditions of poor illumination. Retains the ability to distinguish color and detail under these conditions.');
INSERT INTO trait_text VALUES (17, 0, 'Weapon Proficiency', 'Receive the Martial Weapon Proficiency feats for the longsword, rapier, longbow (including composite longbow), and shortbow (including composite shortbow) as bonus feats.');
INSERT INTO trait_text VALUES (18, 0, 'Skill Bonus', '+2 racial bonus on Listen, Search, and Spot checks. An elf who merely passes within 5 feet of a secret or concealed door is entitled to a Search check to notice it as if she were actively looking for it.');
INSERT INTO trait_text VALUES (19, 0, 'Ability Adjustment', '+2 Constitution, -2 Strength');
INSERT INTO trait_text VALUES (20, 0, 'Weapon Familiarity', 'Gnomes may treat gnome hooked hammers as martial weapons rather than exotic weapons.');
INSERT INTO trait_text VALUES (21, 0, 'Resist Illusion', '+2 racial bonus on saving throws against illusions.');
INSERT INTO trait_text VALUES (22, 0, 'Improved Illusion', '+1 to the Difficulty Class for all saving throws against illusion spells cast by gnomes. This adjustment stacks with those from similar effects.');
INSERT INTO trait_text VALUES (23, 0, 'Animosity', '+1 racial bonus on attack rolls against kobolds and goblinoids.');
INSERT INTO trait_text VALUES (24, 0, 'Skill Bonus', '+2 racial bonus on Listen checks.');
INSERT INTO trait_text VALUES (25, 0, 'Skill Bonus', '+2 racial bonus on Craft (alchemy) checks.');
INSERT INTO trait_text VALUES (26, 0, 'Spell Bonus', '1/day - speak with animals (burrowing mammal only, duration 1 minute)');
INSERT INTO trait_text VALUES (27, 0, 'Spell Bonus', '1/day - dancing lights, ghost sound, prestidigitation. Caster level 1st save DC 10 + gnome Cha modifier + spell level.');
INSERT INTO trait_text VALUES (28, 0, 'Skill Bonus', '+1 racial bonus on Listen, Search, and Spot checks.');
INSERT INTO trait_text VALUES (29, 0, 'Skill Bonus', '+2 racial bonus on Diplomacy and Gather Information checks.');
INSERT INTO trait_text VALUES (30, 0, 'Elven Blood', 'For all effects related to race, a half-elf is considered an elf.');
INSERT INTO trait_text VALUES (31, 0, 'Ability Adjustment', '+2 Strength, -2 Intelligence, -2 Charisma');
INSERT INTO trait_text VALUES (32, 0, 'Orc Blood', 'For all effects related to race, a half-orc is considered an orc.');
INSERT INTO trait_text VALUES (33, 0, 'Ability Adjustment', '+2 Dexterity, -2 Strength');
INSERT INTO trait_text VALUES (34, 0, 'Skill Bonus', '+2 racial bonus on Climb, Jump, and Move Silently checks.');
INSERT INTO trait_text VALUES (35, 0, 'Resist Misfortune', '+1 racial bonus on all saving throws.');
INSERT INTO trait_text VALUES (36, 0, 'Resist Fear', '+2 morale bonus on saving throws against fear: This bonus stacks with the halfling +1 bonus on saving throws in general.');
INSERT INTO trait_text VALUES (37, 0, 'Weapon Proficiency', '+1 racial bonus on attack rolls with thrown weapons and slings.');
INSERT INTO trait_text VALUES (38, 0, 'Skill Bonus', '+2 racial bonus on Listen checks.');
INSERT INTO trait_text VALUES (39, 0, 'Resist spells', '+3 racial bonus on saving throws against spells and spell-like effects.');
INSERT INTO trait_text VALUES (40, 0, 'Resist poison', '+3 racial bonus on saving throws against poison.');
INSERT INTO trait_text VALUES (41, 0, 'Darkvision (90 feet)', 'See up to 90 feet in the dark. Darkvision is black and white only, but it is otherwise like normal sight, can function just fine with no light at all.');
INSERT INTO trait_text VALUES (42, 0, 'Light Sensitivity', 'Dazzled in bright sunlight or within the radius of a daylight spell.');
INSERT INTO trait_text VALUES (43, 0, 'Ability Adjustment', '+2 Constitution, -4 Charisma');
INSERT INTO trait_text VALUES (44, 0, 'Darkvision (120 feet)', 'See up to 120 feet in the dark. Darkvision is black and white only, but it is otherwise like normal sight, can function just fine with no light at all.');
INSERT INTO trait_text VALUES (45, 0, 'Immunity', 'Immunity to paralysis, phantasms, and poison');
INSERT INTO trait_text VALUES (46, 0, 'Spell Bonus', '1/day - enlarge person and invisibility as a wizard of twice the duergar class level (minimum caster level 3rd) these abilities affect only the duergar and whatever it carries.');
INSERT INTO trait_text VALUES (47, 0, 'Skill Bonus', '+4 racial bonus on Move Silently checks');
INSERT INTO trait_text VALUES (48, 0, 'Skill Bonus', '+1 racial bonus on Listen and Spot checks');
INSERT INTO trait_text VALUES (49, 0, 'Ability Adjustment', '+2 Dexterity, -2 Intelligence');
INSERT INTO trait_text VALUES (50, 0, 'Gills', 'Aquatic elves can survive out of the water for 1 hour per point of Constitution (after that, refer to the suffocation rules)');
INSERT INTO trait_text VALUES (51, 0, 'Superior Low-Light Vision', 'See four times as far as a human in starlight, moonlight, torchlight, and similar conditions of low illumination');
INSERT INTO trait_text VALUES (52, 0, 'Poison', 'An opponent hit by a poisoned weapon must succeed on a DC 13 Fortitude save or fall unconscious. After 1 minute, the subject must succeed on another DC 13 Fortitude save or remain unconscious for 2d4 hours.');
INSERT INTO trait_text VALUES (53, 0, 'Ability Adjustment', '+2 Intelligence, +2 Charisma');
INSERT INTO trait_text VALUES (54, 0, 'Spell resistance', 'Spell resistance equal to 11 + class levels.');
INSERT INTO trait_text VALUES (55, 0, 'Spell Bonus', '1/day - dancing lights, darkness, faerie fire. Caster level equals class levels.');
INSERT INTO trait_text VALUES (56, 0, 'Weapon Proficiency', 'A drow is automatically proficient with the hand crossbow, the rapier, and the short sword.');
INSERT INTO trait_text VALUES (57, 0, 'Light Blindness', 'Abrupt exposure to bright light (such as sunlight or a daylight spell) blinds drow for 1 round. On subsequent rounds, they are dazzled as long as they remain in the affected area.');
INSERT INTO trait_text VALUES (58, 0, 'Ability Adjustment', '+2 Intelligence, -2 Strength');
INSERT INTO trait_text VALUES (59, 0, 'Ability Adjustment', '+2 Dexterity, -2 Intelligence');
INSERT INTO trait_text VALUES (60, 0, 'Ability Adjustment', '+2 Strength, -2 Intelligence');
INSERT INTO trait_text VALUES (61, 0, 'Ability Adjustment', '-2 Strength, +2 Dexterity, +2 Wisdom, -4 Charisma');
INSERT INTO trait_text VALUES (62, 0, 'Resist Misfortune', '+2 racial bonus on all saving throws');
INSERT INTO trait_text VALUES (63, 0, 'Spell Bonus', '1/day - blindness/deafness, blur, disguise self. Caster level equals the svirfneblin class levels. The save DC is Charisma-based and include a +4 racial modifier.');
INSERT INTO trait_text VALUES (64, 0, 'Spell Bonus', 'A svirfneblin has a continuous nondetection ability as the spell (caster level equal to class levels).');
INSERT INTO trait_text VALUES (65, 0, 'Skill Bonus', '+2 racial bonus on Hide checks, which improves to +4 underground.');
INSERT INTO trait_text VALUES (66, 0, 'SpellBonus', 'A forest gnome has the innate ability to use pass without trace (self only, as a free action) as the spell cast by a druid of the forest gnome class levels.');
INSERT INTO trait_text VALUES (67, 0, 'Animosity', '+1 racial bonus on attack rolls against kobolds, goblinoids, orcs, and reptilian humanoids.');
INSERT INTO trait_text VALUES (68, 0, 'Skill Bonus', '+4 racial bonus on Hide checks, which improves to +8 in a wooded area.');
INSERT INTO trait_text VALUES (69, 0, 'Skill Bonus', '+2 racial bonus on Search, Spot, and Listen checks. Like an elf, a tallfellow who merely passes within 5 feet of a secret or concealed door is entitled to a Search check as though actively looking for it.');

INSERT INTO trait_text VALUES (0, 1, 'Extra Talent', '1 Bonustalent auf der 1.Stufe');
INSERT INTO trait_text VALUES (1, 1, 'Extra Fertigkeitspunkte', '4 zusaetzliche Fertigkeitspunkte auf der 1 Stufe und 1 zusaetzlicher Fertigkeitspunkt auf jeder weiteren Stufe');
INSERT INTO trait_text VALUES (2, 1, 'Attributsanpassung', '+2 Konstitution, -2 Charisma');
INSERT INTO trait_text VALUES (3, 1, 'Dunkelsicht (18m)', 'Bis zu 18m wie im Dunkeln sehen. Die Dunkelsicht ist nur schwarz-weiss, funktioniert aber ansonsten wie normale Sicht.');
INSERT INTO trait_text VALUES (4, 1, 'Schwere Lasten tragen', 'Zwerge koennen sich dann mit ihrere Grundbewegungsrate bewegen, wenn sie mittelschwere oder schwere Ruestung oder eine mittlere oder schwere Last tragen.');
INSERT INTO trait_text VALUES (5, 1, 'Steinwissen', '+2 Volksbonus auf Suchen von ungewoehnlichen Steinkonstruktionen. Innerhalb von 3m von ungewoehnlichen Steinkonstruktionen wird ein Suchen Wurf durchgefuehrt. Kann Suchen um Steinfallen zu entdecken. Kann intiutive Spueren wie tief er unter der Erde ist.');
INSERT INTO trait_text VALUES (6, 1, 'Waffenvertrautheit', 'Fuer Zwerge gelten Zwergische Streitaexte und Zwergische Urgrosch als Kriegswaffen und nicht als exotische Waffen.');
INSERT INTO trait_text VALUES (7, 1, 'Standfestigkeit', '+4 Volksbonus auf Attributswuerfe um einem Ansturm zu widerstehen oder nicht zu Fall gebracht zu werden, solange er auf dem Boden steht (aber nicht, wenn er klettert, fliegt, reitet oder auf andere Weise nicht fest auf dem Boden steht)');
INSERT INTO trait_text VALUES (8, 1, 'Giften widerstehen', '+2 Volksbonus auf Rettungswuerfe gegen Gift');
INSERT INTO trait_text VALUES (9, 1, 'Zaubern widerstehen', '+2 Volksbonus auf Rettungswuerfe gegen Zaubersprueche und zauberaehnliche Efffekte');
INSERT INTO trait_text VALUES (10, 1, 'Feindseligkeit', '+1 Volksbonus auf Angriffswuerfe gegen Orks und Goblinoiden');
INSERT INTO trait_text VALUES (11, 1, 'Ruestungsbonus gegen Riesen', '+4 Ausweichbonus auf die Ruestungsklasse gegen Monster vom Typ Riese. Wenn eine Kreatur ihren Geschicklichkeitsbonus auf die Ruestungsklasse verliert, wie als waere sie auf dem falschen Fuss erwischt, verliert sie auch ihren Ausweichbonus');
INSERT INTO trait_text VALUES (12, 1, 'Fertigkeitsbonus', '+2 Volksbonus auf Schaetzen in Bezug auf Stein und Metal.');
INSERT INTO trait_text VALUES (13, 1, 'Fertigkeitsbonus', '+2 Volksbonus auf Handwerk in Bezug auf Stein und Metal.');
INSERT INTO trait_text VALUES (14, 1, 'Attributsanpassung', '+2 Geschicklichkeit, -2 Konstitution');
INSERT INTO trait_text VALUES (15, 1, 'Schlaf widerstehen', 'Immunitaet gegen magische Schlaf-Effekte sowie ein Volksbonus von +2 auf Rettungswuerfe gegen Zauber oder magische Effekte aus der Schule der Verzauberung');
INSERT INTO trait_text VALUES (16, 1, 'Daemmersicht', 'Bei Sternen-, Mondlicht, im Fackelschein oder aehnlich schlechten Lichtverhaeltnissen doppelt so weit sehen wie Menschen. Farben und Details koennen unter solchen Umstaenden weiterhin unterschieden werden.');
INSERT INTO trait_text VALUES (17, 1, 'Umgang mit Waffen', 'Erhalten die Talente fuer den Umgang mit Kriegswaffen fuer die Waffen Langschwert, Rapier, Kurzbogen, Langbogen, Kompositbogen (lang) und Kompositbogen (kurz) als Bonustalente.');
INSERT INTO trait_text VALUES (18, 1, 'Fertigkeitsbonus', '+2 Volksbonus auf Entdecken, Lauchen und Suchen. Ein Elf, der in maximal 1,50 m Entfernung an einer geheimen oder verborgenen Tuer vorbeigeht, darf einen Wurf fuer Suchen machen, als hielte er aktive nach der Tuer Ausschau.');
INSERT INTO trait_text VALUES (19, 1, 'Attributsanpassung', '+2 Konstitution, -2 Staerke');
INSERT INTO trait_text VALUES (20, 1, 'Waffenvertrautheit', 'Fuer Gnome gelten gnomische Hakenhaemmer als Kriegswaffen, nicht als exotische Waffen');
INSERT INTO trait_text VALUES (21, 1, 'Illusion widerstehen', '+2 Volksbonus auf Rettungswuerfe gegen Illusion');
INSERT INTO trait_text VALUES (22, 1, 'Verbesserte Illusion', '+1 zum Schwierigkeitsgrad fuer alle Rettungswuerfe gegen Illusionszauber, die von Gnomen gewirkt wurden. Dieser Bonus ist kumulativ zu anderen Boni durch aehnliche Effekte wie das Talent Zauberfokus.');
INSERT INTO trait_text VALUES (23, 1, 'Feindseligkeit', '+1 Volksbonus auf Angriffswuerfe gegen Kobolde und Goblinoiden');
INSERT INTO trait_text VALUES (24, 1, 'Fertigkeitsbonus', '+2 Volksbonus auf Lauschen');
INSERT INTO trait_text VALUES (25, 1, 'Fertigkeitsbonus', '+2 Volksbonus auf Handwerk (Alchemie)');
INSERT INTO trait_text VALUES (26, 1, 'Zauberbonus', '1/Tag - Mit Tieren sprechen (nur im Bau lebende Saeuger, Dauer 1 min)');
INSERT INTO trait_text VALUES (27, 1, 'Zauberbonus', '1/Tag - Geiserhaftes Geraeusch, Tanzende Lister und Zaubertrick. Zauberstufe 1, RW SG 10 + CH-Modifikator des Gnoms + Zaubergrad');
INSERT INTO trait_text VALUES (28, 1, 'Fertigkeitsbonus', '+1 Volksbonus auf Lauschen, Suchen und Entdecken');
INSERT INTO trait_text VALUES (29, 1, 'Fertigkeitsbonus', '+2 Volksbonus auf Diplomatie und Informationen sammeln');
INSERT INTO trait_text VALUES (30, 1, 'Elfenblut', 'Fuer alle rassenbezogene Effekte wird ein Halb-Elf als Elf betrachtet');
INSERT INTO trait_text VALUES (31, 1, 'Attributsanpassung', '+2 Staerke, -2 Intelligenz, -2 Charisma');
INSERT INTO trait_text VALUES (32, 1, 'Orkblut', 'Fuer alle rassenbezogene Effekte wird ein Halb-Ork als Ork betrachtet');
INSERT INTO trait_text VALUES (33, 1, 'Attributsanpassung', '+2 Geschicklichkeit, -2 Staerke');
INSERT INTO trait_text VALUES (34, 1, 'Fertigkeitsbonus', '+2 Volksbonus auf Klettern, Springen und Leise bewegen');
INSERT INTO trait_text VALUES (35, 1, 'Unglueck widerstehen', '+1 Volksbonus auf alle Rettungswuerfe');
INSERT INTO trait_text VALUES (36, 1, 'Angst widerstehen', '+2 Moralbonus auf Rettungswuerfe gegen Furcht. Dieser Bonus wird zu dem Bonus addiert, den Halblinge auf ale Rettungswuerfe erhalten');
INSERT INTO trait_text VALUES (37, 1, 'Umgang mit Waffen', '+1 Volksbonus auf Angriffswuerfe mit Wurfwaffen und Schleudern');
INSERT INTO trait_text VALUES (38, 1, 'Fertigkeitsbonus', '+2 Volksbonus auf Lauschen');
INSERT INTO trait_text VALUES (39, 1, 'Zaubern widerstehen', '+3 Volksbonus auf Rettungswuerfe gegen Zaubersprueche und zauberaehnliche Effekte');
INSERT INTO trait_text VALUES (40, 1, 'Giften widerstehen', '+3 Volksbonus auf Rettungswuerfe gegen Gift');
INSERT INTO trait_text VALUES (41, 1, 'Dunkelsicht (24m)', 'Bis zu 24m wie im Dunkeln sehen. Die Dunkelsicht ist nur schwarz-weiss, funktioniert aber ansonsten wie normale Sicht.');
INSERT INTO trait_text VALUES (42, 1, 'Lichtempfindlichkeit', 'Geblendet im Sonnenlicht oder innerhalb des Wirkungsbereiches des Zaubers Tageslicht');
INSERT INTO trait_text VALUES (43, 1, 'Attributsanpassung', '+2 Konstitution, -4 Charisma');
INSERT INTO trait_text VALUES (44, 1, 'Dunkelsicht (30m)', 'Bis zu 30m wie im Dunkeln sehen. Die Dunkelsicht ist nur schwarz-weiss, funktioniert aber ansonsten wie normale Sicht.');
INSERT INTO trait_text VALUES (45, 1, 'Immunitaet', 'Immunitaet gegen Laehmung, Trugbilder und Gift');
INSERT INTO trait_text VALUES (46, 1, 'Zauberbonus', '1/Tag - Person vergroessern und Unsichtbarkeit wie ein Magier der doppelten Klassenstufe des Duergar. Diese Faehigkeit betrifft nur den Duergar und von ihm getragene Gegenstaende');
INSERT INTO trait_text VALUES (47, 1, 'Fertigkeitsbonus', '+4 Volksbonus auf Leise bewegen');
INSERT INTO trait_text VALUES (48, 1, 'Fertigkeitsbonus', '+1 Volksbonus auf Lauschen und Entdecken');
INSERT INTO trait_text VALUES (49, 1, 'Attributsanpassung', '+2 Geschicklichkeit, -2 Intelligenz');
INSERT INTO trait_text VALUES (50, 1, 'Kiemen', 'Wasserelfen koennen 1 Stunde pro Konstitutionspunkt ausserhalb des Wassers ueberleben (danach gelten die Regeln fuer Ersticken auf Seite 379 im Spielleiter-Handbuch)');
INSERT INTO trait_text VALUES (51, 1, 'Ueberlegene Daemmersicht', 'Bei Sternenlicht, Mondschein, Fackelschein und aehnlich schlechter Beleuchtung viermal so weit sehen wie ein Mensch.');
INSERT INTO trait_text VALUES (52, 1, 'Gift', 'Gegner die von einer vergifteten Waffe getroffen wird muessen einen SG 13 Widerstand Rettungswurf ablegen oder werden bewusstlos. Nach einer Minute muss das Opfer wieder einen SG 13 Widerstand Rettungswurf ablegen oder bleibt fuer 2W4 Stunden bewusstlos.');
INSERT INTO trait_text VALUES (53, 1, 'Attributsanpassung', '+2 Intelligenz, +2 Charisma');
INSERT INTO trait_text VALUES (54, 1, 'Zauberresistenz', 'Zauberresistenz von 11 + Klassenstufen');
INSERT INTO trait_text VALUES (55, 1, 'Zauberbonus', '1/Tag - Dunkelheit, Feenfeuer, Tanzende Lichter. Die Zauberstufe entspricht der Klassenstufen');
INSERT INTO trait_text VALUES (56, 1, 'Umgang mit Waffen', 'Geuebt im Umgang mit Handarmbrust, Rapier und Kurzschwert.');
INSERT INTO trait_text VALUES (57, 1, 'Blindheit durch Licht', 'Werden Drow abrupt hellem Licht wie beispielsweise Sonnenlicht oder dem Zauber Tageslicht ausgesetz, sind sie ein Runde lang blind. Danach sind sie geblendet, solange sie sich in dem beleuchteten Gebiet aufhalten.');
INSERT INTO trait_text VALUES (58, 1, 'Attributsanpassung', '+2 Intelligenz, -2 Staerke');
INSERT INTO trait_text VALUES (59, 1, 'Attributsanpassung', '+2 Geschicklichkeit, -2 Intelligenz');
INSERT INTO trait_text VALUES (60, 1, 'Attributsanpassung', '+2 Staerke, -2 Intelligenz');
INSERT INTO trait_text VALUES (61, 1, 'Attributsanpassung', '-2 Staerke, +2 Geschicklichkeit, +2 Weisheit, -4 Charisma');
INSERT INTO trait_text VALUES (62, 1, 'Unglueck widerstehen', '+2 Volksbonus auf alle Rettungswuerfe');
INSERT INTO trait_text VALUES (63, 1, 'Zauberbonus', '1/Tag - Blindheit/Taubheit verursachen, Selbstverkleidung, Verschwimmen. Die effektive Zauberstufe entspricht der Klassenstufe des Svirfneblin. Der SG der Rettungswuerfe basieren auf Charisma und enthalten eine Volksmodifikator von +4.');
INSERT INTO trait_text VALUES (64, 1, 'Zauberbonus', 'Ein Svirfneblin verfuegt dauerhaft ueber den Effekt des Zaubers Unauffindbarkeit (die Zauberstufe entspricht dabei seinen Klassenstufen)');
INSERT INTO trait_text VALUES (65, 1, 'Fertigkeitsbonus', '+2 Volksbonus auf Verstecken, der sich unterirdisch auf +4 verbessert');
INSERT INTO trait_text VALUES (66, 1, 'Zauberbonus', 'Waldgnome verfuegen ueber die angeborene Faehigkeit, als Freie Aktion den Zauber Spurloses gehen wie ein Drudie zu wirken (nur auf sich selbst), dessen Stufe der Klassenstufe des Waldgnoms entspricht.');
INSERT INTO trait_text VALUES (67, 1, 'Feindseligkeit', '+1 Volksbonus auf Angriffswuerfe gegen Kobolde, Goblinoiden, Orks und Reptilmenschen');
INSERT INTO trait_text VALUES (68, 1, 'Fertigkeitsbonus', '+4 Volksbonus auf Verstecken, der sich im bewaldeten Gebiet auf +4 verbessert');
INSERT INTO trait_text VALUES (69, 1, 'Fertigkeitsbonus', '+2 Volksbonus auf Entdecken, Lauschen und Suchen. Wie ein Elf darf auch ein Grossgefaehrte, der sich einer geheimen oder verborgenen Tuer auf weniger als 1,50m naehrt, einen Fertigkeitswurf fuer Suchen durchfuehren, als ob er aktiv nach ihr suchen wuerde');

CREATE TABLE race_trait
(race_id   INTEGER,
 trait_id  INTEGER,
 PRIMARY KEY(race_id, trait_id),
 FOREIGN KEY (race_id) REFERENCES race(id) ON DELETE CASCADE,
 FOREIGN KEY (trait_id) REFERENCES trait(id) ON DELETE CASCADE
);
INSERT INTO race_trait VALUES (0, 0);
INSERT INTO race_trait VALUES (0, 1);
INSERT INTO race_trait VALUES (1, 2);
INSERT INTO race_trait VALUES (1, 3);
INSERT INTO race_trait VALUES (1, 4);
INSERT INTO race_trait VALUES (1, 5);
INSERT INTO race_trait VALUES (1, 6);
INSERT INTO race_trait VALUES (1, 7);
INSERT INTO race_trait VALUES (1, 8);
INSERT INTO race_trait VALUES (1, 9);
INSERT INTO race_trait VALUES (1, 10);
INSERT INTO race_trait VALUES (1, 11);
INSERT INTO race_trait VALUES (1, 12);
INSERT INTO race_trait VALUES (1, 13);
INSERT INTO race_trait VALUES (2, 14);
INSERT INTO race_trait VALUES (2, 15);
INSERT INTO race_trait VALUES (2, 16);
INSERT INTO race_trait VALUES (2, 17);
INSERT INTO race_trait VALUES (2, 18);
INSERT INTO race_trait VALUES (3, 19);
INSERT INTO race_trait VALUES (3, 16);
INSERT INTO race_trait VALUES (3, 20);
INSERT INTO race_trait VALUES (3, 21);
INSERT INTO race_trait VALUES (3, 22);
INSERT INTO race_trait VALUES (3, 23);
INSERT INTO race_trait VALUES (3, 11);
INSERT INTO race_trait VALUES (3, 24);
INSERT INTO race_trait VALUES (3, 25);
INSERT INTO race_trait VALUES (3, 26);
INSERT INTO race_trait VALUES (3, 27);
INSERT INTO race_trait VALUES (4, 15);
INSERT INTO race_trait VALUES (4, 16);
INSERT INTO race_trait VALUES (4, 28);
INSERT INTO race_trait VALUES (4, 29);
INSERT INTO race_trait VALUES (4, 30);
INSERT INTO race_trait VALUES (5, 3);
INSERT INTO race_trait VALUES (5, 31);
INSERT INTO race_trait VALUES (5, 32);
INSERT INTO race_trait VALUES (6, 33);
INSERT INTO race_trait VALUES (6, 34);
INSERT INTO race_trait VALUES (6, 35);
INSERT INTO race_trait VALUES (6, 36);
INSERT INTO race_trait VALUES (6, 37);
INSERT INTO race_trait VALUES (6, 38);
INSERT INTO race_trait VALUES (7, 2);
INSERT INTO race_trait VALUES (7, 41);
INSERT INTO race_trait VALUES (7, 4);
INSERT INTO race_trait VALUES (7, 5);
INSERT INTO race_trait VALUES (7, 6);
INSERT INTO race_trait VALUES (7, 7);
INSERT INTO race_trait VALUES (7, 40);
INSERT INTO race_trait VALUES (7, 39);
INSERT INTO race_trait VALUES (7, 10);
INSERT INTO race_trait VALUES (7, 11);
INSERT INTO race_trait VALUES (7, 12);
INSERT INTO race_trait VALUES (7, 13);
INSERT INTO race_trait VALUES (7, 42);
INSERT INTO race_trait VALUES (8, 43);
INSERT INTO race_trait VALUES (8, 44);
INSERT INTO race_trait VALUES (8, 4);
INSERT INTO race_trait VALUES (8, 5);
INSERT INTO race_trait VALUES (8, 7);
INSERT INTO race_trait VALUES (8, 45);
INSERT INTO race_trait VALUES (8, 9);
INSERT INTO race_trait VALUES (8, 10);
INSERT INTO race_trait VALUES (8, 11);
INSERT INTO race_trait VALUES (8, 12);
INSERT INTO race_trait VALUES (8, 13);
INSERT INTO race_trait VALUES (8, 46);
INSERT INTO race_trait VALUES (8, 42);
INSERT INTO race_trait VALUES (8, 47);
INSERT INTO race_trait VALUES (8, 48);
INSERT INTO race_trait VALUES (9, 2);
INSERT INTO race_trait VALUES (9, 3);
INSERT INTO race_trait VALUES (9, 4);
INSERT INTO race_trait VALUES (9, 5);
INSERT INTO race_trait VALUES (9, 6);
INSERT INTO race_trait VALUES (9, 7);
INSERT INTO race_trait VALUES (9, 8);
INSERT INTO race_trait VALUES (9, 9);
INSERT INTO race_trait VALUES (9, 10);
INSERT INTO race_trait VALUES (9, 11);
INSERT INTO race_trait VALUES (9, 12);
INSERT INTO race_trait VALUES (9, 13);
INSERT INTO race_trait VALUES (10, 49);
INSERT INTO race_trait VALUES (10, 15);
INSERT INTO race_trait VALUES (10, 17);
INSERT INTO race_trait VALUES (10, 18);
INSERT INTO race_trait VALUES (10, 50);
INSERT INTO race_trait VALUES (10, 51);
INSERT INTO race_trait VALUES (11, 53);
INSERT INTO race_trait VALUES (11, 15);
INSERT INTO race_trait VALUES (11, 44);
INSERT INTO race_trait VALUES (11, 56);
INSERT INTO race_trait VALUES (11, 18);
INSERT INTO race_trait VALUES (11, 52);
INSERT INTO race_trait VALUES (11, 9);
INSERT INTO race_trait VALUES (11, 55);
INSERT INTO race_trait VALUES (11, 57);
INSERT INTO race_trait VALUES (12, 58);
INSERT INTO race_trait VALUES (12, 15);
INSERT INTO race_trait VALUES (12, 16);
INSERT INTO race_trait VALUES (12, 17);
INSERT INTO race_trait VALUES (12, 18);
INSERT INTO race_trait VALUES (13, 59);
INSERT INTO race_trait VALUES (13, 15);
INSERT INTO race_trait VALUES (13, 16);
INSERT INTO race_trait VALUES (13, 17);
INSERT INTO race_trait VALUES (13, 18);
INSERT INTO race_trait VALUES (14, 60);
INSERT INTO race_trait VALUES (14, 15);
INSERT INTO race_trait VALUES (14, 16);
INSERT INTO race_trait VALUES (14, 17);
INSERT INTO race_trait VALUES (14, 18);
INSERT INTO race_trait VALUES (15, 61);
INSERT INTO race_trait VALUES (15, 16);
INSERT INTO race_trait VALUES (15, 20);
INSERT INTO race_trait VALUES (15, 62);
INSERT INTO race_trait VALUES (15, 22);
INSERT INTO race_trait VALUES (15, 23);
INSERT INTO race_trait VALUES (15, 11);
INSERT INTO race_trait VALUES (15, 24);
INSERT INTO race_trait VALUES (15, 25);
INSERT INTO race_trait VALUES (15, 26);
INSERT INTO race_trait VALUES (15, 63);
INSERT INTO race_trait VALUES (15, 5);
INSERT INTO race_trait VALUES (15, 44);
INSERT INTO race_trait VALUES (15, 54);
INSERT INTO race_trait VALUES (15, 64);
INSERT INTO race_trait VALUES (15, 65);
INSERT INTO race_trait VALUES (16, 19);
INSERT INTO race_trait VALUES (16, 16);
INSERT INTO race_trait VALUES (16, 20);
INSERT INTO race_trait VALUES (16, 21);
INSERT INTO race_trait VALUES (16, 22);
INSERT INTO race_trait VALUES (16, 67);
INSERT INTO race_trait VALUES (16, 11);
INSERT INTO race_trait VALUES (16, 24);
INSERT INTO race_trait VALUES (16, 25);
INSERT INTO race_trait VALUES (16, 26);
INSERT INTO race_trait VALUES (16, 27);
INSERT INTO race_trait VALUES (16, 66);
INSERT INTO race_trait VALUES (17, 33);
INSERT INTO race_trait VALUES (17, 35);
INSERT INTO race_trait VALUES (17, 36);
INSERT INTO race_trait VALUES (17, 37);
INSERT INTO race_trait VALUES (17, 69);
INSERT INTO race_trait VALUES (18, 33);
INSERT INTO race_trait VALUES (18, 35);
INSERT INTO race_trait VALUES (18, 36);
INSERT INTO race_trait VALUES (18, 37);
INSERT INTO race_trait VALUES (18, 38);
INSERT INTO race_trait VALUES (18, 3);
INSERT INTO race_trait VALUES (18, 5);
INSERT INTO race_trait VALUES (18, 12);
