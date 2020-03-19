package com.android.ash.charactersheet.gui.sheet.knownspell;

import java.util.LinkedList;
import java.util.List;

/**
 * Data model of KnowSpellPageFragment. Stores the data model of all known spell tabs and takes care of the "ShowAll"
 * checkbox.
 */
public class KnownSpellPageModel {

    private boolean showAll;
    private final List<SpelllistModel> spelllistModels;

    /**
     * Instantiates KnownSpellPageModel with empty list of spell list models.
     */
    public KnownSpellPageModel() {
        spelllistModels = new LinkedList<>();
    }

    /**
     * Set true to show all spells.
     * 
     * @param showAll
     *            True to show all spells.
     */
    public void setShowAll(final boolean showAll) {
        this.showAll = showAll;
        for (final SpelllistModel spelllistModel : spelllistModels) {
            spelllistModel.filter();
        }
    }

    /**
     * Returns true, if all spells are shown.
     * 
     * @return True, if all spells are shown.
     */
    public boolean isShowAll() {
        return showAll;
    }

    /**
     * Returns all spell list models
     * 
     * @return All spell list models.
     */
    public List<SpelllistModel> getSpelllistModels() {
        return spelllistModels;
    }

    /**
     * Adds an spell list model.
     * 
     * @param spelllistModel
     *            The model to add.
     */
    public void addSpelllistModel(final SpelllistModel spelllistModel) {
        spelllistModels.add(spelllistModel);
    }

}
