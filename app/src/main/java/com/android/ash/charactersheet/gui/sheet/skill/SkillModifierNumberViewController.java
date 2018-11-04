package com.android.ash.charactersheet.gui.sheet.skill;

import com.android.ash.charactersheet.gui.widget.numberview.NumberViewController;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.CharacterSkill;
import com.d20charactersheet.framework.boc.service.CharacterService;

/**
 * NumberView Controller of skill modifier.
 */
public class SkillModifierNumberViewController implements NumberViewController {

    private final CharacterService characterService;
    private final Character character;
    private final CharacterSkill characterSkill;

    /**
     * Creates controller of NumberView with given skill.
     * 
     * @param characterService
     *            The character service to modify the skill of the character.
     * @param character
     *            The character of the skills
     * @param characterSkill
     *            The character skill to modify.
     */
    public SkillModifierNumberViewController(final CharacterService characterService, final Character character,
            final CharacterSkill characterSkill) {
        this.characterService = characterService;
        this.character = character;
        this.characterSkill = characterSkill;
    }

    /**
     * Decrease skill modifier by one.
     */
    @Override
    public void decrease() {
        characterSkill.setModifier(characterSkill.getModifier() - 1);
        characterService.updateCharacterSkill(character, characterSkill);
    }

    /**
     * Returns skill modifier.
     */
    @Override
    public Number getNumber() {
        return characterSkill.getModifier();
    }

    /**
     * Increase skill modifier by one.
     */
    @Override
    public void increase() {
        characterSkill.setModifier(characterSkill.getModifier() + 1);
        characterService.updateCharacterSkill(character, characterSkill);
    }

    /**
     * @see com.android.ash.charactersheet.gui.widget.numberview.NumberViewController#setNumber(java.lang.Number)
     */
    @Override
    public void setNumber(final Number number) {
        characterSkill.setModifier(number.intValue());
        characterService.updateCharacterSkill(character, characterSkill);
    }

    @Override
    public void decrease(final Number number) {
        characterSkill.setModifier(characterSkill.getModifier() - number.intValue());
        characterService.updateCharacterSkill(character, characterSkill);
    }

    @Override
    public void increase(final Number number) {
        characterSkill.setModifier(characterSkill.getModifier() + number.intValue());
        characterService.updateCharacterSkill(character, characterSkill);
    }

}
