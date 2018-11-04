package com.android.ash.charactersheet.gui.sheet.item;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.ExpandableListItem;
import com.android.ash.charactersheet.gui.util.ItemFilter;
import com.d20charactersheet.framework.boc.model.Good;
import com.d20charactersheet.framework.boc.model.ItemGroup;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Adapter to display good in a ListView. Displays number and name of the good in the first row. The second row can be
 * expanded and displays type, cost and weight.
 */
public class GoodListAdapter extends ItemListAdapter {

    /**
     * Creates GoodListAdapter with given items to display.
     * 
     * @param context
     *            The context.
     * @param displayService
     *            The display service to format data for display.
     * @param itemViewResourceId
     *            The id of the layout file to display an item.
     * @param equipmentFilter
     *            The filter of the equipment.
     * @param items
     *            The item to display.
     */
    public GoodListAdapter(final Context context, final DisplayService displayService, final int itemViewResourceId,
            final ItemFilter equipmentFilter, final List<ExpandableListItem> items) {
        super(context, displayService, itemViewResourceId, equipmentFilter, items);
    }

    @Override
    protected void fillView(final View view, final ExpandableListItem expandListView) {
        final ItemGroup goodGroup = (ItemGroup) expandListView.getObject();
        final Good good = (Good) goodGroup.getItem();

        setBackgroundColor(view, good.isMagic());

        final TextView numberTextView = (TextView) view.findViewById(R.id.good_number);
        numberTextView.setText(Integer.toString(goodGroup.getNumber()));

        final TextView nameTextView = (TextView) view.findViewById(R.id.good_name);
        nameTextView.setText(displayService.getDisplayItem(good));

        final TableRow secondTableRow = (TableRow) view.findViewById(R.id.second_row);
        final TableRow thirdTableRow = (TableRow) view.findViewById(R.id.third_row);
        if (expandListView.isExpanded()) {
            secondTableRow.setVisibility(View.VISIBLE);

            final TextView typeTextView = (TextView) view.findViewById(R.id.good_type);
            typeTextView.setText(displayService.getDisplayGoodType(good.getGoodType()));

            final TextView costTextView = (TextView) view.findViewById(R.id.good_cost);
            costTextView.setText(displayService.getDisplayCost(good.getCost()));

            final TextView weightTextView = (TextView) view.findViewById(R.id.good_weight);
            weightTextView.setText(displayService.getDisplayWeight(good.getWeight()));

            displayDescription(good, thirdTableRow, view);
        } else {
            secondTableRow.setVisibility(View.GONE);
            thirdTableRow.setVisibility(View.GONE);
        }
    }

}
