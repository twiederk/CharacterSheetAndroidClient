package com.android.ash.charactersheet.gui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.Observable;
import java.util.Observer;

/**
 * Filterable ListAdapter with observable model.
 * 
 * @param <T>
 *            The class of the listed items.
 */
public abstract class ListAdapter<T> extends BaseAdapter implements Filterable, Observer {

    private final int itemViewResourceId;
    private final LayoutInflater mInflater;

    private final ListModel<T> listModel;

    /**
     * Creates instance with given data.
     * 
     * @param context
     *            The context the adapter belongs to.
     * @param itemViewResourceId
     *            The id of the layout file.
     * @param listModel
     *            The model of the items to list.
     */
    protected ListAdapter(final Context context, final int itemViewResourceId, final ListModel<T> listModel) {
        super();
        this.itemViewResourceId = itemViewResourceId;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listModel = listModel;
        listModel.addObserver(this);
    }

    @Override
    public int getCount() {
        return listModel.getCount();
    }

    @Override
    public Object getItem(final int position) {
        return listModel.getItem(position);
    }

    @Override
    public long getItemId(final int position) {
        return listModel.getItemId(position);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(itemViewResourceId, parent, false);
        }
        final Object item = listModel.getItem(position);
        fillView(convertView, item);

        return convertView;
    }

    protected abstract void fillView(View convertView, Object item);

    @Override
    public void update(final Observable observable, final Object data) {
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return listModel.getFilter();
    }

    /**
     * Returns the list model of the adapter.
     * 
     * @return The list model.
     */
    protected ListModel<T> getListModel() {
        return listModel;
    }

}
