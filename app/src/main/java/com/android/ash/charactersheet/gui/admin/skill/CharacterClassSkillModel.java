package com.android.ash.charactersheet.gui.admin.skill;

import com.d20charactersheet.framework.boc.model.CharacterClass;

/**
 * Stores a character class and if it is a class of the skill.
 */
class CharacterClassSkillModel {

    private final CharacterClass characterClass;
    private boolean classSkill;

    /**
     * Creates the model of the given class with the given status about class skill.
     * 
     * @param characterClass
     *            The character class.
     * @param classSkill
     *            True, if the character class is a class of the skill.
     */
    public CharacterClassSkillModel(final CharacterClass characterClass, final boolean classSkill) {
        this.characterClass = characterClass;
        this.classSkill = classSkill;
    }

    /**
     * Returns the character class.
     * 
     * @return The character class.
     */
    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    /**
     * Returns true, if the character class is class of the skill.
     * 
     * @return True, if the character class is class of the skill.
     */
    public boolean isClassSkill() {
        return classSkill;
    }

    /**
     * Sets if the character class is a class of the skill.
     * 
     * @param classSkill
     *            Sets, if the character class is a class of the skill.
     */
    public void setClassSkill(final boolean classSkill) {
        this.classSkill = classSkill;
    }

}
