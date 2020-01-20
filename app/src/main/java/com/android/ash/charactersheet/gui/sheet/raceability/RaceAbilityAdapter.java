package com.android.ash.charactersheet.gui.sheet.raceability;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.ExpandableListItem;
import com.android.ash.charactersheet.gui.widget.DisplayArrayAdapter;
import com.d20charactersheet.framework.boc.model.Ability;
import com.d20charactersheet.framework.boc.service.DisplayService;

import java.util.List;

/**
 * Displays a ability as item of a list. The data to display contains the name, type and description of the ability.
 */
public class RaceAbilityAdapter extends DisplayArrayAdapter<ExpandableListItem> {

    /**
     * Creates adapter to display abilities.
     * 
     * @param context
     *            The context of the application.
     * @param displayService
     *            The display service to display data.
     * @param itemViewResourceId
     *            The id of the list item layout.
     * @param items
     *            The items to display.
     */
    public RaceAbilityAdapter(final Context context, final DisplayService displayService, final int itemViewResourceId,
            final List<ExpandableListItem> items) {
        super(context, displayService, itemViewResourceId, items);
    }

    @Override
    protected void fillView(final View view, final ExpandableListItem expandListView) {
        final Ability ability = (Ability) expandListView.getObject();

        final TextView nameTextView = view.findViewById(R.id.ability_name);
        nameTextView.setText(ability.getName());

        final TextView typeTextView = view.findViewById(R.id.ability_type);
        final TextView descriptionTextView = view.findViewById(R.id.ability_description);

        if (expandListView.isExpanded()) {
            typeTextView.setText(displayService.getDisplayAbilityType(ability.getAbilityType()));
            typeTextView.setVisibility(View.VISIBLE);
            descriptionTextView.setText(ability.getDescription());
            descriptionTextView.setVisibility(View.VISIBLE);
        } else {
            typeTextView.setVisibility(View.GONE);
            descriptionTextView.setVisibility(View.GONE);
        }
    }
}
