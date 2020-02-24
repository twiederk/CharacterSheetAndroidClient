package com.android.ash.charactersheet.gui.sheet.item;

import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.ItemGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity to edit number of goods carried by the character.
 */
public class GoodEditActivity extends ItemEditActivity {

    @Override
    protected List<Item> getAllExpandListViews() {
        return new ArrayList<>(gameSystem.getAllGoods());
    }

    @Override
    protected List<ItemGroup> getCharacterItems() {
        return character.getEquipment().getGoods();
    }

    @Override
    protected void onSave(final List<ItemGroup> items) {
        characterService.updateGoods(character, items);
    }
}
