package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import java.util.List;

import android.database.Cursor;
import android.database.SQLException;

import com.androidash.memorydb.DaoUtil;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.model.SpellSlot;

/**
 * Maps raw data to SpellSlot instance.
 */
public class SpellSlotRowMapper implements RowMapper {

    private final List<Spell> allSpells;
    private final DaoUtil daoUtil;

    /**
     * Instantiates Mapper for spell slots.
     * 
     * @param allSpells
     *            All spells of the game system.
     */
    public SpellSlotRowMapper(final List<Spell> allSpells) {
        this.allSpells = allSpells;
        daoUtil = new DaoUtil();
    }

    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        final SpellSlot spellSlot = new SpellSlot();
        spellSlot.setId(cursor.getInt(0));
        spellSlot.setSpell(getSpellOfSpellSlot(cursor.getInt(1)));
        spellSlot.setLevel(cursor.getInt(2));
        spellSlot.setCast(daoUtil.getBoolean(cursor.getInt(3)));
        return spellSlot;
    }

    private Spell getSpellOfSpellSlot(final int spellId) {
        if (spellId == SpellSlot.EMPTY_SPELL.getId()) {
            return SpellSlot.EMPTY_SPELL;
        }
        return getSpell(spellId, allSpells);
    }

    private Spell getSpell(final int spellId, final List<Spell> allSpells) {
        for (final Spell spell : allSpells) {
            if (spell.getId() == spellId) {
                return spell;
            }
        }
        throw new IllegalArgumentException("Can't find spell with id: " + spellId);
    }

}
