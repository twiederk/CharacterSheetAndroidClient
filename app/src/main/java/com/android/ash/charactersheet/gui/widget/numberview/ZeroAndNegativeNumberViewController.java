package com.android.ash.charactersheet.gui.widget.numberview;

/**
 * Controller allows only zero and positive numbers.
 */
public class ZeroAndNegativeNumberViewController extends SimpleNumberViewController {

    /**
     * Creates ZeroAndNegativeNumberViewController with the given number.
     *
     * @param number The starting number of the controller.
     */
    public ZeroAndNegativeNumberViewController(final int number) {
        super(number);
    }

    /**
     * Increase number by one. Number is always zero or lower.
     */
    @Override
    public void increase() {
        increase(1);
    }

    @Override
    public void increase(final Number number) {
        this.number = Math.min(this.number + number.intValue(), 0);
    }

}
