package com.android.ash.charactersheet.gui.sheet.feat;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Compares feats by name.
 */
class FeatItemComparator implements Comparator<FeatListItem>, Serializable {

    private static final long serialVersionUID = -5375490327201622772L;

    /**
     * Compares feats by name.
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(final FeatListItem featItem1, final FeatListItem featItem2) {
        return featItem1.getName().compareToIgnoreCase(featItem2.getName());
    }

}
