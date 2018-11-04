package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.Feat;
import com.d20charactersheet.framework.boc.model.FeatType;

/**
 * Maps a row of feat data to a StaticFeat object.
 */
public class FeatRowMapper extends BaseRowMapper {

    /**
     * Maps a row of feat data to a StaticFeat object.
     * 
     * @see com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.BaseRowMapper#mapRow(android.database.Cursor)
     */
    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        // feat.id, feat.feat_type_id, feat_text.name, feat_text.prerequisit, feat_text.benefit, feat.fighter,
        // feat.multiple, feat.stack
        final Feat feat = new Feat();
        feat.setId(cursor.getInt(0));
        feat.setName(cursor.getString(1));
        feat.setPrerequisit(cursor.getString(2));
        feat.setBenefit(cursor.getString(3));
        feat.setFeatType((FeatType) getEnum(cursor.getInt(4), FeatType.values()));
        feat.setFighterBonus(getBoolean(cursor.getInt(5)));
        feat.setMultiple(getBoolean(cursor.getInt(6)));
        feat.setStack(getBoolean(cursor.getInt(7)));
        feat.setSpellSlot(cursor.getInt(8));
        return feat;
    }

}
