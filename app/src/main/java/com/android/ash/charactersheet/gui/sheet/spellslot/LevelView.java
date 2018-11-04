package com.android.ash.charactersheet.gui.sheet.spellslot;

/**
 * Displays a spell level in the list of spell slots.
 */
public class LevelView {

    private final int level;
    private boolean expanded;

    /**
     * Instanciates LevelView for given spell level.
     * 
     * @param level
     *            The spell level of the view.
     */
    public LevelView(final int level) {
        this.level = level;
        this.expanded = true;
    }

    /**
     * Returns the spell level of the view.
     * 
     * @return The spell level of the view.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns true, if level is expanded.
     * 
     * @return True, if level is expanded.
     */
    public boolean isExpanded() {
        return expanded;
    }

    /**
     * Set to expand level view.
     * 
     * @param expanded
     *            True, to expand level view.
     */
    public void setExpanded(final boolean expanded) {
        this.expanded = expanded;
    }
}
