CREATE TABLE feat_type
(id            INTEGER,
 name          VARCHAR(15),
 PRIMARY KEY (id)
);

INSERT INTO "feat_type" VALUES (0,"General");
INSERT INTO "feat_type" VALUES (1,"Item Creation");
INSERT INTO "feat_type" VALUES (2,"Metamagic");

CREATE TABLE feat_text
(feat_id             INTEGER,
 language_id         INTEGER,
 name                VARCHAR(30),
 prerequisit         VARCHAR(150),
 benefit             VARCHAR(150),
 PRIMARY KEY (feat_id, language_id),
 FOREIGN KEY (feat_id) REFERENCES feat(id) ON DELETE CASCADE,
 FOREIGN KEY (language_id) REFERENCES language(id)
);

INSERT INTO 'feat_text' VALUES (1, 0, 'Acrobatic', '', '+2 bonus on Jump and Tumble checks');
INSERT INTO 'feat_text' VALUES (2, 0, 'Agile', '', '+2 bonus on Balance and Escape Artist checks');
INSERT INTO 'feat_text' VALUES (3, 0, 'Alertness', '', '+2 bonus on Listen and Spot checks');
INSERT INTO 'feat_text' VALUES (4, 0, 'Animal Affinity', '', '+2 bonus on Handle Animal and Ride checks');
INSERT INTO 'feat_text' VALUES (5, 0, 'Armor Proficiency (Heavy)', 'Armor Proficiency (medium)', 'No armor check penalty on attack rolls');
INSERT INTO 'feat_text' VALUES (6, 0, 'Armor Proficiency (Light)', '', 'No armor check penalty on attack rolls');
INSERT INTO 'feat_text' VALUES (7, 0, 'Armor Proficiency (Medium)', 'Armor Proficiency (light)', 'No armor check penalty on attack rolls');
INSERT INTO 'feat_text' VALUES (8, 0, 'Athletic', '', '+2 bonus on Climb and Swim checks');
INSERT INTO 'feat_text' VALUES (9, 0, 'Augment Summoning', 'Spell Focus (conjuration)', 'Summoned creatures gain +4 Str, +4 Con');
INSERT INTO 'feat_text' VALUES (10, 0, 'Blind-Fight', '', 'Reroll miss change for concealment');
INSERT INTO 'feat_text' VALUES (11, 0, 'Brew Potion', 'Caster level 3rd', 'Create magic potions');
INSERT INTO 'feat_text' VALUES (12, 0, 'Cleave', 'Power Attack', 'Extra melee attack after dropping target');
INSERT INTO 'feat_text' VALUES (13, 0, 'Combat Casting', '', '+4 bonus on Concentration checks for defensive casting');
INSERT INTO 'feat_text' VALUES (14, 0, 'Combat Expertise', 'Int 13', 'Trade attack bonus for AC (max 5 points)');
INSERT INTO 'feat_text' VALUES (15, 0, 'Combat Reflexes', '', 'Additional attack of oppertunity');
INSERT INTO 'feat_text' VALUES (16, 0, 'Craft Magic Arms And Armor', 'Caster level 5th', 'Create magic weapons, armor and shields');
INSERT INTO 'feat_text' VALUES (17, 0, 'Craft Rod', 'Caster level 9th', 'Create magic rods');
INSERT INTO 'feat_text' VALUES (18, 0, 'Craft Staff', 'Caster level 12th', 'Create magic staffs');
INSERT INTO 'feat_text' VALUES (19, 0, 'Craft Wand', 'Caster level 5th', 'Create magic wands');
INSERT INTO 'feat_text' VALUES (20, 0, 'Craft Wondrous Item', 'Caster level 3rd', 'Create magic wondrous items');
INSERT INTO 'feat_text' VALUES (21, 0, 'Deceitful', '', '+2 bonus on Disguise and Forgery checks');
INSERT INTO 'feat_text' VALUES (22, 0, 'Deflect Arrows', 'Dex 13, Improved Unarmed Strike', 'Deflect one ranged attack per round');
INSERT INTO 'feat_text' VALUES (23, 0, 'Deft Hands', '', '+2 bonus on Sleight of Hand and Use Rope checks');
INSERT INTO 'feat_text' VALUES (24, 0, 'Diehard', 'Endurance', 'Remain conscious at -1 to -9 hp');
INSERT INTO 'feat_text' VALUES (25, 0, 'Diligent', '', '+2 bonus on Appraise and Decipher Script checks');
INSERT INTO 'feat_text' VALUES (26, 0, 'Dodge', 'Dex 13', '+1 dodge bonus to AC against selected target');
INSERT INTO 'feat_text' VALUES (27, 0, 'Empower Spell', '', 'Increase spell''s variable, numeric effects by 50%');
INSERT INTO 'feat_text' VALUES (28, 0, 'Endurance', '', '+4 bonus on checks or saves to resist nonlethal damage');
INSERT INTO 'feat_text' VALUES (29, 0, 'Enlarge Spell', '', 'Double spell''s range');
INSERT INTO 'feat_text' VALUES (30, 0, 'Eschew Materials', '', 'Cast spells without material components');
INSERT INTO 'feat_text' VALUES (31, 0, 'Exotic Weapon Proficiency', 'Base attack bonus +1', 'No penalty on attack with specific exotic weapon');
INSERT INTO 'feat_text' VALUES (32, 0, 'Extend Spell', '', 'Double spell''s duration');
INSERT INTO 'feat_text' VALUES (33, 0, 'Extra Turning', 'Ability to turn or rebuke creatures', 'Can turn or rebuke 4 more times per day');
INSERT INTO 'feat_text' VALUES (34, 0, 'Far Shot', 'Point Blank Shot', 'Increase range increment by 50% or 100%');
INSERT INTO 'feat_text' VALUES (35, 0, 'Forge Ring', 'Caster level 12th', 'Create magic rings');
INSERT INTO 'feat_text' VALUES (36, 0, 'Great Cleave', 'Cleave, Power Attack, base attack bonus +4', 'No limit to cleave attacks each round');
INSERT INTO 'feat_text' VALUES (37, 0, 'Great Fortitude', '', '+2 bonus on Fortitude saves');
INSERT INTO 'feat_text' VALUES (38, 0, 'Greater Spell Focus', '', '+1 bonus on save DCs against specific school of magic');
INSERT INTO 'feat_text' VALUES (39, 0, 'Greater Spell Penetration', 'Spell Penetration', '+4 to caster level checks to defeat spell resistance');
INSERT INTO 'feat_text' VALUES (40, 0, 'Greater Two-Weapon Fighting', 'Dex 19, Improved Two-Weapon Fighting, Two-Weapon Fighting, base attack bonus +11', 'Gain third off-hand attack');
INSERT INTO 'feat_text' VALUES (41, 0, 'Greater Weapon Focus', 'Proficiency with weapon, Weapon Focus with weapon, fighter level 8th', '+2 bonus on attack rolls with selected weapon');
INSERT INTO 'feat_text' VALUES (42, 0, 'Greater Weapon Specialization', 'Proficiency with weapon, Greater Weapon Focus with weapon, Weapon Focus with weapon, Weapon Specialization with weapon, fighter level 12th', '+4 bonus on damage rolls with selected weapon');
INSERT INTO 'feat_text' VALUES (43, 0, 'Heighten Spell', '', 'Cast spells as higher level');
INSERT INTO 'feat_text' VALUES (44, 0, 'Improved Bull Rush', 'Power Attack', '+4 bonus on bull rush attempts, no attack of oppertunity');
INSERT INTO 'feat_text' VALUES (45, 0, 'Improved Counterspell', '', 'Counterspell with spell of same school');
INSERT INTO 'feat_text' VALUES (46, 0, 'Improved Critical', 'Proficient with weapon, base attack bonus +8', 'Double threat range of weapon');
INSERT INTO 'feat_text' VALUES (47, 0, 'Improved Disarm', 'Combat Expertise', '+4 bonus on disarm attemps, no attack of oppertunity');
INSERT INTO 'feat_text' VALUES (48, 0, 'Improved Feint', 'Combat Expertise', 'Feint in combat as move action');
INSERT INTO 'feat_text' VALUES (49, 0, 'Improved Grapple', 'Dex 13, Improved Unarmed Strike', '+4 bonus on grapple checks, no attack of oppertunity');
INSERT INTO 'feat_text' VALUES (50, 0, 'Improved Initiative', '', '+4 bonus on initative checks');
INSERT INTO 'feat_text' VALUES (51, 0, 'Improved Overrun', 'Power Attack', '+4 bonus on overrun attempts, no attack of opperunity');
INSERT INTO 'feat_text' VALUES (52, 0, 'Improved Precise Shot', 'Dex 19, Point Blank Shot, Precise Shot, base attack bonus +11', 'Ignore less than total cover/concealment on ranged attacks');
INSERT INTO 'feat_text' VALUES (53, 0, 'Improved Shield Bash', 'Shield Proficiency', 'Retain shild bonus to AC when shield bashing');
INSERT INTO 'feat_text' VALUES (54, 0, 'Improved Sunder', 'Power Attack', '+4 bonus on sunder attempts, no attack of oppertunity');
INSERT INTO 'feat_text' VALUES (55, 0, 'Improved Trip', 'Combat Expertise', '+4 bonus on trip attempts, no attack of oppertunity');
INSERT INTO 'feat_text' VALUES (56, 0, 'Improved Turning', 'Ability to turn or rebuke creatures', '+1 level for turning checks');
INSERT INTO 'feat_text' VALUES (57, 0, 'Improved Two-Weapon Fighting', 'Dex 17, Two-Weapon Fighting, base attack bonus +6', 'Gain second off-hand attack');
INSERT INTO 'feat_text' VALUES (58, 0, 'Improved Unarmed Strike', '', 'Considered armed even when unarmed');
INSERT INTO 'feat_text' VALUES (59, 0, 'Investigator', '', '+2 bonus on Gather Information and Search checks');
INSERT INTO 'feat_text' VALUES (60, 0, 'Iron Will', '', '+2 bonus on Will saves');
INSERT INTO 'feat_text' VALUES (61, 0, 'Leadership', 'Character level 6th', 'Attract cohort and followers');
INSERT INTO 'feat_text' VALUES (62, 0, 'Lightning Reflexes', '', '+2 on Reflex saves');
INSERT INTO 'feat_text' VALUES (63, 0, 'Magical Aptitude', '', '+2 bonus on Spellcraft and Use Magic Device checks');
INSERT INTO 'feat_text' VALUES (64, 0, 'Manyshot', 'Dex 17, Point Blank Shot, Rapid Shot, base attack bonus +6', 'Shoot two or more arrows simultaneously');
INSERT INTO 'feat_text' VALUES (65, 0, 'Martial Weapon Proficiency', '', 'No penalty on attacks with specific martial weapons');
INSERT INTO 'feat_text' VALUES (66, 0, 'Maximize Spell', '', 'Maximize spell''s variable, nummeric effects');
INSERT INTO 'feat_text' VALUES (67, 0, 'Mobility', 'Dodge', '+4 dodge bonus to AC against some attacks of opportunity');
INSERT INTO 'feat_text' VALUES (68, 0, 'Mounted Archery', 'Mounted Combat', 'Half penalty for ranged attacks while mounted');
INSERT INTO 'feat_text' VALUES (69, 0, 'Mounted Combat', 'Ride 1 rank', 'Negate hits on mount with Ride check');
INSERT INTO 'feat_text' VALUES (70, 0, 'Natural Spell', 'Wis 13, Ability to use wild shape', 'Cast spells while in wild shape');
INSERT INTO 'feat_text' VALUES (71, 0, 'Negotiator', '', '+2 bonus on Diplomacy and Sense Motive checks');
INSERT INTO 'feat_text' VALUES (72, 0, 'Nimble Fingers', '', '+2 bonus on Disable Device and Open Lock checks');
INSERT INTO 'feat_text' VALUES (73, 0, 'Persuasive', '', '+2 bonus on Bluff checks and Intimidate checks');
INSERT INTO 'feat_text' VALUES (74, 0, 'Point Blank Shot', '', '+1 bonus on ranged attack and damage within 30 ft.');
INSERT INTO 'feat_text' VALUES (75, 0, 'Power Attack', 'Str 13', 'Trade attack bonus for damage (up to base attack bonus)');
INSERT INTO 'feat_text' VALUES (76, 0, 'Precise Shot', 'Point Blank Shot', 'No -4 penalty for shooting into melee');
INSERT INTO 'feat_text' VALUES (77, 0, 'Quick Draw', 'Base attack bonus +1', 'Draw weapon as free action');
INSERT INTO 'feat_text' VALUES (78, 0, 'Quicken Spell', '', 'Cast spells as free action');
INSERT INTO 'feat_text' VALUES (79, 0, 'Rapid Reload', 'Weapon Proficiency with crossbow', 'Reload crossbow more quickly');
INSERT INTO 'feat_text' VALUES (80, 0, 'Rapid Shot', 'Dex 13, Point Blank Shot', 'One extra ranged attack each round');
INSERT INTO 'feat_text' VALUES (81, 0, 'Ride-By Attack', 'Mounted Combat', 'Move before and after mounted charge');
INSERT INTO 'feat_text' VALUES (82, 0, 'Run', '', 'Run at 4 times normal speed, +4 bonus on Jump checks made after running start');
INSERT INTO 'feat_text' VALUES (83, 0, 'Scribe Scroll', 'Caster level 1st', 'Create magic scrolls');
INSERT INTO 'feat_text' VALUES (84, 0, 'Self-Sufficient', '', '+2 bonus on Heal and Survival checks');
INSERT INTO 'feat_text' VALUES (85, 0, 'Shield Proficiency', '', 'No armor check penalty on attack rolls');
INSERT INTO 'feat_text' VALUES (86, 0, 'Shot On The Run', 'Dex 13, Dodge, Mobility, Point Blank Shot, base attack bonus +4', 'Move before and after ranged attack');
INSERT INTO 'feat_text' VALUES (87, 0, 'Silent Spell', '', 'Cast spells without verbal components');
INSERT INTO 'feat_text' VALUES (88, 0, 'Simple Weapon Proficiency', '', 'No -4 penalty on attack rolls with simple weapons');
INSERT INTO 'feat_text' VALUES (89, 0, 'Skill Focus', '', '+3 bonus on checks with selected skill');
INSERT INTO 'feat_text' VALUES (90, 0, 'Snatch Arrows', 'Dex 15, Deflect Arrows, Improved Unarmed Strike', 'Catch a deflected ranged attack');
INSERT INTO 'feat_text' VALUES (91, 0, 'Spell Focus', '', '+1 bonus on save DCs against specific school of magic');
INSERT INTO 'feat_text' VALUES (92, 0, 'Spell Mastery', 'Wizard level 1st', 'Can prepare some spells without spellbook');
INSERT INTO 'feat_text' VALUES (93, 0, 'Spell Penetration', '', '+2 bonus on caster level ckecks to defeat spell resistance');
INSERT INTO 'feat_text' VALUES (94, 0, 'Spirited Charge', 'Mounted Combat, Ride-By Attack.', 'Double damage with mounted charge');
INSERT INTO 'feat_text' VALUES (95, 0, 'Spring Attack', 'Mobility, base attack bonus +4', 'Move before and after melee attack');
INSERT INTO 'feat_text' VALUES (96, 0, 'Stealthy', '', '+2 bonus on Hide and Move Silently checks');
INSERT INTO 'feat_text' VALUES (97, 0, 'Still Spell', '', 'Cast spells without somatic components');
INSERT INTO 'feat_text' VALUES (98, 0, 'Stunning Fist', 'Dex 13, Wis 13, Improved Unarmed Strike, base attack bonus +8', 'Stun opponent with unarmed strike');
INSERT INTO 'feat_text' VALUES (99, 0, 'Toughness', '', '+3 hit points');
INSERT INTO 'feat_text' VALUES (100, 0, 'Tower Shield Proficiency', 'Shield Proficiency', 'No armor check penalty on attack rolls');
INSERT INTO 'feat_text' VALUES (101, 0, 'Track', '', '');
INSERT INTO 'feat_text' VALUES (102, 0, 'Trample', 'Mounted Combat', 'Targets cannot avoid mounted overrun');
INSERT INTO 'feat_text' VALUES (103, 0, 'Two-Weapon Defense', 'Two-Weapon Fighting', 'Off-hand weapon grants +1 shield bonus to AC');
INSERT INTO 'feat_text' VALUES (104, 0, 'Two-Weapon Fighting', 'Dex 15', 'Reduce two-weapon fighting penealties by 2');
INSERT INTO 'feat_text' VALUES (105, 0, 'Weapon Finesse', 'base attack bonus +1', 'Use Dex modifier insteat of Str modifier on attack rolls with light melee weapons');
INSERT INTO 'feat_text' VALUES (106, 0, 'Weapon Focus', 'Proficiency with weapon, base attack bonus +1', '+1 bonus on attack rolls with selected weapon');
INSERT INTO 'feat_text' VALUES (107, 0, 'Weapon Specialization', 'Proficiency with weapon, Weapon Focus with weapon, fighter level 4th', '+2 bonus on damage rolls with selected weapon');
INSERT INTO 'feat_text' VALUES (108, 0, 'Whirlwind Attack', 'Dex 13, Int 13, Combat Expertise, Dodge, Mobility, Spring Attack, base attack bonus +4', 'One melee attack against each opponent within reach');
INSERT INTO 'feat_text' VALUES (109, 0, 'Widen Spell', '', 'Double spell''s area');

