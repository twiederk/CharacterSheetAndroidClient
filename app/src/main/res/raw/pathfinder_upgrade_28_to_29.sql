CREATE TABLE combat_type
(id       INTEGER,
 name     VARCHAR(16) NOT NULL,
 PRIMARY KEY (id)
);
INSERT INTO combat_type VALUES (0, 'Melee Weapon');
INSERT INTO combat_type VALUES (1, 'Ranged Weapon');


CREATE TABLE weapon_encumbrance
(id       INTEGER,
 name     VARCHAR(32) NOT NULL,
 PRIMARY KEY (id)
);
INSERT INTO weapon_encumbrance VALUES (0, 'Light Melee Weapon');
INSERT INTO weapon_encumbrance VALUES (1, 'One-Handed Melee Weapon');
INSERT INTO weapon_encumbrance VALUES (2, 'Two-Handed Melee Weapon');
INSERT INTO weapon_encumbrance VALUES (3, 'Light Ranged Weapon');
INSERT INTO weapon_encumbrance VALUES (4, 'One-Handed Ranged Weapon');
INSERT INTO weapon_encumbrance VALUES (5, 'Two-Handed Ranged Weapon');


CREATE TABLE weapon_category
(id       INTEGER,
 name     VARCHAR(32) NOT NULL,
 PRIMARY KEY (id)
);
INSERT INTO weapon_category VALUES (0, 'Normal Weapon');
INSERT INTO weapon_category VALUES (1, 'Reach Weapon');
INSERT INTO weapon_category VALUES (2, 'Double Weapon');
INSERT INTO weapon_category VALUES (3, 'Thrown Weapon');
INSERT INTO weapon_category VALUES (4, 'Projectile Weapon');
INSERT INTO weapon_category VALUES (5, 'Ammunition');


CREATE TABLE weapon_family
(id       INTEGER,
 name     VARCHAR(32) NOT NULL,
 PRIMARY KEY (id)
);
INSERT INTO weapon_family VALUES (1, 'Unarmed strike' );
INSERT INTO weapon_family VALUES (2, 'Dagger' );
INSERT INTO weapon_family VALUES (3, 'Mace, light' );
INSERT INTO weapon_family VALUES (4, 'Sickle' );
INSERT INTO weapon_family VALUES (5, 'Club' );
INSERT INTO weapon_family VALUES (6, 'Mace, heavy' );
INSERT INTO weapon_family VALUES (7, 'Morningstar' );
INSERT INTO weapon_family VALUES (8, 'Shortspear' );
INSERT INTO weapon_family VALUES (9, 'Longspear' );
INSERT INTO weapon_family VALUES (10, 'Quarterstaff' );
INSERT INTO weapon_family VALUES (11, 'Spear' );
INSERT INTO weapon_family VALUES (12, 'Blowgun' );
INSERT INTO weapon_family VALUES (13, 'Crossbow, heavy' );
INSERT INTO weapon_family VALUES (14, 'Crossbow, light' );
INSERT INTO weapon_family VALUES (15, 'Dart' );
INSERT INTO weapon_family VALUES (16, 'Javelin' );
INSERT INTO weapon_family VALUES (17, 'Sling' );
INSERT INTO weapon_family VALUES (18, 'Axe, throwing' );
INSERT INTO weapon_family VALUES (19, 'Hammer, light' );
INSERT INTO weapon_family VALUES (20, 'Handaxe' );
INSERT INTO weapon_family VALUES (21, 'Kukri' );
INSERT INTO weapon_family VALUES (22, 'Pick, light' );
INSERT INTO weapon_family VALUES (23, 'Sap' );
INSERT INTO weapon_family VALUES (24, 'Starknife' );
INSERT INTO weapon_family VALUES (25, 'Sword, short' );
INSERT INTO weapon_family VALUES (26, 'Battleaxe' );
INSERT INTO weapon_family VALUES (27, 'Flail' );
INSERT INTO weapon_family VALUES (28, 'Longsword' );
INSERT INTO weapon_family VALUES (29, 'Pick, heavy' );
INSERT INTO weapon_family VALUES (30, 'Rapier' );
INSERT INTO weapon_family VALUES (31, 'Scimitar' );
INSERT INTO weapon_family VALUES (32, 'Trident' );
INSERT INTO weapon_family VALUES (33, 'Warhammer' );
INSERT INTO weapon_family VALUES (34, 'Falchion' );
INSERT INTO weapon_family VALUES (35, 'Glaive' );
INSERT INTO weapon_family VALUES (36, 'Greataxe' );
INSERT INTO weapon_family VALUES (37, 'Greatclub' );
INSERT INTO weapon_family VALUES (38, 'Flail, heavy' );
INSERT INTO weapon_family VALUES (39, 'Greatsword' );
INSERT INTO weapon_family VALUES (40, 'Guisarme' );
INSERT INTO weapon_family VALUES (41, 'Halberd' );
INSERT INTO weapon_family VALUES (42, 'Lance' );
INSERT INTO weapon_family VALUES (43, 'Ranseur' );
INSERT INTO weapon_family VALUES (44, 'Scythe' );
INSERT INTO weapon_family VALUES (45, 'Longbow' );
INSERT INTO weapon_family VALUES (46, 'Longbow, composite' );
INSERT INTO weapon_family VALUES (47, 'Shortbow' );
INSERT INTO weapon_family VALUES (48, 'Shortbow, composite' );
INSERT INTO weapon_family VALUES (49, 'Kama' );
INSERT INTO weapon_family VALUES (50, 'Nunchaku' );
INSERT INTO weapon_family VALUES (51, 'Sai' );
INSERT INTO weapon_family VALUES (52, 'Siangham' );
INSERT INTO weapon_family VALUES (53, 'Sword, bastard' );
INSERT INTO weapon_family VALUES (54, 'Waraxe, dwarven' );
INSERT INTO weapon_family VALUES (55, 'Whip' );
INSERT INTO weapon_family VALUES (56, 'Axe, orc double' );
INSERT INTO weapon_family VALUES (57, 'Chain, spiked' );
INSERT INTO weapon_family VALUES (58, 'Curve blade, elven' );
INSERT INTO weapon_family VALUES (59, 'Flail, dire' );
INSERT INTO weapon_family VALUES (60, 'Hammer, gnome hooked' );
INSERT INTO weapon_family VALUES (61, 'Sword, two-bladed' );
INSERT INTO weapon_family VALUES (62, 'Urgrosh, dwarven' );
INSERT INTO weapon_family VALUES (63, 'Bolas' );
INSERT INTO weapon_family VALUES (64, 'Crossbow, hand' );
INSERT INTO weapon_family VALUES (65, 'Crossbow, repeating heavy' );
INSERT INTO weapon_family VALUES (66, 'Crossbow, repeating light' );
INSERT INTO weapon_family VALUES (67, 'Net' );
INSERT INTO weapon_family VALUES (68, 'Shuriken' );
INSERT INTO weapon_family VALUES (69, 'Sling staff, halfling' );
INSERT INTO weapon_family VALUES (70, 'Ammunition' );
INSERT INTO weapon_family VALUES (71, 'Grapple' );
INSERT INTO weapon_family VALUES (72, 'Ray' );
INSERT INTO weapon_family VALUES (73, 'Shield, light' );
INSERT INTO weapon_family VALUES (74, 'Shield, heavy' );
INSERT INTO weapon_family VALUES (75, 'Spiked armor' );


CREATE TABLE attack_wield
(id       INTEGER,
 name     VARCHAR(32) NOT NULL,
 PRIMARY KEY (id)
);
INSERT INTO attack_wield VALUES (0, 'Normal Weapon' );
INSERT INTO attack_wield VALUES (1, 'Reach  Weapon' );
INSERT INTO attack_wield VALUES (2, 'Double Weapon' );
INSERT INTO attack_wield VALUES (3, 'Thrown Weapon' );
INSERT INTO attack_wield VALUES (4, 'Projectile Weapon' );
INSERT INTO attack_wield VALUES (5, 'Ammunition' );


