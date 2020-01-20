package com.android.ash.charactersheet.gui.admin.spelllist;

import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.model.Spelllist;

/**
 * Represents as spell assignment in the spell list administration.
 */
class SpelllevelView {

    private final Spelllist spelllist;
    private final Spell spell;
    private int level;

    /**
     * Create a SpelllevelView wit the given parameters.
     * 
     * @param spelllist
     *            The spell list.
     * @param spell
     *            The spell.
     * @param level
     *            The level the spell is assigned to the spell list.
     */
    public SpelllevelView(final Spelllist spelllist, final Spell spell, final int level) {
        this.spelllist = spelllist;
        this.spell = spell;
        this.level = level;
    }

    /**
     * Returns the spell list.
     * 
     * @return The spell list.
     */
    public Spelllist getSpelllist() {
        return spelllist;
    }

    /**
     * Returns the spell.
     * 
     * @return The spell.
     */
    public Spell getSpell() {
        return spell;
    }

    /**
     * Returns the level the spell is assigned to the spell list.
     * 
     * @return The level the spell is assigned to the spell list.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level the spell is assigned to the spell list.
     * 
     * @param level
     *            The level
     */
    public void setLevel(final int level) {
        this.level = level;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((spell == null) ? 0 : spell.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SpelllevelView other = (SpelllevelView) obj;
        if (spell == null) {
            return other.spell == null;
        } else return spell.equals(other.spell);
    }

}
