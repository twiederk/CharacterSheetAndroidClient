package com.android.ash.charactersheet.gui.sheet.clazz;

import com.android.ash.charactersheet.gui.widget.numberview.NumberViewController;
import com.d20charactersheet.framework.boc.model.ClassLevel;

/**
 * Controller to display a class level in a NumberView.
 */
public class NumberViewClassLevelController implements NumberViewController {

    private static final int MIN_LEVEL = 1;

    private final ClassLevel classLevel;

    /**
     * Creates controller with the class level to display.
     * 
     * @param classLevel
     *            The class level to display.
     */
    public NumberViewClassLevelController(final ClassLevel classLevel) {
        this.classLevel = classLevel;
    }

    /**
     * Decreases the level of the class if it greater than 1.
     * 
     * @see com.android.ash.charactersheet.gui.widget.numberview.NumberViewController#decrease()
     */
    @Override
    public void decrease() {
        if (classLevel.getLevel() > MIN_LEVEL) {
            classLevel.setLevel(classLevel.getLevel() - 1);
        }
    }

    /**
     * Returns the level of the class level.
     * 
     * @see com.android.ash.charactersheet.gui.widget.numberview.NumberViewController#getNumber()
     */
    @Override
    public Number getNumber() {
        return classLevel.getLevel();
    }

    /**
     * Increases the level.
     * 
     * @see com.android.ash.charactersheet.gui.widget.numberview.NumberViewController#increase()
     */
    @Override
    public void increase() {
        classLevel.setLevel(classLevel.getLevel() + 1);
    }

    /**
     * @see com.android.ash.charactersheet.gui.widget.numberview.NumberViewController#setNumber(java.lang.Number)
     */
    @Override
    public void setNumber(final Number number) {
        classLevel.setLevel(number.intValue());
    }

    @Override
    public void decrease(final Number number) {
        if (classLevel.getLevel() - number.intValue() >= MIN_LEVEL) {
            classLevel.setLevel(classLevel.getLevel() - number.intValue());
        } else {
            classLevel.setLevel(1);
        }
    }

    @Override
    public void increase(final Number number) {
        classLevel.setLevel(classLevel.getLevel() + number.intValue());
    }

}
