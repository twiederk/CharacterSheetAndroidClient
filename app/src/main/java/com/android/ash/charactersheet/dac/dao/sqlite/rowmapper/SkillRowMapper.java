package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.Attribute;
import com.d20charactersheet.framework.boc.model.Skill;

/**
 * Maps character independent skill data to a skill.
 */
public class SkillRowMapper extends BaseRowMapper {

    /**
     * Maps character independent skill data to a skill.
     * 
     * @see com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.BaseRowMapper#mapRow(android.database.Cursor)
     */
    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        final Skill skill = new Skill();
        skill.setId(cursor.getInt(0));
        skill.setAttribute((Attribute) getEnum(cursor.getInt(1), Attribute.values()));
        skill.setUntrained(getBoolean(cursor.getInt(2)));
        skill.setName(cursor.getString(3));
        return skill;
    }
}
