package com.android.ash.charactersheet.gui.sheet.attribute;

import com.android.ash.charactersheet.gui.widget.numberview.NumberViewController;

/**
 * Controller of NumberView displaying attribute values.
 */
public class AttributeNumberViewController implements NumberViewController {

    private static final int MIN_ATTRIBUTE_VALUE = 1;
    private static final int MAX_ATTRIBUTE_VALUE = 99;

    private final AttributeModel attributeModel;

    /**
     * Creates Controller with given attribute value.
     * 
     * @param attributeModel
     *            The model of the attribute.
     */
    public AttributeNumberViewController(final AttributeModel attributeModel) {
        this.attributeModel = attributeModel;
    }

    /**
     * Decreases attribute by one. Attribute is always 1 or higher.
     */
    @Override
    public void decrease() {
        decrease(1);
    }

    /**
     * @see com.android.ash.charactersheet.gui.widget.numberview.NumberViewController#getNumber()
     */
    @Override
    public Number getNumber() {
        return attributeModel.getAttributeValue();
    }

    /**
     * Increases attribute. Attribute is always lower 99.
     */
    @Override
    public void increase() {
        increase(1);
    }

    /**
     * @see com.android.ash.charactersheet.gui.widget.numberview.NumberViewController#setNumber(java.lang.Number)
     */
    @Override
    public void setNumber(final Number number) {
        attributeModel.setAttributeValue(number.intValue());
    }

    @Override
    public void decrease(final Number number) {
        attributeModel.setAttributeValue(Math.max(attributeModel.getAttributeValue() - number.intValue(), MIN_ATTRIBUTE_VALUE));

    }

    @Override
    public void increase(final Number number) {
        attributeModel.setAttributeValue(Math.min(attributeModel.getAttributeValue() + number.intValue(), MAX_ATTRIBUTE_VALUE));
    }

}
