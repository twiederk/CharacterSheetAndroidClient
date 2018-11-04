package com.android.ash.charactersheet.gui.sheet.spellslot;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.widget.Spinner;

import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Displays spells for a spell slot to select.
 */
public class SpellSelectionAdapter extends SpinnerArrayAdapter<Spell> implements Observer {

    private final SpellSlotActivityModel spellSlotActivityModel;
    private final Spinner spinner;

    /**
     * Instanciate SpellSelectionAdapter for given spinner with given SpellSlotActivityModel.
     * 
     * @param context
     *            The context of the activity.
     * @param spinner
     *            The spinner to fill.
     * @param displayService
     *            DisplayService to display data.
     * @param spellSlotActivityModel
     *            The model containing the spells for selection.
     */
    public SpellSelectionAdapter(final Context context, final Spinner spinner, final DisplayService displayService,
            final SpellSlotActivityModel spellSlotActivityModel) {
        super(context, displayService, spellSlotActivityModel.getSpells());
        this.spinner = spinner;
        this.spellSlotActivityModel = spellSlotActivityModel;
        spellSlotActivityModel.addObserver(this);
    }

    @Override
    public void update(final Observable observable, final Object object) {
        clear();
        addAll(spellSlotActivityModel.getSpells());
        spinner.setSelection(0);
        notifyDataSetChanged();
    }

}
