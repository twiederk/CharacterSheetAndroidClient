package com.android.ash.charactersheet.gui.widget.numberview;

/**
 * Controller allows only zero and positive numbers.
 */
public class ZeroAndNegativeNumberViewController extends SimpleNumberViewController {

    /**
     * Creates ZeroAndPostiveNumberViewController with the given number.
     * 
     * @param number
     *            The starting number of the controller.
     */
    public ZeroAndNegativeNumberViewController(final int number) {
        super(number);
    }

    /**
     * Increase number by one. Number is always zero or lower.
     */
    @Override
    public void increase() {
        if (number < 0) {
            number++;
        }
    }

    @Override
    public void increase(final Number number) {
        if (this.number + number.intValue() <= 0) {
            this.number = this.number + number.intValue();
        } else {
            this.number = 0;
        }
    }

}
