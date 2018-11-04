package com.android.ash.charactersheet.gui.widget.numberview;

/**
 * Controller handling integer numbers.
 */
public class SimpleNumberViewController implements NumberViewController {

    protected int number;

    /**
     * Creates controller handling integers.
     * 
     * @param number
     *            The starting number.
     */
    public SimpleNumberViewController(final int number) {
        this.number = number;
    }

    /**
     * Decrease number by one.
     */
    @Override
    public void decrease() {
        number--;
    }

    /**
     * @see com.android.ash.charactersheet.gui.widget.numberview.NumberViewController#getNumber()
     */
    @Override
    public Number getNumber() {
        return number;
    }

    /**
     * Increase number by one.
     */
    @Override
    public void increase() {
        number++;
    }

    /**
     * @see com.android.ash.charactersheet.gui.widget.numberview.NumberViewController#setNumber(java.lang.Number)
     */
    @Override
    public void setNumber(final Number number) {
        this.number = number.intValue();

    }

    @Override
    public void decrease(final Number number) {
        this.number = this.number - number.intValue();
    }

    @Override
    public void increase(final Number number) {
        this.number = this.number + number.intValue();
    }

}
