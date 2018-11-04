package com.android.ash.charactersheet.gui.admin.skill;

import java.util.List;

import android.content.Context;

import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.Attribute;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * ArrayAdapter to display abilities in a ListView or Spinner.
 */
public class AttributeArrayAdapter extends SpinnerArrayAdapter<Attribute> {

    /**
     * Creates ArrayAdapter with given abilities.
     * 
     * @param context
     *            The application context.
     * @param displayService
     *            Service used to diplay data.
     * @param attributes
     *            The abilities to display.
     */
    public AttributeArrayAdapter(final Context context, final DisplayService displayService, final List<Attribute> attributes) {
        super(context, displayService, attributes);
    }

    @Override
    protected String getText(final Attribute attribute) {
        return displayService.getDisplayAttribute(attribute);
    }

}
