package com.android.ash.charactersheet.gui.admin.item.good;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Good;
import com.d20charactersheet.framework.boc.model.Item;

import java.util.Objects;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * Edit an existing good.
 */
public class GoodAdministrationEditActivity extends GoodAdministrationActivity {

    @Override
    protected Item createForm() {
        final int goodId = Objects.requireNonNull(getIntent().getExtras()).getInt(INTENT_EXTRA_DATA_OBJECT);
        return itemService.getItemById(goodId, gameSystem.getAllGoods());
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
