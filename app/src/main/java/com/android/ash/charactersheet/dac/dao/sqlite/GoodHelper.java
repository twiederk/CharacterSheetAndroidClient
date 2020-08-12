package com.android.ash.charactersheet.dac.dao.sqlite;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.Good;
import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.ItemGroup;
import com.d20charactersheet.framework.dac.dao.sql.rowmapper.GoodRowMapper;

import java.util.ArrayList;
import java.util.List;

import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_COST;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_DESCRIPTION;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_GOOD_TYPE_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_NAME;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_QUALITY_TYPE_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_WEIGHT;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.FROM;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.SELECT;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.TABLE_CHARAKTER_GOOD;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.TABLE_GOOD;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.WHERE;

/**
 * Helper class of the ItemDao. Handles all database activities concerning goods. Its like an GoodDao.
 */
class GoodHelper extends ItemHelper {

    private static final String SQL_GET_ALL_GOODS = SELECT
            + "id, name, description, cost, weight, good_type_id, quality_type_id FROM " + TABLE_GOOD;

    private static final String SQL_GET_CHARACTER_GOODS = SELECT + "id, good_id, number " //
            + FROM + TABLE_CHARAKTER_GOOD + " " //
            + WHERE + "charakter_id = ?";

    private static final String SQL_GET_GOOD_ID = SELECT + "id FROM " + TABLE_GOOD + " WHERE rowid = ?";

    private final GoodRowMapper goodRowMapper;

    /**
     * Creates GoodHelper to help with given database and using helper with basic item tasks.
     * 
     * @param db
     *            The database to connect to.
     * @param helper
     *            The helper for basic item tasks.
     */
    public GoodHelper(final SQLiteDatabase db, final SQLiteItemDaoHelper helper) {
        super(db, helper);
        goodRowMapper = new GoodRowMapper();
    }

    /**
     * Returns all available goods.
     * 
     * @return All available goods.
     */
    public List<Good> getAllGoods() {
        final List<Item> allItems = getAllItems(SQL_GET_ALL_GOODS, goodRowMapper);
        final List<Good> allGoods = new ArrayList<>();
        for (final Item item : allItems) {
            allGoods.add((Good) item);
        }
        return allGoods;
    }

    /**
     * Returns the goods of the character.
     * 
     * @param character
     *            The character to get its goods.
     * @param allGoods
     *            All available goods.
     * @return The goods of the character.
     */
    public List<ItemGroup> getGoods(final Character character, final List<Good> allGoods) {
        return getItemGroups(SQL_GET_CHARACTER_GOODS, character, allGoods);
    }

    /**
     * Updates the goods owned by the character.
     * 
     * @param character
     *            The characte owning the goods.
     * @param goods
     *            The owned goods.
     * @return The updated owned goods of the character.
     */
    public List<ItemGroup> updateGoods(final Character character, final List<ItemGroup> goods) {
        return helper.updateItems(character.getEquipment().getGoods(), goods, TABLE_CHARAKTER_GOOD, character.getId());
    }

    /**
     * Insert the good in the good table and good_text table and set the id of the good.
     * 
     * @param good
     *            The good to insert.
     * @return The good with its id.
     */
    public Good createGood(final Good good) {
        insertGoodTable(good);
        return good;
    }

    private void insertGoodTable(final Good good) {
        final ContentValues contentValues = getContentValues(good);
        contentValues.putNull(COLUMN_ID);
        synchronized (DBHelper.DB_LOCK) {
            final long rowId = db.insertOrThrow(TABLE_GOOD, null, contentValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert good in good table");
            }
            good.setId(getId(SQL_GET_GOOD_ID, rowId));
        }
    }

    private ContentValues getContentValues(final Good good) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, good.getName());
        contentValues.put(COLUMN_DESCRIPTION, good.getDescription());
        contentValues.put(COLUMN_COST, good.getCost());
        contentValues.put(COLUMN_WEIGHT, good.getWeight());
        contentValues.put(COLUMN_GOOD_TYPE_ID, good.getGoodType().ordinal());
        contentValues.put(COLUMN_QUALITY_TYPE_ID, good.getQualityType().ordinal());
        return contentValues;
    }

    /**
     * Updates a good by deleting it first and inserting it again, while keeping its original id.
     * 
     * @param good
     *            The good to update.
     */
    public void updateGood(final Good good) {
        deleteGood(good);
        updateGoodTable(good);
    }

    private void updateGoodTable(final Good good) {
        final ContentValues contentValues = getContentValues(good);
        contentValues.put(COLUMN_ID, good.getId());
        synchronized (DBHelper.DB_LOCK) {
            final long rowId = db.insertOrThrow(TABLE_GOOD, null, contentValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert good in good table");
            }
        }
    }

    /**
     * Delete the good from the good and good_text table.
     * 
     * @param good
     *            The good to delete.
     */
    public void deleteGood(final Good good) {
        final String[] goodId = new String[] { Integer.toString(good.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_GOOD, COLUMN_ID + " = ?", goodId);
        }
    }

}
