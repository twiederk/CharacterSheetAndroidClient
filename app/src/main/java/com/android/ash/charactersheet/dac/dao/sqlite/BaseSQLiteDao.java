package com.android.ash.charactersheet.dac.dao.sqlite;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.dac.dao.TableAndColumnNames;
import com.android.ash.charactersheet.gui.util.Logger;

/**
 * Base class to extend SQLite specific Dao from. Supplies methods required by all Daos. Contains constants representing
 * database specific objects like tables and columns.
 */
public class BaseSQLiteDao implements TableAndColumnNames {

    SQLiteDatabase db;

    /**
     * Create BaseSQLiteDao with given database.
     * 
     * @param db
     *            The database to access.
     */
    public BaseSQLiteDao(final SQLiteDatabase db) {
        this.db = db;
    }

    int getId(final String sql, final long rowId) {
        final String[] bindVariables = new String[1];
        bindVariables[0] = Long.toString(rowId);
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql, bindVariables);
            cursor.moveToFirst();
            final int id = cursor.getInt(0);
            return id;
        } catch (final SQLException sqlException) {
            Logger.error("Can't get id of rowId: " + rowId);
            throw sqlException;
        } finally {
            close(cursor);
        }
    }

    void close(final Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

}
