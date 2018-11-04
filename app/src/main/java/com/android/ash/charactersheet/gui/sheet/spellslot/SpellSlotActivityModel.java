package com.android.ash.charactersheet.gui.sheet.spellslot;

import java.util.Collections;
import java.util.List;
import java.util.Observable;

import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.Feat;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.model.SpellSlot;
import com.d20charactersheet.framework.boc.service.RuleService;
import com.d20charactersheet.framework.boc.util.SpellComparator;

/**
 * The model of the spell slot activity.
 */
public class SpellSlotActivityModel extends Observable {

    private final RuleService ruleService;
    private final Character character;
    private final SpellSlot spellSlot;
    private List<Spell> spells;

    /**
     * Creates SpellSlotActivityModel for the given character ans spell slot.
     * 
     * @param ruleService
     *            The RuleService to get spells to select.
     * @param character
     *            The character the spell slot belongs to.
     * @param spellSlot
     *            The spell slot.
     */
    public SpellSlotActivityModel(final RuleService ruleService, final Character character, final SpellSlot spellSlot) {
        super();
        this.ruleService = ruleService;
        this.character = character;
        this.spellSlot = spellSlot;
        this.spells = calculateSpells();
    }

    private List<Spell> calculateSpells() {
        final List<Spell> spells = ruleService.getSpellSelection(character, spellSlot);
        Collections.sort(spells, new SpellComparator());
        spells.add(0, SpellSlot.EMPTY_SPELL);
        return spells;
    }

    /**
     * Returns the spell slot.
     * 
     * @return The spell slot.
     */
    public SpellSlot getSpellSlot() {
        return spellSlot;
    }

    /**
     * Adds metamagic feat to spell slot.
     * 
     * @param feat
     *            The feat to add.
     */
    public void add(final Feat feat) {
        spellSlot.getMetamagicFeats().add(feat);
        changeFeat();
    }

    /**
     * Removes metamagic feat from spell slot.
     * 
     * @param feat
     *            The feat to remove.
     */
    public void remove(final Feat feat) {
        spellSlot.getMetamagicFeats().remove(feat);
        changeFeat();
    }

    private void changeFeat() {
        spells = calculateSpells();
        spellSlot.setSpell(SpellSlot.EMPTY_SPELL);
        fireEvent();
    }

    private void fireEvent() {
        setChanged();
        notifyObservers();
    }

    /**
     * Returns the character.
     * 
     * @return The character.
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Returns the spells to select from.
     * 
     * @return The spells to select from.
     */
    public List<Spell> getSpells() {
        return spells;
    }

    /**
     * Returns the position of the currently selected spell of the spell slot in the list of spells.
     * 
     * @return The position of the currently selected spell of the spell slot in the list of spells.
     */
    public int getSpellPosition() {
        for (int i = 0; i < spells.size(); i++) {
            if (spells.get(i).equals(spellSlot.getSpell())) {
                return i;
            }
        }
        return 0;
    }

}
