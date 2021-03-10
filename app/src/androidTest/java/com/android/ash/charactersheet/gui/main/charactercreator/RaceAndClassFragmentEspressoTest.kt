package com.android.ash.charactersheet.gui.main.charactercreator

import androidx.fragment.app.testing.FragmentScenario
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.d20charactersheet.framework.boc.model.*
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class RaceAndClassFragmentEspressoTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterCreatorViewModel: CharacterCreatorViewModel by inject()

    @Test
    fun updateCharacterData_readDataFromGuiComponents_characterDataContainsDataFromGuiComponents() {
        // arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(mock())
        whenever(gameSystem.allRaces).doReturn(listOf(Race().apply { name = "myRace" }))
        whenever(gameSystem.allCharacterClasses).doReturn(listOf(CharacterClass().apply { name = "myClass" }))
        whenever(gameSystem.allXpTables).doReturn(listOf(XpTable().apply { name = "myXpTable" }))
        gameSystemHolder.gameSystem = gameSystem

        val scenario = FragmentScenario.launchInContainer(RaceAndClassFragment::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

        onView(withId(R.id.create_name)).perform(typeText("myName"))
        onView(withId(R.id.create_player)).perform(typeText("myPlayer"))

        // act
        scenario.onFragment { fragment -> fragment.updateCharacterData() }

        // assert
        assertThat(characterCreatorViewModel.name).isEqualTo("myName")
        assertThat(characterCreatorViewModel.player).isEqualTo("myPlayer")
        assertThat(characterCreatorViewModel.race?.name).isEqualTo("myRace")
        assertThat(characterCreatorViewModel.sex).isEqualTo(Sex.MALE)
        assertThat(characterCreatorViewModel.clazz?.name).isEqualTo("myClass")
        assertThat(characterCreatorViewModel.alignment).isEqualTo(Alignment.LAWFUL_GOOD)
    }

}