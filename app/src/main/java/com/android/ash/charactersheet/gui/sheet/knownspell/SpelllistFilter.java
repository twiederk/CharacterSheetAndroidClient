package com.android.ash.charactersheet.gui.sheet.knownspell;

import com.android.ash.charactersheet.gui.widget.ListModel;
import com.android.ash.charactersheet.gui.widget.StringListFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * The SpelllistFilter filters a spell list by spells marked by the character and entered letters.
 */
public class SpelllistFilter extends StringListFilter<Object> {

    private final KnownSpellPageModel knownSpellPageModel;

    /**
     * Instanciates SpelllistFilter.
     * 
     * @param knownSpellPageModel
     *            The known spell page model, containing the show all information.
     * @param listModel
     *            The list model.
     */
    public SpelllistFilter(final KnownSpellPageModel knownSpellPageModel, final ListModel<Object> listModel) {
        super(listModel);
        this.knownSpellPageModel = knownSpellPageModel;
    }

    @Override
    public List<Object> performFiltering() {
        List<Object> filteredItems = super.performFiltering();
        if (!knownSpellPageModel.isShowAll()) {
            filteredItems = filterByKnownSpells(filteredItems);
        }
        return filteredItems;
    }

    private List<Object> filterByKnownSpells(final List<Object> items) {
        final List<Object> filteredItems = new ArrayList<>();
        for (final Object item : items) {
            if (item instanceof LevelItem) {
                filteredItems.add(item);
            }
            if (item instanceof SpellItem) {
                final SpellItem spellItem = (SpellItem) item;
                if (spellItem.isKnownSpell()) {
                    filteredItems.add(spellItem);
                }
            }
        }
        return filteredItems;
    }

}
