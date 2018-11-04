package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.dac.dao.util.SpellSerializor;

/**
 * Maps a data row to a spell.
 */
public class SpellRowMapper extends BaseRowMapper {

    private final SpellSerializor spellSerializor = new SpellSerializor();

    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        // id, name, components, casting_time, range, effect, duraction, saving_throw, spell_resistance,
        // short_description, description, mat_component, focus
        final Spell spell = new Spell();
        spell.setId(cursor.getInt(0));
        spell.setName(cursor.getString(1));
        setSchools(spell, cursor.getString(2));
        setComponents(spell, cursor.getInt(3));
        spell.setCastingTime(spellSerializor.deserializeCastingTime(cursor.getString(4)));
        spell.setRange(spellSerializor.deserializeRange(cursor.getString(5)));
        spell.setEffect(cursor.getString(6));
        spell.setDuration(cursor.getString(7));
        spell.setSavingThrow(cursor.getString(8));
        spell.setSpellResistance(spellSerializor.deserializeSpellResistance(cursor.getString(9)));
        spell.setShortDescription(cursor.getString(10));
        spell.setMaterialComponent(cursor.getString(11));
        spell.setFocus(cursor.getString(12));
        return spell;
    }

    private void setSchools(final Spell spell, final String data) {
        spell.setSchool(spellSerializor.deserializeSchool(data));
        spell.setSubSchool(spellSerializor.deserializeSubSchool(data));
        spell.setDescriptors(spellSerializor.deserializeDescriptors(data));
    }

    private void setComponents(final Spell spell, final int components) {
        spell.setVerbal(isSet(components, 0));
        spell.setSomatic(isSet(components, 1));
        spell.setMaterial(isSet(components, 2));
        spell.setFocus(isSet(components, 3));
        spell.setDivineFocus(isSet(components, 4));
        spell.setXpCost(isSet(components, 5));

    }

    private boolean isSet(final int components, final int position) {
        final int mask = 1 << position;
        final boolean isSet = (components & mask) > 0;
        if (isSet) {
            return true;
        }
        return false;
    }

}
