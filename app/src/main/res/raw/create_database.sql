CREATE TABLE sex(id             INTEGER, name           VARCHAR(6) NOT NULL, PRIMARY KEY (id));CREATE TABLE alignment(id       INTEGER, name     VARCHAR(15) NOT NULL, PRIMARY KEY (id));CREATE TABLE attribute(id            INTEGER, name          VARCHAR(15), PRIMARY KEY (id));CREATE TABLE size(id             INTEGER, name           VARCHAR(15), PRIMARY KEY(id));CREATE TABLE die(id             INTEGER, name           VARCHAR(15), PRIMARY KEY(id));CREATE TABLE xp_table(id             INTEGER, name           VARCHAR(15), PRIMARY KEY(id));CREATE TABLE xp_level(id             INTEGER, xp_table_id    INTEGER, level          INTEGER NOT NULL, xp             INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(id) FOREIGN KEY (xp_table_id) REFERENCES xp_table(id) ON DELETE CASCADE);CREATE TABLE race(id				   INTEGER, name              VARCHAR(30), size_id           INTEGER, base_land_speed   INTEGER, fav_class_id      INTEGER, image_id          INTEGER NOT NULL DEFAULT 0, PRIMARY KEY (id));CREATE TABLE ability_type(id        INTEGER, name      VARCHAR(30), PRIMARY KEY(id));CREATE TABLE ability(id                INTEGER, name              VARCHAR(64), description       VARCHAR(4096), ability_type_id   INTEGER, classname         VARCHAR(50) DEFAULT 'Ability', PRIMARY KEY(id), FOREIGN KEY (ability_type_id) REFERENCES ability_type(id) ON DELETE CASCADE);CREATE TABLE ability_property(ability_id      INTEGER, property_key    VARCHAR(50), property_value  VARCHAR(50), PRIMARY KEY(ability_id, property_key), FOREIGN KEY (ability_id) REFERENCES ability_(id) ON DELETE CASCADE);CREATE TABLE race_ability(race_id   INTEGER, ability_id  INTEGER, PRIMARY KEY(race_id, ability_id), FOREIGN KEY (race_id) REFERENCES race(id) ON DELETE CASCADE, FOREIGN KEY (ability_id) REFERENCES ability(id) ON DELETE CASCADE);CREATE TABLE image(id             INTEGER, image          BLOB NOT NULL, PRIMARY KEY(id));CREATE TABLE skill(id           INTEGER, name         VARCHAR(50), description  VARCHAR(10000), attribute_id INTEGER, untrained    INTEGER, PRIMARY KEY(id), FOREIGN KEY (attribute_id) REFERENCES attribute(id));CREATE TABLE class(id                       INTEGER, name                     VARCHAR(30), saves                    INTEGER DEFAULT 0, alignments               INTEGER DEFAULT 0, base_attack_bonus        INTEGER, skill_points_per_level   INTEGER, hit_die_id               INTEGER, image_id                 INTEGER DEFAULT 0, PRIMARY KEY (id));CREATE TABLE class_skill(class_id              INTEGER, skill_id              INTEGER, PRIMARY KEY (class_id, skill_id), FOREIGN KEY (class_id) REFERENCES class(id) ON DELETE CASCADE, FOREIGN KEY (skill_id) REFERENCES skill(id) ON DELETE CASCADE);CREATE TABLE class_ability(class_id      INTEGER, ability_id    INTEGER, level         INTEGER, PRIMARY KEY(class_id, ability_id), FOREIGN KEY (class_id) REFERENCES class(id) ON DELETE CASCADE, FOREIGN KEY (ability_id) REFERENCES ability(id) ON DELETE CASCADE);CREATE TABLE class_starter_pack_box(id            INTEGER, class_id      INTEGER, name          VARCHAR(32), PRIMARY KEY(id), FOREIGN KEY (class_id) REFERENCES class(id) ON DELETE CASCADE);CREATE TABLE class_starter_pack_option_query(id                          INTEGER, starter_pack_box_id         INTEGER, starter_pack_box_option_id  INTEGER, equipment_type_id           INTEGER, type_id                     INTEGER, combat_id                   INTEGER, item_id                     INTEGER, quantity                    INTEGER, PRIMARY KEY(id), FOREIGN KEY (starter_pack_box_id) REFERENCES class_starter_pack_box(id) ON DELETE CASCADE);CREATE TABLE feat_type(id            INTEGER, name          VARCHAR(15), PRIMARY KEY (id));CREATE TABLE feat(id				INTEGER, name           VARCHAR(30), prerequisit    VARCHAR(150), benefit        VARCHAR(150), feat_type_id   INTEGER not null, fighter        INTEGER, multiple       INTEGER, stack          INTEGER, spell_slot     INTEGER DEFAULT 0, PRIMARY KEY(id), FOREIGN KEY (feat_type_id) REFERENCES feat_type(id) ON DELETE CASCADE);CREATE TABLE quality_type(id        INTEGER, name      VARCHAR(30), PRIMARY KEY(id));CREATE TABLE weapon_type(id       INTEGER, name     VARCHAR(6) NOT NULL, PRIMARY KEY (id));CREATE TABLE combat_type(id       INTEGER, name     VARCHAR(16) NOT NULL, PRIMARY KEY (id));CREATE TABLE weapon_encumbrance(id       INTEGER, name     VARCHAR(32) NOT NULL, PRIMARY KEY (id));CREATE TABLE weapon_category(id       INTEGER, name     VARCHAR(32) NOT NULL, PRIMARY KEY (id));CREATE TABLE weapon_family(id       INTEGER, name     VARCHAR(32) NOT NULL, PRIMARY KEY (id));CREATE TABLE attack_wield(id       INTEGER, name     VARCHAR(32) NOT NULL, PRIMARY KEY (id));CREATE TABLE weapon(id                      INTEGER, name                    VARCHAR(30), description             VARCHAR(2048), cost                    FLOAT DEFAULT 0, weight                  FLOAT DEFAULT 0, number_of_dice          INTEGER DEFAULT 0, die_id                  INTEGER DEFAULT 0, enhancement_bonus       INTEGER DEFAULT 0, critical_hit            INTEGER DEFAULT 0, critical_mod            INTEGER DEFAULT 0, weapon_type_id          INTEGER DEFAULT 0, quality_type_id         INTEGER DEFAULT 0, combat_type_id          INTEGER DEFAULT 0, weapon_encumbrance_id   INTEGER DEFAULT 0, weapon_category_id      INTEGER DEFAULT 0, weapon_family_id        INTEGER DEFAULT 1, range_increment         INTEGER DEFAULT 0, PRIMARY KEY (id), FOREIGN KEY (die_id) REFERENCES die(id), FOREIGN KEY (weapon_type_id) REFERENCES weapon_type(id) FOREIGN KEY (quality_type_id) REFERENCES quality_type(id) FOREIGN KEY (combat_type_id) REFERENCES combat_type(id) FOREIGN KEY (weapon_encumbrance_id) REFERENCES weapon_encumbrance(id) FOREIGN KEY (weapon_category_id) REFERENCES weapon_category(id) FOREIGN KEY (weapon_family_id) REFERENCES weapon_family(id));CREATE TABLE armor_type(id       INTEGER, name     VARCHAR(6) NOT NULL, PRIMARY KEY (id));CREATE TABLE armor(id               INTEGER, name             VARCHAR(30), description      VARCHAR(2048), cost             FLOAT, weight           FLOAT, armor_bonus      INTEGER, armor_penalty    INTEGER, armor_type_id    INTEGER, quality_type_id  INTEGER DEFAULT 0, PRIMARY KEY (id), FOREIGN KEY (armor_type_id) REFERENCES armor_type(id) FOREIGN KEY (quality_type_id) REFERENCES quality_type(id));CREATE TABLE good_type(id       INTEGER, name     VARCHAR(8) NOT NULL, PRIMARY KEY (id));CREATE TABLE good(id               INTEGER, name             VARCHAR(30), description      VARCHAR(2048), cost             FLOAT, weight           FLOAT, good_type_id     INTEGER, quality_type_id  INTEGER DEFAULT 0, PRIMARY KEY (id), FOREIGN KEY (good_type_id) REFERENCES good_type(id) FOREIGN KEY (quality_type_id) REFERENCES quality_type(id));CREATE TABLE equipment_pack(id               INTEGER, name             VARCHAR(30), PRIMARY KEY (id));CREATE TABLE equipment_pack_item_group(id                INTEGER, equipment_pack_id INTEGER, good_id           INTEGER, quantity          INTEGER, PRIMARY KEY (id), FOREIGN KEY (equipment_pack_id) REFERENCES equipment_pack(id) FOREIGN KEY (good_id) REFERENCES good(id));CREATE TABLE spelllist(id             INTEGER, name           VARCHAR(20), shortname      VARCHAR(20), domain         INTEGER DEFAULT 0, min_level      INTEGER DEFAULT 0, max_level      INTEGER DEFAULT 9, PRIMARY KEY (id));CREATE TABLE spell(id                 INTEGER, name               VARCHAR(80), school             VARCHAR(256), components         INTEGER, casting_time       VARCHAR(30), range              VARCHAR(80), effect             VARCHAR(256), duration           VARCHAR(100), saving_throw       VARCHAR(80), spell_resistance   VARCHAR(60), short_description  VARCHAR(120), description        VARCHAR(8096), mat_component      VARCHAR(256), focus              VARCHAR(256), PRIMARY KEY (id));CREATE TABLE spelllist_spell(spelllist_id  INTEGER, spell_id      INTEGER, level         INTEGER, PRIMARY KEY(spelllist_id, spell_id), FOREIGN KEY (spelllist_id) REFERENCES spelllist(id) ON DELETE CASCADE, FOREIGN KEY (spell_id) REFERENCES spell(id) ON DELETE CASCADE);CREATE TABLE charakter(id 			      INTEGER, player 		    VARCHAR(30) NOT NULL, name 			    VARCHAR(30) NOT NULL, race_id       	INTEGER NOT NULL, sex_id         INTEGER NOT NULL, alignment_id  	INTEGER NOT NULL, xp_table_id    INTEGER NOT NULL DEFAULT 1, experience 	  INTEGER NOT NULL, strength   	  INTEGER NOT NULL, dexterity  	  INTEGER NOT NULL, constitution 	INTEGER NOT NULL, intelligence	  INTEGER NOT NULL, wisdom			    INTEGER NOT NULL, charisma		    INTEGER NOT NULL, hitpoints		  INTEGER NOT NULL, max_hitpoints	INTEGER NOT NULL, armor      	  INTEGER NOT NULL, ini_misc_mod   INTEGER NOT NULL, cmb_mod    INTEGER NOT NULL DEFAULT 0, cmd_mod    INTEGER NOT NULL DEFAULT 0, save_str_misc_mod  INTEGER NOT NULL DEFAULT 0, save_dex_misc_mod  INTEGER NOT NULL DEFAULT 0, save_con_misc_mod  INTEGER NOT NULL DEFAULT 0, save_int_misc_mod  INTEGER NOT NULL DEFAULT 0, save_wis_misc_mod  INTEGER NOT NULL DEFAULT 0, save_cha_misc_mod  INTEGER NOT NULL DEFAULT 0, image_id       INTEGER, thumb_image_id INTEGER, platinum       INTEGER NOT NULL DEFAULT 0, gold           INTEGER NOT NULL DEFAULT 0, silver         INTEGER NOT NULL DEFAULT 0, copper         INTEGER NOT NULL DEFAULT 0, PRIMARY KEY (id), FOREIGN KEY (race_id) REFERENCES race(id) ON DELETE CASCADE, FOREIGN KEY (sex_id) REFERENCES sex(id) ON DELETE CASCADE, FOREIGN KEY (alignment_id) REFERENCES alignment(id) ON DELETE CASCADE, FOREIGN KEY (image_id) REFERENCES image(id) ON DELETE CASCADE, FOREIGN KEY (thumb_image_id) REFERENCES image(id) ON DELETE CASCADE);CREATE TABLE charakter_class_level(id                     INTEGER, charakter_id           INTEGER, class_id		        INTEGER, level			        INTEGER, PRIMARY KEY (id), FOREIGN KEY (charakter_id) REFERENCES charakter(id) ON DELETE CASCADE, FOREIGN KEY (class_id) REFERENCES class(id) ON DELETE CASCADE);CREATE TABLE charakter_ability(id                 INTEGER, charakter_id       INTEGER, class_id           INTEGER, ability_id         INTEGER, owned              INTEGER DEFAULT 1, PRIMARY KEY (id), FOREIGN KEY (charakter_id) REFERENCES charakter (id), FOREIGN KEY (class_id) REFERENCES class (id), FOREIGN KEY (ability_id) REFERENCES ability (id));CREATE TABLE charakter_skill(skill_id          INTEGER, charakter_id      INTEGER, rank              FLOAT, misc_modifier     INTEGER, favorite          INTEGER, PRIMARY KEY (skill_id, charakter_id), FOREIGN KEY (skill_id) REFERENCES skill(id), FOREIGN KEY (charakter_id) REFERENCES charakter(id));CREATE TABLE charakter_feat(id                INTEGER, feat_id           INTEGER, charakter_id      INTEGER, category          VARCHAR(150), PRIMARY KEY(id), FOREIGN KEY (feat_id) REFERENCES feat(id), FOREIGN KEY (charakter_id) REFERENCES charakter(id));CREATE TABLE charakter_weapon_attack(id                    INTEGER, charakter_id          INTEGER, name                  VARCHAR(32), description           VARCHAR(2048), attack_wield_id       INTEGER DEFAULT 0, weapon_id             INTEGER DEFAULT 0, ammunition            INTEGER DEFAULT 0, attack_bonus_modifier INTEGER DEFAULT 0, damage_bonus_modifier INTEGER DEFAULT 0, PRIMARY KEY (id), FOREIGN KEY (charakter_id) REFERENCES charakter(id), FOREIGN KEY (attack_wield_id) REFERENCES attack_wield(id), FOREIGN KEY (weapon_id) REFERENCES weapon(id));CREATE TABLE charakter_weapon(id             INTEGER, charakter_id   INTEGER, weapon_id      INTEGER, number         INTEGER, PRIMARY KEY (id), FOREIGN KEY (charakter_id) REFERENCES charakter(id), FOREIGN KEY (weapon_id) REFERENCES weapon(id));CREATE TABLE charakter_armor(id             INTEGER, charakter_id   INTEGER, armor_id       INTEGER, number         INTEGER, PRIMARY KEY (id), FOREIGN KEY (charakter_id) REFERENCES charakter(id), FOREIGN KEY (armor_id) REFERENCES armor(id));CREATE TABLE charakter_good(id             INTEGER, charakter_id   INTEGER, good_id        INTEGER, number         INTEGER, PRIMARY KEY (id), FOREIGN KEY (charakter_id) REFERENCES charakter(id), FOREIGN KEY (good_id) REFERENCES good(id));CREATE TABLE charakter_note(id               INTEGER, charakter_id     INTEGER, date             VARCHAR(25), text             INTEGER, PRIMARY KEY (id), FOREIGN KEY (charakter_id) REFERENCES charakter(id));CREATE TABLE charakter_known_spell(id                INTEGER NOT NULL, charakter_id      INTEGER NOT NULL, spelllist_id      INTEGER NOT NULL, spell_id          INTEGER NOT NULL, PRIMARY KEY(id), FOREIGN KEY (charakter_id) REFERENCES charakter(id), FOREIGN KEY (spelllist_id) REFERENCES spelllist(id), FOREIGN KEY (spell_id) REFERENCES spell(id));CREATE TABLE known_spells_table(id                INTEGER NOT NULL, name              VARCHAR(15), PRIMARY KEY (id));CREATE TABLE known_spells_level(id                       INTEGER NOT NULL, known_spells_table_id    INTEGER NOT NULL, spellcaster_level        INTEGER NOT NULL, level_0                  INTEGER NOT NULL DEFAULT 0, level_1                  INTEGER NOT NULL DEFAULT 0, level_2                  INTEGER NOT NULL DEFAULT 0, level_3                  INTEGER NOT NULL DEFAULT 0, level_4                  INTEGER NOT NULL DEFAULT 0, level_5                  INTEGER NOT NULL DEFAULT 0, level_6                  INTEGER NOT NULL DEFAULT 0, level_7                  INTEGER NOT NULL DEFAULT 0, level_8                  INTEGER NOT NULL DEFAULT 0, level_9                  INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(id) FOREIGN KEY (known_spells_table_id) REFERENCES known_spells_table(id) ON DELETE CASCADE);CREATE TABLE spells_per_day_table(id                INTEGER NOT NULL, name              VARCHAR(15), PRIMARY KEY (id));CREATE TABLE spells_per_day_level(id                       INTEGER NOT NULL, spells_per_day_table_id  INTEGER NOT NULL, spellcaster_level        INTEGER NOT NULL, level_0                  INTEGER NOT NULL DEFAULT 0, level_1                  INTEGER NOT NULL DEFAULT 0, level_2                  INTEGER NOT NULL DEFAULT 0, level_3                  INTEGER NOT NULL DEFAULT 0, level_4                  INTEGER NOT NULL DEFAULT 0, level_5                  INTEGER NOT NULL DEFAULT 0, level_6                  INTEGER NOT NULL DEFAULT 0, level_7                  INTEGER NOT NULL DEFAULT 0, level_8                  INTEGER NOT NULL DEFAULT 0, level_9                  INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(id) FOREIGN KEY (spells_per_day_table_id) REFERENCES spells_per_day_table(id) ON DELETE CASCADE);CREATE TABLE charakter_spell_slot(id             INTEGER NOT NULL, charakter_id   INTEGER NOT NULL, spell_id       INTEGER NOT NULL, level          INTEGER NOT NULL, cast           INTEGER NOT NULL, PRIMARY KEY(id) FOREIGN KEY (charakter_id) REFERENCES charakter(id) ON DELETE CASCADE FOREIGN KEY (spell_id) REFERENCES spell(id) ON DELETE CASCADE);CREATE TABLE charakter_spell_slot_ability(id             INTEGER NOT NULL, spell_slot_id  INTEGER NOT NULL, ability_id     INTEGER NOT NULL, PRIMARY KEY(id) FOREIGN KEY (spell_slot_id) REFERENCES spell_slot(id) ON DELETE CASCADE FOREIGN KEY (ability_id) REFERENCES ability(id) ON DELETE CASCADE);CREATE TABLE charakter_spell_slot_feat(id             INTEGER NOT NULL, spell_slot_id  INTEGER NOT NULL, feat_id        INTEGER NOT NULL, PRIMARY KEY(id) FOREIGN KEY (spell_slot_id) REFERENCES spell_slot(id) ON DELETE CASCADE FOREIGN KEY (feat_id) REFERENCES feat(id) ON DELETE CASCADE);CREATE TABLE charakter_body_part(id             INTEGER NOT NULL, charakter_id   INTEGER NOT NULL, body_part_id   INTEGER NOT NULL, item_id        INTEGER NOT NULL, itemclass      VARCHAR(10), PRIMARY KEY(id) FOREIGN KEY (charakter_id) REFERENCES charakter(id) ON DELETE CASCADE FOREIGN KEY (item_id) REFERENCES item(id) ON DELETE CASCADE);