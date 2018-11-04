package com.android.ash.charactersheet.gui.admin.item.armor;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Armor;
import com.d20charactersheet.framework.boc.model.Item;

/**
 * Displays detail data of armor and allows to edit it.
 */
public class ArmorAdministrationEditActivity extends ArmorAdministrationActivity {

    @Override
    protected Item createForm() {
        final int armorId = getIntent().getExtras().getInt(INTENT_EXTRA_DATA_OBJECT);
        final Armor armor = (Armor) itemService.getItemById(armorId, gameSystem.getAllArmor());
        return armor;
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
