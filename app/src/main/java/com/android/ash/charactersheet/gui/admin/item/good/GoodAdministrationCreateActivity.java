package com.android.ash.charactersheet.gui.admin.item.good;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Good;
import com.d20charactersheet.framework.boc.model.GoodType;
import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.QualityType;

/**
 * Creates new good.
 */
public class GoodAdministrationCreateActivity extends GoodAdministrationActivity {

    @Override
    protected Item createForm() {
        final Good good = new Good();
        good.setName("");
        good.setGoodType(GoodType.ADVENTURING_GEAR);
        good.setWeight(1);
        good.setCost(0);
        good.setQualityType(QualityType.NORMAL);
        good.setDescription("");
        return good;
    }

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.good_administration_create_title);
    }

    @Override
    protected void saveForm() {
        if (!form.getName().trim().isEmpty()) {
            itemService.createGood((Good) form);
            gameSystem.clear();
        }
    }

}
