package com.android.ash.charactersheet.gui.main.exportimport;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.ItemFilter;
import com.d20charactersheet.framework.boc.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

/**
 * Adapter to display items in a ListView.
 * 
 * @param <T>
 *            The class to export.
 */
class ExportItemListAdapter<T extends Item> extends BaseAdapter implements Observer, Filterable {

    private final Resources resources;
    private final int itemViewResourceId;
    private final LayoutInflater mInflater;
    private final List<T> allItems;
    private List<T> filteredItems;

    private final ItemFilter equipmentFilter;
    private final List<T> selectedItems;

    /**
     * Creates adapter to display given items.
     * 
     * @param context
     *            The application context.
     * @param itemViewResourceId
     *            The id of the layout file used by each item view.
     * @param equipmentFilter
     *            The filter of the equipment.
     * @param allItems
     *            The items to display.
     */
    public ExportItemListAdapter(final Context context, final int itemViewResourceId, final ItemFilter equipmentFilter,
            final List<T> allItems) {
        super();
        this.resources = context.getResources();
        this.itemViewResourceId = itemViewResourceId;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.allItems = allItems;
        this.filteredItems = new ArrayList<>(allItems);
        this.equipmentFilter = equipmentFilter;
        equipmentFilter.addObserver(this);
        selectedItems = new ArrayList<>(allItems.size());
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
        final T item = filteredItems.get(position);
        fillView(convertView, item);

        return convertView;
    }

    private void fillView(final View view, final T item) {
        setBackgroundColor(view, item.isMagic());
        final CheckBox checkbox = view.findViewById(R.id.listitem_export_checkbox);
        checkbox.setText(item.getName());

        checkbox.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View view) {
                final CheckBox checkBox = (CheckBox) view;
                if (checkBox.isChecked()) {
                    selectedItems.add(item);
                } else {
                    selectedItems.remove(item);
                }
            }
        });

    }

    private void setBackgroundColor(final View view, final boolean magic) {
        Context context = view.getContext();
        if (magic) {
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.magic));
        } else {
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));
        }
    }

    @Override
    public void update(final Observable observable, final Object object) {
        filterItems();
        notifyDataSetChanged();
    }

    private void filterItems() {
        filteredItems = new ArrayList<>(allItems);
        filterByMagic();
        filterByName();
    }

    private void filterByMagic() {
        if (equipmentFilter.isMagic()) {
            final List<T> itemsToFilter = new ArrayList<>(filteredItems);
            for (final T item : itemsToFilter) {
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

        final List<T> itemsToFilter = new ArrayList<>(filteredItems);
        for (final T item : itemsToFilter) {
            final String name = item.getName().toLowerCase(Locale.getDefault());

            if (!matchSplittedValue(name)) {
                filteredItems.remove(item);
            }
        }
    }

    private boolean matchSplittedValue(final String name) {
        final String[] words = name.split(" ");

        for (String word : words) {
            if (word.startsWith(equipmentFilter.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns list of selected items for export.
     * 
     * @return List of selected items for export.
     */
    public List<T> getSelectedItems() {
        return selectedItems;
    }
}
