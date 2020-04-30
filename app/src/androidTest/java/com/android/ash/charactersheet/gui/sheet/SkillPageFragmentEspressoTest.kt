package com.android.ash.charactersheet.gui.sheet

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.boc.model.FavoriteCharacterSkill
import com.d20charactersheet.framework.boc.model.*
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.RuleService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class SkillPageFragmentEspressoTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterHolder: CharacterHolder by inject()

    @Test
    fun onCreateView() {
        // Arrange
        val displayService: DisplayService = mock()
        whenever(displayService.getDisplayModifier(any())).doReturn("+2")
        whenever(displayService.getDisplayAttributeShort(Attribute.WISDOM)).doReturn("WIS")

        val ruleService: RuleService = mock()
        whenever(ruleService.getMaxClassSkillRank(any())).doReturn(8)
        whenever(ruleService.getMaxCrossClassSkillRank(any())).doReturn(4.0F)
        whenever(ruleService.getSpentSkillPoints(any())).doReturn(24)
        whenever(ruleService.getSkillPoints(any(), any())).doReturn(48)

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(displayService)
        whenever(gameSystem.ruleService).doReturn(ruleService)

        gameSystemHolder.gameSystem = gameSystem
        characterHolder.character = Character().apply {
            classLevels = listOf(ClassLevel(CharacterClass().apply { classAbilities = listOf() }, 1))
            characterSkills = listOf(
                    FavoriteCharacterSkill(
                            Skill().apply { name = "mySkill"; attribute = Attribute.WISDOM }
                    ).apply { isFavorite = true; rank = 4.0F })
        }

        // Act
        launchFragmentInContainer<SkillPageFragment>()

        // Assert
        onView(withId(R.id.max_ranks)).check(matches(withText("Max Ranks: 8 / 4.0")))
        onView(withId(R.id.skill_points)).check(matches(withText("Skill points: 24 of 48")))
        onView(withId(R.id.skill_roll_button)).check(matches(withText("+2")))
        onView(withId(R.id.skill_attribute_modifier)).check(matches(withText("WIS: +2")))
        onView(withId(R.id.skill_rank)).check(matches(withText("Rank: 4.0")))
        onView(withId(R.id.skill_modifier)).check(matches(withText("Misc Mod: +2")))
    }

}