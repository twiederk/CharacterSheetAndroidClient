package com.android.ash.charactersheet.gui.sheet.feat;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.FeatType;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Adapter between the ListView of the feats and the feats model. It observes the feat model. The feat list is
 * filterable.
 */
public class FeatListAdapter extends BaseAdapter implements Observer, Filterable {

    private final int itemViewResourceId;
    private final LayoutInflater mInflater;
    private final Resources resources;

    private final FeatModel featModel;
    private List<FeatListItem> featItems;
    private final FeatType featType;
    private final FeatFilter featFilter;

    /**
     * Create adapter of given view using the feat model and feat type to retrieve the feats to display.
     * 
     * @param context
     *            The context to get the resources an layout inflater of.
     * @param itemViewResourceId
     *            The resource if of the list view.
     * @param featModel
     *            The model containing the feats.
     * @param featType
     *            The type of the feats to display.
     */
    public FeatListAdapter(final Context context, final int itemViewResourceId, final FeatModel featModel,
            final FeatType featType) {
        super();
        this.resources = context.getResources();
        this.itemViewResourceId = itemViewResourceId;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.featModel = featModel;
        this.featType = featType;
        this.featFilter = new FeatFilter(featModel);
        this.featItems = featModel.getFeatItems(featType);
        featModel.addObserver(this);
    }

    /**
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getCount() {
        return featItems.size();
    }

    /**
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public Object getItem(final int position) {
        return featItems.get(position);
    }

    /**
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public long getItemId(final int position) {
        if (position >= featItems.size()) {
            return -1;
        }
        return featItems.get(position).getId();
    }

    /**
     * Returns the filled view.
     * 
     * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(itemViewResourceId, parent, false);
        }
        final FeatListItem featItem = featItems.get(position);
        fillView(convertView, featItem);

        return convertView;
    }

    private void fillView(final View view, final FeatListItem featItem) {
        final TextView nameTextView = view.findViewById(R.id.feat_name);
        nameTextView.setText(featItem.getName());

        final TextView benefitTextView = view.findViewById(R.id.feat_benefit);
        benefitTextView.setText(featItem.getBenefit());
        final TextView headingBenefitTextView = view.findViewById(R.id.feat_benefit_heading);
        setVisibility(headingBenefitTextView, benefitTextView, featItem.isExpanded());

        final TextView prerequisitTextView = view.findViewById(R.id.feat_prerequisit);
        prerequisitTextView.setText(featItem.getPrerequisit());
        final TextView headingPrerequisitTextView = view.findViewById(R.id.feat_prerequisit_heading);
        setVisibility(headingPrerequisitTextView, prerequisitTextView, featItem.isExpanded());

        final TextView fighterBonusAndKindTextView = view.findViewById(R.id.feat_fighter_bonus_and_kind);
        fighterBonusAndKindTextView.setText(getFighterBonusAndKind(featItem));
        setVisibility(null, fighterBonusAndKindTextView, featItem.isExpanded());
    }

    private void setVisibility(final TextView headingTextView, final TextView contentTextView, final boolean expanded) {
        int visibility = View.GONE;
        if (expanded && !"".equals(contentTextView.getText().toString())) {
            visibility = View.VISIBLE;
        }
        if (headingTextView != null) {
            headingTextView.setVisibility(visibility);
        }
        contentTextView.setVisibility(visibility);
    }

    String getFighterBonusAndKind(final FeatListItem featItem) {
        final StringBuilder output = new StringBuilder();
        if (featItem.isFighterBonus() || featItem.isMultiple() || featItem.isStack()) {
            output.append("[");
            if (featItem.isFighterBonus()) {
                output.append(resources.getString(R.string.feat_list_fighter_bonus));
            }
            output.append(appendComma(featItem));
            if (featItem.isMultiple()) {
                output.append(resources.getString(R.string.feat_list_multiple));
            }
            if (featItem.isStack()) {
                output.append(resources.getString(R.string.feat_list_stack));
            }
            output.append("]");
        }
        return output.toString();
    }

    private String appendComma(final FeatListItem featItem) {
        if (featItem.isFighterBonus() && (featItem.isMultiple() || featItem.isStack())) {
            return ", ";
        }
        return "";
    }

    /**
     * Called by the feat model to notify the adapter of data changes in the model.
     * 
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(final Observable observable, final Object object) {
        Logger.debug("FeatListAdapter.update()");
        featItems = featModel.getFeatItems(featType);
        notifyDataSetChanged();
    }

    /**
     * @see android.widget.Filterable#getFilter()
     */
    @Override
    public Filter getFilter() {
        return featFilter;
    }

}
