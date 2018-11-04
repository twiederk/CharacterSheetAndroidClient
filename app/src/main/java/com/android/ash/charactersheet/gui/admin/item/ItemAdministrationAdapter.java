package com.android.ash.charactersheet.gui.admin.item;

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
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.ItemFilter;
import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * The adapter of listview in ItemAdministrationListActivity. It displays a listitem with its name. Filters the list by
 * name and magical. Magic items are displayed with a different background color.
 */
public class ItemAdministrationAdapter extends ArrayAdapter<Item> implements Observer, Filterable {

    private final Resources resources;
    private final int itemViewResourceId;
    private final LayoutInflater mInflater;
    private final List<Item> allItems;
    private List<Item> filteredItems;

    private final ItemFilter equipmentFilter;
    private final DisplayService displayService;

    /**
     * Creates the ItemAdministrationAdapter with the given filter and list of items.
     * 
     * @param context
     *            The activtiy context.
     * @param displayService
     *            The service to display data in humanreadable format.
     * @param itemViewResourceId
     *            The id of the layout resource.
     * @param equipmentFilter
     *            The filter of the equipment.
     * @param allItems
     *            The list of all items to display.
     */
    public ItemAdministrationAdapter(final Context context, final DisplayService displayService,
            final int itemViewResourceId, final ItemFilter equipmentFilter, final List<Item> allItems) {
        super(context, itemViewResourceId);
        this.resources = context.getResources();
        this.itemViewResourceId = itemViewResourceId;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.displayService = displayService;
        this.allItems = allItems;
        this.filteredItems = new ArrayList<Item>(allItems);
        this.equipmentFilter = equipmentFilter;
        equipmentFilter.addObserver(this);
    }

    @Override
    public int getCount() {
        return filteredItems.size();
    }

    @Override
    public Item getItem(final int position) {
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
        final Item item = filteredItems.get(position);
        fillView(convertView, item);

        return convertView;
    }

    protected void fillView(final View view, final Item item) {
        final TextView featNameTextView = (TextView) view.findViewById(R.id.name);
        featNameTextView.setText(displayService.getDisplayItem(item));

        setBackgroundColor(view, item.isMagic());
    }

    private void setBackgroundColor(final View view, final boolean magic) {
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
        filteredItems = new ArrayList<Item>(allItems);
        filterByMagic();
        filterByName();

    }

    private void filterByMagic() {
        if (equipmentFilter.isMagic()) {
            final List<Item> itemsToFilter = new ArrayList<Item>(filteredItems);
            for (final Item item : itemsToFilter) {
                if (!item.isMagic()) {
                    filteredItems.remove(item);
                }
            }
        }
    }

    private void filterByName() {
        if (equipmentFilter.getName() == null || equipmentFilter.getName().length() == 0) {
            return;
        }

        final List<Item> itemsToFilter = new ArrayList<Item>(filteredItems);
        for (final Item item : itemsToFilter) {
            final String name = item.getName().toLowerCase(Locale.getDefault());
            if (!matchSplittedValue(name)) {
                filteredItems.remove(item);
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
