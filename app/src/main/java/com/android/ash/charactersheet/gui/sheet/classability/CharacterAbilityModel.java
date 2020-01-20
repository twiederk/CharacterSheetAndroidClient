package com.android.ash.charactersheet.gui.sheet.classability;

import com.android.ash.charactersheet.gui.util.ExpandableListItem;
import com.android.ash.charactersheet.gui.widget.ListModel;
import com.d20charactersheet.framework.boc.model.ClassLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Stores all abilities a character gained by its classes.
 */
public class CharacterAbilityModel extends ListModel<ExpandableListItem> {

    private Map<String, Integer> characterClassLevels;
    private boolean showAll;

    /**
     * Create model with given classes of the character, its abilities and create filter.
     * 
     * @param classLevels
     *            The classes of the character.
     * @param characterAbilities
     *            The abilites of the character.
     */
    public CharacterAbilityModel(final List<ClassLevel> classLevels, final List<ExpandableListItem> characterAbilities) {
        super(characterAbilities);
        setFilter(new ClassAbilityListFilter(this));
        setCharacterClassLevels(classLevels);
    }

    private void setCharacterClassLevels(final List<ClassLevel> classLevels) {
        characterClassLevels = new HashMap<>();
        for (final ClassLevel classLevel : classLevels) {
            characterClassLevels.put(classLevel.getCharacterClass().getName(), classLevel.getLevel());
        }
    }

    /**
     * Set to true, to show all class abilities of the character. Set to false, to show only class abilities the
     * character is of proper level in this class.
     * 
     * @param showAll
     *            True, to show all abilities. False, to show only abilites allowed by class level.
     */
    public void setShowAll(final boolean showAll) {
        this.showAll = showAll;
        filter();
    }

    /**
     * Returns true, if all abilities are shown.
     * 
     * @return True, if all abilities are shown.
     */
    public boolean isShowAll() {
        return showAll;
    }

    /**
     * Returns the class name and the level the character has in this class.
     * 
     * @return The class name and the level the character has in this class.
     */
    public Map<String, Integer> getCharacterClassLevels() {
        return characterClassLevels;
    }
}