INSERT INTO 'feat_text' VALUES (1, 1, 'Akrobat', '', 'Bonus von +2 auf Wuerfe fuer Springen und Turnen');
INSERT INTO 'feat_text' VALUES (2, 1, 'Agil', '', 'Bonus von +2 auf Wuerfe fuer Balancieren und Entfesselungskunst');
INSERT INTO 'feat_text' VALUES (3, 1, 'Wachsamkeit', '', 'Bonus von +2 auf Wuerfe fuer Entdecken und Lauschen');
INSERT INTO 'feat_text' VALUES (4, 1, 'Verbundenheit mit Tieren', '', 'Bonus von +2 auf Wuerfe fuer Mit Tieren umgehen und Reiten');
INSERT INTO 'feat_text' VALUES (5, 1, 'Umgang mit Ruestungen (schwere)', 'Umgang mit Ruestungen (mittelschwere)', 'Kein Ruestungsmalus auf Angriffswuerfe');
INSERT INTO 'feat_text' VALUES (6, 1, 'Umgang mit Ruestungen (leichte)', '', 'Kein Ruestungsmalus auf Angriffswuerfe');
INSERT INTO 'feat_text' VALUES (7, 1, 'Umgang mit Ruestungen (mittelschwere)', 'Umgang mit Ruestungen (leichte)', 'Kein Ruestungsmalus auf Angriffswuerfe');
INSERT INTO 'feat_text' VALUES (8, 1, 'Athlet', '', 'Bonus von +2 auf Wuerfe fuer Klettern und Schwimmen');
INSERT INTO 'feat_text' VALUES (9, 1, 'Verstaerkte Herbeizauberung', 'Zauberfokus (Beschwoerung)', 'Herbeigezauberte Kreaturen erhalten einen Bonus von +4 auf ST und KO');
INSERT INTO 'feat_text' VALUES (10, 1, 'Blind kaempfen', '', 'Prozentwurf fuer Fehlschlag aufgrund Tarnung darf erneut gewuerfelt werden');
INSERT INTO 'feat_text' VALUES (11, 1, 'Trank brauen', 'Zauberstufe 3+', 'Zaubertraenke erschaffen');
INSERT INTO 'feat_text' VALUES (12, 1, 'Doppelschlag', 'Heftiger Angriff', 'Zusaetzlicher Nahkampfangriff, nachdem das ziel ausgeschaltet wurde');
INSERT INTO 'feat_text' VALUES (13, 1, 'Im Kampf zaubern', '', 'Bonus von +4 auf Wuerfe fuer Konzentration, um defensiv zu zaubern');
INSERT INTO 'feat_text' VALUES (14, 1, 'Defensive Kampfweise', 'IN 13', 'Angriffsbonus gegen Bonus auf RK eintauschen (maximal 5 Punkte)');
INSERT INTO 'feat_text' VALUES (15, 1, 'Kampfreflexe', '', 'Zusaetzliche Gelegenheitsangriffe');
INSERT INTO 'feat_text' VALUES (16, 1, 'Magische Waffen und Ruestungen herstellen', 'Zauberstufe 5+', 'Magische Waffen, Ruestungen und Schilde erschaffen');
INSERT INTO 'feat_text' VALUES (17, 1, 'Zauberzepter herstellen', 'Zauberstufe 9+', 'Zauberzepter erschaffen');
INSERT INTO 'feat_text' VALUES (18, 1, 'Zauberstecken herstellen', 'Zauberstufe 12+', 'Zauberstecken erschaffen');
INSERT INTO 'feat_text' VALUES (19, 1, 'Zauberstab herstellen', 'Zauberstufe 5+', 'Zauberstaebe erschaffen');
INSERT INTO 'feat_text' VALUES (20, 1, 'Wundersamen Gegenstand herstellen', 'Zauberstufe 3+', 'Magische wundersame Gegenstaende erschaffen');
INSERT INTO 'feat_text' VALUES (21, 1, 'Taeuscher', '', 'Bonus von +2 auf Wuerfe fuer Faelschen und Verkleiden');
INSERT INTO 'feat_text' VALUES (22, 1, 'Geschosse abwehren', 'GE 13, Verbesserter waffenloser Schlag', 'Einen Fernkampfangriff pro Runde abwehren');
INSERT INTO 'feat_text' VALUES (23, 1, 'Geschickte Haende', '', 'Bonus von +2 auf Wuerfe fuer Fingerfertigkeit und Seil benutzen');
INSERT INTO 'feat_text' VALUES (24, 1, 'Unverwuestlich ', 'Ausdauer', 'Im Bereich -1 bis -9 TP bei Bewusstsein bleiben');
INSERT INTO 'feat_text' VALUES (25, 1, 'Sorgfalt', '', 'Bonus von +2 auf Wuerfe fuer Schaetzen und Schriftzeichen entschluesseln');
INSERT INTO 'feat_text' VALUES (26, 1, 'Ausweichen', 'GE 13', 'Ausweichbonus von +1 gegen ausgewaehlten Gegner');
INSERT INTO 'feat_text' VALUES (27, 1, 'Zauber verstaerken', '', 'Alle variablen, numerischen Effekte von Zaubern um 50% erhoehen');
INSERT INTO 'feat_text' VALUES (28, 1, 'Ausdauer', '', 'Bonus von +4 auf Wuerfe oder Rettungswuerfe, um nichttoedlichen Schaden zu widerstehen');
INSERT INTO 'feat_text' VALUES (29, 1, 'Zauberreichweite erhoehen', '', 'Reichweite von Zaubern verdoppeln');
INSERT INTO 'feat_text' VALUES (30, 1, 'Materialkomponentenlos zaubern', '', 'Zaube ohne Materialkomponenten wirken');
INSERT INTO 'feat_text' VALUES (31, 1, 'Umgang mit exotischen Waffen', 'G.-AB +1 oder hoeher', 'Kein Angriffsmalus mit bestimmter exotischer Waffe');
INSERT INTO 'feat_text' VALUES (32, 1, 'Zauber ausdehnen', '', 'Die Wirkungsdauer von Zaubern verdoppeln');
INSERT INTO 'feat_text' VALUES (33, 1, 'Zusaetzliches Vertreiben', 'Faehigkeit, Kreaturen zu vertreiben oder zu beeindrucken', 'Pro Tag 4 weitere Male vertreiben oder beeindrucken');
INSERT INTO 'feat_text' VALUES (34, 1, 'Fernschuss', 'Kernschuss', 'Grundreichweite um 50% oder 100% erhoehen');
INSERT INTO 'feat_text' VALUES (35, 1, 'Ringe schmieden', 'Zauberstufe 12+', 'Magische Ringe erschaffen');
INSERT INTO 'feat_text' VALUES (36, 1, 'Rundumschlag', 'Doppelschlag, Heftiger Angriff, G.-AB +4 oder hoeher', 'Keine Begrenzung der Doppelschlaege pro Runde');
INSERT INTO 'feat_text' VALUES (37, 1, 'Grosse Zaehigkeit', '', 'Bonus von +2 auf Zaehigkeitswuerfe');
INSERT INTO 'feat_text' VALUES (38, 1, 'Maechtiger Zauberfokus', 'Zauberfokus bestimmte Schule der Magie', 'Bonus von +2 auf SG der Rettungswuerfe gegen eine bestimmte Schule der Magie');
INSERT INTO 'feat_text' VALUES (39, 1, 'Maechtige durchschlagende Zauber', 'Durchschlagende Zauber', 'Bonus von +4 auf Zauberstufenwuerfe, um Zauberresistenz zu ueberwinden');
INSERT INTO 'feat_text' VALUES (40, 1, 'Maechtiger Kampf mit zwei Waffen', 'GE 19, Kampf mit zwei Waffen, G.-AB +6 oder hoeher', 'Dritter Angriff mit Zweithand');
INSERT INTO 'feat_text' VALUES (41, 1, 'Maechtiger Waffenfokus', 'Umgang mit Waffe, Waffenfokus mit Waffe, Klassenstufe als Kaempfer 8. oder hoehere', 'Bonus von +1 auf Angriffswuerfe mit gewaehlter Waffe');
INSERT INTO 'feat_text' VALUES (42, 1, 'Maechtige Waffenspezialisierung', 'Umgang mit Waffe, Waffenfokus mit Waffe, Waffenspezialisierung mit Waffe, Klassenstufe als Kaempfer 12. oder hoehere', 'Bonus von +4 auf Schadenswuerfe mit gewaehlter Waffe');
INSERT INTO 'feat_text' VALUES (43, 1, 'Zaubergrad erhoehen', '', 'Zauber als Zauber hoeheren Grades wirken');
INSERT INTO 'feat_text' VALUES (44, 1, 'Verbesserter Ansturm', 'Heftiger Angriff', 'Bonus von +4 auf Ansturmversuche, keine Gelegenheitsangriffe');
INSERT INTO 'feat_text' VALUES (45, 1, 'Verbesserter Gegenzauber', '', 'Gegenzauber mit einem Zauber aus derselben Schule der Magie');
INSERT INTO 'feat_text' VALUES (46, 1, 'Verbesserter kritischer Treffer', 'Umgang mit Waffe, G.-AB +8 oder hoeher', 'Bedrohungschance der Waffe verdoppeln');
INSERT INTO 'feat_text' VALUES (47, 1, 'Verbessertes Entwaffnen', 'Defensive Kampfweise', 'Bonus von +4 auf Versuche, den Gegner zu entwaffnen, keine Gelegenkeitsangriffe');
INSERT INTO 'feat_text' VALUES (48, 1, 'Verbesserte Finte', 'Defensive Kampfweise', 'Finten im Kampf als Bewegungs-Aktionen machen');
INSERT INTO 'feat_text' VALUES (49, 1, 'Verbesserter Ringkampf', 'GE 13, Verbesserter waffenloser Schlag', 'Bonus von +4 auf Ringkampfwuerfe, keine Gelegenheitsangriffe');
INSERT INTO 'feat_text' VALUES (50, 1, 'Verbesserte Initiative', '', 'Bonus von +4 auf Initiativewuerfe');
INSERT INTO 'feat_text' VALUES (51, 1, 'Verbessertes ueberrennen', 'Heftiger Angriff', 'Bonus von +4 auf Versuche zum ueberrennen');
INSERT INTO 'feat_text' VALUES (52, 1, 'Verbesserter Praezisionsschuss', 'GE 19, Kernschuss, Praezisiionsschuss', 'Weniger als 100% Deckung/100% Tarnung bei Fernkampfangriffen ignorieren');
INSERT INTO 'feat_text' VALUES (53, 1, 'Verbesserter Schildstoss', 'Umgang mit Schilden', 'Schildbonus auf die RK bei einem Schildstoss behalten');
INSERT INTO 'feat_text' VALUES (54, 1, 'Verbessertes Waffe zerschmettern', 'Heftiger Angriff', 'Bonus von +4 auf Versuche, die Waffe des Gegners zu zerschmettern, keine Gelegenheitsangriffe');
INSERT INTO 'feat_text' VALUES (55, 1, 'Verbessertes "Zu Fall bringen"', 'Defensive Kampfweise', 'Bonus von +4 auf Versuche, den Gegner zu Fall zu bringen, keine Gelegenheitsangriffe');
INSERT INTO 'feat_text' VALUES (56, 1, 'Verbessertes Vertreiben', 'Faehigkeit, Kreaturen zu vertreiben oder zu beeindrucken', 'Bonus von +1 auf Vertreibungswuerfe');
INSERT INTO 'feat_text' VALUES (57, 1, 'Verbesserter Kampf mit zwei Waffen', 'GE 17, Kampf mit zwei Waffen, G.-AB +6 oder hoeher', 'Zweiter Angriff mit Zweithand');
INSERT INTO 'feat_text' VALUES (58, 1, 'Verbesserter waffenloser Schlag', '', 'Unbewaffnet als bewaffnet gelten');
INSERT INTO 'feat_text' VALUES (59, 1, 'Spuernase', '', 'Bonus von +2 auf Wuerfe fuer Informationen sammeln und Suchen');
INSERT INTO 'feat_text' VALUES (60, 1, 'Eiserner Wille', '', 'Bonus von +2 auf Willenswuerfe');
INSERT INTO 'feat_text' VALUES (61, 1, 'Anfuehren', '6. Charakterstufe oder eine hoehere', 'Gefolgsleute und Anhaenger anziehen');
INSERT INTO 'feat_text' VALUES (62, 1, 'Blitzschnelle Reflexe', '', 'Bonus von +2 auf Reflexwuerfe');
INSERT INTO 'feat_text' VALUES (63, 1, 'Neigung zur Magie', '', 'Bonus von +2 auf Wuerfe fuer Magischen Gegenstand bentzen und Zauberkunde');
INSERT INTO 'feat_text' VALUES (64, 1, 'Mehrfachschuss', 'GE 17, Kernschuss, Schnelles Schiessen, G.AB +6 oder hoeher', 'Zwei oder mehr Pfeile gleichzeitig verschiessen');
INSERT INTO 'feat_text' VALUES (65, 1, 'Umgang mit Kriegswaffen', '', 'Kein Angriffsmalus mit bestimmter Kriegswaffe');
INSERT INTO 'feat_text' VALUES (66, 1, 'Zaubeeffekt maximieren', '', 'Alle variablen, numerischen Effekte von Zaubern maximieren');
INSERT INTO 'feat_text' VALUES (67, 1, 'Beweglichkeit', 'Ausweichen', 'Ausweichbonus von +4 gegen bestimmte Gelegenheitsangriffe');
INSERT INTO 'feat_text' VALUES (68, 1, 'Berittener Fernkampf', 'Berittener Kampf', 'Beim Fernkampfangriffen waehrend des Reitens nur halber Malus');
INSERT INTO 'feat_text' VALUES (69, 1, 'Berittener Kampf', '1+ Raenge in Reiten', 'Treffer am Reittier durch Wuerfe fuer Reiten verhindern');
INSERT INTO 'feat_text' VALUES (70, 1, 'In Tiergestalt zaubern', 'WE 13, Faehigkeit zur Tiergestalt', 'In der Tiergestalt Zauber wirken');
INSERT INTO 'feat_text' VALUES (71, 1, 'Unterhaendler', '', 'Bonus von +2 auf Wuerfe fuer Diplomatie und Motiv erkennen');
INSERT INTO 'feat_text' VALUES (72, 1, 'Flinke Finger', '', 'Bonus von +2 auf Wuerfe fuer Mechanismus ausschalten und Schloesser oeffnen');
INSERT INTO 'feat_text' VALUES (73, 1, 'Beredsamkeit', '', 'Bonus von +2 auf Wuerfe fuer Bluffen und Einschuechtern');
INSERT INTO 'feat_text' VALUES (74, 1, 'Kernschuss', '', 'Bonus von +1 auf Angriffs- und Schadenswuerfe im Fernkampf innerhalb von 9m');
INSERT INTO 'feat_text' VALUES (75, 1, 'Heftiger Angriff', 'ST 13', 'Angriffsbonus fuer zusaetzlichen Schaden eintauschen (bis maximal G.-AB)');
INSERT INTO 'feat_text' VALUES (76, 1, 'Praezisionsschuss', 'Kernschuss', 'Kein Malus von -4, wenn in den Nahkampf geschossen wird');
INSERT INTO 'feat_text' VALUES (77, 1, 'Schnelle Waffenbereitschaft', 'G.-AB +1', 'Eine Waffe als Freie Aktion ziehen');
INSERT INTO 'feat_text' VALUES (78, 1, 'Schnell zauben', '', 'Zauber als Freie Aktion wirken');
INSERT INTO 'feat_text' VALUES (79, 1, 'Schnelles Nachladen', 'Umgang mit Armbruesten', 'Armbrust schneller nachladen');
INSERT INTO 'feat_text' VALUES (80, 1, 'Schnelles Schiessen', 'GE 13, Kernschuss', 'Ein zusaetzlicher Fernkampfangriff pro Runde');
INSERT INTO 'feat_text' VALUES (81, 1, 'Angriff im Vorbeireiten', 'Berittener Kampf', 'Sich vor und nach einem berittenen Sturmangriff bewegen');
INSERT INTO 'feat_text' VALUES (82, 1, 'Rennen', '', 'Mit fuenffacher Bewegungsrate Rennen, Bonus von +4 auf Wuerfe fuer Springen, die mit einem Anlauf durchgefuehrt werden.');
INSERT INTO 'feat_text' VALUES (83, 1, 'Schriftrolle anfertigen', 'Zauberstufe 1+', 'Magische Schriftrollen erschaffen');
INSERT INTO 'feat_text' VALUES (84, 1, 'Selbsterhaltung', '', 'Bonus von +2 auf Wuerfe fuer Heilkunde und ueberlebenskunst');
INSERT INTO 'feat_text' VALUES (85, 1, 'Umgang mit Schilden', '', 'Kein Ruestungsmalus auf Angriffswuerfe');
INSERT INTO 'feat_text' VALUES (86, 1, 'Aus vollem Lauf schiessen', 'GE 13, Ausweichen, Beweglichkeit, Kernschuss, G.-AB +4 oder hoeher', 'Sich vor und nach einem Fernkampfangriff bewegen');
INSERT INTO 'feat_text' VALUES (87, 1, 'Lautlos zaubern', '', 'Zauber ohne verbale Komponente wirken');
INSERT INTO 'feat_text' VALUES (88, 1, 'Umgang mit einfachen Waffen', '', 'Kein Malus von -4 auf Angriffswuerfe mit einfachen Waffen');
INSERT INTO 'feat_text' VALUES (89, 1, 'Fertigkeitsfokus', '', 'Bonus von +3 auf Wuerfe fuer gewaehlte Fertigkeit');
INSERT INTO 'feat_text' VALUES (90, 1, 'Geschosse fangen', 'GE 15, Geschosse abwehren, Verbesserter waffenloser Schlag', 'Einen abgewehrten Fernkampfangriff auffangen');
INSERT INTO 'feat_text' VALUES (91, 1, 'Zauberfokus', '', 'Bonus von +1 auf SG der Rettungswuerfe gegen eine bestimmte Schule der Magie');
INSERT INTO 'feat_text' VALUES (92, 1, 'Zaubermeisterschaft', 'Zauberfokus bestimmte Schule der Magie', 'Bonus von +2 auf SG der Rettungswuerfe gegen eine bestimmte Schule der Magie');
INSERT INTO 'feat_text' VALUES (93, 1, 'Durchschlagende Zauber', '', 'Bonus von +2 auf Zauberstufenwuerfe, um Zauberresistenz zu ueberwinden');
INSERT INTO 'feat_text' VALUES (94, 1, 'Beherzter Sturmangriff', 'Berittener Kampf, Angriff im Vorbeireiten', 'Doppelter Schaden bei berittenem Sturmangriff');
INSERT INTO 'feat_text' VALUES (95, 1, 'Taenzelnder Angriff', 'Beweglichkeit, G.-AB +4 oder hoeher', 'Sich vor und nach einem Nahkampfangriff bewegen');
INSERT INTO 'feat_text' VALUES (96, 1, 'Verstohlenheit', '', 'Bonus von +2 auf Wuerfe fuer Leise bewegen und Verstecken');
INSERT INTO 'feat_text' VALUES (97, 1, 'Gestenlos zaubern', '', 'Zauber ohne Gestenkomponente wirken');
INSERT INTO 'feat_text' VALUES (98, 1, 'Betaeubender Schlag', 'GE 13, WE 13, Verbesserter waffenloser Schlag, G.-AB +8 oder hoeher', 'Einen Gegener durch einen Waffenlosen Schlag betaeuben');
INSERT INTO 'feat_text' VALUES (99, 1, 'Abhaertung', '', '+3 Trefferpunkte');
INSERT INTO 'feat_text' VALUES (100, 1, 'Umgang mit Turmschilden', 'Umgang mit Schilden', 'Kein Ruestungsmalus auf Angriffswuerfe');
INSERT INTO 'feat_text' VALUES (101, 1, 'Spuren lesen', '', 'Die Fertigkeit ueberlebenskunst einsetzen koennen, um Spuren zu folgen');
INSERT INTO 'feat_text' VALUES (102, 1, 'Niederreiten', 'Berittener Kampf', 'Ziel kann berittenem ueberrennen nicht ausweichen');
INSERT INTO 'feat_text' VALUES (103, 1, 'Verteidigung mit zwei Waffen', 'Kampf mit zwei Waffen', 'Zweithand verleiht einen Schildbonus von +1 auf RK');
INSERT INTO 'feat_text' VALUES (104, 1, 'Kampf mit zwei Waffen', 'GE 15', 'Die Mali fuer den Kampf mit zwei Waffen um -2 reduzieren');
INSERT INTO 'feat_text' VALUES (105, 1, 'Waffenfinesse', 'G.-AB +1 oder hoeher', 'Auf Angriffswuerfe mit leichten Nahkampfwaffen den GE- statt den ST-Modifikator anrechnen');
INSERT INTO 'feat_text' VALUES (106, 1, 'Waffenfokus', 'Umgang mit Waffe, G.-AB +1 oder hoeher', 'Bonus von +1 auf Angriffswuerfe mit gewaehlter Waffe');
INSERT INTO 'feat_text' VALUES (107, 1, 'Waffenspezialisierung', 'Umgang mit Waffe, Waffenfokus mit Waffe, Klassenstufe als Kaempfer 4. oder hoehere', 'Bonus von +2 auf Schadenswuerfe mit gewaehlter Waffe');
INSERT INTO 'feat_text' VALUES (108, 1, 'Wirbelwindangriff', 'GE 13, Ausweichen, Beweglichkeit, Defensive Kampfweise, Taenzelnder Angriff, G.-AB +4 oder hoeher', 'Einen Nahkampfangriff gegen jeden Gegner innerhalt von 1,50m ausfuehren');
INSERT INTO 'feat_text' VALUES (109, 1, 'Zauberbereich erweitern', '', 'Wirkungsbereich von Zaubern verdoppeln');

