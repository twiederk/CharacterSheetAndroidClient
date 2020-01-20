package com.android.ash.charactersheet.gui.sheet.feat;

import com.d20charactersheet.framework.boc.model.CharacterFeat;

/**
 * Wraps CharacterFeat to display in list of character feats.
 */
public class CharacterFeatItem extends AbstractFeatListItem {

    private final CharacterFeat characterFeat;

    /**
     * Instanciates CharacterFeatItem with feat to wrap.
     *
     * @param characterFeat
     *            The character feat to wrap.
     */
    public CharacterFeatItem(final CharacterFeat characterFeat) {
        super(characterFeat.getFeat());
        this.characterFeat = characterFeat;
    }

    /**
     * Returns the name of the feat and in bracket the category of the feat if the feat can be gained multiple times.
     */
    @Override
    public String getName() {
        if (isMultiple()) {
            return getFeat().getName() + " (" + getCategory() + ")";
        }
        return getFeat().getName();
    }

    /**
     * Returns the category of the feat.
     *
     * @return The category of the feat.
     */
    public String getCategory() {
        return characterFeat.getCategory();
    }

    /**
     * Set the category of the feat.
     *
     * @param category
     *            The category to set.
     */
    public void setCategory(final String category) {
        characterFeat.setCategory(category);
    }

    /**
     * Returns the character feat.
     *
     * @return The character feat.
     */
    public CharacterFeat getCharacterFeat() {
        return characterFeat;
    }
}
