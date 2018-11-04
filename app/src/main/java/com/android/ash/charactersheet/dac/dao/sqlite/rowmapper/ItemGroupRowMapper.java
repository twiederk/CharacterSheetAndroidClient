package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import java.util.List;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.ItemGroup;

/**
 * Maps raw data to an item group.
 */
public class ItemGroupRowMapper extends BaseRowMapper {

    private final List<? extends Item> allItems;

    /**
     * All available items to map.
     * 
     * @param allItems
     *            All available items to map.
     */
    public ItemGroupRowMapper(final List<? extends Item> allItems) {
        super();
        this.allItems = allItems;
    }

    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        // id, weapon_id, number
        final ItemGroup itemGroup = new ItemGroup();
        itemGroup.setId(cursor.getInt(0));
        itemGroup.setItem(getItem(cursor.getInt(1), allItems));
        itemGroup.setNumber(cursor.getInt(2));
        return itemGroup;
    }

}
