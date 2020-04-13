package com.android.ash.charactersheet.gui.admin.clazz;

import android.content.Context;

import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.BaseAttackBonus;
import com.d20charactersheet.framework.boc.service.DisplayService;

import java.util.List;

/**
 * Adapter of the base attack bonus spinner in the CharacterClassAdministrationActivity.
 */
public class BaseAttackBonusArrayAdapter extends SpinnerArrayAdapter<BaseAttackBonus> {

    /**
     * Creates adapter for base attack bonus spinner.
     * 
     * @param context
     *            The context of the array.
     * @param displayService
     *            The display service to display data.
     * @param baseAttackBoni
     *            The base attack boni to display.
     */
    BaseAttackBonusArrayAdapter(final Context context, final DisplayService displayService,
                                final List<BaseAttackBonus> baseAttackBoni) {
        super(context, displayService, baseAttackBoni);
    }

    @Override
    protected String getText(final BaseAttackBonus baseAttackBonus) {
        return displayService.getDisplayBaseAttackBonus(baseAttackBonus);
    }

}
