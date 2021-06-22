package com.android.ash.charactersheet.gui.sheet

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.CharacterFeat
import com.d20charactersheet.framework.boc.model.Feat
import com.d20charactersheet.framework.boc.model.FeatType
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.RuleService
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class FeatPageFragmentEspressoTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterHolder: CharacterHolder by inject()

    @Test
    fun onCreateView() {
        // Arrange
        val ruleService: RuleService = mock()
        whenever(ruleService.getNumberOfFeats(any())).doReturn(3)

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.featService).doReturn(mock())
        whenever(gameSystem.ruleService).doReturn(ruleService)

        gameSystemHolder.gameSystem = gameSystem
        characterHolder.character = Character().apply {
            characterFeats = listOf(CharacterFeat(
                    Feat().apply {
                        name = "myCharacterFeat"
                        featType = FeatType.GENERAL
                    }))
        }

        // Act
        launchFragmentInContainer<FeatPageFragment>()

        // Assert
        onView(withId(R.id.feat_list_number_of_feats)).check(matches(withText("1 of 3 feats")))
        onView(withId(R.id.feat_name)).check(matches(withText("myCharacterFeat")))
    }

}