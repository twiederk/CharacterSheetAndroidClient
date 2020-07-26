package com.android.ash.charactersheet.gui.widget.numberview;

/**
 * Controller allows only zero and positive numbers.
 */
public class ZeroAndPositiveNumberViewController extends SimpleNumberViewController {

    /**
     * Creates ZeroAndPositiveNumberViewController with the given number.
     *
     * @param number The starting number of the controller.
     */
    public ZeroAndPositiveNumberViewController(final int number) {
        super(number);
    }

    /**
     * Decrease only zero, not below.
     * 
     * @see com.android.ash.charactersheet.gui.widget.numberview.SimpleNumberViewController#decrease()
     */
    @Override
    public void decrease() {
        decrease(1);
    }

    @Override
    public void decrease(final Number number) {
        this.number = Math.max(this.number - number.intValue(), 0);
    }

}
