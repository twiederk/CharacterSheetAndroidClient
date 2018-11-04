package com.android.ash.charactersheet.gui.sheet.skill;

import com.android.ash.charactersheet.gui.util.MessageManager;
import com.android.ash.charactersheet.gui.widget.numberview.NumberViewController;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.CharacterSkill;
import com.d20charactersheet.framework.boc.model.RuleError;
import com.d20charactersheet.framework.boc.model.RuleException;
import com.d20charactersheet.framework.boc.service.CharacterService;

/**
 * NumberView Controller of skill rank.
 */
public class SkillRankNumberViewController implements NumberViewController {

    private final CharacterService characterService;

    private final Character character;
    private final CharacterSkill characterSkill;
    private final float maxRank;
    private final float step;

    private final MessageManager messageManager;

    /**
     * Creates NumberView controller of skill rank.
     * 
     * @param characterService
     *            The service to modifiy the skill rank.
     * @param character
     *            The character the skills belong to.
     * @param characterSkill
     *            Skill to control.
     * @param maxRank
     *            The maximum rank allowed.
     * @param step
     *            The step the rank increase by a invested skill point.
     * @param messageManager
     *            The MessageManger used to display a message if maximum rank is exceeded.
     */
    public SkillRankNumberViewController(final CharacterService characterService, final Character character,
            final CharacterSkill characterSkill, final float maxRank, final float step,
            final MessageManager messageManager) {
        this.characterService = characterService;
        this.character = character;
        this.characterSkill = characterSkill;
        this.maxRank = maxRank;
        this.step = step;
        this.messageManager = messageManager;
    }

    /**
     * Decreases rank by 0.5. Ranks is always >= 0.
     */
    @Override
    public void decrease() {
        if (characterSkill.getRank() > 0) {
            characterSkill.setRank(characterSkill.getRank() - step);
            characterService.updateCharacterSkill(character, characterSkill);
        }
    }

    /**
     * Returns rank of skill.
     */
    @Override
    public Number getNumber() {
        return characterSkill.getRank();
    }

    /**
     * Increases rank by 0.5.
     */
    @Override
    public void increase() {
        if (characterSkill.getRank() + step <= maxRank) {
            characterSkill.setRank(characterSkill.getRank() + step);
            characterService.updateCharacterSkill(character, characterSkill);
        } else {
            messageManager.showErrorMessage(new RuleException(RuleError.MAX_SKILL_RANK_EXCEEDED, maxRank));
        }
    }

    /**
     * @see com.android.ash.charactersheet.gui.widget.numberview.NumberViewController#setNumber(java.lang.Number)
     */
    @Override
    public void setNumber(final Number number) {
        characterSkill.setRank(number.floatValue());
        characterService.updateCharacterSkill(character, characterSkill);
    }

    @Override
    public void decrease(final Number number) {
        if (characterSkill.getRank() - number.floatValue() >= 0) {
            characterSkill.setRank(characterSkill.getRank() - number.floatValue());
        } else {
            characterSkill.setRank(0.0f);
        }
        characterService.updateCharacterSkill(character, characterSkill);
    }

    @Override
    public void increase(final Number number) {
        characterSkill.setRank(characterSkill.getRank() + number.floatValue());
        characterService.updateCharacterSkill(character, characterSkill);
    }

}