CREATE TABLE feat
(id       INTEGER,
 feat_type_id   INTEGER not null,
 fighter        INTEGER,
 multiple       INTEGER,
 stack          INTEGER,
 PRIMARY KEY(id),
 FOREIGN KEY (feat_type_id) REFERENCES feat_type(id) ON DELETE CASCADE
);

INSERT INTO 'feat' VALUES (1, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (2, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (3, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (4, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (5, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (6, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (7, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (8, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (9, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (10, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (11, 1, 0, 0, 0);
INSERT INTO 'feat' VALUES (12, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (13, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (14, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (15, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (16, 1, 0, 0, 0);
INSERT INTO 'feat' VALUES (17, 1, 0, 0, 0);
INSERT INTO 'feat' VALUES (18, 1, 0, 0, 0);
INSERT INTO 'feat' VALUES (19, 1, 0, 0, 0);
INSERT INTO 'feat' VALUES (20, 1, 0, 0, 0);
INSERT INTO 'feat' VALUES (21, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (22, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (23, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (24, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (25, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (26, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (27, 2, 0, 0, 0);
INSERT INTO 'feat' VALUES (28, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (29, 2, 0, 0, 0);
INSERT INTO 'feat' VALUES (30, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (31, 0, 1, 1, 0);
INSERT INTO 'feat' VALUES (32, 2, 0, 0, 0);
INSERT INTO 'feat' VALUES (33, 0, 0, 0, 1);
INSERT INTO 'feat' VALUES (34, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (35, 1, 0, 0, 0);
INSERT INTO 'feat' VALUES (36, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (37, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (38, 0, 0, 1, 0);
INSERT INTO 'feat' VALUES (39, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (40, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (41, 0, 1, 1, 0);
INSERT INTO 'feat' VALUES (42, 0, 1, 1, 0);
INSERT INTO 'feat' VALUES (43, 2, 0, 0, 0);
INSERT INTO 'feat' VALUES (44, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (45, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (46, 0, 1, 1, 0);
INSERT INTO 'feat' VALUES (47, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (48, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (49, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (50, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (51, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (52, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (53, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (54, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (55, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (56, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (57, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (58, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (59, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (60, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (61, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (62, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (63, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (64, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (65, 0, 0, 1, 0);
INSERT INTO 'feat' VALUES (66, 2, 0, 0, 0);
INSERT INTO 'feat' VALUES (67, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (68, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (69, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (70, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (71, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (72, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (73, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (74, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (75, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (76, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (77, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (78, 2, 0, 0, 0);
INSERT INTO 'feat' VALUES (79, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (80, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (81, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (82, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (83, 1, 0, 0, 0);
INSERT INTO 'feat' VALUES (84, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (85, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (86, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (87, 2, 0, 0, 0);
INSERT INTO 'feat' VALUES (88, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (89, 0, 0, 1, 0);
INSERT INTO 'feat' VALUES (90, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (91, 0, 0, 1, 0);
INSERT INTO 'feat' VALUES (92, 0, 0, 1, 0);
INSERT INTO 'feat' VALUES (93, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (94, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (95, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (96, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (97, 2, 0, 0, 0);
INSERT INTO 'feat' VALUES (98, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (99, 0, 0, 0, 1);
INSERT INTO 'feat' VALUES (100, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (101, 0, 0, 0, 0);
INSERT INTO 'feat' VALUES (102, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (103, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (104, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (105, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (106, 0, 1, 1, 0);
INSERT INTO 'feat' VALUES (107, 0, 1, 1, 0);
INSERT INTO 'feat' VALUES (108, 0, 1, 0, 0);
INSERT INTO 'feat' VALUES (109, 2, 0, 0, 0);

CREATE TABLE charakter_feat
(id                INTEGER,
 feat_id           INTEGER,
 charakter_id      INTEGER,
 category          VARCHAR(150),
 PRIMARY KEY(id),
 FOREIGN KEY (feat_id) REFERENCES feat(id),
 FOREIGN KEY (charakter_id) REFERENCES charakter(id)
);

INSERT INTO 'charakter_feat' VALUES (0, 9, 0, '');
INSERT INTO 'charakter_feat' VALUES (1, 91, 0, 'Beschwoerung');
INSERT INTO 'charakter_feat' VALUES (2, 32, 0, '');

INSERT INTO 'charakter_feat' VALUES (3, 104, 1, '');
INSERT INTO 'charakter_feat' VALUES (4, 103, 1, '');
INSERT INTO 'charakter_feat' VALUES (5, 26, 1, '');
INSERT INTO 'charakter_feat' VALUES (6, 105, 1, '');
INSERT INTO 'charakter_feat' VALUES (7, 101, 1, '');
INSERT INTO 'charakter_feat' VALUES (8, 28, 1, '');

