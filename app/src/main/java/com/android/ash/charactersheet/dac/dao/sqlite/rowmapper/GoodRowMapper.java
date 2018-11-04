package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.Good;
import com.d20charactersheet.framework.boc.model.GoodType;

/**
 * Maps a good from database to Good object.
 */
public class GoodRowMapper extends BaseRowMapper {

    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        // good.id, good_text.name, cost, weight, good_type_id
        final Good good = new Good();
        good.setId(cursor.getInt(0));
        good.setName(cursor.getString(1));
        good.setDescription(cursor.getString(2));
        good.setCost(cursor.getFloat(3));
        good.setWeight(cursor.getFloat(4));
        good.setGoodType((GoodType) getEnum(cursor.getInt(5), GoodType.values()));
        good.setQualityType(getQualityType(cursor.getInt(6)));
        return good;
    }

}
