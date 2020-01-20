package com.android.ash.charactersheet.gui.admin.item.armor;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Armor;
import com.d20charactersheet.framework.boc.model.Item;

import java.util.Objects;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * Displays detail data of armor and allows to edit it.
 */
public class ArmorAdministrationEditActivity extends ArmorAdministrationActivity {

    @Override
    protected Item createForm() {
        final int armorId = Objects.requireNonNull(getIntent().getExtras()).getInt(INTENT_EXTRA_DATA_OBJECT);
        return itemService.getItemById(armorId, gameSystem.getAllArmor());
    }

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.armor_administration_edit_title);
    }

    @Override
    protected void saveForm() {
        itemService.updateArmor((Armor) form);
        gameSystem.clear();
    }

}
