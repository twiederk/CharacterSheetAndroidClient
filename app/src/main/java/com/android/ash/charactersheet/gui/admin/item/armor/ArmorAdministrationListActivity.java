package com.android.ash.charactersheet.gui.admin.item.armor;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

import java.util.List;

import android.content.Intent;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.admin.item.ItemAdministrationListActivity;
import com.d20charactersheet.framework.boc.model.Item;

/**
 * Lists all armors in alphabetical order. Allows to touch and armor to open the Edit Armor activity. The option menu
 * contains the option to create a new armor.
 */
public class ArmorAdministrationListActivity extends ItemAdministrationListActivity {

    @Override
    protected int getTitleResource() {
        return R.string.armor_administration_list_title;
    }

    @Override
    protected List<? extends Item> getAllItems() {
        return gameSystem.getAllArmor();
    }

    @Override
    protected Intent getCreateIntent() {
        return new Intent(this, ArmorAdministrationCreateActivity.class);
    }

    @Override
    protected Intent getEditIntent(final Item item) {
        return new Intent(this, ArmorAdministrationEditActivity.class).putExtra(INTENT_EXTRA_DATA_OBJECT, item.getId());
    }

}
