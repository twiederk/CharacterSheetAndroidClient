package com.android.ash.charactersheet.gui.admin.item.weapon;

import java.util.List;

import android.content.Context;

import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.CombatType;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Adapter to display combat types in a spinner.
 */
public class CombatTypeAdapter extends SpinnerArrayAdapter<CombatType> {

    /**
     * Instantiates CombatTypeAdapter.
     * 
     * @param activity
     *            The activity the adapter is used in.
     * @param displayService
     *            The service to display data.
     * @param combatTypes
     *            The combat types to display.
     */
    public CombatTypeAdapter(final Context activity, final DisplayService displayService,
            final List<CombatType> combatTypes) {
        super(activity, displayService, combatTypes);
    }

    @Override
    protected String getText(final CombatType combatType) {
        return displayService.getDisplayCombatType(combatType);
    }

}
