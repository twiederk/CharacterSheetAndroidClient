UPDATE race SET id = id + 3 WHERE id >= 8;
UPDATE race_ability SET race_id = race_id + 3 WHERE race_id >= 8;
UPDATE charakter SET race_id = race_id + 3 WHERE race_id >= 8;

UPDATE ability SET id = id + 550 WHERE id >= 534;
UPDATE race_ability SET ability_id = ability_id + 550 WHERE ability_id >= 534;
UPDATE charakter_ability SET ability_id = ability_id + 550 WHERE ability_id >= 534;
UPDATE class_ability SET ability_id = ability_id + 550 WHERE ability_id >= 534;
UPDATE ability_property SET ability_id = ability_id + 550 WHERE ability_id >= 534;

UPDATE charakter_class SET id = id + 21 WHERE id >= 22;
UPDATE charakter_class_skill SET charakter_class_id = charakter_class_id + 21 WHERE charakter_class_id >= 22;
UPDATE charakter_class_level SET charakter_class_id = charakter_class_id + 21 WHERE charakter_class_id >= 22;
UPDATE race SET fav_class_id = fav_class_id + 21 WHERE fav_class_id >= 22;
UPDATE charakter_ability SET class_id = class_id + 21 WHERE class_id >= 22;
UPDATE class_ability SET class_id = class_id + 21 WHERE class_id >= 22;

UPDATE feat SET id = id + 163 WHERE id >= 177;
UPDATE charakter_feat SET feat_id = feat_id + 163 WHERE feat_id >= 177;

UPDATE weapon SET id = id + 41 WHERE id >= 123;
UPDATE weapon_attack SET weapon_id = weapon_id + 41 WHERE weapon_id >= 123;
UPDATE charakter_weapon SET weapon_id = weapon_id + 41 WHERE weapon_id >= 123;

UPDATE armor SET id = id + 26 WHERE id >= 39;
UPDATE charakter_armor SET armor_id = armor_id + 26 WHERE armor_id >= 39;

UPDATE good SET id = id + 93 WHERE id >= 127;
UPDATE charakter_good SET good_id = good_id + 93 WHERE good_id >= 127;

DELETE FROM charakter WHERE race_id NOT IN (SELECT id FROM race);
DELETE FROM race_ability WHERE race_id NOT IN (SELECT id FROM race);

DELETE FROM race_ability WHERE ability_id NOT IN (SELECT id FROM ability);
DELETE FROM charakter_ability WHERE ability_id NOT IN (SELECT id FROM ability);
DELETE FROM class_ability WHERE ability_id NOT IN (SELECT id FROM ability);
DELETE FROM ability_property WHERE ability_id NOT IN (SELECT id FROM ability);

DELETE FROM charakter_class_skill WHERE charakter_class_id NOT IN (SELECT id FROM charakter_class);
DELETE FROM charakter_class_level WHERE charakter_class_id NOT IN (SELECT id FROM charakter_class);
DELETE FROM race WHERE fav_class_id != -1 AND fav_class_id NOT IN (SELECT id FROM charakter_class);
DELETE FROM charakter_ability WHERE class_id NOT IN (SELECT id FROM charakter_class);
DELETE FROM class_ability WHERE class_id NOT IN (SELECT id FROM charakter_class);

DELETE FROM charakter_feat WHERE feat_id NOT IN (SELECT id FROM feat);

DELETE FROM weapon_attack WHERE weapon_id NOT IN (SELECT id FROM weapon);
DELETE FROM charakter_weapon WHERE weapon_id NOT IN (SELECT id FROM weapon);

DELETE FROM charakter_armor WHERE armor_id NOT IN (SELECT id FROM armor);

DELETE FROM charakter_good WHERE good_id NOT IN (SELECT id FROM good);

