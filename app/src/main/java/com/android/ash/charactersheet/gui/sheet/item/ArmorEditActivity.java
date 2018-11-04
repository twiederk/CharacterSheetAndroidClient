package com.android.ash.charactersheet.gui.sheet.item;

import java.util.ArrayList;
import java.util.List;

import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.ItemGroup;

/**
 * Allows to edit the armor of the character.
 */
public class ArmorEditActivity extends ItemEditActivity {

    @Override
    protected List<Item> getAllExpandListViews() {
        return new ArrayList<Item>(gameSystem.getAllArmor());
    }

    @Override
    protected List<ItemGroup> getCharacterItems() {
        return character.getEquipment().getArmor();
    }

    @Override
    protected void onSave(final List<ItemGroup> items) {
        characterService.updateArmor(character, items);
    }

}
