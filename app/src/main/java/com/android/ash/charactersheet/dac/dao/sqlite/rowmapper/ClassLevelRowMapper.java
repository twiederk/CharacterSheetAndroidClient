package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import java.util.List;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.ClassLevel;

/**
 * Maps a row of the table character_class_level to a ClassLevel object.
 */
public class ClassLevelRowMapper implements RowMapper {

    private final List<CharacterClass> allCharacterClasses;

    /**
     * Creates ClassLevelRowMapper, whicht requires all character classes to map the data to a ClassLevel.
     * 
     * @param allCharacterClasses
     *            All character classes of the game system.
     */
    public ClassLevelRowMapper(final List<CharacterClass> allCharacterClasses) {
        this.allCharacterClasses = allCharacterClasses;
    }

    /**
     * Maps a row of the table classLevel to a ClassLevel object.
     * 
     * @see com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.BaseRowMapper#mapRow(android.database.Cursor)
     */
    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        // id, charakter_class_id, level
        final CharacterClass characterClass = getCharacterClass(cursor.getInt(1));
        final ClassLevel classLevel = new ClassLevel(characterClass);
        classLevel.setId(cursor.getInt(0));
        classLevel.setLevel(cursor.getInt(2));
        return classLevel;
    }

    private CharacterClass getCharacterClass(final int characterClassId) {
        for (final CharacterClass characterClass : allCharacterClasses) {
            if (characterClassId == characterClass.getId()) {
                return characterClass;
            }
        }
        throw new IllegalArgumentException("Can't get character class with id: " + characterClassId);
    }
}
