package com.android.ash.charactersheet.dac.dao.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.RowMapper;
import com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.XpTableRowMapper;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.XpTable;
import com.d20charactersheet.framework.dac.dao.XpDao;

/**
 * Create data access object to access xp tables in SQLite3 database.
 */
public class SQLiteXpDao extends BaseSQLiteDao implements XpDao {

    /**
     * Instanciates SQLiteXpDao.
     * 
     * @param db
     *            The database to access.
     */
    public SQLiteXpDao(final SQLiteDatabase db) {
        super(db);
    }

    @Override
    public List<XpTable> getAllXpTables() {
        final List<XpTable> allXpTables = new ArrayList<XpTable>();
        Cursor cursor = null;
        try {
            final RowMapper xpTableRowMapper = new XpTableRowMapper();
            cursor = db.rawQuery(SQL_GET_ALL_XP_TABLES, new String[0]);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                final XpTable xpTable = (XpTable) xpTableRowMapper.mapRow(cursor);
                final int[] levels = selectLevels(xpTable);
                xpTable.setLevelTable(levels);
                allXpTables.add(xpTable);
            }
        } catch (final SQLException sqlException) {
            Logger.error("Can't get all xp tables", sqlException);
        } finally {
            close(cursor);
        }
        return allXpTables;
    }

    private int[] selectLevels(final XpTable xpTable) {
        Cursor cursor = null;
        final List<Integer> levelList = new ArrayList<Integer>(20);
        try {
            final String[] xpTableId = new String[] { Integer.toString(xpTable.getId()) };
            cursor = db.rawQuery(SQL_GET_XP_LEVELS, xpTableId);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                levelList.add(cursor.getInt(0) - 1, cursor.getInt(1));
            }
        } catch (final SQLException sqlException) {
            Logger.error("Can't get all xp tables", sqlException);
        } finally {
            close(cursor);
        }

        // convert from List<Integer> to int[]
        final int[] levels = new int[levelList.size()];
        for (int i = 0; i < levels.length; i++) {
            levels[i] = levelList.get(i);
        }
        return levels;
    }
}
