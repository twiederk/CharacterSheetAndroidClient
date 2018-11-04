package com.android.ash.charactersheet.gui.admin.item.good;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Good;
import com.d20charactersheet.framework.boc.model.Item;

/**
 * Edit an existing good.
 */
public class GoodAdministrationEditActivity extends GoodAdministrationActivity {

    @Override
    protected Item createForm() {
        final int goodId = getIntent().getExtras().getInt(INTENT_EXTRA_DATA_OBJECT);
        final Good good = (Good) itemService.getItemById(goodId, gameSystem.getAllGoods());
        return good;
    }

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.good_administration_edit_title);
    }

    @Override
    protected void saveForm() {
        itemService.updateGood((Good) form);
        gameSystem.clear();
    }

}
