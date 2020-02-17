package com.android.ash.charactersheet.gui.sheet.knownspell;

import com.d20charactersheet.framework.boc.model.Spell;

import androidx.annotation.NonNull;

/**
 * Model of spell in SpelllistPageFragment.
 */
public class SpellItem {

    private final Spell spell;
    private final LevelItem levelItem;
    private boolean knownSpell;

    /**
     * Creates SpellItem instance with given Spell and LevelItem.
     * 
     * @param spell
     *            The spell to store.
     * @param levelItem
     *            The level item to be part of.
     */
    public SpellItem(final Spell spell, final LevelItem levelItem) {
        this.spell = spell;
        this.levelItem = levelItem;
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
     * Returns the level item.
     * 
     * @return The level item.
     */
    public LevelItem getLevelItem() {
        return levelItem;
    }

    /**
     * Returns the name of the spell.
     * 
     * @return the name of the spell.
     */
    public String getName() {
        return spell.getName();
    }

    /**
     * Returns the short description of the spell.
     * 
     * @return The short description of the spell.
     */
    public String getShortDescription() {
        return spell.getShortDescription();
    }

    /**
     * Set to true, if the spell is marked by the character.
     * 
     * @param knownSpell
     *            True, to be marked by the character.
     */
    public void setKnownSpell(final boolean knownSpell) {
        this.knownSpell = knownSpell;
    }

    /**
     * Returns true, if spell is marked by the character.
     * 
     * @return True, if spell is marked by the character.
     */
    public boolean isKnownSpell() {
        return knownSpell;
    }

    @NonNull
    @Override
    public String toString() {
        return spell.getName();
    }

}
