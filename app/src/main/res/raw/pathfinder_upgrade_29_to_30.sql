DELETE FROM charakter_weapon WHERE weapon_id NOT IN (SELECT id FROM weapon);
