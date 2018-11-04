package com.android.ash.charactersheet.gui.admin.item.weapon;

import java.util.List;

import android.content.Context;

import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.WeaponType;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Lists all weapon types for the spinner in the WeaponAdministrationActivity.
 */
public class WeaponTypeArrayAdapter extends SpinnerArrayAdapter<WeaponType> {

    /**
     * Creates WeaponTypeArrayAdapter with the given values.
     * 
     * @param context
     *            The application context.
     * @param displayService
     *            Used to display data.
     * @param weaponFamilies
     *            The weapon families to display.
     */
    public WeaponTypeArrayAdapter(final Context context, final DisplayService displayService,
            final List<WeaponType> weaponFamilies) {
        super(context, displayService, weaponFamilies);
    }

    @Override
    protected String getText(final WeaponType weaponFamily) {
        return displayService.getDisplayWeaponType(weaponFamily);
    }

}
