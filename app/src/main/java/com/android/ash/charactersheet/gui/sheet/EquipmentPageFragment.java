package com.android.ash.charactersheet.gui.sheet;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.android.ash.charactersheet.FBAnalytics;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.sheet.item.ArmorEditActivity;
import com.android.ash.charactersheet.gui.sheet.item.ArmorListAdapter;
import com.android.ash.charactersheet.gui.sheet.item.EquipmentHelper;
import com.android.ash.charactersheet.gui.sheet.item.GoodEditActivity;
import com.android.ash.charactersheet.gui.sheet.item.GoodListAdapter;
import com.android.ash.charactersheet.gui.sheet.item.ItemListAdapter;
import com.android.ash.charactersheet.gui.sheet.item.WeaponEditActivity;
import com.android.ash.charactersheet.gui.sheet.item.WeaponListAdapter;
import com.android.ash.charactersheet.gui.util.ExpandOnClickListener;
import com.android.ash.charactersheet.gui.util.ItemFilter;
import com.android.ash.charactersheet.gui.util.Logger;
import com.android.ash.charactersheet.gui.util.MagicOnClickListener;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

/**
 * Displays equipment of the character. Weapons, armor and goods are displayed in separate tabs. Items can be touched to
 * expand displayed information. The menu options call activities to edit number of weapons, armor and goods.
 */
public class EquipmentPageFragment extends PageFragment {

    private ItemFilter equipmentFilter;
    private EquipmentHelper equipmentListHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.page_equip;
    }

    @Override
    protected void doCreateView() {
        createLayout();
    }

    private void createLayout() {
        equipmentFilter = new ItemFilter();
        createTabs();
    }

    private void resumeLoad() {
        final TextView loadTextView = view.findViewById(R.id.equipment_list_load);
        loadTextView.setText(getLoadText());
    }

    private String getLoadText() {
        final float weight = ruleService.getLoad(character);
        return getResources().getString(R.string.equipment_list_load) + ": " + weight + " " + getResources().getString(R.string.unit_weight);
    }

    private void createTabs() {
        final TabHost tabHost = view.findViewById(android.R.id.tabhost);
        tabHost.setup();

        addTab(R.string.tab_label_weapon, R.id.weapon_list, R.drawable.icon_sword);
        addTab(R.string.tab_label_armor, R.id.armor_list, R.drawable.icon_shield);
        addTab(R.string.tab_label_good, R.id.good_list, R.drawable.icon_backpack);

        tabHost.setCurrentTab(0);
    }

    @Override
    void addTab(final int labelId, final int layoutId, final int iconId) {
        final TabHost tabHost = view.findViewById(android.R.id.tabhost);
        final String label = (String) requireActivity().getResources().getText(labelId);
        final TabSpec tabSpec = tabHost.newTabSpec(label);
        tabSpec.setIndicator(label, ContextCompat.getDrawable(requireActivity(), iconId));
        tabSpec.setContent(layoutId);
        tabHost.addTab(tabSpec);
    }

    @Override
    public void onResume() {
        super.onResume();
        firebaseAnalytics.getValue().setCurrentScreen(requireActivity(), FBAnalytics.ScreenName.EQUIPMENT, "EquipmentPageFragment");
        equipmentListHelper = new EquipmentHelper(character);

        resumeLoad();
        resumeWeaponListView();
        resumeArmorListView();
        resumeGoodListView();
    }

    private void resumeWeaponListView() {
        final ItemListAdapter weaponListAdapter = new WeaponListAdapter(getActivity(), displayService,
                R.layout.listitem_weapon, equipmentFilter, equipmentListHelper.getWeaponViews());
        setListView(R.id.weapon_list, weaponListAdapter);
    }

    private void resumeArmorListView() {
        final ArmorListAdapter armorListAdapter = new ArmorListAdapter(getActivity(), displayService,
                R.layout.listitem_armor, equipmentFilter, equipmentListHelper.getArmorViews());
        setListView(R.id.armor_list, armorListAdapter);
    }

    private void resumeGoodListView() {
        final GoodListAdapter goodListAdapter = new GoodListAdapter(getActivity(), displayService,
                R.layout.listitem_good, equipmentFilter, equipmentListHelper.getGoodViews());
        setListView(R.id.good_list, goodListAdapter);
    }

    private void setListView(final int listViewId, final BaseAdapter adapter) {
        final ListView listView = view.findViewById(listViewId);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new ExpandOnClickListener());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull final Menu menu, @NonNull final MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.menu_page_equip, menu);

        final MenuItem menuItem = menu.findItem(R.id.menu_page_equip_magic_item);
        final CheckBox magicItemCheckBox = (CheckBox) menuItem.getActionView();
        if (magicItemCheckBox != null) {
            magicItemCheckBox.setText(getString(R.string.equipment_list_show_magic));
            magicItemCheckBox.setChecked(equipmentFilter.isMagic());
            magicItemCheckBox.setOnClickListener(new MagicOnClickListener(equipmentFilter));
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_page_equip_edit_weapons:
                editWeapons();
                break;
            case R.id.menu_page_equip_edit_armor:
                editArmor();
                break;
            case R.id.menu_page_equip_edit_goods:
                editGoods();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void editWeapons() {
        Logger.debug("editWeapons");
        startActivity(new Intent(getActivity(), WeaponEditActivity.class));
    }

    private void editArmor() {
        Logger.debug("editArmor");
        startActivity(new Intent(getActivity(), ArmorEditActivity.class));
    }

    private void editGoods() {
        Logger.debug("editGoods");
        startActivity(new Intent(getActivity(), GoodEditActivity.class));
    }

}
