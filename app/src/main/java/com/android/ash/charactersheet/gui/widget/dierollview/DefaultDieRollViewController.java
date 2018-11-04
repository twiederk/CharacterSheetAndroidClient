package com.android.ash.charactersheet.gui.widget.dierollview;

import android.graphics.Color;

import com.d20charactersheet.framework.boc.model.DieRoll;

/**
 * Default implementation of DieRollViewController.
 */
public class DefaultDieRollViewController implements DieRollViewController {

    private final String title;
    private final String subtitle;
    private final DieRoll dieRoll;
    private final int color;

    /**
     * Creates a controller with a blue die, no subtitle to display.
     * 
     * @param title
     *            The title do display.
     * @param dieRoll
     *            The die roll to display.
     */
    public DefaultDieRollViewController(final String title, final DieRoll dieRoll) {
        this(title, dieRoll, Color.BLUE, null);
    }

    /**
     * Creates a controller with given parameters.
     * 
     * @param title
     *            The title to display.
     * @param dieRoll
     *            The die roll to display.
     * @param color
     *            The color of the die to display.
     * @param subtitle
     *            The subtitle to display.
     */
    public DefaultDieRollViewController(final String title, final DieRoll dieRoll, final int color,
            final String subtitle) {
        this.title = title;
        this.dieRoll = dieRoll;
        this.color = color;
        this.subtitle = subtitle;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public DieRoll getDieRoll() {
        return dieRoll;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public String getSubtitle() {
        return subtitle;
    }

}
