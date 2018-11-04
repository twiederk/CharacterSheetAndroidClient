package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import java.util.List;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.Race;
import com.d20charactersheet.framework.boc.model.Size;

/**
 * Maps a data row to a instance of class Race.
 */
public class RaceRowMapper extends BaseRowMapper {

    private final List<CharacterClass> allCharacterClasses;

    /**
     * Creates row mapper with the given character classes.
     * 
     * @param allCharacterClasses
     *            All character classes of the game system.
     */
    public RaceRowMapper(final List<CharacterClass> allCharacterClasses) {
        super();
        this.allCharacterClasses = allCharacterClasses;
    }

    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        final Race race = new Race();
        race.setId(cursor.getInt(0));
        race.setName(cursor.getString(1));
        race.setSize((Size) getEnum(cursor.getInt(2), Size.values()));
        race.setBaseLandSpeed(cursor.getInt(3));
        race.setFavoredCharacterClass(getCharacterClass(cursor.getInt(4), allCharacterClasses));
        return race;
    }

}
