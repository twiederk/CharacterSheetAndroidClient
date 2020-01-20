package com.android.ash.charactersheet.gui.sheet;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.sheet.raceability.RaceAbilityAdapter;
import com.android.ash.charactersheet.gui.util.ExpandOnClickListener;
import com.android.ash.charactersheet.gui.util.ExpandableListItem;
import com.d20charactersheet.framework.boc.model.Ability;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.Race;
import com.d20charactersheet.framework.boc.util.AbilityComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Display the detailed data of a race, containing its name, favored class, size, reach, space, speed and its abilities.
 * The abilities are displayed as list. Each ability can be expanded and collapes in the list.
 */
public class RaceAbilityPageFragment extends PageFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.page_race_ability;
    }

    @Override
    protected void doCreateView() {
        setHeader();
        setBody();
    }

    private void setHeader() {
        final Race race = character.getRace();

        final TextView raceName = view.findViewById(R.id.race_name);
        raceName.setText(getName(race));

        final TextView raceFavoriteCharacterClass = view
                .findViewById(R.id.race_favorite_character_class_name);
        raceFavoriteCharacterClass.setText(getFavoriteCharacterClass(race));

        final TextView raceSize = view.findViewById(R.id.race_size);
        raceSize.setText(getSize(race));

        final TextView raceReach = view.findViewById(R.id.race_reach);
        raceReach.setText(getReach(race));

        final TextView raceSpace = view.findViewById(R.id.race_space);
        raceSpace.setText(getSpace(race));

        final TextView raceSpeed = view.findViewById(R.id.race_speed);
        raceSpeed.setText(getSpeed(race));

    }

    private String getName(final Race race) {
        final String label = getResources().getString(R.string.race_ability_list_name);
        final String value = race.getName();
        return composeText(label, value);
    }

    private String getFavoriteCharacterClass(final Race race) {
        final String label = getResources().getString(R.string.race_ability_list_favorite_character_class);
        String value;
        final CharacterClass favoriteCharacterClass = race.getFavoredCharacterClass();
        if (CharacterClass.ANY_CHARACTER_CLASS.equals(favoriteCharacterClass)) {
            value = getResources().getString(R.string.race_ability_any_character_class);
        } else {
            value = race.getFavoredCharacterClass().getName();
        }
        return composeText(label, value);
    }

    private String getSize(final Race race) {
        final String label = getResources().getString(R.string.race_ability_list_size);
        final String value = displayService.getDisplaySize(race.getSize());
        return composeText(label, value);
    }

    private String getReach(final Race race) {
        final String label = getResources().getString(R.string.race_ability_list_reach);
        final String value = Integer.toString(race.getSize().getReach());
        return composeText(label, value);
    }

    private String getSpace(final Race race) {
        final String label = getResources().getString(R.string.race_ability_list_space);
        final String value = Float.toString(race.getSize().getSpace());
        return composeText(label, value);
    }

    private String getSpeed(final Race race) {
        final String label = getResources().getString(R.string.race_ability_list_speed);
        final String value = Integer.toString(race.getBaseLandSpeed());
        return composeText(label, value);
    }

    private String composeText(final String label, final String value) {
        return label +
                ": " +
                value;
    }

    private void setBody() {
        final List<ExpandableListItem> racialAbilities = getRaceAbilities();
        final ArrayAdapter<ExpandableListItem> raceAbilityArrayAdapter = new RaceAbilityAdapter(getActivity(),
                displayService, R.layout.listitem_race_ability, racialAbilities);
        final ListView listView = view.findViewById(R.id.race_ability_list_view);
        listView.setAdapter(raceAbilityArrayAdapter);
        listView.setOnItemClickListener(new ExpandOnClickListener());
        listView.setTextFilterEnabled(true);
    }

    private List<ExpandableListItem> getRaceAbilities() {
        final List<Ability> abilities = character.getRace().getAbilities();
        Collections.sort(abilities, new AbilityComparator());
        final List<ExpandableListItem> racialAbilities = new ArrayList<>();
        for (final Ability ability : abilities) {
            final ExpandableListItem expandListView = new ExpandableListItem(ability);
            racialAbilities.add(expandListView);
        }
        return racialAbilities;
    }

}
