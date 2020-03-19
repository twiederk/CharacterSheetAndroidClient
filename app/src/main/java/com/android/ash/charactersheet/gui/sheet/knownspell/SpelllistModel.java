package com.android.ash.charactersheet.gui.sheet.knownspell;

import com.android.ash.charactersheet.gui.widget.ListModel;
import com.d20charactersheet.framework.boc.model.KnownSpell;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.model.Spelllist;
import com.d20charactersheet.framework.boc.model.SpelllistAbility;
import com.d20charactersheet.framework.boc.util.SpellComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The model of the ListView in SpelllistPageFragment.
 */
public class SpelllistModel extends ListModel<Object> {

    private final SpelllistAbility spelllistAbility;
    private final List<KnownSpell> knownSpells;

    /**
     * Instantiates SpelllistModel. If knownSpells is empty all spells are shown, otherwise only marked spells.
     * 
     * @param knownSpellPageModel
     *            The known spell page model.
     * @param spelllistAbility
     *            The spell list ability.
     * @param knownSpells
     *            The spells marked by the character.
     */
    public SpelllistModel(final KnownSpellPageModel knownSpellPageModel, final SpelllistAbility spelllistAbility,
            final List<KnownSpell> knownSpells) {
        super();
        this.knownSpells = knownSpells;
        this.spelllistAbility = spelllistAbility;
        setFilter(new SpelllistFilter(knownSpellPageModel, this));
        final List<Object> spelllistItems = createSpelllistItems(spelllistAbility.getSpelllist());
        setItems(spelllistItems);
    }

    private List<Object> createSpelllistItems(final Spelllist spelllist) {
        final List<Object> spelllistItems = new ArrayList<>();
        final List<Integer> levels = new ArrayList<>(spelllist.getSpellsByLevel().keySet());
        Collections.sort(levels);
        for (final Integer level : levels) {
            final LevelItem levelItem = createLevelItem(level);
            spelllistItems.add(levelItem);

            final List<SpellItem> spellItems = createSpellItems(spelllist, levelItem);
            levelItem.setSpellItems(spellItems);
            spelllistItems.addAll(spellItems);
        }
        return spelllistItems;
    }

    private LevelItem createLevelItem(final int level) {
        final LevelItem levelItem = new LevelItem();
        levelItem.setLevel(level);
        levelItem.setExpanded(true);
        return levelItem;
    }

    private List<SpellItem> createSpellItems(final Spelllist spelllist, final LevelItem levelItem) {
        final List<Spell> spells = new ArrayList<>(spelllist.getSpellsOfLevel(levelItem.getLevel()));
        Collections.sort(spells, new SpellComparator());
        final List<SpellItem> spellItems = new ArrayList<>(spells.size());
        for (final Spell spell : spells) {
            final SpellItem spellItem = new SpellItem(spell, levelItem);
            spellItem.setKnownSpell(isKnownSpell(spell));
            spellItems.add(spellItem);
        }
        return spellItems;
    }

    private boolean isKnownSpell(final Spell spell) {
        for (final KnownSpell knownSpell : knownSpells) {
            if (knownSpell.getSpell().equals(spell)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns spell marked by character. Null if the spell isn't marked by the character.
     * 
     * @param spell
     *            The spell to get the marked KnownSpell to.
     * @return The marked KnownSpell, or null if the spell isn't marked.
     */
    public KnownSpell getKnownSpellBySpellId(final Spell spell) {
        for (final KnownSpell knownSpell : knownSpells) {
            if (knownSpell.getSpell().equals(spell)) {
                return knownSpell;
            }
        }
        return null;
    }

    /**
     * Returns the name of the spell list.
     * 
     * @return The name of the spell list.
     */
    public String getName() {
        return spelllistAbility.getSpelllist().getName();
    }

    /**
     * Returns the spell list ability.
     * 
     * @return The spell list ability.
     */
    public SpelllistAbility getSpelllistAbility() {
        return spelllistAbility;
    }

}
