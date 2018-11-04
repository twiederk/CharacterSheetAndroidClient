package com.android.ash.charactersheet.gui.admin.item.weapon;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

import java.util.List;

import android.content.Intent;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.admin.item.ItemAdministrationListActivity;
import com.d20charactersheet.framework.boc.model.Item;

/**
 * Lists all weapons. Opens the WeaponAdministrationEditActivity if a weapon is touched. The option menu contains the
 * Create Weapon option, which opens the WeaponAdministrationCreateActivity to create a new weapon.
 */
public class WeaponAdministrationListActivity extends ItemAdministrationListActivity {

    @Override
    protected int getTitleResource() {
        return R.string.weapon_administration_list_title;
    }

    @Override
    protected List<? extends Item> getAllItems() {
        return gameSystem.getAllWeapons();
    }

    @Override
    protected Intent getCreateIntent() {
        return new Intent(this, WeaponAdministrationCreateActivity.class);
    }

    @Override
    protected Intent getEditIntent(final Item item) {
        return new Intent(this, WeaponAdministrationEditActivity.class)
                .putExtra(INTENT_EXTRA_DATA_OBJECT, item.getId());
    }

}
