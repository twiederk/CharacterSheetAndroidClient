package com.android.ash.charactersheet.gui.sheet.spellslot;

import java.util.LinkedList;
import java.util.List;

/**
 * The model of the spell slot page.
 */
public class SpellSlotPageModel {

    private final List<SpellSlotModel> spellSlotModels;

    /**
     * Instanciate SpellSlotPageModel with empty list of SpellSlotModels.
     */
    public SpellSlotPageModel() {
        this.spellSlotModels = new LinkedList<SpellSlotModel>();
    }

    /**
     * Returns spell slots models of the page.
     * 
     * @return Spell slots models of the page.
     */
    public List<SpellSlotModel> getSpellSlotModels() {
        return spellSlotModels;
    }

    /**
     * Add spell slot model to page.
     * 
     * @param spellSlotModel
     *            The spell slot model to add.
     */
    public void addSpellSlotModel(final SpellSlotModel spellSlotModel) {
        spellSlotModels.add(spellSlotModel);
    }

    /**
     * Called after resume from SpellSlotActivity to resume all spell slot models.
     */
    public void resumeFromSpellSlotActivity() {
        for (final SpellSlotModel spellSlotModel : spellSlotModels) {
            spellSlotModel.resumeFromSpellSlotActivity();
        }
    }

}
