package com.android.ash.charactersheet.gui.sheet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ListView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.sheet.classability.CharacterAbilityAdapter;
import com.android.ash.charactersheet.gui.sheet.classability.CharacterAbilityListComparator;
import com.android.ash.charactersheet.gui.sheet.classability.CharacterAbilityListItem;
import com.android.ash.charactersheet.gui.sheet.classability.CharacterAbilityModel;
import com.android.ash.charactersheet.gui.sheet.classability.CheckBoxModelController;
import com.android.ash.charactersheet.gui.util.ExpandOnClickListener;
import com.android.ash.charactersheet.gui.util.ExpandableListItem;
import com.android.ash.charactersheet.gui.widget.ListAdapter;
import com.d20charactersheet.framework.boc.model.CharacterAbility;
import com.d20charactersheet.framework.boc.model.ClassLevel;

/**
 * Displays all abilities of an character gained by its classes. The abilities can be filtered by the level of the
 * character in its classes.
 */
public class ClassAbilityPageFragment extends PageFragment {

    private CharacterAbilityModel model;

    @Override
    protected int getLayoutId() {
        return R.layout.page_class_ability;
    }

    @Override
    protected void doCreateView() {
        final List<ExpandableListItem> listItems = new ArrayList<ExpandableListItem>();
        for (final ClassLevel classLevel : character.getClassLevels()) {
            listItems.addAll(getListItems(classLevel));
        }
        Collections.sort(listItems, new CharacterAbilityListComparator());
        model = new CharacterAbilityModel(character.getClassLevels(), listItems);
        model.filter();

        setBody();
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_page_class_ability, menu);
        final MenuItem menuItem = menu.findItem(R.id.menu_page_class_abilitiy_show_all_abilities);
        final CheckBox showAllCheckBox = (CheckBox) menuItem.getActionView();
        showAllCheckBox.setText(getString(R.string.character_class_ability_list_show_all_abilities));
        showAllCheckBox.setChecked(model.isShowAll());
        showAllCheckBox.setOnClickListener(new CheckBoxModelController(model, showAllCheckBox));
    }

    private Collection<ExpandableListItem> getListItems(final ClassLevel classLevel) {
        final List<ExpandableListItem> listItems = new ArrayList<ExpandableListItem>();
        for (final CharacterAbility characterAbility : classLevel.getCharacterAbilities()) {
            final CharacterAbilityListItem listItem = new CharacterAbilityListItem(characterAbility, classLevel
                    .getCharacterClass().getName());
            final ExpandableListItem expandableListItem = new ExpandableListItem(listItem);
            listItems.add(expandableListItem);
        }
        return listItems;
    }

    private void setBody() {
        final ListAdapter<ExpandableListItem> classAbilityArrayAdapter = new CharacterAbilityAdapter(getActivity(),
                displayService, characterService, R.layout.listitem_character_class_ability, model);
        final ListView listView = (ListView) view.findViewById(R.id.character_class_ability_list_view);
        listView.setAdapter(classAbilityArrayAdapter);
        listView.setOnItemClickListener(new ExpandOnClickListener());
        listView.setTextFilterEnabled(true);
    }

}
