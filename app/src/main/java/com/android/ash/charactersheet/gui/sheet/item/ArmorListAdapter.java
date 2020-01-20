package com.android.ash.charactersheet.gui.sheet.item;

import android.content.Context;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.ExpandableListItem;
import com.android.ash.charactersheet.gui.util.ItemFilter;
import com.d20charactersheet.framework.boc.model.Armor;
import com.d20charactersheet.framework.boc.model.ItemGroup;
import com.d20charactersheet.framework.boc.service.DisplayService;

import java.util.List;
import java.util.Locale;

/**
 * Adapter of ListView of armor. It displays item groups containing armor with number, name and bonus of armor in first
 * row. The second row is expanded by touch. The second row displays type, cost, weight and penalty of armor.
 */
public class ArmorListAdapter extends ItemListAdapter {

    /**
     * Creates adapter with given list of armor.
     * 
     * @param context
     *            The context.
     * @param displayService
     *            The display service to format data to display.
     * @param itemViewResourceId
     *            The resource id of the layout to display a single item.
     * @param equipmentFilter
     *            The filter of the items.
     * @param allItems
     *            The items to display in the ListView.
     */
    public ArmorListAdapter(final Context context, final DisplayService displayService, final int itemViewResourceId,
            final ItemFilter equipmentFilter, final List<ExpandableListItem> allItems) {
        super(context, displayService, itemViewResourceId, equipmentFilter, allItems);
    }

    @Override
    protected void fillView(final View view, final ExpandableListItem expandListView) {
        final ItemGroup itemGroup = (ItemGroup) expandListView.getObject();
        final Armor armor = (Armor) itemGroup.getItem();

        setBackgroundColor(view, armor.isMagic());

        final TextView numberTextView = view.findViewById(R.id.armor_number);
        numberTextView.setText(String.format(Locale.US, "%d", itemGroup.getNumber()));

        final TextView nameTextView = view.findViewById(R.id.armor_name);
        nameTextView.setText(displayService.getDisplayItem(armor));

        final TextView damageTextView = view.findViewById(R.id.armor_bonus);
        damageTextView.setText(getArmorBonusText(armor.getArmorBonus()));

        final TableRow secondTableRow = view.findViewById(R.id.second_row);
        final TableRow thirdTableRow = view.findViewById(R.id.third_row);
        if (expandListView.isExpanded()) {
            secondTableRow.setVisibility(View.VISIBLE);

            final TextView typeTextView = view.findViewById(R.id.armor_type);
            typeTextView.setText(displayService.getDisplayArmorType(armor.getArmorType()));

            final TextView costTextView = view.findViewById(R.id.armor_cost);
            costTextView.setText(displayService.getDisplayCost(armor.getCost()));

            final TextView weightTextView = view.findViewById(R.id.armor_weight);
            weightTextView.setText(displayService.getDisplayWeight(armor.getWeight()));

            final TextView criticalTextView = view.findViewById(R.id.armor_check_penalty);
            criticalTextView.setText(String.format(Locale.US, "%d", armor.getArmorCheckPenalty()));

            displayDescription(armor, thirdTableRow, view);
        } else {
            secondTableRow.setVisibility(View.GONE);
            thirdTableRow.setVisibility(View.GONE);
        }
    }

    private String getArmorBonusText(final int armorBonus) {
        return resources.getString(R.string.equipment_list_armor_bonus) +
                " " +
                displayService.getDisplayModifier(armorBonus);
    }

}
