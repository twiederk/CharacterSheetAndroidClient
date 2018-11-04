package com.android.ash.charactersheet.gui.admin.item.armor;

import java.util.List;

import android.content.Context;

import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.ArmorType;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Lists all weapon types for the spinner in the WeaponAdministrationActivity.
 */
public class ArmorTypeArrayAdapter extends SpinnerArrayAdapter<ArmorType> {

    /**
     * Creates WeaponTypeArrayAdapter with the given values.
     * 
     * @param context
     *            The application context.
     * @param displayService
     *            Used to display data.
     * @param weaponTypes
     *            The weapon types to display.
     */
    public ArmorTypeArrayAdapter(final Context context, final DisplayService displayService, final List<ArmorType> weaponTypes) {
        super(context, displayService, weaponTypes);
    }

    @Override
    protected String getText(final ArmorType weaponType) {
        return displayService.getDisplayArmorType(weaponType);
    }

}
