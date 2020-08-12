package com.android.ash.charactersheet.dac.dao.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.ItemGroup;
import com.d20charactersheet.framework.dac.dao.ItemDaoHelper;

import java.util.List;

import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_ARMOR_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_CHARAKTER_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_GOOD_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_NUMBER;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_WEAPON_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.SELECT;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.SQL_WHERE_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.TABLE_CHARAKTER_ARMOR;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.TABLE_CHARAKTER_GOOD;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.TABLE_CHARAKTER_WEAPON;

/**
 * Expands ItemDaoHelper to use optimized update of item groups.
 */
class SQLiteItemDaoHelper extends ItemDaoHelper {

    private final SQLiteDatabase db;

    /**
     * Creates SQLiteItemDaoHelper with access to given database.
     *
     * @param db Database to access.
     */
    public SQLiteItemDaoHelper(final SQLiteDatabase db) {
        super();
        this.db = db;
    }

    @Override
    protected void deleteItemGroups(final List<ItemGroup> currentItems, final List<ItemGroup> editedItems,
            final String tableName) {
        final List<ItemGroup> itemsToDelete = getItemsToDelete(currentItems, editedItems);
        synchronized (DBHelper.DB_LOCK) {
            for (final ItemGroup itemToDelete : itemsToDelete) {
                final String[] itemGroupId = new String[] { Integer.toString(itemToDelete.getId()) };
                db.delete(tableName, COLUMN_ID + " = ?", itemGroupId);
            }
        }
    }

    @Override
    protected List<ItemGroup> updateItemGroups(final List<ItemGroup> currentItems, final List<ItemGroup> editedItems,
            final String tableName, final int characterId) {
        final List<ItemGroup> itemsToUpdate = getItemsToUpdate(currentItems, editedItems);
        synchronized (DBHelper.DB_LOCK) {
            for (final ItemGroup itemGroup : itemsToUpdate) {
                final ContentValues itemValues = new ContentValues();
                itemValues.put(COLUMN_NUMBER, itemGroup.getNumber());
                itemValues.put(COLUMN_CHARAKTER_ID, characterId);
                final String[] bindVariables = new String[] { Integer.toString(itemGroup.getId()) };
                db.update(tableName, itemValues, SQL_WHERE_ID, bindVariables);
            }
        }
        return itemsToUpdate;
    }

    private ContentValues getItemValues(final ItemGroup itemGroup, final String tableName, final int characterId) {
        // id, character id, item id, number
        final ContentValues itemValues = new ContentValues();
        itemValues.put(COLUMN_CHARAKTER_ID, characterId);
        itemValues.put(getColumnName(tableName), itemGroup.getItem().getId());
        itemValues.put(COLUMN_NUMBER, itemGroup.getNumber());
        return itemValues;
    }

    private String getColumnName(final String tableName) {
        if (TABLE_CHARAKTER_WEAPON.equals(tableName)) {
            return COLUMN_WEAPON_ID;
        } else if (TABLE_CHARAKTER_ARMOR.equals(tableName)) {
            return COLUMN_ARMOR_ID;
        } else if (TABLE_CHARAKTER_GOOD.equals(tableName)) {
            return COLUMN_GOOD_ID;
        }
        throw new IllegalArgumentException("Can't determine column name of " + tableName);
    }

    @Override
    protected List<ItemGroup> insertItemGroups(final List<ItemGroup> editedItems, final String tableName,
            final int characterId) {
        final List<ItemGroup> itemsToInsert = getItemsToInsert(editedItems);
        synchronized (DBHelper.DB_LOCK) {
            for (final ItemGroup itemToInsert : itemsToInsert) {
                final ContentValues itemValues = getItemValues(itemToInsert, tableName, characterId);
                itemValues.putNull(COLUMN_ID);
                final long rowId = db.insertOrThrow(tableName, null, itemValues);
                if (rowId == -1) {
                    throw new SQLException("Can't insert " + itemsToInsert + " in " + tableName);
                }
                itemToInsert.setId(getId(tableName, rowId));
            }
        }
        return itemsToInsert;
    }

    private int getId(final String tableName, final long rowId) {
        final String sql = SELECT + "id FROM " + tableName + " WHERE rowid = ?";
        final String[] bindVariables = new String[1];
        bindVariables[0] = Long.toString(rowId);
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql, bindVariables);
            cursor.moveToFirst();
            return cursor.getInt(0);
        } catch (final SQLException sqlException) {
            Logger.error("Can't get id of rowId: " + rowId);
            throw sqlException;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }

}
