package com.android.ash.charactersheet.gui.sheet.clazz;

import java.util.List;

import android.content.Context;

import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * ArrayAdapter to display classes in a ListView or Spinner.
 */
public class CharacterClassArrayAdapter extends SpinnerArrayAdapter<CharacterClass> {

    /**
     * Create instance to display given classes.
     * 
     * @param context
     *            The context of the view.
     * @param displayService
     *            The display service to display data in the GUI.
     * @param characterClasses
     *            The character classes to display.
     */
    public CharacterClassArrayAdapter(final Context context, final DisplayService displayService,
            final List<CharacterClass> characterClasses) {
        super(context, displayService, characterClasses);
    }

    @Override
    protected String getText(final CharacterClass characterClass) {
        return characterClass.getName();
    }

}
