package com.android.ash.charactersheet.gui.sheet.item;

import android.content.Context;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.ExpandableListItem;
import com.android.ash.charactersheet.gui.util.ItemFilter;
import com.d20charactersheet.framework.boc.model.ItemGroup;
import com.d20charactersheet.framework.boc.model.Weapon;
import com.d20charactersheet.framework.boc.service.DisplayService;

import java.util.List;
import java.util.Locale;

/**
 * The Adapter of the weapon list of the character. Displays the number, name and damage of the weapon in the first
 * line. The second line displays type of weapon, cost, weight and critical. The second line can be expanded by touching
 * the weapon.
 */
public class WeaponListAdapter extends ItemListAdapter {

    /**
     * Creates the WeaponListAdapter.
     * 
     * @param context
     *            The context of the application.
     * @param displayService
     *            The service to display data to the user.
     * @param itemViewResourceId
     *            The id of the layout to use for each list item.
     * @param equipmentFilter
     *            The filter of the equipment.
     * @param allItems
     *            All available items.
     */
    public WeaponListAdapter(final Context context, final DisplayService displayService, final int itemViewResourceId,
            final ItemFilter equipmentFilter, final List<ExpandableListItem> allItems) {
        super(context, displayService, itemViewResourceId, equipmentFilter, allItems);
    }

    @Override
    protected void fillView(final View view, final ExpandableListItem expandListView) {
        final ItemGroup itemGroup = (ItemGroup) expandListView.getObject();
        final Weapon weapon = (Weapon) itemGroup.getItem();

        setBackgroundColor(view, weapon.isMagic());

        final TextView numberTextView = view.findViewById(R.id.weapon_number);
        numberTextView.setText(String.format(Locale.US, "%d", itemGroup.getNumber()));

        final TextView nameTextView = view.findViewById(R.id.weapon_name);
        nameTextView.setText(displayService.getDisplayItem(weapon));

        final TextView damageTextView = view.findViewById(R.id.weapon_damage);
        damageTextView.setText(displayService.getDisplayDamage(weapon));

        final TableRow secondTableRow = view.findViewById(R.id.second_row);
        final TableRow thirdTableRow = view.findViewById(R.id.third_row);
        if (expandListView.isExpanded()) {
            secondTableRow.setVisibility(View.VISIBLE);

            final TextView typeTextView = view.findViewById(R.id.weapon_type);
            typeTextView.setText(displayService.getDisplayWeaponType(weapon.getWeaponType()));

            final TextView costTextView = view.findViewById(R.id.weapon_cost);
            costTextView.setText(displayService.getDisplayCost(weapon.getCost()));

            final TextView weightTextView = view.findViewById(R.id.weapon_weight);
            weightTextView.setText(displayService.getDisplayWeight(weapon.getWeight()));

            final TextView criticalTextView = view.findViewById(R.id.weapon_critical);
            criticalTextView.setText(displayService.getDisplayCritical(weapon.getCritical()));

            displayDescription(weapon, thirdTableRow, view);
        } else {
            secondTableRow.setVisibility(View.GONE);
            thirdTableRow.setVisibility(View.GONE);
        }
    }

}
