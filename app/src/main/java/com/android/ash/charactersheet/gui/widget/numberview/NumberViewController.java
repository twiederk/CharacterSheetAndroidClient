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
    Number getNumber();

    /**
     * Increase the number.
     */
    void increase();

    /**
     * Decrease the number.
     */
    void decrease();

    /**
     * Set the number to the given value.
     * 
     * @param number
     *            The value to set.
     */
    void setNumber(Number number);

    /**
     * Decrease the number by the given value.
     * 
     * @param number
     *            The value to decrease the number by.
     */
    void decrease(Number number);

    /**
     * Increase the number by the given value.
     * 
     * @param number
     *            The value to increase the number by.
     */
    void increase(Number number);

}
