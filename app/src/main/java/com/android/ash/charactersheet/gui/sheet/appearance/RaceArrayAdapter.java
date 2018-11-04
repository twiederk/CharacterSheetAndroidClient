package com.android.ash.charactersheet.gui.sheet.appearance;

import java.util.List;

import android.content.Context;

import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.Race;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * ArrayAdapter to display races in a ListView or Spinner.
 */
public class RaceArrayAdapter extends SpinnerArrayAdapter<Race> {

    /**
     * Create instance to display given races.
     * 
     * @param context
     *            The context of the view.
     * @param displayService
     *            The display service to display data in the GUI.
     * @param races
     *            The races to display.
     */
    public RaceArrayAdapter(final Context context, final DisplayService displayService, final List<Race> races) {
        super(context, displayService, races);
    }

    @Override
    protected String getText(final Race race) {
        return race.getName();
    }

}
