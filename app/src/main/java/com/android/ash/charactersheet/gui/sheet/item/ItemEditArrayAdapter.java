package com.android.ash.charactersheet.gui.sheet.item;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.ExpandableListItem;
import com.android.ash.charactersheet.gui.util.ItemFilter;
import com.android.ash.charactersheet.gui.widget.numberview.StepNumberView;
import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.ItemGroup;
import com.d20charactersheet.framework.boc.service.DisplayService;

import java.util.List;

/**
 * Adapater to display item groups in a ListView.
 */
public class ItemEditArrayAdapter extends ItemListAdapter {

    /**
     * Creates adapter to display group items.
     * 
     * @param context
     *            The context.
     * @param displayService
     *            Display service to format output.
     * @param layoutResourceId
     *            Id of layout file of item view.
     * @param equipmentFilter
     *            Filter of equipment.
     * @param items
     *            The list of item groups to display.
     */
    public ItemEditArrayAdapter(final Context context, final DisplayService displayService, final int layoutResourceId,
            final ItemFilter equipmentFilter, final List<ExpandableListItem> items) {
        super(context, displayService, layoutResourceId, equipmentFilter, items);
    }

    @Override
    protected void fillView(final View view, final ExpandableListItem expandListView) {
        final ItemGroup itemGroup = (ItemGroup) expandListView.getObject();
        final Item item = itemGroup.getItem();

        setBackgroundColor(view, item.isMagic());

        final TextView nameTextView = view.findViewById(R.id.item_name);
        nameTextView.setText(displayService.getDisplayItem(item));

        final StepNumberView goodNumberView = view.findViewById(R.id.item_number);
        goodNumberView.setController(new ItemGroupNumberViewController(itemGroup));

    }

}