CREATE TABLE weapon_attack
(id                INTEGER,
 charakter_id      INTEGER,
 name              VARCHAR(32),
 description       VARCHAR(2048),
 attack_wield_id   INTEGER DEFAULT 0,
 weapon_id         INTEGER DEFAULT 0,
 ammunition        INTEGER DEFAULT 0,
 PRIMARY KEY (id),
 FOREIGN KEY (charakter_id) REFERENCES charakter(id),
 FOREIGN KEY (attack_wield_id) REFERENCES attack_wield(id),
 FOREIGN KEY (weapon_id) REFERENCES weapon(id)
);


CREATE TABLE weapon_temp
(id                      INTEGER,
 name                    VARCHAR(30),
 description             VARCHAR(2048),
 cost                    FLOAT DEFAULT 0,
 weight                  FLOAT DEFAULT 0,
 number_of_dice          INTEGER DEFAULT 0,
 die_id                  INTEGER DEFAULT 0,
 enhancement_bonus       INTEGER DEFAULT 0,
 critical_hit            INTEGER DEFAULT 0,
 critical_mod            INTEGER DEFAULT 0,
 weapon_type_id          INTEGER DEFAULT 0,
 quality_type_id         INTEGER DEFAULT 0,
 combat_type_id          INTEGER DEFAULT 0,
 weapon_encumbrance_id   INTEGER DEFAULT 0,
 weapon_category_id      INTEGER DEFAULT 0,
 weapon_family_id        INTEGER DEFAULT 1,
 range_increment         INTEGER DEFAULT 0,
 PRIMARY KEY (id),
 FOREIGN KEY (die_id) REFERENCES die(id),
 FOREIGN KEY (weapon_type_id) REFERENCES weapon_type(id)
 FOREIGN KEY (quality_type_id) REFERENCES quality_type(id)
 FOREIGN KEY (combat_type_id) REFERENCES combat_type(id)
 FOREIGN KEY (weapon_encumbrance_id) REFERENCES weapon_encumbrance(id)
 FOREIGN KEY (weapon_category_id) REFERENCES weapon_category(id)
 FOREIGN KEY (weapon_family_id) REFERENCES weapon_family(id)
);

INSERT INTO weapon_temp (id, name, description, cost, weight, number_of_dice, die_id, enhancement_bonus, critical_hit, critical_mod, weapon_type_id, quality_type_id) SELECT id, name, description, cost, weight, number_of_dice, die_id, bonus, critical_hit, critical_mod, weapon_type_id, quality_type_id FROM weapon WHERE id > 117;
UPDATE weapon_temp SET id = id + 5;
DROP TABLE weapon;
ALTER TABLE weapon_temp RENAME TO weapon;
UPDATE charakter_weapon SET weapon_id = weapon_id + 5 WHERE weapon_id > 117;

