package com.android.ash.charactersheet.gui.util;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;


/**
 * Set the magic property of the EquipmentFilter if checked.
 */
public class MagicOnClickListener implements OnClickListener {

    private final ItemFilter equipmentFilter;

    /**
     * Creates MagicOnClickListener with the given EquipmentFilter.
     * 
     * @param equipmentFilter
     *            The equipment filter to update if checked.
     */
    public MagicOnClickListener(final ItemFilter equipmentFilter) {
        this.equipmentFilter = equipmentFilter;
    }

    @Override
    public void onClick(final View view) {
        final CheckBox magicCheckBox = (CheckBox) view;
        equipmentFilter.setMagic(magicCheckBox.isChecked());
    }

}
