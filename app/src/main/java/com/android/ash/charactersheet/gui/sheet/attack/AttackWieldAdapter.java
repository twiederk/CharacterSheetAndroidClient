package com.android.ash.charactersheet.gui.sheet.attack;

import java.util.List;

import android.content.Context;

import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.AttackWield;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Adapter to display AttackWield values.
 */
public class AttackWieldAdapter extends SpinnerArrayAdapter<AttackWield> {

    /**
     * Instantiates AttackWieldAdapter instance.
     * 
     * @param context
     *            The context of the activity.
     * @param displayService
     *            The service to display data.
     * @param attackWields
     *            All attackWields.
     */
    public AttackWieldAdapter(final Context context, final DisplayService displayService,
            final List<AttackWield> attackWields) {
        super(context, displayService, attackWields);
    }

    @Override
    protected String getText(final AttackWield item) {
        return displayService.getDisplayAttackWield(item);
    }

}
