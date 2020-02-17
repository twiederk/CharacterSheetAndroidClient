package com.android.ash.charactersheet.gui.admin.clazz.alignment;

import com.d20charactersheet.framework.boc.model.Alignment;

import androidx.annotation.NonNull;

/**
 * The alignment model stores if an alignment is a class alignment or not.
 */
class AlignmentModel {

    private final Alignment alignment;
    private boolean classAlignment;

    /**
     * Creates AlignmentModel with the given alignment and the given class alignment state.
     * 
     * @param alignment
     *            The alignment.
     * @param classAlignment
     *            True, if the alignment is a class alignment.
     */
    public AlignmentModel(final Alignment alignment, final boolean classAlignment) {
        this.alignment = alignment;
        this.classAlignment = classAlignment;
    }

    /**
     * Returns the alignment.
     * 
     * @return The alignment.
     */
    public Alignment getAlignment() {
        return alignment;
    }

    /**
     * Returns true, if alignment is a class alignment.
     * 
     * @return True, if alignment is a class alignment.
     */
    public boolean isClassAlignment() {
        return classAlignment;
    }

    /**
     * Set class alignment or not.
     * 
     * @param classAlignment
     *            True, if alignment is a class alignment.
     */
    public void setClassAlignment(final boolean classAlignment) {
        this.classAlignment = classAlignment;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @NonNull
    @Override
    public String toString() {
        return alignment + ": " + classAlignment;
    }

}
