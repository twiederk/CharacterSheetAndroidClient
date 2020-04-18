package com.android.ash.charactersheet.gui.sheet.item;

import com.android.ash.charactersheet.FBAnalytics;
import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.ItemGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows to edit the armor of the character.
 */
public class ArmorEditActivity extends ItemEditActivity {

    @Override
    protected List<Item> getAllExpandListViews() {
        return new ArrayList<>(gameSystem.getAllArmor());
    }

    @Override
    protected List<ItemGroup> getCharacterItems() {
        return character.getEquipment().getArmor();
    }

    @Override
    protected void onSave(final List<ItemGroup> items) {
        characterService.updateArmor(character, items);
        firebaseAnalytics.getValue().logEvent(FBAnalytics.Event.ARMOR_EDIT, null);
    }

}
