package com.android.ash.charactersheet.gui.sheet.knownspell;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Model of spell level which is display on SpelllistPageFragment. Spell list can be expanded or collapsed.
 */
public class LevelItem {

    private int level;
    private boolean expanded;
    private List<SpellItem> spellItems;

    /**
     * Returns the spell level.
     * 
     * @return The spell level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the spell level.
     * 
     * @param level
     *            The spell level.
     */
    public void setLevel(final int level) {
        this.level = level;
    }

    /**
     * Returns true if expanded.
     * 
     * @return True, if expanded.
     */
    public boolean isExpanded() {
        return expanded;
    }

    /**
     * Set true to expand.
     * 
     * @param expanded
     *            True, to expand.
     */
    public void setExpanded(final boolean expanded) {
        this.expanded = expanded;
    }

    /**
     * Sets spells of spell level.
     * 
     * @param spellItems
     *            Spells of spell level.
     */
    public void setSpellItems(final List<SpellItem> spellItems) {
        this.spellItems = spellItems;
    }

    /**
     * Returns true, if character has at least one marked spell.
     * 
     * @return True, if character has at least one marked spell.
     */
    public boolean hasKnownSpells() {
        for (final SpellItem spellItem : spellItems) {
            if (spellItem.isKnownSpell()) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    @Override
    public String toString() {
        return Integer.toString(level);
    }

    /**
     * Returns the number of known spells for this spell level.
     * 
     * @return The number of known spells for this spell level.
     */
    public int getNumberOfKnownSpells() {
        int numberOfKnownSpells = 0;
        for (final SpellItem spellItem : spellItems) {
            if (spellItem.isKnownSpell()) {
                numberOfKnownSpells++;
            }
        }
        return numberOfKnownSpells;
    }

}
