package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.KnownSpellsTable;

/**
 * Maps raw data to known spells table.
 */
public class KnownSpellsTableRowMapper implements RowMapper {

    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        final KnownSpellsTable knownSpellsTable = new KnownSpellsTable();
        knownSpellsTable.setId(cursor.getInt(0));
        knownSpellsTable.setName(cursor.getString(1));
        return knownSpellsTable;
    }

}
