package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.SpellsPerDayTable;

/**
 * Maps spell per day tables.
 */
public class SpellsPerDayTableRowMapper implements RowMapper {

    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        final SpellsPerDayTable spellsPerDayTable = new SpellsPerDayTable();
        spellsPerDayTable.setId(cursor.getInt(0));
        spellsPerDayTable.setName(cursor.getString(1));
        return spellsPerDayTable;
    }

}
