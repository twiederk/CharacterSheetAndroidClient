package com.android.ash.charactersheet.gui.admin.item.weapon;

import java.util.List;

import android.content.Context;

import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.WeaponEncumbrance;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Adapter to display weapon encumbrance.
 */
public class WeaponEncumbranceAdapter extends SpinnerArrayAdapter<WeaponEncumbrance> {

    /**
     * Instantiates WeaponEncumbranceAdapter.
     * 
     * @param context
     *            The context.
     * @param displayService
     *            The service to display data.
     * @param weaponEncumbrances
     *            The weaopn encumbrances to display.
     */
    public WeaponEncumbranceAdapter(final Context context, final DisplayService displayService,
            final List<WeaponEncumbrance> weaponEncumbrances) {
        super(context, displayService, weaponEncumbrances);
    }

    @Override
    protected String getText(final WeaponEncumbrance weaponEncumbrance) {
        return displayService.getDisplayWeaponEncumbrance(weaponEncumbrance);
    }

}
