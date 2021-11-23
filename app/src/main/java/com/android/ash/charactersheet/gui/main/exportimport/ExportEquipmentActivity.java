package com.android.ash.charactersheet.gui.main.exportimport;

import static com.d20charactersheet.framework.boc.service.ExportImportService.EXPORT_EQUIPMENT_FILE_PREFIX;

import android.Manifest;
import android.content.pm.PackageManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.ItemFilter;
import com.android.ash.charactersheet.gui.util.Logger;
import com.android.ash.charactersheet.gui.util.MagicOnClickListener;
import com.d20charactersheet.framework.boc.model.Armor;
import com.d20charactersheet.framework.boc.model.Good;
import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.Weapon;
import com.d20charactersheet.framework.boc.service.ExportImportService;

import java.io.File;
import java.util.List;

/**
 * Displays equipment to export. The weapons, armor and goods are displayed in different tabs. Each equipment item is
 * displayed with its name and a check box to select it for export. The magic item check box allows to filter magic
 * items. An action bar icon starts the export.
 */
public class ExportEquipmentActivity extends AbstractExportActivity {

    private static final int PERMISSION_EXTERNAL_STORAGE = 100;

    private ItemFilter itemFilter;

    @Override
    int getLayoutId() {
        return R.layout.activity_export_equipment;
    }

    @Override
    int getTitleId() {
        return R.string.export_equipment_title;
    }

    @Override
    void createExportView() {
        itemFilter = new ItemFilter();
        createTabs();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_export_equipment, menu);
        final MenuItem menuItem = menu.findItem(R.id.menu_activity_export_equipment_magic_item);
        final CheckBox magicItemCheckBox = (CheckBox) menuItem.getActionView();
        magicItemCheckBox.setText(getString(R.string.equipment_list_show_magic));
        magicItemCheckBox.setChecked(itemFilter.isMagic());
        magicItemCheckBox.setOnClickListener(new MagicOnClickListener(itemFilter));
        return true;
    }

    private void createTabs() {
        final TabHost tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup();

        addTab(R.string.tab_label_weapon, R.id.weapon_list, R.drawable.icon_sword);
        addTab(R.string.tab_label_armor, R.id.armor_list, R.drawable.icon_shield);
        addTab(R.string.tab_label_good, R.id.good_list, R.drawable.icon_backpack);

        tabHost.setCurrentTab(0);
    }

    private void addTab(final int labelId, final int layoutId, final int iconId) {
        final TabHost tabHost = findViewById(android.R.id.tabhost);
        final String label = getString(labelId);
        final TabSpec tabSpec = tabHost.newTabSpec(label);
        tabSpec.setIndicator(label, ContextCompat.getDrawable(this, iconId));
        tabSpec.setContent(layoutId);
        tabHost.addTab(tabSpec);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final ExportItemListAdapter<Weapon> weaponListAdapter = new ExportItemListAdapter<>(this,
                R.layout.listitem_export, itemFilter, gameSystem.getAllWeapons());
        setListView(R.id.weapon_list, weaponListAdapter);

        final ExportItemListAdapter<Armor> armorListAdapter = new ExportItemListAdapter<>(this,
                R.layout.listitem_export, itemFilter, gameSystem.getAllArmor());
        setListView(R.id.armor_list, armorListAdapter);

        final ExportItemListAdapter<Good> goodListAdapter = new ExportItemListAdapter<>(this,
                R.layout.listitem_export, itemFilter, gameSystem.getAllGoods());
        setListView(R.id.good_list, goodListAdapter);
    }

    private void setListView(final int listViewId, final BaseAdapter adapter) {
        final ListView listView = findViewById(listViewId);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
    }

    @Override
    void export() {
        Logger.debug("export");
        final List<Weapon> weapons = getSelectedItems(R.id.weapon_list);
        final List<Armor> armor = getSelectedItems(R.id.armor_list);
        final List<Good> goods = getSelectedItems(R.id.good_list);

        Logger.debug("weapons: " + weapons);
        Logger.debug("armor: " + armor);
        Logger.debug("goods: " + goods);

        if (weapons.isEmpty() && armor.isEmpty() && goods.isEmpty()) {
            final String message = getResources().getString(R.string.export_equipment_message_select_equipment);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            return;
        }

        // export
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_EXTERNAL_STORAGE);
        } else {
            exportEquipment();
        }
    }

    private void exportEquipment() {
        final List<Weapon> weapons = getSelectedItems(R.id.weapon_list);
        final List<Armor> armor = getSelectedItems(R.id.armor_list);
        final List<Good> goods = getSelectedItems(R.id.good_list);

        final File exportFile = getExportFile(EXPORT_EQUIPMENT_FILE_PREFIX);
        final ExportImportService exportImportService = gameSystem.getExportImportService();
        try {
            exportImportService.exportEquipment(gameSystem, exportFile, weapons, armor, goods);
            displayMessage(R.string.export_message_export_succeed, exportFile.toString());
        } catch (final Exception exception) {
            Logger.error("Can't export characters", exception);
            displayMessage(R.string.export_message_export_failed, exception.getMessage());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                exportEquipment();
            }
        }
    }


    @SuppressWarnings("unchecked")
    private <T extends Item> List<T> getSelectedItems(final int listViewId) {
        final ListView listView = findViewById(listViewId);
        final ExportItemListAdapter<T> adapter = (ExportItemListAdapter<T>) listView.getAdapter();
        return adapter.getSelectedItems();
    }
}
