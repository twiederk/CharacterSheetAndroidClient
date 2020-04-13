package com.android.ash.charactersheet.gui.sheet.feat;

import android.widget.Filter;

import com.android.ash.charactersheet.gui.util.Logger;

/**
 * Filter of the feat list. The filtering is done by the Feat Model. This class is only implemented to follow the
 * construct Android uses to filter list views.
 */
class FeatFilter extends Filter {

    private final FeatModel featModel;

    /**
     * Creates FeatFilter.
     * 
     * @param featModel
     *            The feat model.
     */
    FeatFilter(final FeatModel featModel) {
        super();
        this.featModel = featModel;
    }

    /**
     * Returns empty FilterResult.
     */
    @Override
    protected FilterResults performFiltering(final CharSequence filter) {
        Logger.debug("FeatFilter.performFiltering(" + filter + ")");
        return new FilterResults();

    }

    /**
     * Sets the filter to the feat model, which performs the filtering and notifies the views.
     */
    @Override
    protected void publishResults(final CharSequence filter, final FilterResults filterResults) {
        Logger.debug("FeatFilter.publishResults(" + filter + ")");
        featModel.setFilter((String) filter);
    }

}
