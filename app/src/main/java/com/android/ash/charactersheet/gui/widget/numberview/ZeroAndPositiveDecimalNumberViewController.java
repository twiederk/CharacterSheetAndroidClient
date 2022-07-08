package com.android.ash.charactersheet.gui.widget.numberview;

/**
 * Controller of NumberView allowing zero an positive decimal values.
 */
public class ZeroAndPositiveDecimalNumberViewController implements NumberViewController {

    private float value;

    /**
     * Creates controller with given value.
     * 
     * @param value
     *            The value to start with.
     */
    public ZeroAndPositiveDecimalNumberViewController(final float value) {
        this.value = value;
    }

    @Override
    public void decrease() {
        if (value >= 1) {
            value--;
        }
    }

    @Override
    public Number getNumber() {
        return value;
    }

    @Override
    public void increase() {
        value++;
    }

    @Override
    public void setNumber(final Number number) {
        value = number.floatValue();
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public void decrease(final Number number) {
        if (value - number.floatValue() >= 0) {
            value -= number.floatValue();
        }
        value = 0.0f;
    }

    @Override
    public void increase(final Number number) {
        value += number.floatValue();
    }

}
