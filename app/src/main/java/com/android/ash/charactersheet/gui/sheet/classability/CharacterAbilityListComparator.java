package com.android.ash.charactersheet.gui.sheet.classability;

import java.io.Serializable;
import java.util.Comparator;

import com.android.ash.charactersheet.gui.util.ExpandableListItem;

/**
 * Compares class abilities by their name.
 */
public class CharacterAbilityListComparator implements Comparator<ExpandableListItem>, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public int compare(final ExpandableListItem expandItem1, final ExpandableListItem expandItem2) {
        final CharacterAbilityListItem abilityItem1 = (CharacterAbilityListItem) expandItem1.getObject();
        final CharacterAbilityListItem abilityItem2 = (CharacterAbilityListItem) expandItem2.getObject();
        return abilityItem1.getAbilityName().compareTo(abilityItem2.getAbilityName());
    }

}
