package com.android.ash.charactersheet.gui.sheet.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.ExpandableListItem;
import com.android.ash.charactersheet.gui.util.ItemFilter;
import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.ItemGroup;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Adapter to display items in a ListView.
 */
public abstract class ItemListAdapter extends BaseAdapter implements Observer, Filterable {

    protected Resources resources;
    private final int itemViewResourceId;
    private final LayoutInflater mInflater;
    private final List<ExpandableListItem> allItems;
    private List<ExpandableListItem> filteredItems;

    protected DisplayService displayService;
    private final ItemFilter equipmentFilter;

    /**
     * Creates adapter to display given items.
     * 
     * @param context
     *            The application context.
     * @param displayService
     *            The display service to format output.
     * @param itemViewResourceId
     *            The id of the layout file used by each item view.
     * @param equipmentFilter
     *            The filter of the equipment.
     * @param allItems
     *            The items to display.
     */
    public ItemListAdapter(final Context context, final DisplayService displayService, final int itemViewResourceId,
            final ItemFilter equipmentFilter, final List<ExpandableListItem> allItems) {
        super();
        this.resources = context.getResources();
        this.itemViewResourceId = itemViewResourceId;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.displayService = displayService;
        this.allItems = allItems;
        this.filteredItems = new ArrayList<ExpandableListItem>(allItems);
        this.equipmentFilter = equipmentFilter;
        equipmentFilter.addObserver(this);

    }

    @Override
    public int getCount() {
        return filteredItems.size();
    }

    @Override
    public Object getItem(final int position) {
        return filteredItems.get(position);
    }

    @Override
    public long getItemId(final int position) {
        if (position >= filteredItems.size()) {
            return -1;
        }
        return position;
    }

    @Override
    public Filter getFilter() {
        return equipmentFilter;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(itemViewResourceId, parent, false);
        }
        final ExpandableListItem expandListView = filteredItems.get(position);
        fillView(convertView, expandListView);

        return convertView;
    }

    protected abstract void fillView(View convertView, ExpandableListItem expandListView);

    protected void setBackgroundColor(final View view, final boolean magic) {
        if (magic) {
            view.setBackgroundColor(resources.getColor(R.color.magic));
        } else {
            view.setBackgroundColor(resources.getColor(R.color.transparent));
        }
    }

    @Override
    public void update(final Observable observable, final Object object) {
        filterItems();
        notifyDataSetChanged();
    }

    private void filterItems() {
        filteredItems = new ArrayList<ExpandableListItem>(allItems);
        filterByMagic();
        filterByName();
    }

    private void filterByMagic() {
        if (equipmentFilter.isMagic()) {
            final List<ExpandableListItem> itemsToFilter = new ArrayList<ExpandableListItem>(filteredItems);
            for (final ExpandableListItem expandListView : itemsToFilter) {
                final ItemGroup itemGroup = (ItemGroup) expandListView.getObject();
                if (!itemGroup.getItem().isMagic()) {
                    filteredItems.remove(expandListView);
                }
            }
        }
    }

    private void filterByName() {
        if (equipmentFilter.getName() == null || equipmentFilter.getName().length() == 0) {
            return;
        }

        final List<ExpandableListItem> itemsToFilter = new ArrayList<ExpandableListItem>(filteredItems);
        for (final ExpandableListItem expandListView : itemsToFilter) {
            final ItemGroup itemGroup = (ItemGroup) expandListView.getObject();
            final String name = itemGroup.getItem().getName().toLowerCase(Locale.getDefault());

            if (!matchSplittedValue(name)) {
                filteredItems.remove(expandListView);
            }
        }
    }

    private boolean matchSplittedValue(final String name) {
        final String[] words = name.split(" ");
        final int wordCount = words.length;

        for (int i = 0; i < wordCount; i++) {
            if (words[i].startsWith(equipmentFilter.getName())) {
                return true;
            }
        }
        return false;
    }

    protected void displayDescription(final Item item, final TableRow tableRow, final View view) {
        if (item.getDescription() != null && item.getDescription().length() > 0) {
            tableRow.setVisibility(View.VISIBLE);

            final TextView descTextView = (TextView) view.findViewById(R.id.item_desc);
            descTextView.setText(item.getDescription());
        } else {
            tableRow.setVisibility(View.GONE);
        }
    }

}
