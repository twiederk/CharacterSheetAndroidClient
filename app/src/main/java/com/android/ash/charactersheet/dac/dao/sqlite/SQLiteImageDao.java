package com.android.ash.charactersheet.dac.dao.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.dac.dao.ImageDao;

/**
 * SQLite specific implementation of the ImageDao interface.
 */
public class SQLiteImageDao extends BaseSQLiteDao implements ImageDao {

    private static final String SQL_GET_IMAGE = SELECT + COLUMN_IMAGE + " FROM " + TABLE_IMAGE + " WHERE " + COLUMN_ID
            + " = ?";
    private static final String SQL_GET_ID = SELECT + COLUMN_ID + " FROM " + TABLE_IMAGE + " WHERE rowid = ?";

    /**
     * Instanciates ImageDaoSQLite with the given context containing the database.
     * 
     * @param db
     *            The database to access.
     */
    public SQLiteImageDao(final SQLiteDatabase db) {
        super(db);
    }

    /**
     * @see com.d20charactersheet.framework.dac.dao.ImageDao#getImage(int)
     */
    @Override
    public byte[] getImage(final int imageId) {
        final String[] bindVariables = new String[1];
        bindVariables[0] = Integer.toString(imageId);
        Cursor cursor = null;
        byte[] data = null;
        try {
            cursor = db.rawQuery(SQL_GET_IMAGE, bindVariables);
            if (cursor.getCount() == 1) {
                cursor.moveToFirst();
                data = cursor.getBlob(0);
            }
        } catch (final SQLException sqlException) {
            Logger.error("Can't get image width imageId: " + imageId);
        } finally {
            close(cursor);
        }
        return data;
    }

    /**
     * @see com.d20charactersheet.framework.dac.dao.ImageDao#createImage(byte[])
     */
    @Override
    public int createImage(final byte[] imageData) {
        final ContentValues values = new ContentValues();
        values.putNull(COLUMN_ID);
        values.put(COLUMN_IMAGE, imageData);
        final long rowId;
        synchronized (DBHelper.DB_LOCK) {
            rowId = db.insertOrThrow(TABLE_IMAGE, null, values);
        }
        final int imageId = getId(SQL_GET_ID, rowId);
        return imageId;
    }

    /**
     * @see com.d20charactersheet.framework.dac.dao.ImageDao#deleteImage(int)
     */
    @Override
    public void deleteImage(final int imageId) {
        final String[] bindVariables = new String[1];
        bindVariables[0] = Integer.toString(imageId);
        synchronized (DBHelper.DB_LOCK) {
            final int deletedRows = db.delete(TABLE_IMAGE, SQL_WHERE_ID, bindVariables);
            if (deletedRows != 1) {
                throw new IllegalStateException("Too many or to less deleted rows: " + deletedRows
                        + ". Expected one deleted row!");
            }
        }
    }

}
