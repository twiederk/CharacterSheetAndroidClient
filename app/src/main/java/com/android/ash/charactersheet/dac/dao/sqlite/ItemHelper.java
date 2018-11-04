package com.android.ash.charactersheet.dac.dao.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.ItemGroupRowMapper;
import com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.RowMapper;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.ItemGroup;

/**
 * Helper to retrieve all items of a specific implemention (weapon, armor, good) from the database. Retrieves all items
 * of a character of a specific item type.
 */
public class ItemHelper extends BaseSQLiteDao {

    SQLiteItemDaoHelper helper;

    /**
     * Creates ItemHelper with given database and helper.
     * 
     * @param db
     *            The database to connect to.
     * @param helper
     *            The helper to handle basic item tasks.
     */
    public ItemHelper(final SQLiteDatabase db, final SQLiteItemDaoHelper helper) {
        super(db);
        this.helper = helper;
    }

    /**
     * Returns all items of a sub type of item.
     * 
     * @param sql
     *            The sql to select all items of the sub type.
     * @param rowMapper
     *            Rowmapper to map row data to sub type of item.
     * @return All items of a sub type of item.
     */
    public List<Item> getAllItems(final String sql, final RowMapper rowMapper) {
        final List<Item> allItems = new ArrayList<Item>();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql, new String[0]);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                final Item item = (Item) rowMapper.mapRow(cursor);
                allItems.add(item);
            }
        } catch (final SQLException sqlException) {
            Logger.error("Can't get all items", sqlException);
        } finally {
            close(cursor);
        }
        return allItems;
    }

    /**
     * Returns all items of a character grouped together.
     * 
     * @param sql
     *            The sql to select the a subtype of an item.
     * @param character
     *            The character to get its items grouped.
     * @param allItems
     *            All items available in the game system.
     * @return All items of a character grouped together.
     */
    public List<ItemGroup> getItemGroups(final String sql, final Character character,
            final List<? extends Item> allItems) {
        final List<ItemGroup> itemGroups = new ArrayList<ItemGroup>();
        Cursor cursor = null;
        try {
            final String[] params = new String[] { Integer.toString(character.getId()) };
            cursor = db.rawQuery(sql, params);
            final ItemGroupRowMapper itemGroupRowMapper = new ItemGroupRowMapper(allItems);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                mapAndAddItemGroup(cursor, itemGroups, itemGroupRowMapper);
            }
        } catch (final SQLException sqlException) {
            Logger.error("Can't get items of character", sqlException);
        } finally {
            close(cursor);
        }
        return itemGroups;
    }

    private void mapAndAddItemGroup(final Cursor cursor, final List<ItemGroup> characterItem,
            final ItemGroupRowMapper itemGroupRowMapper) {
        try {
            final ItemGroup itemGroup = (ItemGroup) itemGroupRowMapper.mapRow(cursor);
            characterItem.add(itemGroup);
        } catch (final Exception exception) {
            Logger.error("Can't map item group, skipping it", exception);
        }
    }

}
