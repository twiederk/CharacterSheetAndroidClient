package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import android.database.Cursor;
import android.database.SQLException;

/**
 * Maps data of a database row to an object (ORM).
 */
public interface RowMapper {

    /**
     * Maps the rows of the cursor to an object.
     * 
     * @param cursor
     *            The cursor pointing to the rows.
     * @return The mapped object.
     * @throws SQLException
     *             Thrown by wrong cursor handling.
     */
    public Object mapRow(Cursor cursor) throws SQLException;

}
