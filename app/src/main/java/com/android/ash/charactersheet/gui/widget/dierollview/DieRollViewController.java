package com.android.ash.charactersheet.gui.widget.dierollview;

import com.d20charactersheet.framework.boc.model.DieRoll;

/**
 * The Controller of the DieRollView.
 */
public interface DieRollViewController {

    /**
     * Returns the title of the DieRollView.
     * 
     * @return the title of the DieRollView.
     */
    public String getTitle();

    /**
     * Returns the die roll.
     * 
     * @return The die roll.
     */
    public DieRoll getDieRoll();

    /**
     * Returns the color of the die.
     * 
     * @return The color of the die.
     */
    public int getColor();

    /**
     * Returns the subtitle of the DieRollView.
     * 
     * @return The subtitle of the DieRollView.
     */
    public String getSubtitle();

}
