package com.android.ash.charactersheet.gui.sheet.spelllist

import com.android.ash.charactersheet.gui.sheet.knownspell.KnownSpellPageModel
import com.android.ash.charactersheet.gui.sheet.knownspell.SpelllistModel
import com.android.ash.charactersheet.gui.widget.ListFilter
import com.d20charactersheet.framework.boc.model.KnownSpell
import com.d20charactersheet.framework.boc.model.Spell
import com.d20charactersheet.framework.boc.model.Spelllist
import com.d20charactersheet.framework.boc.model.SpelllistAbility
import org.assertj.core.api.Assertions
import org.junit.Test
import java.util.*

class SpelllistModelTest {

    @Test
    fun testFilter() {

        // Arrange
        val firstSpell = createSpell(1, "firstSpell")
        val secondSpell = createSpell(2, "secondSpell")
        val thirdSpell = createSpell(3, "thirdSpell")
        val spellsByLevel: MutableMap<Int, List<Spell>> = HashMap()
        spellsByLevel[0] = listOf(firstSpell)
        spellsByLevel[1] = listOf(secondSpell)
        spellsByLevel[2] = listOf(thirdSpell)

        val spelllist = Spelllist().apply { this.spellsByLevel = spellsByLevel }
        val spelllistAbility = SpelllistAbility().apply { this.spelllist = spelllist }

        val knownSpells = listOf(
                createKnownSpell(firstSpell, spelllist),
                createKnownSpell(secondSpell, spelllist)
        )

        val spelllistModel = SpelllistModel(KnownSpellPageModel().apply { isShowAll = false }, spelllistAbility, knownSpells)
        val filter = spelllistModel.filter as ListFilter<*>

        // Act
        val spelllistItems = filter.performFiltering()

        // Assert
        Assertions.assertThat(spelllistItems).hasSize(5)
    }

    private fun createSpell(id: Int, name: String): Spell {
        val spell = Spell()
        spell.id = id
        spell.name = name
        return spell
    }

    private fun createKnownSpell(spell: Spell, spelllist: Spelllist): KnownSpell {
        val knownSpell = KnownSpell()
        knownSpell.spell = spell
        knownSpell.spelllist = spelllist
        return knownSpell
    }
}