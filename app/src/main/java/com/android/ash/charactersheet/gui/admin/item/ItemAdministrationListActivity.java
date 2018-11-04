package com.android.ash.charactersheet.gui.admin.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.AdministrationListActivity;
import com.android.ash.charactersheet.gui.util.ItemFilter;
import com.android.ash.charactersheet.gui.util.MagicOnClickListener;
import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.util.ItemComparator;

/**
 * Base Activity to display list of items in administration. Allows to call create and edit activity of the item and to
 * filter items by name and magic. Derive from this class for specific item implementations like weapon, armor and good.
 */
public abstract class ItemAdministrationListActivity extends AdministrationListActivity<Item> {

    private ItemFilter equipmentFilter;
    private CheckBox magicItemCheckBox;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        equipmentFilter = new ItemFilter();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.item_administration_list;
    }

    @Override
    protected ArrayAdapter<Item> getAdapter() {
        final List<Item> items = new ArrayList<Item>(getAllItems());
        Collections.sort(items, new ItemComparator());
        final ArrayAdapter<Item> adapter = new ItemAdministrationAdapter(this, displayService, R.layout.listitem_name,
                equipmentFilter, items);
        return adapter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumeListView();
    }

    private void resumeListView() {
        final ListView listView = getListView();
        listView.setAdapter(getAdapter());
        listView.setTextFilterEnabled(true);
        listView.setOnCreateContextMenuListener(this);
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_item_administration, menu);
        final MenuItem menuItem = menu.findItem(R.id.menu_activity_item_administration_magic_item);
        magicItemCheckBox = (CheckBox) menuItem.getActionView();
        magicItemCheckBox.setText(getString(R.string.item_administration_list_magic));
        magicItemCheckBox.setChecked(equipmentFilter.isMagic());
        magicItemCheckBox.setOnClickListener(new MagicOnClickListener(equipmentFilter));
        return true;
    }

    protected abstract List<? extends Item> getAllItems();

}
