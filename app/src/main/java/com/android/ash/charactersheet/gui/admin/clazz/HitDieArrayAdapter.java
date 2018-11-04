package com.android.ash.charactersheet.gui.admin.clazz;

import java.util.List;

import android.content.Context;

import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.Die;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * The adapter of the hit die spinner in the character class administration.
 */
public class HitDieArrayAdapter extends SpinnerArrayAdapter<Die> {

    /**
     * Creates adapter of the hit die spinner in the character class administration.
     * 
     * @param activity
     *            The activity of the spinner.
     * @param displayService
     *            The display service to display data.
     * @param hitDice
     *            The hit dice to offer in the spinner.
     */
    public HitDieArrayAdapter(final Context activity, final DisplayService displayService, final List<Die> hitDice) {
        super(activity, displayService, hitDice);
    }

    @Override
    protected String getText(final Die die) {
        return displayService.getDisplayDie(die);
    }

}
