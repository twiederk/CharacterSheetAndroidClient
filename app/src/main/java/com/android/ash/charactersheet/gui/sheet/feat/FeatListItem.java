package com.android.ash.charactersheet.gui.sheet.feat;

import com.d20charactersheet.framework.boc.model.Feat;
import com.d20charactersheet.framework.boc.model.FeatType;

/**
 * Interface to display a feat in the feat list.
 */
public interface FeatListItem {

    /**
     * Returns the feat to display.
     * 
     * @return The feat to display.
     */
    public Feat getFeat();

    /**
     * Returns true, if the view of the list item is expanded.
     * 
     * @return True, if the view of the list item is expanded.
     */
    public boolean isExpanded();

    /**
     * Set to true, to expand the view of the list item.
     * 
     * @param expanded
     *            Set true to expand list item view.
     */
    public void setExpanded(boolean expanded);

    /**
     * Returns the name of the feat.
     * 
     * @return The name of the feat.
     */
    public String getName();

    /**
     * Returns the benefit of the feat.
     * 
     * @return The benefit of the feat.
     */
    public String getBenefit();

    /**
     * Returns the id of the feat.
     * 
     * @return The id of the feat.
     */
    public int getId();

    /**
     * Returns the type of the feat.
     * 
     * @return The type of the feat.
     */
    public FeatType getFeatType();

    /**
     * Returns true, if the feat can be gained multiple times.
     * 
     * @return True, if the feat can be gained multiple times.
     */
    public boolean isMultiple();

    /**
     * Returns true, if effect of feat is cumulative.
     * 
     * @return True, if effect of feat is cumulative.
     */
    public boolean isStack();

    /**
     * Returns the prerequisit of the feat.
     * 
     * @return The prerequisit of the feat.
     */
    public String getPrerequisit();

    /**
     * Returns true for fighter bonus feats.
     * 
     * @return True for fighter bonsu feats.
     */
    public boolean isFighterBonus();

}
