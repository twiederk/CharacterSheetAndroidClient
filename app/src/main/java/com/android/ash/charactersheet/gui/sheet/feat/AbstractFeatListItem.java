package com.android.ash.charactersheet.gui.sheet.feat;

import com.android.ash.charactersheet.gui.util.ExpandableListItem;
import com.d20charactersheet.framework.boc.model.Feat;
import com.d20charactersheet.framework.boc.model.FeatType;

/**
 * Abstract implemention of FeatItem. Use to derive FeatItem implementations from it.
 */
public abstract class AbstractFeatListItem extends ExpandableListItem implements FeatListItem {

    private final Feat feat;

    /**
     * Instanciate FeatItem of given feat.
     *
     * @param feat
     *            The feat of the FeatItem.
     */
    public AbstractFeatListItem(final Feat feat) {
        super(feat);
        this.feat = feat;
    }

    @Override
    public int getId() {
        return feat.getId();
    }

    @Override
    public FeatType getFeatType() {
        return feat.getFeatType();
    }

    @Override
    public String getBenefit() {
        return feat.getBenefit();
    }

    @Override
    public String getName() {
        return feat.getName();
    }

    @Override
    public boolean isMultiple() {
        return feat.isMultiple();
    }

    @Override
    public String toString() {
        return getName();
    }

    public Feat getFeat() {
        return feat;
    }

    public boolean isStack() {
        return feat.isStack();
    }

    public String getPrerequisit() {
        return feat.getPrerequisit();
    }

    public boolean isFighterBonus() {
        return feat.isFighterBonus();
    }
}
