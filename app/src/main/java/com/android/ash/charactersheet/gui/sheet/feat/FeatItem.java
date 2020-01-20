package com.android.ash.charactersheet.gui.sheet.feat;

import com.d20charactersheet.framework.boc.model.Feat;

/**
 * Wraps a static feat to display in a list view.
 */
class FeatItem extends AbstractFeatListItem {

    /**
     * Creates StaticFeatItem of feat.
     * 
     * @param feat
     *            The feat to display.
     */
    public FeatItem(final Feat feat) {
        super(feat);
    }

}
