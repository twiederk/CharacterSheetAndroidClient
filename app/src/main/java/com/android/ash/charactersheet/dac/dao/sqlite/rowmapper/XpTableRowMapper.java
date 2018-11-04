package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.XpTable;

/**
 * Maps a data row to a new XpTable instance.
 */
public class XpTableRowMapper extends BaseRowMapper implements RowMapper {

    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        final XpTable xpTable = new XpTable();
        xpTable.setId(cursor.getInt(0));
        xpTable.setName(cursor.getString(1));
        return xpTable;
    }

}
