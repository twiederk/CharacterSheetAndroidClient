package com.android.ash.charactersheet.gui.admin.spelllist;

import com.android.ash.charactersheet.gui.widget.ListModel;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.model.Spelllist;
import com.d20charactersheet.framework.boc.util.SpellComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Model of spell list. Spells are ordered by level and then sorted alphabetically.
 */
class SpelllistViewModel extends ListModel<SpelllevelView> {

    /**
     * Instantiates SpellListViewModel.
     * 
     * @param spelllist
     *            The spell list of the model.
     */
    public SpelllistViewModel(final Spelllist spelllist) {
        super();
        final List<SpelllevelView> items = createSpelllevelViews(spelllist);
        setItems(items);
    }

    private List<SpelllevelView> createSpelllevelViews(final Spelllist spelllist) {
        final List<SpelllevelView> viewObjects = new ArrayList<>();
        final List<Integer> levels = new ArrayList<>(spelllist.getSpellsByLevel().keySet());
        Collections.sort(levels);
        for (final Integer level : levels) {
            viewObjects.addAll(createViewObjects(spelllist, level));
        }
        return viewObjects;
    }

    private List<SpelllevelView> createViewObjects(final Spelllist spelllist, final int level) {
        final List<Spell> spells = new ArrayList<>(spelllist.getSpellsOfLevel(level));
        Collections.sort(spells, new SpellComparator());
        final List<SpelllevelView> viewObjects = new ArrayList<>(spells.size());
        for (final Spell spell : spells) {
            final SpelllevelView viewObject = new SpelllevelView(spelllist, spell, level);
            viewObjects.add(viewObject);
        }
        return viewObjects;
    }

}
