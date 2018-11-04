package com.android.ash.charactersheet.gui.sheet.classability;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.android.ash.charactersheet.gui.util.ExpandableListItem;
import com.android.ash.charactersheet.gui.widget.StringListFilter;

/**
 * Filters list of class abilities by the level of the character in this class.
 */
public class ClassAbilityListFilter extends StringListFilter<ExpandableListItem> {

    /**
     * Creates filter to filter class abilities.
     * 
     * @param model
     *            Model to filter.
     */
    public ClassAbilityListFilter(final CharacterAbilityModel model) {
        super(model);
    }

    @Override
    public List<ExpandableListItem> performFiltering() {
        List<ExpandableListItem> filteredItems = super.performFiltering();
        final CharacterAbilityModel characterAbility = (CharacterAbilityModel) model;
        if (!characterAbility.isShowAll()) {
            filteredItems = filterByLevel(filteredItems);
        }
        return filteredItems;
    }

    private List<ExpandableListItem> filterByLevel(final List<ExpandableListItem> items) {
        final CharacterAbilityModel characterAbilityModel = (CharacterAbilityModel) model;
        final Map<String, Integer> characterClassLevels = characterAbilityModel.getCharacterClassLevels();
        final List<ExpandableListItem> filteredItems = new ArrayList<ExpandableListItem>();
        for (final ExpandableListItem expandableListItem : items) {
            final CharacterAbilityListItem listItem = (CharacterAbilityListItem) expandableListItem.getObject();
            final int level = characterClassLevels.get(listItem.getCharacterClassName());
            if (listItem.getLevel() <= level && listItem.isOwned()) {
                filteredItems.add(expandableListItem);
            }
        }
        return filteredItems;
    }

}
