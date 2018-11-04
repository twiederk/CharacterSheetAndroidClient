package com.android.ash.charactersheet.gui.widget;

import java.io.File;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.ash.charactersheet.R;

/**
 * Adapter to display file names in a ListView.
 */
public class FileListAdapter extends ListAdapter<File> {

    /**
     * Creates FileListAdapter.
     * 
     * @param context
     *            The context.
     * @param itemViewResourceId
     *            The layout resource to use for display.
     * @param listModel
     *            The files to displays.
     */
    public FileListAdapter(final Context context, final int itemViewResourceId, final ListModel<File> listModel) {
        super(context, itemViewResourceId, listModel);
    }

    @Override
    protected void fillView(final View convertView, final Object item) {
        final File file = (File) item;
        final TextView nameTextView = (TextView) convertView.findViewById(R.id.name);
        nameTextView.setText(file.getName());
    }

}
