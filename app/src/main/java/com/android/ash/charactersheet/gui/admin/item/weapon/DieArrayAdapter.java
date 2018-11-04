package com.android.ash.charactersheet.gui.admin.item.weapon;

import java.util.List;

import android.content.Context;

import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.Die;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * The adapter of the die spinner in the weapon administration.
 */
public class DieArrayAdapter extends SpinnerArrayAdapter<Die> {

    /**
     * Creates adapter of the die spinner in the weapon administration.
     * 
     * @param activity
     *            The activity of the spinner.
     * @param displayService
     *            The display service to display data.
     * @param dice
     *            The dice to offer in the spinner.
     */
    public DieArrayAdapter(final Context activity, final DisplayService displayService, final List<Die> dice) {
        super(activity, displayService, dice);
    }

    @Override
    protected String getText(final Die die) {
        return displayService.getDisplayDie(die);
    }

}
