package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import java.util.List;

import android.database.Cursor;
import android.database.SQLException;

import com.android.ash.charactersheet.boc.model.FavoriteCharacterSkill;
import com.d20charactersheet.framework.boc.model.Skill;

/**
 * Maps the character dependent data to a skill.
 */
public class CharacterSkillRowMapper extends BaseRowMapper {

    private final List<Skill> allSkills;

    /**
     * Creates a CharacterSkillRowMapper mapping data to character skills. The list of all skills is necessary to create
     * the CharacterSkill with the skill of the game system.
     * 
     * @param allSkills
     *            All skills of the game system.
     */
    public CharacterSkillRowMapper(final List<Skill> allSkills) {
        super();
        this.allSkills = allSkills;
    }

    /**
     * Maps the character dependent data to a skill.
     * 
     * @see com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.SkillRowMapper#mapRow(android.database.Cursor)
     */
    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        // skill_id, rank, misc_modifier, favorite
        final Skill skill = getSkill(cursor.getInt(0));
        final FavoriteCharacterSkill characterSkill = new FavoriteCharacterSkill(skill);
        characterSkill.setRank(cursor.getFloat(1));
        characterSkill.setModifier(cursor.getInt(2));
        characterSkill.setFavorite(getBoolean(cursor.getInt(3)));
        return characterSkill;
    }

    private Skill getSkill(final int id) {
        for (final Skill skill : allSkills) {
            if (skill.getId() == id) {
                return skill;
            }
        }
        throw new IllegalArgumentException("Can't get skill with id: " + id);
    }

}
