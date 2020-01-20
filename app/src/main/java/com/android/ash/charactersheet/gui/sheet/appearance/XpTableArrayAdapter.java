package com.android.ash.charactersheet.gui.sheet.appearance;

import android.content.Context;

import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.XpTable;
import com.d20charactersheet.framework.boc.service.DisplayService;

import java.util.List;

/**
 * Adapter to display names of xp tables in spinner.
 */
class XpTableArrayAdapter extends SpinnerArrayAdapter<XpTable> {

    /**
     * Instanciates XpTableArrayAdapter
     * 
     * @param context
     *            The context
     * @param displayService
     *            The service to display data.
     * @param xpTables
     *            The xp tables to display.
     */
    public XpTableArrayAdapter(final Context context, final DisplayService displayService, final List<XpTable> xpTables) {
        super(context, displayService, xpTables);
    }

}
