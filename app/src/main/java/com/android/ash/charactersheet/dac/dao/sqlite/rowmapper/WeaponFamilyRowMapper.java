package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.WeaponFamily;

/**
 * Maps a data row to a new WeaponFamily instance.
 */
public class WeaponFamilyRowMapper extends BaseRowMapper {

    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        final WeaponFamily weaponFamily = new WeaponFamily();
        weaponFamily.setId(cursor.getInt(0));
        weaponFamily.setName(cursor.getString(1));
        return weaponFamily;
    }

}