INSERT INTO weapon VALUES ( 1, 'Gauntlet', 'This metal glove lets you deal lethal damage rather than nonlethal damage with unarmed strikes. A strike with a gauntlet is otherwise considered an unarmed attack. The cost and weight given are for a single gauntlet. Medium and heavy armors (except breastplate) come with gauntlets. Your opponent cannot use a disarm action to disarm you of gauntlets.', 2, 1, 1, 1, 0, 20, 2, 0, 0, 0, 0, 0, 1, 0 );
INSERT INTO weapon VALUES ( 2, 'Unarmed strike', 'A Medium character deals 1d3 points of nonlethal damage with an unarmed strike. A Small character deals 1d2 points of nonlethal damage. A monk or any character with the Improved Unarmed Strike feat can deal lethal or nonlethal damage with unarmed strikes, at his discretion. The damage from an unarmed strike is considered weapon damage for the purposes of effects that give you a bonus on weapon damage rolls. An unarmed strike is always considered a light weapon. Therefore, you can use the Weapon Finesse feat to apply your Dexterity modifier instead of your Strength modifier to attack rolls with an unarmed strike. Unarmed strikes do not count as natural weapons (see Combat).', 0, 0, 1, 1, 0, 20, 2, 0, 0, 0, 0, 0, 1, 0 );
INSERT INTO weapon VALUES ( 3, 'Dagger', 'A dagger has a blade that is about 1 foot in length. You get a +2 bonus on Sleight of Hand skill checks made to conceal a dagger on your body (see Using Skills).', 2, 1, 1, 2, 0, 19, 2, 0, 0, 0, 0, 0, 2, 10 );
INSERT INTO weapon VALUES ( 4, 'Dagger, punching', 'A punching dagger''s blade is attached to a horizontal handle that projects out from the fist when held.', 2, 1, 1, 2, 0, 20, 3, 0, 0, 0, 0, 0, 2, 0 );
INSERT INTO weapon VALUES ( 5, 'Gauntlet, spiked', 'The cost and weight given are for a single gauntlet. An attack with a spiked gauntlet is considered an armed attack. Your opponent cannot use a disarm action to disarm you of spiked gauntlets.', 5, 1, 1, 2, 0, 20, 2, 0, 0, 0, 0, 0, 1, 0 );
INSERT INTO weapon VALUES ( 6, 'Mace, light', 'A mace is made up of an ornate metal head attached to a simple wooden or metal shaft.', 5, 4, 1, 3, 0, 20, 2, 0, 0, 0, 0, 0, 3, 0 );
INSERT INTO weapon VALUES ( 7, 'Sickle', '', 6, 2, 1, 3, 0, 20, 2, 0, 0, 0, 0, 0, 4, 0 );
INSERT INTO weapon VALUES ( 8, 'Club', '', 0, 3, 1, 3, 0, 20, 2, 0, 0, 0, 1, 0, 5, 10 );
INSERT INTO weapon VALUES ( 9, 'Mace, heavy', 'A heavy mace has a larger head and a longer handle than a normal mace.', 12, 8, 1, 4, 0, 20, 2, 0, 0, 0, 1, 0, 6, 0 );
INSERT INTO weapon VALUES ( 10, 'Morningstar', 'A morningstar is a spiked metal ball, affixed to the top of a long handle.', 8, 6, 1, 4, 0, 20, 2, 0, 0, 0, 1, 0, 7, 0 );
INSERT INTO weapon VALUES ( 11, 'Shortspear', 'A shortspear is about 3 feet in length, making it a suitable thrown weapon.', 1, 3, 1, 3, 0, 20, 2, 0, 0, 0, 1, 0, 8, 20 );
INSERT INTO weapon VALUES ( 12, 'Longspear', 'A longspear is about 8 feet in length.', 5, 9, 1, 4, 0, 20, 3, 0, 0, 0, 2, 1, 9, 0 );
INSERT INTO weapon VALUES ( 13, 'Quarterstaff', 'A quarterstaff is a simple piece of wood, about 5 feet in length.', 0, 4, 1, 3, 0, 20, 2, 0, 0, 0, 2, 2, 10, 0 );
INSERT INTO weapon VALUES ( 14, 'Spear', 'A spear is 5 feet in length and can be thrown.', 2, 6, 1, 4, 0, 20, 3, 0, 0, 0, 2, 0, 11, 20 );
INSERT INTO weapon VALUES ( 15, 'Blowgun', 'Blowguns are generally used to deliver debilitating (but rarely fatal) poisons from a distance. They are nearly silent when fired. For a list of appropriate poisons, see Poison.', 2, 1, 1, 0, 0, 20, 2, 0, 0, 1, 3, 4, 12, 20 );
INSERT INTO weapon VALUES ( 16, 'Darts, Blowgun (10)', '', 0.5, 0, 0, 0, 0, 0, 0, 3, 0, 1, 3, 5, 70, 0 );
INSERT INTO weapon VALUES ( 17, 'Crossbow, heavy', 'You draw a heavy crossbow back by turning a small winch. Loading a heavy crossbow is a full-round action that provokes attacks of opportunity. Normally, operating a heavy crossbow requires two hands. However, you can shoot, but not load, a heavy crossbow with one hand at a -4 penalty on attack rolls. You can shoot a heavy crossbow with each hand, but you take a penalty on attack rolls as if attacking with two one-handed weapons. This penalty is cumulative with the penalty for one-handed firing.', 50, 8, 1, 5, 0, 19, 2, 0, 0, 1, 3, 4, 13, 120 );
INSERT INTO weapon VALUES ( 18, 'Bolts, Crossbow, heavy (10)', 'A crossbow bolt used as a melee weapon is treated as a light improvised weapon (-4 penalty on attack rolls) and deals damage as a dagger of its size (crit x2). Bolts come in a case or quiver that holds 10 bolts (or 5, for a repeating crossbow). ', 1, 1, 0, 0, 0, 0, 0, 3, 0, 1, 3, 5, 70, 0 );
INSERT INTO weapon VALUES ( 19, 'Crossbow, light', 'You draw a light crossbow back by pulling a lever. Loading a light crossbow is a move action that provokes attacks of opportunity. Normally, operating a light crossbow requires two hands. However, you can shoot, but not load, a light crossbow with one hand at a -2 penalty on attack rolls. You can shoot a light crossbow with each hand, but you take a penalty on attack rolls as if attacking with two light weapons. This penalty is cumulative with the penalty for one-handed firing.', 35, 4, 1, 4, 0, 19, 2, 0, 0, 1, 3, 4, 14, 80 );
INSERT INTO weapon VALUES ( 20, 'Bolts, Crossbow, light (10)', 'A crossbow bolt used as a melee weapon is treated as a light improvised weapon (-4 penalty on attack rolls) and deals damage as a dagger of its size (crit x2). Bolts come in a case or quiver that holds 10 bolts (or 5, for a repeating crossbow). ', 1, 1, 0, 0, 0, 0, 0, 3, 0, 1, 3, 5, 70, 0 );
INSERT INTO weapon VALUES ( 21, 'Dart', '', 0.5, 0.5, 1, 2, 0, 20, 2, 0, 0, 1, 4, 3, 15, 20 );
INSERT INTO weapon VALUES ( 22, 'Javelin', 'A javelin is a thin throwing spear . Since it is not designed for melee, you are treated as nonproficient with it and take a -4 penalty on attack rolls if you use a javelin as a melee weapon.', 1, 2, 1, 3, 0, 20, 2, 0, 0, 1, 4, 3, 16, 30 );
INSERT INTO weapon VALUES ( 23, 'Sling', 'A sling is little more than a leather cup attached to a pair of strings. Your Strength modifier applies to damage rolls when you use a sling, just as it does for thrown weapons. You can fire, but not load, a sling with one hand. Loading a sling is a move action that requires two hands and provokes attacks of opportunity. You can hurl ordinary stones with a sling, but stones are not as dense or as round as bullets. Thus, such an attack deals damage as if the weapon were designed for a creature one size category smaller than you and you take a -1 penalty on attack rolls.', 0, 0.1, 1, 2, 0, 20, 2, 0, 0, 1, 4, 4, 17, 50 );
INSERT INTO weapon VALUES ( 24, 'Bullets, sling (10)', 'Bullets are shaped metal balls, designed to be used by a sling or halfling sling staff. Bullets come in a leather pouch that holds 10 bullets.', 0.1, 5, 0, 0, 0, 0, 0, 3, 0, 1, 3, 5, 70, 0 );
INSERT INTO weapon VALUES ( 25, 'Axe, throwing', '', 8, 2, 1, 3, 0, 20, 2, 1, 0, 0, 0, 3, 18, 10 );
INSERT INTO weapon VALUES ( 26, 'Hammer, light', '', 1, 2, 1, 2, 0, 20, 2, 1, 0, 0, 0, 0, 19, 20 );
INSERT INTO weapon VALUES ( 27, 'Handaxe', '', 6, 3, 1, 3, 0, 20, 3, 1, 0, 0, 0, 0, 20, 0 );
INSERT INTO weapon VALUES ( 28, 'Kukri', 'A kukri is a curved blade, about 1 foot in length.', 8, 2, 1, 2, 0, 18, 2, 1, 0, 0, 0, 0, 21, 0 );
INSERT INTO weapon VALUES ( 29, 'Pick, light', '', 4, 3, 1, 2, 0, 20, 4, 1, 0, 0, 0, 0, 22, 0 );
INSERT INTO weapon VALUES ( 30, 'Sap', '', 1, 2, 1, 3, 0, 20, 2, 1, 0, 0, 0, 0, 23, 0 );
INSERT INTO weapon VALUES ( 31, 'Starknife', 'From a central metal ring, four tapering metal blades extend like points on a compass rose. A wielder can stab with the starknife or throw it. ', 24, 3, 1, 2, 0, 20, 3, 1, 0, 0, 0, 0, 24, 20 );
INSERT INTO weapon VALUES ( 32, 'Sword, short', 'This sword is about 2 feet in length.', 10, 2, 1, 3, 0, 19, 2, 1, 0, 0, 0, 0, 25, 0 );
INSERT INTO weapon VALUES ( 33, 'Battleaxe', '', 10, 6, 1, 4, 0, 20, 3, 1, 0, 0, 1, 0, 26, 0 );
INSERT INTO weapon VALUES ( 34, 'Flail', 'A flail consists of a spiked metal ball, connected to a handle by a sturdy chain.', 8, 5, 1, 4, 0, 20, 2, 1, 0, 0, 1, 0, 27, 0 );
INSERT INTO weapon VALUES ( 35, 'Longsword', 'This sword is about 3-1/2 feet in length.', 15, 4, 1, 4, 0, 19, 2, 1, 0, 0, 1, 0, 28, 0 );
INSERT INTO weapon VALUES ( 36, 'Pick, heavy', '', 8, 6, 1, 3, 0, 20, 4, 1, 0, 0, 1, 0, 29, 0 );
INSERT INTO weapon VALUES ( 37, 'Rapier', 'You can use the Weapon Finesse feat to apply your Dexterity modifier instead of your Strength modifier to attack rolls with a rapier sized for you, even though it isn''t a light weapon. You can''t wield a rapier in two hands in order to apply 1-1/2 times your Strength bonus to damage.', 20, 2, 1, 3, 0, 18, 2, 1, 0, 0, 1, 0, 30, 0 );
INSERT INTO weapon VALUES ( 38, 'Scimitar', '', 15, 4, 1, 3, 0, 18, 2, 1, 0, 0, 1, 0, 31, 0 );
INSERT INTO weapon VALUES ( 39, 'Trident', 'A trident has three metal prongs at end of a 4-foot-long shaft. This weapon can be thrown.', 15, 4, 1, 4, 0, 20, 2, 1, 0, 0, 1, 0, 32, 10 );
INSERT INTO weapon VALUES ( 40, 'Warhammer', '', 12, 5, 1, 4, 0, 20, 3, 1, 0, 0, 1, 0, 33, 0 );
INSERT INTO weapon VALUES ( 41, 'Falchion', '', 75, 8, 2, 2, 0, 18, 2, 1, 0, 0, 2, 0, 34, 0 );
INSERT INTO weapon VALUES ( 42, 'Glaive', 'A glaive is a simple blade, mounted to the end of a pole about 7 feet in length.', 8, 10, 1, 5, 0, 20, 3, 1, 0, 0, 2, 1, 35, 0 );
INSERT INTO weapon VALUES ( 43, 'Greataxe', '', 20, 12, 1, 6, 0, 20, 3, 1, 0, 0, 2, 0, 36, 0 );
INSERT INTO weapon VALUES ( 44, 'Greatclub', '', 5, 8, 1, 5, 0, 20, 2, 1, 0, 0, 2, 0, 37, 0 );
INSERT INTO weapon VALUES ( 45, 'Flail, heavy', 'Similar to a flail, a heavy flail has a larger metal ball and a longer handle. ', 15, 10, 1, 5, 0, 19, 2, 1, 0, 0, 2, 0, 38, 0 );
INSERT INTO weapon VALUES ( 46, 'Greatsword', 'This immense two-handed sword is about 5 feet in length.', 50, 8, 2, 3, 0, 19, 2, 1, 0, 0, 2, 0, 39, 0 );
INSERT INTO weapon VALUES ( 47, 'Guisarme', 'A guisarme is an 8-foot-long shaft with a blade and a hook mounted at the tip.', 9, 12, 2, 2, 0, 20, 3, 1, 0, 0, 2, 1, 40, 0 );
INSERT INTO weapon VALUES ( 48, 'Halberd', 'A halberd is similar to a 5-foot-long spear, but it also has a small, axe-like head mounted near the tip.', 10, 12, 1, 5, 0, 20, 3, 1, 0, 0, 2, 0, 41, 0 );
INSERT INTO weapon VALUES ( 49, 'Lance', 'A lance deals double damage when used from the back of a charging mount. While mounted, you can wield a lance with one hand.', 10, 10, 1, 4, 0, 20, 3, 1, 0, 0, 2, 1, 42, 0 );
INSERT INTO weapon VALUES ( 50, 'Ranseur', 'Similar in appearance to a trident, a ranseur has a single spear at its tip, flanked by a pair of short, curving blades.', 10, 12, 2, 2, 0, 20, 3, 1, 0, 0, 2, 1, 43, 0 );
INSERT INTO weapon VALUES ( 51, 'Scythe', '', 18, 10, 2, 2, 0, 20, 4, 1, 0, 0, 2, 0, 44, 0 );
INSERT INTO weapon VALUES ( 52, 'Longbow', 'At almost 5 feet in height, a longbow is made up of one solid piece of carefully curved wood. You need two hands to use a bow, regardless of its size. A longbow is too unwieldy to use while you are mounted. If you have a penalty for low Strength, apply it to damage rolls when you use a longbow. If you have a Strength bonus, you can apply it to damage rolls when you use a composite longbow (see below), but not when you use a regular longbow.', 75, 3, 1, 4, 0, 20, 3, 1, 0, 1, 5, 4, 45, 100 );
INSERT INTO weapon VALUES ( 53, 'Arrows, Longbow (20)', 'An arrow used as a melee weapon is treated as a light improvised weapon (-4 penalty on attack rolls) and deals damage as a dagger of its size (critical multiplier x2). Arrows come in a leather quiver that holds 20 arrows.', 1, 3, 0, 0, 0, 0, 0, 3, 0, 1, 3, 5, 70, 0 );
INSERT INTO weapon VALUES ( 54, 'Longbow, composite', 'You need at least two hands to use a bow, regardless of its size. You can use a composite longbow while mounted. All composite bows are made with a particular strength rating (that is, each requires a minimum Strength modifier to use with proficiency). If your Strength bonus is less than the strength rating of the composite bow, you can''t effectively use it, so you take a -2 penalty on attacks with it. The default composite longbow requires a Strength modifier of +0 or higher to use with proficiency. A composite longbow can be made with a high strength rating to take advantage of an above-average Strength score, this feature allows you to add your Strength bonus to damage, up to the maximum bonus indicated for the bow. Each point of Strength bonus granted by the bow adds 100 gp to its cost. If you have a penalty for low Strength, apply it to damage rolls when you use a composite longbow. For purposes of Weapon Proficiency and similar feats, a composite longbow is treated as if it were a longbow.', 100, 3, 1, 4, 0, 20, 3, 1, 0, 1, 5, 4, 46, 110 );
INSERT INTO weapon VALUES ( 55, 'Arrows, Longbow, composite (20)', 'An arrow used as a melee weapon is treated as a light improvised weapon (-4 penalty on attack rolls) and deals damage as a dagger of its size (critical multiplier x2). Arrows come in a leather quiver that holds 20 arrows.', 1, 3, 0, 0, 0, 0, 0, 3, 0, 1, 3, 5, 70, 0 );
INSERT INTO weapon VALUES ( 56, 'Shortbow', 'A shortbow is made up of one piece of wood, about 3 feet in length. You need two hands to use a bow, regardless of its size. You can use a shortbow while mounted. If you have a penalty for low Strength, apply it to damage rolls when you use a shortbow. If you have a bonus for high Strength, you can apply it to damage rolls when you use a composite shortbow (see below), but not a regular shortbow.', 30, 2, 1, 3, 0, 20, 3, 1, 0, 1, 5, 4, 47, 60 );
INSERT INTO weapon VALUES ( 57, 'Arrows, Shortbow (20)', 'An arrow used as a melee weapon is treated as a light improvised weapon (-4 penalty on attack rolls) and deals damage as a dagger of its size (critical multiplier x2). Arrows come in a leather quiver that holds 20 arrows.', 1, 3, 0, 0, 0, 0, 0, 3, 0, 1, 3, 5, 70, 0 );
INSERT INTO weapon VALUES ( 58, 'Shortbow, composite', 'You need at least two hands to use a bow, regardless of its size. You can use a composite shortbow while mounted. All composite bows are made with a particular strength rating (that is, each requires a minimum Strength modifier to use with proficiency). If your Strength bonus is lower than the strength rating of the composite bow, you can''t effectively use it, so you take a -2 penalty on attacks with it. The default composite shortbow requires a Strength modifier of +0 or higher to use with proficiency. A composite shortbow can be made with a high strength rating to take advantage of an above-average Strength score, this feature allows you to add your Strength bonus to damage, up to the maximum bonus indicated for the bow. Each point of Strength bonus granted by the bow adds 75 gp to its cost. If you have a penalty for low Strength, apply it to damage rolls when you use a composite shortbow. For purposes of Weapon Proficiency, Weapon Focus, and similar feats, a composite shortbow is treated as if it were a shortbow.', 75, 2, 1, 3, 0, 20, 3, 1, 0, 1, 5, 4, 48, 70 );
INSERT INTO weapon VALUES ( 59, 'Arrows, Shortbow, composite (20)', 'An arrow used as a melee weapon is treated as a light improvised weapon (-4 penalty on attack rolls) and deals damage as a dagger of its size (critical multiplier x2). Arrows come in a leather quiver that holds 20 arrows.', 1, 3, 0, 0, 0, 0, 0, 3, 0, 1, 3, 0, 70, 0 );
INSERT INTO weapon VALUES ( 60, 'Kama', 'Similar to a sickle, a kama is a short, curved blade attached to a simple handle.', 2, 2, 1, 3, 0, 20, 2, 2, 0, 0, 0, 0, 49, 0 );
INSERT INTO weapon VALUES ( 61, 'Nunchaku', 'A nunchaku is made up of two wooden or metal bars connected by a small length of rope or chain.', 2, 2, 1, 3, 0, 20, 2, 2, 0, 0, 0, 0, 50, 0 );
INSERT INTO weapon VALUES ( 62, 'Sai', 'A sai is a metal spike flanked by a pair of prongs used to trap an enemy''s weapon. With a sai, you get a +2 bonus on Combat Maneuver Checks to sunder an enemy''s weapon. Though pointed, a sai is used primarily to bludgeon foes and to disarm weapons.', 1, 1, 1, 2, 0, 20, 2, 2, 0, 0, 0, 0, 51, 0 );
INSERT INTO weapon VALUES ( 63, 'Siangham', 'This weapon is a handheld shaft fitted with a pointed tip for stabbing foes.', 3, 1, 1, 3, 0, 20, 2, 2, 0, 0, 0, 0, 52, 0 );
INSERT INTO weapon VALUES ( 64, 'Sword, bastard', 'A bastard sword is about 4 feet in length, making it too large to use in one hand without special training, thus, it is an exotic weapon. A character can use a bastard sword two-handed as a martial weapon.', 35, 6, 1, 5, 0, 19, 2, 2, 0, 0, 1, 0, 53, 0 );
INSERT INTO weapon VALUES ( 65, 'Waraxe, dwarven', 'A dwarven waraxe has a large, ornate head mounted to a thick handle, making it too large to use in one hand without special training, thus, it is an exotic weapon. A Medium character can use a dwarven waraxe two-handed as a martial weapon, or a Large creature can use it one-handed in the same way. A dwarf treats a dwarven waraxe as a martial weapon even when using it in one hand.', 30, 8, 1, 5, 0, 20, 3, 2, 0, 0, 1, 0, 54, 0 );
INSERT INTO weapon VALUES ( 66, 'Whip', 'A whip deals no damage to any creature with an armor bonus of +1 or higher or a natural armor bonus of +3 or higher. The whip is treated as a melee weapon with 15-foot reach, though you don''t threaten the area into which you can make an attack. In addition, unlike most other weapons with reach, you can use it against foes anywhere within your reach (including adjacent foes).', 1, 2, 1, 1, 0, 20, 2, 2, 0, 0, 1, 1, 55, 0 );
INSERT INTO weapon VALUES ( 67, 'Axe, orc double', 'A cruel weapon with blades placed at opposite ends of a long haft, an orc double axe is a double weapon.', 60, 15, 1, 4, 0, 20, 3, 2, 0, 0, 2, 2, 56, 0 );
INSERT INTO weapon VALUES ( 68, 'Chain, spiked', 'A spiked chain is about 4 feet in length, covered in wicked barbs. You can use the Weapon Finesse feat to apply your Dexterity modifier instead of your Strength modifier to attack rolls with a spiked chain sized for you, even though it isn''t a light weapon.', 25, 10, 2, 2, 0, 20, 2, 2, 0, 0, 2, 0, 57, 0 );
INSERT INTO weapon VALUES ( 69, 'Curve blade, elven', 'Essentially a longer version of a scimitar, but with a thinner blade, the elven curve blade is exceptionally rare. You receive a +2 circumstance bonus to your Combat Maneuver Defense whenever a foe attempts to sunder your elven curve blade due to its flexible metal. You can use the Weapon Finesse feat to apply your Dexterity modifier instead of your Strength modifier to attack rolls with an elven curve blade sized for you, even though it isn''t a light weapon.', 80, 7, 1, 5, 0, 18, 2, 2, 0, 0, 2, 0, 58, 0 );
INSERT INTO weapon VALUES ( 70, 'Flail, dire', 'A dire flail consists of two spheres of spiked iron dangling from chains at opposite ends of a long haft.', 90, 10, 1, 4, 0, 20, 2, 2, 0, 0, 2, 2, 59, 0 );
INSERT INTO weapon VALUES ( 71, 'Hammer, gnome hooked', 'A gnome hooked hammer is a double weapon-an ingenious tool with a hammer head at one end of its haft and a long, curved pick at the other. The hammer''s blunt head is a bludgeoning weapon that deals 1d6 points of damage (crit x3). Its hook is a piercing weapon that deals 1d4 points of damage (crit x4). You can use either head as the primary weapon. Gnomes treat hooked hammers as martial weapons.', 20, 6, 1, 4, 0, 20, 3, 2, 0, 0, 2, 2, 60, 0 );
INSERT INTO weapon VALUES ( 72, 'Sword, two-bladed', 'A two-bladed sword is a double weapon-twin blades extend from either side of a central, short haft, allowing the wielder to attack with graceful but deadly flourishes.', 100, 10, 1, 4, 0, 19, 2, 2, 0, 0, 2, 2, 61, 0 );
INSERT INTO weapon VALUES ( 73, 'Urgrosh, dwarven', 'A dwarven urgrosh is a double weapon-an axe head and a spear point on opposite ends of a long haft. The urgrosh''s axe head is a slashing weapon that deals 1d8 points of damage. Its spear head is a piercing weapon that deals 1d6 points of damage. You can use either head as the primary weapon. The other becomes the off-hand weapon. If you use an urgrosh against a charging character, the spear head is the part of the weapon that deals damage. Dwarves treat dwarven urgroshes as martial weapons.', 50, 12, 1, 4, 0, 20, 3, 2, 0, 0, 2, 2, 62, 0 );
INSERT INTO weapon VALUES ( 74, 'Bolas', 'A bolas is a pair of weights, connected by a thin rope or cord. You can use this weapon to make a ranged trip attack against an opponent. You can''t be tripped during your own trip attempt when using a bolas.', 5, 2, 1, 2, 0, 20, 2, 2, 0, 1, 3, 3, 63, 10 );
INSERT INTO weapon VALUES ( 75, 'Crossbow, hand', 'You can draw a hand crossbow back by hand. Loading a hand crossbow is a move action that provokes attacks of opportunity. You can shoot, but not load, a hand crossbow with one hand at no penalty. You can shoot a hand crossbow with each hand, but you take a penalty on attack rolls as if attacking with two light weapons.', 100, 2, 1, 2, 0, 19, 2, 2, 0, 1, 4, 4, 64, 30 );
INSERT INTO weapon VALUES ( 76, 'Bolts, Crossbow, hand (10)', 'A crossbow bolt used as a melee weapon is treated as a light improvised weapon (-4 penalty on attack rolls) and deals damage as a dagger of its size (crit x2). Bolts come in a case or quiver that holds 10 bolts (or 5, for a repeating crossbow). ', 1, 1, 0, 0, 0, 0, 0, 3, 0, 1, 3, 5, 70, 0 );
INSERT INTO weapon VALUES ( 77, 'Crossbow, repeating heavy', 'The repeating crossbow (whether heavy or light) holds 5 crossbow bolts. As long as it holds bolts, you can reload it by pulling the reloading lever (a free action). Loading a new case of 5 bolts is a full-round action that provokes attacks of opportunity. You can fire a repeating crossbow with one hand or fire a repeating crossbow in each hand in the same manner as you would a normal crossbow of the same size. However, you must fire the weapon with two hands in order to use the reloading lever, and you must use two hands to load a new case of bolts.', 400, 12, 1, 0, 0, 19, 2, 2, 0, 1, 4, 4, 65, 120 );
INSERT INTO weapon VALUES ( 78, 'Bolts, Crossbow, repeating heavy (5)', 'A crossbow bolt used as a melee weapon is treated as a light improvised weapon (-4 penalty on attack rolls) and deals damage as a dagger of its size (crit x2). Bolts come in a case or quiver that holds 10 bolts (or 5, for a repeating crossbow). ', 1, 1, 0, 0, 0, 0, 0, 3, 0, 1, 3, 5, 70, 0 );
INSERT INTO weapon VALUES ( 79, 'Crossbow, repeating light', 'The repeating crossbow (whether heavy or light) holds 5 crossbow bolts. As long as it holds bolts, you can reload it by pulling the reloading lever (a free action). Loading a new case of 5 bolts is a full-round action that provokes attacks of opportunity. You can fire a repeating crossbow with one hand or fire a repeating crossbow in each hand in the same manner as you would a normal crossbow of the same size. However, you must fire the weapon with two hands in order to use the reloading lever, and you must use two hands to load a new case of bolts.', 250, 6, 1, 4, 0, 19, 2, 2, 0, 1, 4, 4, 66, 80 );
INSERT INTO weapon VALUES ( 80, 'Bolts, Crossbow, repeating light (5)', 'A crossbow bolt used as a melee weapon is treated as a light improvised weapon (-4 penalty on attack rolls) and deals damage as a dagger of its size (crit x2). Bolts come in a case or quiver that holds 10 bolts (or 5, for a repeating crossbow). ', 1, 1, 0, 0, 0, 0, 0, 3, 0, 1, 3, 5, 70, 0 );
INSERT INTO weapon VALUES ( 81, 'Net', 'A net is used to entangle enemies. When you throw a net, you make a ranged touch attack against your target. A net''s maximum range is 10 feet. If you hit, the target is entangled. An entangled creature takes a -2 penalty on attack rolls and a -4 penalty on Dexterity, can move at only half speed, and cannot charge or run. If you control the trailing rope by succeeding on an opposed Strength check while holding it, the entangled creature can move only within the limits that the rope allows. If the entangled creature attempts to cast a spell, it must make a concentration check with a DC of 15 + the spell''s level or be unable to cast the spell. An entangled creature can escape with a DC 20 Escape Artist check (a full-round action). The net has 5 hit points and can be burst with a DC 25 Strength check (also a full-round action). A net is useful only against creatures within one size category of you. A net must be folded to be thrown effectively. The first time you throw your net in a fight, you make a normal ranged touch attack roll. After the net is unfolded, you take a -4 penalty on attack rolls with it. It takes 2 rounds for a proficient user to fold a net and twice that long for a nonproficient one to do so.', 20, 6, 0, 0, 0, 0, 0, 2, 0, 1, 4, 3, 67, 10 );
INSERT INTO weapon VALUES ( 82, 'Shuriken (5)', 'A shuriken is a small piece of metal with sharpened edges, designed for throwing. A shuriken can''t be used as a melee weapon. Although they are thrown weapons, shuriken are treated as ammunition for the purposes of drawing them, crafting masterwork or otherwise special versions of them, and what happens to them after they are thrown.', 1, 0.5, 1, 0, 0, 20, 2, 2, 0, 1, 3, 3, 68, 10 );
INSERT INTO weapon VALUES ( 83, 'Sling staff, halfling', 'Made from a specially designed sling attached to a short club, a halfling sling staff can be used by a proficient wielder to devastating effect. Your Strength modifier applies to damage rolls when you use a halfling sling staff, just as it does for thrown weapons. You can fire, but not load, a halfling sling staff with one hand. Loading a halfling sling staff is a move action that requires two hands and provokes attacks of opportunity. You can hurl ordinary stones with a halfling sling staff, but stones are not as dense or as round as bullets. Thus, such an attack deals damage as if the weapon were designed for a creature one size category smaller than you and you take a -1 penalty on attack rolls. A halfling sling staff can be used as a simple weapon that deals bludgeoning damage equal to that of a club of its size. Halflings treat halfling sling staves as martial weapons.', 20, 3, 1, 4, 0, 20, 3, 2, 0, 1, 5, 4, 69, 80 );
INSERT INTO weapon VALUES ( 84, 'Adamantine Battleaxe', 'This nonmagical axe is made out of adamantine. As a masterwork weapon, it has a +1 enhancement bonus on attack rolls.', 3010, 6, 1, 4, 0, 20, 3, 1, 0, 0, 1, 0, 26, 0 );
INSERT INTO weapon VALUES ( 85, 'Adamantine Dagger', 'This nonmagical dagger is made out of adamantine. As a masterwork weapon, it has a +1 enhancement bonus on attack rolls.', 3002, 1, 1, 2, 0, 19, 2, 0, 0, 0, 0, 0, 2, 0 );
INSERT INTO weapon VALUES ( 86, 'Assassin''s Dagger', 'This wicked-looking, curved +2 dagger provides a +1 bonus to the DC of a Fortitude save forced by the death attack of an assassin.', 10302, 1, 1, 2, 2, 19, 2, 0, 2, 0, 0, 0, 2, 0 );
INSERT INTO weapon VALUES ( 87, 'Dagger of Venom', 'This black +1 dagger has a serrated edge. It allows the wielder to use a poison effect (as the spell, save DC 14) upon a creature struck by the blade once per day. The wielder can decide to use the power after he has struck. Doing so is a free action, but the poison effect must be invoked in the same round that the dagger strikes. ', 8302, 1, 1, 2, 1, 19, 2, 0, 2, 0, 0, 0, 2, 0 );
INSERT INTO weapon VALUES ( 88, 'Dwarven Thrower', 'This weapon functions as a +2 warhammer in the hands of most users. Yet in the hands of a dwarf, the warhammer gains an additional +1 enhancement bonus (for a total enhancement bonus of +3) and gains the returning special ability. It can be hurled with a 30-foot range increment. When hurled, a dwarven thrower deals an extra 2d8 points of damage against creatures of the giant subtype or an extra 1d8 points of damage against any other target. ', 60312, 5, 1, 4, 2, 20, 3, 1, 2, 0, 1, 0, 33, 0 );
INSERT INTO weapon VALUES ( 89, 'Flame Tongue', 'This is a +1 flaming burst longsword. Once per day, the sword can blast forth a fiery ray at any target within 30 feet as a ranged touch attack. The ray deals 4d6 points of fire damage on a successful hit. ', 20715, 4, 1, 4, 1, 19, 2, 1, 2, 0, 1, 0, 28, 0 );
INSERT INTO weapon VALUES ( 90, 'Frost Brand', 'This +3 frost greatsword sheds light as a torch when the temperature drops below 0� F. At such times it cannot be concealed when drawn, nor can its light be shut off. Its wielder is protected from fire, the sword absorbs the first 10 points of fire damage each round that the wielder would otherwise take.\nA frost brand extinguishes all nonmagical fires in a 20-foot radius. As a standard action, it can also dispel lasting fire spells, but not instantaneous effects. You must succeed on a dispel check (1d20 +14) against each spell to dispel it. The DC to dispel such spells is 11 + the caster level of the fire spell.', 54475, 8, 2, 3, 3, 19, 2, 1, 2, 0, 2, 0, 39, 0 );
INSERT INTO weapon VALUES ( 91, 'Holy Avenger', 'This +2 cold iron longsword becomes a +5 holy cold iron longsword in the hands of a paladin.\nThis sacred weapon provides spell resistance of 5 + the paladin''s level to the wielder and anyone adjacent to her. It also enables the paladin to use greater dispel magic (once per round as a standard action) at the class level of the paladin. Only the area dispel is possible, not the targeted dispel or counterspell versions of greater dispel magic.', 120630, 4, 1, 4, 2, 19, 2, 1, 2, 0, 1, 0, 28, 0 );
INSERT INTO weapon VALUES ( 92, 'Javelin of Lightning', 'This javelin becomes a 5d6 lightning bolt when thrown (Reflex DC 14 half). It is consumed in the attack.', 1500, 2, 1, 3, 0, 20, 2, 0, 2, 1, 4, 3, 16, 30 );
INSERT INTO weapon VALUES ( 93, 'Life-Drinker', 'This +1 greataxe is favored by undead and constructs, who do not suffer its drawback. A life-drinker bestows two negative levels on its target whenever it deals damage, just as if its target had been struck by an undead creature. One day after being struck, subjects must make a DC 16 Fortitude save for each negative level or the negative levels become permanent.\nEach time a life-drinker deals damage to a foe, it also bestows one negative level on the wielder. Any negative levels gained by the wielder in this fashion lasts for 1 hour. ', 40320, 12, 1, 6, 1, 20, 3, 1, 2, 0, 2, 0, 36, 0 );
INSERT INTO weapon VALUES ( 94, 'Longsword, Cold Iron', 'This nonmagical longsword is crafted out of cold iron. As a masterwork weapon, it has a +1 enhancement bonus on attack rolls.', 330, 4, 1, 4, 0, 19, 2, 1, 1, 0, 1, 0, 28, 0 );
INSERT INTO weapon VALUES ( 95, 'Luck Blade (0 wishes)', 'This +2 short sword gives its possessor a +1 luck bonus on all saving throws. Its possessor also gains the power of good fortune, usable once per day. This extraordinary ability allows its possessor to reroll one roll that she just made, before the results are revealed. She must take the result of the reroll, even if it''s worse than the original roll. In addition, a luck blade may contain up to three wishes (when randomly rolled, a luck blade holds 1d4-1 wishes, minimum 0). When the last wish is used, the sword remains a +2 short sword, still grants the +1 luck bonus, and still grants its reroll power. ', 22060, 2, 1, 3, 2, 19, 2, 1, 2, 0, 0, 0, 25, 0 );
INSERT INTO weapon VALUES ( 96, 'Luck Blade (1 wish)', 'This +2 short sword gives its possessor a +1 luck bonus on all saving throws. Its possessor also gains the power of good fortune, usable once per day. This extraordinary ability allows its possessor to reroll one roll that she just made, before the results are revealed. She must take the result of the reroll, even if it''s worse than the original roll. In addition, a luck blade may contain up to three wishes (when randomly rolled, a luck blade holds 1d4-1 wishes, minimum 0). When the last wish is used, the sword remains a +2 short sword, still grants the +1 luck bonus, and still grants its reroll power. ', 62360, 2, 1, 3, 2, 19, 2, 1, 2, 0, 0, 0, 25, 0 );
INSERT INTO weapon VALUES ( 97, 'Luck Blade (2 wishes)', 'This +2 short sword gives its possessor a +1 luck bonus on all saving throws. Its possessor also gains the power of good fortune, usable once per day. This extraordinary ability allows its possessor to reroll one roll that she just made, before the results are revealed. She must take the result of the reroll, even if it''s worse than the original roll. In addition, a luck blade may contain up to three wishes (when randomly rolled, a luck blade holds 1d4-1 wishes, minimum 0). When the last wish is used, the sword remains a +2 short sword, still grants the +1 luck bonus, and still grants its reroll power. ', 102660, 2, 1, 3, 2, 19, 2, 1, 2, 0, 0, 0, 25, 0 );
INSERT INTO weapon VALUES ( 98, 'Luck Blade (3 wishes)', 'This +2 short sword gives its possessor a +1 luck bonus on all saving throws. Its possessor also gains the power of good fortune, usable once per day. This extraordinary ability allows its possessor to reroll one roll that she just made, before the results are revealed. She must take the result of the reroll, even if it''s worse than the original roll. In addition, a luck blade may contain up to three wishes (when randomly rolled, a luck blade holds 1d4-1 wishes, minimum 0). When the last wish is used, the sword remains a +2 short sword, still grants the +1 luck bonus, and still grants its reroll power. ', 142960, 2, 1, 3, 2, 19, 2, 1, 2, 0, 0, 0, 25, 0 );
INSERT INTO weapon VALUES ( 99, 'Mace of Smiting', 'This +3 adamantine heavy mace has a +5 enhancement bonus against constructs, and a successful critical hit dealt to a construct completely destroys the construct (no saving throw). A critical hit dealt to an outsider deals x4 damage rather than x2. ', 75312, 8, 1, 4, 3, 20, 2, 0, 2, 0, 1, 0, 6, 0 );
INSERT INTO weapon VALUES ( 100, 'Mace of Terror', 'This weapon usually appears to be a particularly frightening-looking iron or steel mace. On command, this +2 heavy mace causes the wielder''s clothes and appearance to transform into an illusion of darkest horror such that living creatures in a 30-foot cone become panicked as if by a fear spell (Will DC 16 partial). Those who fail take a -2 morale penalty on saving throws, and they flee from the wielder. The wielder may use this ability up to three times per day.', 38552, 8, 1, 4, 2, 20, 2, 0, 2, 0, 1, 0, 6, 0 );
INSERT INTO weapon VALUES ( 101, 'Nine Lives Stealer', 'This longsword always performs as a +2 longsword, but it also has the power to draw the life force from an opponent. It can do this nine times before the ability is lost. At that point, the sword becomes a simple +2 longsword (with a faint evil aura). A critical hit must be dealt for the sword''s death-dealing ability to function, and this weapon has no effect on creatures not subject to critical hits. The victim is entitled to a DC 20 Fortitude save to avoid death. If the save is successful, the sword''s death-dealing ability does not function, no use of the ability is expended, and normal critical damage is determined. This sword is evil, and any good character attempting to wield it gains two negative levels. These negative levels remain as long as the sword is in hand and disappear when the sword is no longer wielded. These negative levels never result in actual level loss, but they cannot be overcome in any way (including restoration spells) while the sword is wielded.', 23057, 4, 1, 4, 2, 19, 2, 1, 2, 0, 1, 0, 28, 0 );
INSERT INTO weapon VALUES ( 102, 'Oathbow', 'Of elven make, this white +2 composite longbow (+2 Str bonus) whispers, ''Swift defeat to my enemies'' in Elven when nocked and pulled. Once per day, if the archer swears aloud to slay her target (a free action), the bow''s whisper becomes the shout ''Death to those who have wronged me!'' Against such a sworn enemy, the bow has a +5 enhancement bonus, and arrows launched from it deal an additional 2d6 points of damage (and x4 on a critical hit instead of the normal x3). After an enemy has been sworn, the bow is treated as only a masterwork weapon against all foes other than the sworn enemy, and the archer takes a -1 penalty on attack rolls with any weapon other than the oathbow. These bonuses and penalties last for 7 days or until the sworn enemy is slain or destroyed by the wielder of the oathbow, whichever comes first.\nThe oathbow may only have one sworn enemy at a time. Once the wielder swears to slay a target, he cannot make a new oath until he has slain that target or 7 days have passed. Even if the wielder slays the sworn enemy on the same day that he makes the oath, he cannot activate the oathbow''s special power again until 24 hours have passed from the time he made the oath. ', 25600, 3, 1, 4, 2, 20, 3, 1, 2, 1, 5, 4, 46, 110 );
INSERT INTO weapon VALUES ( 103, 'Rapier of Puncturing', 'Three times per day, this +2 wounding rapier allows the wielder to make a touch attack with the weapon that deals 1d6 points of Constitution damage by draining blood. Creatures immune to critical hits are immune to the Constitution damage dealt by this weapon.', 50320, 2, 1, 3, 2, 18, 2, 1, 2, 0, 1, 0, 30, 0 );
INSERT INTO weapon VALUES ( 104, 'Screaming Bolt', 'These +2 bolts scream when fired, forcing all enemies of the wielder within 20 feet of the path of the bolt to succeed on a DC 14 Will save or become shaken. This is a mind-affecting fear effect.', 267, 0.1, 0, 0, 0, 0, 0, 3, 2, 1, 3, 5, 70, 0 );
INSERT INTO weapon VALUES ( 105, 'Shatterspike', 'This intimidating weapon appears to be a longsword with multiple hooks, barbs, and serrations along the blade, excellent for catching and sundering a foe''s weapon. Wielders without the Improved Sunder feat use a shatterspike as a +1 longsword only. Wielders with the Improved Sunder feat instead use shatterspike as a +4 longsword when attempting to sunder an opponent''s weapon. Shatterspike can damage weapons with an enhancement bonus of +4 or lower.', 4315, 4, 1, 4, 0, 19, 2, 1, 2, 0, 1, 0, 28, 0 );
INSERT INTO weapon VALUES ( 106, 'Shifter''s Sorrow', 'This +1/+1 two-bladed sword has blades of alchemical silver. The weapon deals an extra 2d6 points of damage against any creature with the shapechanger subtype. When a shapechanger or a creature in an alternate form (such as a druid using wild shape) is struck by the weapon, it must make a DC 15 Will save or return to its natural form.', 12780, 10, 1, 4, 0, 19, 2, 2, 2, 0, 2, 2, 61, 0 );
INSERT INTO weapon VALUES ( 107, 'Silver Dagger', 'As a masterwork weapon, this alchemical silver dagger has a +1 enhancement bonus on attack rolls (but not to damage rolls).', 322, 1, 1, 2, 0, 19, 2, 0, 1, 0, 0, 0, 2, 0 );
INSERT INTO weapon VALUES ( 108, 'Slaying Arrow', 'This +1 arrow is keyed to a particular type or subtype of creature. If it strikes such a creature, the target must make a DC 20 Fortitude save or take 50 points of damage. Note that even creatures normally exempt from Fortitude saves (undead and constructs) are subject to this attack. When keyed to a living creature, this is a death effect (and thus death ward protects a target). To determine the type or subtype of creature the arrow is keyed to, roll on the table below.\nA greater slaying arrow functions just like a normal slaying arrow, but the DC to avoid the death effect is 23 and the arrow deals 100 points of damage if the saving throw is failed.', 2282, 0.1, 0, 0, 0, 0, 0, 3, 2, 1, 3, 5, 70, 0 );
INSERT INTO weapon VALUES ( 109, 'Slaying Arrow, greater', 'This +1 arrow is keyed to a particular type or subtype of creature. If it strikes such a creature, the target must make a DC 20 Fortitude save or take 50 points of damage. Note that even creatures normally exempt from Fortitude saves (undead and constructs) are subject to this attack. When keyed to a living creature, this is a death effect (and thus death ward protects a target). To determine the type or subtype of creature the arrow is keyed to, roll on the table below.\nA greater slaying arrow functions just like a normal slaying arrow, but the DC to avoid the death effect is 23 and the arrow deals 100 points of damage if the saving throw is failed.', 4057, 0.1, 0, 0, 0, 0, 0, 3, 2, 1, 3, 5, 70, 0 );
INSERT INTO weapon VALUES ( 110, 'Sleep Arrow', 'This +1 arrow is painted white and has white fletching. If it strikes a foe so that it would normally deal damage, it instead bursts into magical energy that deals nonlethal damage (in the same amount as would lethal damage) and forces the target to make a DC 11 Will save or fall asleep.', 132, 0.1, 0, 0, 0, 0, 0, 3, 2, 1, 3, 5, 70, 0 );
INSERT INTO weapon VALUES ( 111, 'Sun Blade', 'This sword is the size of a bastard sword. However, a sun blade is wielded as if it were a short sword with respect to weight and ease of use. In other words, the weapon appears to all viewers to be a bastard sword, and deals bastard sword damage, but the wielder feels and reacts as if the weapon were a short sword. Any individual able to use either a bastard sword or a short sword with proficiency is proficient in the use of a sun blade. Likewise, Weapon Focus and Weapon Specialization in short sword and bastard sword apply equally, but the benefits of those feats do not stack.\nIn normal combat, the glowing golden blade of the weapon is equal to a +2 bastard sword. Against evil creatures, its enhancement bonus is +4. Against Negative Energy Plane creatures or undead creatures, the sword deals double damage (and x3 on a critical hit instead of the usual x2).\nThe blade also has a special sunlight power. Once per day, the wielder can swing the blade vigorously above his head while speaking a command word. The sun blade then sheds a bright yellow radiance that acts like bright light and affects creatures susceptible to light as if it were natural sunlight. The radiance begins shining in a 10-foot radius around the sword wielder and extends outward at 5 feet per round for 10 rounds thereafter, to create a globe of light with a 60-foot radius. When the wielder stops swinging, the radiance fades to a dim glow that persists for another minute before disappearing entirely. All sun blades are of good alignment, and any evil creature attempting to wield one gains one negative level. The negative level remains as long as the sword is in hand and disappears when the sword is no longer wielded. This negative level cannot be overcome in any way (including by restoration spells) while the sword is wielded.', 50335, 2, 1, 5, 0, 19, 2, 2, 2, 0, 1, 0, 53, 0 );
INSERT INTO weapon VALUES ( 112, 'Sword of Life Stealing', 'This black iron +2 longsword bestows a negative level when it deals a critical hit. The sword wielder gains 1d6 temporary hit points each time a negative level is bestowed on another. These temporary hit points last for 24 hours. One day after being struck, subjects must make a DC 16 Fortitude save for each negative level gained or they become permanent.', 25715, 4, 1, 4, 2, 19, 2, 1, 2, 0, 1, 0, 28, 0 );
INSERT INTO weapon VALUES ( 113, 'Sword of the Planes', 'This longsword has an enhancement bonus of +1 on the Material Plane, but on any Elemental Plane its enhancement bonus increases to +2. The +2 enhancement bonus also applies whenever the weapon is used against creatures native to the Elemental Plane. It operates as a +3 longsword on the Astral Plane and the Ethereal Plane, or when used against opponents native to either of those planes. On any other plane, or against any outsider, it functions as a +4 longsword.', 22315, 4, 1, 4, 1, 19, 2, 1, 2, 0, 1, 0, 28, 0 );
INSERT INTO weapon VALUES ( 114, 'Sword of Subtlety', 'A +1 short sword with a thin, dull gray blade, this weapon provides a +4 bonus on its wielder''s attack and damage rolls when he is making a sneak attack with it.', 22310, 2, 1, 3, 1, 19, 2, 1, 2, 0, 0, 0, 25, 0 );
INSERT INTO weapon VALUES ( 115, 'Sylvan Scimitar', 'This +3 scimitar, when used outdoors in a temperate climate, grants its wielder the use of the Cleave feat and deals an extra 1d6 points of damage.', 47315, 4, 1, 3, 3, 18, 2, 1, 2, 0, 1, 0, 31, 0 );
INSERT INTO weapon VALUES ( 116, 'Trident of Fish Command', 'The magical properties of this +1 trident with a 6-foot-long haft enable its wielder to charm up to 14 HD of aquatic animals as per the spell charm animals (Will DC 16 negates, animals get a +5 bonus if currently under attack by the wielder or his allies), no two of which can be more than 30 feet apart. The wielder can use this effect up to three times per day. The wielder can communicate with the animals as if using a speak with animals spell. Animals making their saving throws are free of control, but they will not approach within 10 feet of the trident.', 18650, 4, 1, 4, 1, 20, 2, 1, 2, 0, 1, 0, 32, 0 );
INSERT INTO weapon VALUES ( 117, 'Trident of Warning', 'A weapon of this type enables its wielder to determine the location, depth, kind, and number of aquatic predators within 680 feet. A trident of warning must be grasped and pointed in order for the character using it to gain such information, and it requires 1 round to scan a hemisphere with a radius of 680 feet. The weapon is otherwise a +2 trident.', 10115, 4, 1, 4, 2, 20, 2, 1, 2, 0, 1, 0, 32, 0 );
INSERT INTO weapon VALUES ( 118, 'Shield, light', 'You can bash with a shield instead of using it for defense.', 0, 0, 1, 1, 0, 20, 2, 1, 0, 0, 0, 0, 73, 0 );
INSERT INTO weapon VALUES ( 119, 'Spiked armor', 'You can outfit your armor with spikes, which can deal damage in a grapple or as a separate attack. See Armor, below, for details.', 0, 0, 1, 3, 0, 20, 2, 1, 0, 0, 0, 0, 75, 0 );
INSERT INTO weapon VALUES ( 120, 'Spiked shield, light', 'You can bash with a spiked shield instead of using it for defense.', 0, 0, 1, 2, 0, 20, 2, 1, 0, 0, 0, 0, 73, 0 );
INSERT INTO weapon VALUES ( 121, 'Shield, heavy', 'You can bash with a shield instead of using it for defense.', 0, 0, 1, 2, 0, 20, 2, 1, 0, 0, 1, 0, 74, 0 );
INSERT INTO weapon VALUES ( 122, 'Spiked shield, heavy', 'You can bash with a spiked shield instead of using it for defense.', 0, 0, 1, 3, 0, 20, 2, 1, 0, 0, 1, 0, 74, 0 );


