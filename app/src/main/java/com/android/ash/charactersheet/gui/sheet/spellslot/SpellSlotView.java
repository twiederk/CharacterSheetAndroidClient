package com.android.ash.charactersheet.gui.sheet.spellslot;

import com.d20charactersheet.framework.boc.model.SpellSlot;

/**
 * Displays spell slot in list of spell slots.
 */
class SpellSlotView {

    private final SpellSlot spellSlot;
    private final LevelView levelView;

    /**
     * Instantiates SpellSlotView with level of the spell slot and the spell slot.
     * 
     * @param levelView
     *            The level of the spell slot.
     * @param spellSlot
     *            The spell slot.
     */
    public SpellSlotView(final LevelView levelView, final SpellSlot spellSlot) {
        this.spellSlot = spellSlot;
        this.levelView = levelView;
    }

    /**
     * Returns level of spell slot.
     * 
     * @return Level of spell slot.
     */
    public LevelView getLevelView() {
        return levelView;
    }

    /**
     * Returns spell slot.
     * 
     * @return Spell slot.
     */
    public SpellSlot getSpellSlot() {
        return spellSlot;
    }

}
