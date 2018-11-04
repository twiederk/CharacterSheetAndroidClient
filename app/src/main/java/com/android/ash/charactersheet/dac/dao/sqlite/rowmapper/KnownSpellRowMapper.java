package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import java.util.List;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.KnownSpell;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.model.Spelllist;

/**
 * Maps raw data to KnownSpell class.
 */
public class KnownSpellRowMapper implements RowMapper {

    private final List<Spell> allSpells;
    private final List<Spelllist> allSpelllists;

    /**
     * Instanciates KnownSpellRowMapper with all spell lists and spells of the game system.
     * 
     * @param allSpelllists
     *            All spell lists of the game system.
     * @param allSpells
     *            All spells of the game system.
     */
    public KnownSpellRowMapper(final List<Spelllist> allSpelllists, final List<Spell> allSpells) {
        this.allSpelllists = allSpelllists;
        this.allSpells = allSpells;
    }

    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        final KnownSpell knownSpell = new KnownSpell();
        knownSpell.setId(cursor.getInt(0));
        knownSpell.setSpelllist(getSpelllist(cursor.getInt(1)));
        knownSpell.setSpell(getSpell(cursor.getInt(2)));
        return knownSpell;
    }

    private Spelllist getSpelllist(final int spelllistId) {
        for (final Spelllist spelllist : allSpelllists) {
            if (spelllist.getId() == spelllistId) {
                return spelllist;
            }
        }
        return null;
    }

    private Spell getSpell(final int spellId) {
        for (final Spell spell : allSpells) {
            if (spell.getId() == spellId) {
                return spell;
            }
        }
        throw new IllegalArgumentException("Can't find spell with id: " + spellId);
    }

}
