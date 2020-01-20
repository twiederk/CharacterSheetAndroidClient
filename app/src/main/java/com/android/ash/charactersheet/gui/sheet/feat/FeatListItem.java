package com.android.ash.charactersheet.gui.sheet.feat;

import com.d20charactersheet.framework.boc.model.Feat;
import com.d20charactersheet.framework.boc.model.FeatType;

/**
 * Interface to display a feat in the feat list.
 */
interface FeatListItem {

    /**
     * Returns the feat to display.
     * 
     * @return The feat to display.
     */
    Feat getFeat();

    /**
     * Returns true, if the view of the list item is expanded.
     * 
     * @return True, if the view of the list item is expanded.
     */
    boolean isExpanded();

    /**
     * Returns the name of the feat.
     * 
     * @return The name of the feat.
     */
    String getName();

    /**
     * Returns the benefit of the feat.
     * 
     * @return The benefit of the feat.
     */
    String getBenefit();

    /**
     * Returns the id of the feat.
     * 
     * @return The id of the feat.
     */
    int getId();

    /**
     * Returns the type of the feat.
     * 
     * @return The type of the feat.
     */
    FeatType getFeatType();

    /**
     * Returns true, if the feat can be gained multiple times.
     * 
     * @return True, if the feat can be gained multiple times.
     */
    boolean isMultiple();

    /**
     * Returns true, if effect of feat is cumulative.
     * 
     * @return True, if effect of feat is cumulative.
     */
    boolean isStack();

    /**
     * Returns the prerequisit of the feat.
     * 
     * @return The prerequisit of the feat.
     */
    String getPrerequisit();

    /**
     * Returns true for fighter bonus feats.
     * 
     * @return True for fighter bonsu feats.
     */
    boolean isFighterBonus();

}
