package com.android.ash.charactersheet.gui.sheet.item;

import com.android.ash.charactersheet.gui.widget.numberview.NumberViewController;
import com.d20charactersheet.framework.boc.model.ItemGroup;

/**
 * Controller between ItemGroup as model and NumberView as view. Increases number of item by one, less than zero is not
 * allowed.
 */
public class ItemGroupNumberViewController implements NumberViewController {

    private final ItemGroup itemGroup;

    /**
     * Create controller with given item group as model.
     *
     * @param itemGroup The item group as model.
     */
    public ItemGroupNumberViewController(final ItemGroup itemGroup) {
        this.itemGroup = itemGroup;
    }

    @Override
    public void decrease() {
        decrease(1);
    }

    @Override
    public Number getNumber() {
        return itemGroup.getNumber();
    }

    @Override
    public void increase() {
        increase(1);
    }

    @Override
    public void setNumber(final Number number) {
        itemGroup.setNumber(number.intValue());
    }

    @Override
    public void decrease(final Number number) {
        itemGroup.setNumber(Math.max(itemGroup.getNumber() - number.intValue(), 0));
    }

    @Override
    public void increase(final Number number) {
        itemGroup.setNumber(itemGroup.getNumber() + number.intValue());
    }

}
