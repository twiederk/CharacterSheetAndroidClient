package com.android.ash.charactersheet.gui.widget.numberview;

/**
 * Controller of NumberView using only positive numbers.
 */
public class PositiveNumberViewController extends SimpleNumberViewController {

    /**
     * Creates controller using only positive numbers.
     * 
     * @param number
     *            The starting number.
     */
    public PositiveNumberViewController(final int number) {
        super(number);
    }

    /**
     * Decreases number by one. Number is always 1 or higher.
     */
    @Override
    public void decrease() {
        if (number > 1) {
            number--;
        }

    }

}
