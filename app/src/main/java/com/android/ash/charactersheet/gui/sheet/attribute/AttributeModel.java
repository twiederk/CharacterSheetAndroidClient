package com.android.ash.charactersheet.gui.sheet.attribute;

import java.util.Observable;

import com.android.ash.charactersheet.gui.util.Logger;

/**
 * Observable attribute value.
 */
public class AttributeModel extends Observable {

    private int attributeValue;

    /**
     * Creates AttributeModel with given value.
     * 
     * @param attributeValue
     *            The value of the attribute.
     */
    public AttributeModel(final int attributeValue) {
        super();
        this.attributeValue = attributeValue;
    }

    /**
     * Returns the attribute value.
     * 
     * @return The attribute value.
     */
    public int getAttributeValue() {
        return attributeValue;
    }

    /**
     * Sets the attribute value and notifies observers.
     * 
     * @param attributeValue
     *            The attribute value to set.
     */
    public void setAttributeValue(final int attributeValue) {
        this.attributeValue = attributeValue;
        fireEvent();
    }

    private void fireEvent() {
        Logger.debug("AttributeModel.fireEvent()");
        Logger.debug("countObservers: " + countObservers());
        setChanged();
        notifyObservers(attributeValue);
    }

}
