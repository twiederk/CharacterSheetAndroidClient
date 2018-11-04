package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import java.util.List;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.CharacterAbility;
import com.d20charactersheet.framework.boc.model.ClassAbility;

/**
 * Maps data row to new CharacterAbility instance.
 */
public class CharacterAbilityRowMapper extends BaseRowMapper {

    private final List<ClassAbility> classAbilities;

    /**
     * Creates CharacterAbilityRowMapper with list of all available class abilities.
     * 
     * @param classAbilities
     */
    public CharacterAbilityRowMapper(final List<ClassAbility> classAbilities) {
        super();
        this.classAbilities = classAbilities;
    }

    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        // id, ability_id, owned
        final CharacterAbility characterAbility = new CharacterAbility();
        characterAbility.setId(cursor.getInt(0));
        characterAbility.setClassAbility(getClassAbility(cursor.getInt(1)));
        characterAbility.setOwned(getBoolean(cursor.getInt(2)));
        return characterAbility;
    }

    private ClassAbility getClassAbility(final int abilityId) {
        for (final ClassAbility classAbility : classAbilities) {
            if (classAbility.getAbility().getId() == abilityId) {
                return classAbility;
            }
        }
        throw new IllegalArgumentException("Can't find ability of id: " + abilityId);
    }

}
