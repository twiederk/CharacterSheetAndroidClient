package com.android.ash.charactersheet.gui.sheet.item;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.BaseCharacterSheetActivity;
import com.android.ash.charactersheet.gui.util.ExpandableListItem;
import com.android.ash.charactersheet.gui.util.ItemFilter;
import com.android.ash.charactersheet.gui.util.MagicOnClickListener;
import com.android.ash.charactersheet.gui.util.SearchTextWatcher;
import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.ItemGroup;
import com.d20charactersheet.framework.boc.service.ItemService;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Base class to expand activities to edit items from. Displays items in a list view. The number of each item can be
 * edit by a NumberView. Saves data if back button is pressed.
 */
public abstract class ItemEditActivity extends BaseCharacterSheetActivity {

    ItemService itemService;

    private List<ExpandableListItem> allExpandListViews;
    private EquipmentHelper equipmentHelper;
    private ItemFilter equipmentFilter;

    @Override
    protected int getLayoutId() {
        return R.layout.item_edit;
    }

    @Override
    protected void doCreate() {
        itemService = Objects.requireNonNull(gameSystem).getItemService();

        createLayout();
    }

    private void createLayout() {
        equipmentFilter = new ItemFilter();
        equipmentHelper = new EquipmentHelper(character);
        setItemList();
    }

    private void setItemList() {
        allExpandListViews = equipmentHelper.createItemGroups(getAllExpandListViews(), getCharacterItems());
        final ItemEditArrayAdapter itemEditArrayAdapter = new ItemEditArrayAdapter(this, displayService,
                equipmentFilter, allExpandListViews);
        final ListView listView = findViewById(R.id.item_edit_list);
        listView.setAdapter(itemEditArrayAdapter);
        listView.setTextFilterEnabled(true);

        final EditText searchEditText = findViewById(R.id.item_edit_search_text);
        searchEditText.addTextChangedListener(new SearchTextWatcher(listView));
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            saveItems();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void saveItems() {
        Toast.makeText(this, R.string.toast_saving, Toast.LENGTH_LONG).show();
        final List<ItemGroup> items = getItems();
        onSave(items);
    }

    private List<ItemGroup> getItems() {
        final List<ItemGroup> items = new LinkedList<>();
        for (final ExpandableListItem expandListView : allExpandListViews) {
            final ItemGroup itemGroup = (ItemGroup) expandListView.getObject();
            if (itemGroup.getNumber() > 0) {
                items.add(itemGroup);
            }
        }
        return items;
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_item_edit, menu);
        final MenuItem menuItem = menu.findItem(R.id.menu_activity_item_edit_magic_item);
        final CheckBox magicItemCheckBox = (CheckBox) menuItem.getActionView();
        magicItemCheckBox.setText(getString(R.string.item_edit_magic_label));
        magicItemCheckBox.setChecked(equipmentFilter.isMagic());
        magicItemCheckBox.setOnClickListener(new MagicOnClickListener(equipmentFilter));
        return true;
    }

    protected abstract List<ItemGroup> getCharacterItems();

    protected abstract List<Item> getAllExpandListViews();

    protected abstract void onSave(List<ItemGroup> items);

}
