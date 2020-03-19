package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import java.util.List;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.CharacterFeat;
import com.d20charactersheet.framework.boc.model.Feat;

/**
 * Maps the data of a character feat row to a CharacterFeat instance.
 */
public class CharacterFeatRowMapper extends BaseRowMapper {

    private final List<Feat> allFeats;

    /**
     * Instantiates mapper with list of all feats.
     * 
     * @param allFeats
     *            All feats.
     */
    public CharacterFeatRowMapper(final List<Feat> allFeats) {
        super();
        this.allFeats = allFeats;
    }

    /**
     * Maps the data of a character feat row to an Character Feat, using the list of all static feats.
     * 
     * @see com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.BaseRowMapper#mapRow(android.database.Cursor)
     */
    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        final Feat feat = getFeat(cursor.getInt(1));
        final CharacterFeat characterFeat = new CharacterFeat(feat);
        characterFeat.setId(cursor.getInt(0));
        characterFeat.setCategory(cursor.getString(2));
        return characterFeat;
    }

    private Feat getFeat(final int featId) {
        for (final Feat feat : allFeats) {
            if (feat.getId() == featId) {
                return feat;
            }
        }
        throw new IllegalStateException("Can't get static feat: " + featId);
    }

}
