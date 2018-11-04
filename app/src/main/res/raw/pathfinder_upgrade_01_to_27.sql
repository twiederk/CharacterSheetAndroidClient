UPDATE charakter_skill SET misc_modifier = 0 WHERE skill_id = 4 AND charakter_id = 0;
UPDATE charakter_skill SET misc_modifier = 0 WHERE skill_id = 34 AND charakter_id = 0;

UPDATE spell SET short_description = 'Makes subject drowsy -5 on Perception checks, -2 on Will saves against sleep.' WHERE id = 319;
