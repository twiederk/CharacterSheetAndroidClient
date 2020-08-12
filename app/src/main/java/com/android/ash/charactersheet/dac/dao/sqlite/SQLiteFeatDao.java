package com.android.ash.charactersheet.dac.dao.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.Feat;
import com.d20charactersheet.framework.dac.dao.FeatDao;
import com.d20charactersheet.framework.dac.dao.RowMapper;
import com.d20charactersheet.framework.dac.dao.sql.rowmapper.FeatRowMapper;

import java.util.ArrayList;
import java.util.List;

import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_BENEFIT;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_FEAT_TYPE_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_FIGHTER_BONUS;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_MULTIPLE;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_NAME;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_PREREQUISITE;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_SPELL_SLOT;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_STACK;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.SELECT;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.SQL_GET_ALL_FEATS;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.SQL_WHERE_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.TABLE_FEAT;

/**
 * Implementation of FeatDao to access data in a SQLite database on an Android device.
 */
public class SQLiteFeatDao extends BaseSQLiteDao implements FeatDao {

    private static final String SQL_GET_ID = SELECT + "id FROM " + TABLE_FEAT + " WHERE rowid = ?";

    private final RowMapper featRowMapper;

    /**
     * Creates DAO with application context.
     * 
     * @param db
     *            The database to access.
     */
    public SQLiteFeatDao(final SQLiteDatabase db) {
        super(db);
        featRowMapper = new FeatRowMapper();
    }

    /**
     * Returns all feats. Caches feats after first request.
     * 
     * @see com.d20charactersheet.framework.dac.dao.FeatDao#getAllFeats()
     */
    @Override
    public List<Feat> getAllFeats() {
        final List<Feat> feats = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(SQL_GET_ALL_FEATS, new String[0]);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                final Feat staticFeat = (Feat) featRowMapper.mapRow(new SQLiteDataRow(cursor));
                feats.add(staticFeat);
            }
        } catch (final SQLException | java.sql.SQLException sqlException) {
            Logger.error("Can't get all feats", sqlException);
        } finally {
            close(cursor);
        }
        return feats;
    }

    /**
     * @see com.d20charactersheet.framework.dac.dao.FeatDao#createFeat(com.d20charactersheet.framework.boc.model.Feat)
     */
    @Override
    public Feat createFeat(final Feat feat) {
        insertFeatTable(feat);
        return feat;
    }

    private void insertFeatTable(final Feat feat) {
        final ContentValues featValues = getFeatValues(feat);
        featValues.putNull(COLUMN_ID);
        synchronized (DBHelper.DB_LOCK) {
            final long rowId = db.insertOrThrow(TABLE_FEAT, null, featValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert feat in feat table");
            }
            feat.setId(getId(SQL_GET_ID, rowId));
        }
    }

    private ContentValues getFeatValues(final Feat feat) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, feat.getName());
        contentValues.put(COLUMN_BENEFIT, feat.getBenefit());
        contentValues.put(COLUMN_PREREQUISITE, feat.getPrerequisit());
        contentValues.put(COLUMN_FEAT_TYPE_ID, feat.getFeatType().ordinal());
        contentValues.put(COLUMN_FIGHTER_BONUS, feat.isFighterBonus());
        contentValues.put(COLUMN_MULTIPLE, feat.isMultiple());
        contentValues.put(COLUMN_STACK, feat.isStack());
        contentValues.put(COLUMN_SPELL_SLOT, feat.getSpellSlot());
        return contentValues;
    }

    @Override
    public Feat updateFeat(final Feat feat) {
        updateFeatTable(feat);
        return feat;
    }

    private void updateFeatTable(final Feat feat) {
        final ContentValues featValues = getFeatValues(feat);
        final String[] bindVariables = new String[] { Integer.toString(feat.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            final int numberOfAffectedRows = db.update(TABLE_FEAT, featValues, SQL_WHERE_ID, bindVariables);
            if (numberOfAffectedRows != 1) {
                throw new SQLException("More or Less than 1 row affected: " + numberOfAffectedRows);
            }
        }
    }

    /**
     * @see com.d20charactersheet.framework.dac.dao.FeatDao#deleteFeat(com.d20charactersheet.framework.boc.model.Feat)
     */
    @Override
    public void deleteFeat(final Feat feat) {

        // delete from feat table
        final String[] featId = new String[] { Integer.toString(feat.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            final int numberOfAffectedRows = db.delete(TABLE_FEAT, COLUMN_ID + " = ?", featId);
            if (numberOfAffectedRows == 0) {
                throw new SQLException("Can't delete feat: " + feat);
            }
        }
    }

}
