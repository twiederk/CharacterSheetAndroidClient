package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import java.util.List;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.Ability;
import com.d20charactersheet.framework.boc.model.ClassAbility;

/**
 * Creates ClassAbility instances read from data rows.
 */
public class ClassAbilityRowMapper extends BaseRowMapper {

    private final List<Ability> allAbilities;

    /**
     * Creates ClassAbilityRowMapper with all class abilities.
     * 
     * @param allAbilities
     *            All class abilities.
     */
    public ClassAbilityRowMapper(final List<Ability> allAbilities) {
        super();
        this.allAbilities = allAbilities;
    }

    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        final Ability ability = getAbility(cursor.getInt(0));
        final ClassAbility classAbility = new ClassAbility(ability);
        classAbility.setLevel(cursor.getInt(1));
        return classAbility;
    }

    private Ability getAbility(final int abilityId) {
        for (final Ability ability : allAbilities) {
            if (ability.getId() == abilityId) {
                return ability;
            }
        }
        throw new RuntimeException("Can't determine ability with id: " + abilityId);
    }

}
