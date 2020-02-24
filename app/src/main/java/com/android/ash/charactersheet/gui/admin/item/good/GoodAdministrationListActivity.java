package com.android.ash.charactersheet.gui.admin.item.good;

import android.content.Intent;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.admin.item.ItemAdministrationListActivity;
import com.d20charactersheet.framework.boc.model.Item;

import java.util.List;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * Displays all goods of the game system in an alphabetical list. Allows to create or edit a good.
 */
public class GoodAdministrationListActivity extends ItemAdministrationListActivity {

    @Override
    protected int getTitleResource() {
        return R.string.good_administration_list_title;
    }

    @Override
    protected List<? extends Item> getAllItems() {
        return gameSystem.getAllGoods();
    }

    @Override
    protected Intent getCreateIntent() {
        return new Intent(this, GoodAdministrationCreateActivity.class);
    }

    @Override
    protected Intent getEditIntent(final Item item) {
        return new Intent(this, GoodAdministrationEditActivity.class).putExtra(INTENT_EXTRA_DATA_OBJECT, item.getId());
    }

}
