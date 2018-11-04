package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.AbilityType;
import com.d20charactersheet.framework.dac.abilitybuilder.AbilityConfig;

/**
 * Maps a data row to a ability instance.
 */
public class AbilityConfigRowMapper extends BaseRowMapper {

    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        final AbilityConfig abilityConfig = new AbilityConfig();
        abilityConfig.setId(cursor.getInt(0));
        abilityConfig.setName(cursor.getString(1));
        abilityConfig.setDescription(cursor.getString(2));
        abilityConfig.setAbilityType((AbilityType) getEnum(cursor.getInt(3), AbilityType.values()));
        abilityConfig.setClassname(cursor.getString(4));
        return abilityConfig;
    }

}
