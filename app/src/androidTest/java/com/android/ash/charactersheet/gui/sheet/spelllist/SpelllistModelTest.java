package com.android.ash.charactersheet.gui.sheet.spelllist;

import java.util.List;

import junit.framework.TestCase;

import com.android.ash.charactersheet.gui.sheet.knownspell.KnownSpellPageModel;
import com.android.ash.charactersheet.gui.sheet.knownspell.SpelllistModel;
import com.android.ash.charactersheet.gui.widget.ListFilter;
import com.d20charactersheet.framework.DnDv35Universe;
import com.d20charactersheet.framework.Universe;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.KnownSpell;
import com.d20charactersheet.framework.boc.model.SpelllistAbility;
import com.d20charactersheet.framework.boc.service.GameSystem;

public class SpelllistModelTest extends TestCase {

    private GameSystem gameSystem;

    @Override
    protected void setUp() throws Exception {
        final Universe universe = new DnDv35Universe();
        gameSystem = universe.getGameSystem();
    }

    public void testFilter() {
        final Character belvador = gameSystem.getCharacterService().findCharacterByName("Belvador the Summoner",
                gameSystem.getAllCharacters());
        final SpelllistAbility spelllistAbility = belvador.getSpelllistAbilities().get(0);
        final List<KnownSpell> knownSpells = belvador.getKnownSpells(spelllistAbility.getSpelllist());

        final KnownSpellPageModel knownSpellPageModel = new KnownSpellPageModel();
        final SpelllistModel spelllistModel = new SpelllistModel(knownSpellPageModel, spelllistAbility, knownSpells);
        knownSpellPageModel.setShowAll(false);

        final ListFilter<Object> filter = (ListFilter<Object>) spelllistModel.getFilter();
        final List<Object> spelllistItems = filter.performFiltering();
        assertNotNull(spelllistItems);
        assertEquals(41, spelllistItems.size());
    }
}
