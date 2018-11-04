package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import android.database.Cursor;
import android.database.SQLException;

import com.androidash.memorydb.DaoUtil;
import com.d20charactersheet.framework.boc.model.Spelllist;

/**
 * Maps a data row to a new Spelllist instance.
 */
public class SpelllistRowMapper extends BaseRowMapper {

    private final DaoUtil daoUtil = new DaoUtil();

    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        final Spelllist spelllist = new Spelllist();
        spelllist.setId(cursor.getInt(0));
        spelllist.setName(cursor.getString(1));
        spelllist.setShortname(cursor.getString(2));
        spelllist.setDomain(daoUtil.getBoolean(cursor.getInt(3)));
        spelllist.setMinLevel(cursor.getInt(4));
        spelllist.setMaxLevel(cursor.getInt(5));
        return spelllist;
    }

}
