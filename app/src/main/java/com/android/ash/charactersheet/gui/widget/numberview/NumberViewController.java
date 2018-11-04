package com.android.ash.charactersheet.gui.widget.numberview;

/**
 * Controller of a NumberView an its Model. The controller is called by the NumberView if the increase or decrease
 * button is clicked. The NumberView request the number from the controller.
 */
public interface NumberViewController {

    /**
     * Returns the number.
     * 
     * @return The number.
     */
    public Number getNumber();

    /**
     * Increase the number.
     */
    public void increase();

    /**
     * Decrease the number.
     */
    public void decrease();

    /**
     * Set the number to the given value.
     * 
     * @param number
     *            The value to set.
     */
    public void setNumber(Number number);

    /**
     * Decrease the number by the given value.
     * 
     * @param number
     *            The value to decrease the number by.
     */
    public void decrease(Number number);

    /**
     * Increase the number by the given value.
     * 
     * @param number
     *            The value to increase the number by.
     */
    public void increase(Number number);

}
