package com.android.ash.charactersheet.gui.sheet.attack;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.widget.NameDisplayArrayAdapter;
import com.d20charactersheet.framework.boc.model.Weapon;
import com.d20charactersheet.framework.boc.service.DisplayService;

import java.util.List;

/**
 * Displays weapon in list view of WeaponAttackPageFragment.
 */
public class WeaponAttackSearchAdapter extends NameDisplayArrayAdapter<Weapon> {

    /**
     * Instanciates WeaponAttackSearchAdapter.
     * 
     * @param context
     *            The context of the view.
     * @param displayService
     *            The service to display data.
     * @param weapons
     *            The weapons to display.
     */
    public WeaponAttackSearchAdapter(final Context context, final DisplayService displayService,
            final List<Weapon> weapons) {
        super(context, displayService, weapons);
    }

    @Override
    protected void fillView(final View view, final Weapon weapon) {
        final TextView nameTextView = view.findViewById(R.id.name);
        nameTextView.setText(displayService.getDisplayItem(weapon));
    }

}
