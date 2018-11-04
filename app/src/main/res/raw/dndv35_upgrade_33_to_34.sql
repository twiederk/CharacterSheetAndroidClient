UPDATE weapon SET weapon_encumbrance_id = weapon_encumbrance_id - 3 WHERE weapon_encumbrance_id > 2;

ALTER TABLE weapon_attack ADD COLUMN attack_bonus_modifier INTEGER DEFAULT 0;
ALTER TABLE weapon_attack ADD COLUMN damage_bonus_modifier INTEGER DEFAULT 0;
UPDATE weapon_attack SET attack_bonus_modifier = 0, damage_bonus_modifier = 0;

