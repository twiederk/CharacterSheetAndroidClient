package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import java.util.EnumSet;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.Alignment;
import com.d20charactersheet.framework.boc.model.BaseAttackBonus;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.Die;
import com.d20charactersheet.framework.boc.model.Save;

/**
 * Maps a data row to a character class.
 */
public class ClassRowMapper extends BaseRowMapper {

    /**
     * Maps a data row to a character class.
     * 
     * @see com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.BaseRowMapper#mapRow(android.database.Cursor)
     */
    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        // id, name, base_attack_bonus, saves, alignments, skill_points_per_level, hit_die_id
        final CharacterClass characterClass = new CharacterClass();
        characterClass.setId(cursor.getInt(0));
        characterClass.setName(cursor.getString(1));
        final EnumSet<Save> saves = getEnums(cursor.getInt(2), Save.class);
        characterClass.setSaves(saves);
        final EnumSet<Alignment> alignments = getEnums(cursor.getInt(3), Alignment.class);
        characterClass.setAlignments(alignments);
        characterClass.setBaseAttackBonus((BaseAttackBonus) getEnum(cursor.getInt(4), BaseAttackBonus.values()));
        characterClass.setSkillPointsPerLevel(cursor.getInt(5));
        characterClass.setHitDie((Die) getEnum(cursor.getInt(6), Die.values()));
        return characterClass;
    }

}
