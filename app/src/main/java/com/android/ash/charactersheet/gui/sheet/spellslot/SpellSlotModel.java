package com.android.ash.charactersheet.gui.sheet.spellslot;

import android.annotation.SuppressLint;

import com.android.ash.charactersheet.gui.widget.ListModel;
import com.d20charactersheet.framework.boc.model.Attribute;
import com.d20charactersheet.framework.boc.model.ClassLevel;
import com.d20charactersheet.framework.boc.model.SpellSlot;
import com.d20charactersheet.framework.boc.util.SpellSlotComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model of spell slot list. Contains all class level, spell attribute an all spell slots.
 */
public class SpellSlotModel extends ListModel<Object> {

    private final ClassLevel classLevel;
    private final Attribute spellAttribute;
    private final List<SpellSlot> spellSlots;

    /**
     * Creates model with given call level, spell attribute and list of spell slots.
     * 
     * @param classLevel
     *            The class level, with the class and level to display.
     * @param spellAttribute
     *            The spell attribute to display.
     * @param spellSlots
     *            The spell slots belonging to this class.
     */
    public SpellSlotModel(final ClassLevel classLevel, final Attribute spellAttribute, final List<SpellSlot> spellSlots) {
        super();
        this.classLevel = classLevel;
        this.spellAttribute = spellAttribute;
        this.spellSlots = spellSlots;
        final List<Object> spellSlotItems = createSpellSlotItems();
        setItems(spellSlotItems);
    }

    @SuppressWarnings("ConstantConditions")
    private List<Object> createSpellSlotItems() {
        final List<Object> spellSlotItems = new ArrayList<>();
        final Map<Integer, List<SpellSlot>> spellSlotsByLevel = createSpellSlotsByLevel();
        final List<Integer> levels = new ArrayList<>(spellSlotsByLevel.keySet());
        Collections.sort(levels);
        for (final Integer level : levels) {
            final LevelView levelView = createLevelView(level);
            spellSlotItems.add(levelView);

            final List<SpellSlotView> spellSlotViews = createSpellSlotViews(levelView, spellSlotsByLevel.get(level));
            // levelView.setSpellItems(spellItems);
            spellSlotItems.addAll(spellSlotViews);
        }
        return spellSlotItems;
    }

    private Map<Integer, List<SpellSlot>> createSpellSlotsByLevel() {
        @SuppressLint("UseSparseArrays") final Map<Integer, List<SpellSlot>> spellSlotsByLevel = new HashMap<>();
        for (final SpellSlot spellSlot : spellSlots) {
            final Integer level = spellSlot.getLevel();
            List<SpellSlot> spellSlots = spellSlotsByLevel.get(level);
            if (spellSlots == null) {
                spellSlots = new ArrayList<>();
            }
            spellSlots.add(spellSlot);
            spellSlotsByLevel.put(level, spellSlots);
        }
        return spellSlotsByLevel;
    }

    private LevelView createLevelView(final Integer level) {
        return new LevelView(level);
    }

    private List<SpellSlotView> createSpellSlotViews(final LevelView levelView, final List<SpellSlot> spellSlots) {
        spellSlots.sort(new SpellSlotComparator());
        final List<SpellSlotView> spellSlotViews = new ArrayList<>();
        for (final SpellSlot spellSlot : spellSlots) {
            final SpellSlotView spellSlotView = new SpellSlotView(levelView, spellSlot);
            spellSlotViews.add(spellSlotView);
        }
        return spellSlotViews;
    }

    /**
     * Returns the name of the class to display as tab heading.
     * 
     * @return The name of the class.
     */
    public String getName() {
        return classLevel.getCharacterClass().getName();
    }

    /**
     * Returns the complete class level to display data at the top of the spell slot list.
     * 
     * @return The complete class level to display data at the top of the spell slot list.
     */
    public ClassLevel getClassLevel() {
        return classLevel;
    }

    /**
     * Returns the spell attribute.
     * 
     * @return The spell attribute.
     */
    public Attribute getSpellAttribute() {
        return spellAttribute;
    }

    /**
     * Called by resume from SpellSlotActivity.
     */
    public void resumeFromSpellSlotActivity() {
        fireEvent();
    }

}
