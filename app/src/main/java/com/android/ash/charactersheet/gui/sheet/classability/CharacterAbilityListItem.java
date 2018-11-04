package com.android.ash.charactersheet.gui.sheet.classability;

import com.d20charactersheet.framework.boc.model.AbilityType;
import com.d20charactersheet.framework.boc.model.CharacterAbility;

/**
 * Hold data to display class ability and its according class.
 */
public class CharacterAbilityListItem {

    private final CharacterAbility characterAbility;
    private final String characterClassName;

    /**
     * Creates instance with data of class ability and according class name.
     * 
     * @param characterAbility
     *            The CharacterAbility.
     * @param characterClassName
     *            The name of the class the ability belongs to.
     */
    public CharacterAbilityListItem(final CharacterAbility characterAbility, final String characterClassName) {
        this.characterAbility = characterAbility;
        this.characterClassName = characterClassName;
    }

    /**
     * Returns the name of the ability.
     * 
     * @return The name of the ability.
     */
    public String getAbilityName() {
        return characterAbility.getClassAbility().getAbility().getName();
    }

    /**
     * Returns the type of the ability.
     * 
     * @return The type of the ability.
     */
    public AbilityType getAbilityType() {
        return characterAbility.getClassAbility().getAbility().getAbilityType();
    }

    /**
     * Returns the description of the ability.
     * 
     * @return The description of the ability.
     */
    public String getAbilityDescription() {
        return characterAbility.getClassAbility().getAbility().getDescription();
    }

    /**
     * Returns the level the abiltiy is gained.
     * 
     * @return The level the ability is gained.
     */
    public int getLevel() {
        return characterAbility.getClassAbility().getLevel();
    }

    /**
     * Returns true, if the ability is owned by the character.
     * 
     * @return True, if the ability is owned by the character.
     */
    public boolean isOwned() {
        return characterAbility.isOwned();
    }

    /**
     * Set true to own the ability by the character.
     * 
     * @param owned
     *            True, to own ability.
     */
    public void setOwned(final boolean owned) {
        characterAbility.setOwned(owned);
    }

    /**
     * Returns the name of the class the ability belongs to.
     * 
     * @return The name of the class the ability belongs to.
     */
    public String getCharacterClassName() {
        return characterClassName;
    }

    /**
     * Returns the CharacterAbility.
     * 
     * @return The CharacterAbility.
     */
    public CharacterAbility getCharacterAbility() {
        return characterAbility;
    }

    @Override
    public String toString() {
        return characterAbility.toString();
    }

}
