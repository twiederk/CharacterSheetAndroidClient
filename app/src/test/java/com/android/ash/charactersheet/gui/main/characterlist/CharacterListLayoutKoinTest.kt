package com.android.ash.charactersheet.gui.main.characterlist

import android.content.res.Resources
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.compose.ui.platform.ComposeView
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.d20charactersheet.framework.boc.service.DnDv35RuleServiceImpl
import com.d20charactersheet.framework.boc.service.GameSystem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.analytics.FirebaseAnalytics
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CharacterListLayoutKoinTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Before
    fun before() {
        startKoin {
            modules(appModule)
        }
        declareMock<FirebaseAnalytics>()
    }


    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun layout_createLayout_displayTitleOfGameSystem() {
        // Arrange
        val characterListActivity: CharacterListActivity = mock()
        whenever(characterListActivity.findViewById<Toolbar>(R.id.toolbar)).doReturn(mock())
        val actionBar: ActionBar = mock()
        whenever(characterListActivity.supportActionBar).doReturn(actionBar)
        whenever(characterListActivity.findViewById<Button>(R.id.button_ok)).doReturn(mock())
        whenever(characterListActivity.findViewById<FloatingActionButton>(R.id.favorite_action_button)).doReturn(
            mock()
        )
        whenever(characterListActivity.findViewById<View>(R.id.character_list_view)).doReturn(mock())
        whenever(characterListActivity.findViewById<ComposeView>(R.id.character_list_feedback)).doReturn(
            mock()
        )
        whenever(characterListActivity.theme).doReturn(mock())
        val resources: Resources = mock()
        whenever(resources.getString(R.string.character_list_title)).doReturn("Character List")
        whenever(characterListActivity.resources).doReturn(resources)
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.ruleService).doReturn(DnDv35RuleServiceImpl())
        gameSystemHolder.gameSystem = gameSystem
        gameSystemHolder.gameSystemType = GameSystemType.DNDV35

        // Act
        CharacterListLayout(characterListActivity, mock()).layout()

        // Assert
        verify(actionBar).title = "Character List - DnD v.3.5"

    }

}