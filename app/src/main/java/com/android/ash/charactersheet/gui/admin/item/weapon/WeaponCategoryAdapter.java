package com.android.ash.charactersheet.gui.admin.item.weapon;

import java.util.List;

import android.content.Context;

import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.WeaponCategory;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Adapter to display weapon catories in a spinner.
 */
public class WeaponCategoryAdapter extends SpinnerArrayAdapter<WeaponCategory> {

    /**
     * Instanciates WeaponCategoryAdapter
     * 
     * @param context
     *            The context the adapter runs in.
     * @param displayService
     *            The service to display data.
     * @param weaponCategories
     *            The weapon categories to display.
     */
    public WeaponCategoryAdapter(final Context context, final DisplayService displayService,
            final List<WeaponCategory> weaponCategories) {
        super(context, displayService, weaponCategories);
    }

    @Override
    protected String getText(final WeaponCategory weaponCategory) {
        return displayService.getDisplayWeaponCategory(weaponCategory);
    }

}
