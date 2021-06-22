package com.android.ash.charactersheet.gui.main.characterlist

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.billing.Billing
import com.android.ash.charactersheet.gui.main.characterlist.purchase.PurchaseDialog
import com.d20charactersheet.framework.boc.service.GameSystem
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.kotlin.*

class CharacterListOptionsMenuKoinTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val billing: Billing by inject()

    @Before
    fun before() {
        startKoin {
            modules(appModule)
        }
        declareMock<Billing>()
    }


    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun onOptionsItemSelected_aboutOption_startActivity() {
        // Arrange
        val activity: AppCompatActivity = mock()
        val menuItem: MenuItem = mock()
        whenever(menuItem.itemId).doReturn(R.id.menu_activity_character_list_about)

        // Act
        val result = CharacterListOptionsMenu(activity).onOptionsItemSelected(menuItem)

        // Assert
        assertThat(result).isTrue
        verify(activity).startActivity(any())
    }

    @Test
    fun onOptionsItemSelected_switchGameSystemToPathfinder_executeSwitchGameSystemAsyncTask() {
        // Arrange
        val activity: CharacterListActivity = mock()
        val menuItem: MenuItem = mock()
        whenever(menuItem.itemId).doReturn(R.id.menu_activity_character_list_switch_pathfinder)

        // Act
        val result = CharacterListOptionsMenu(activity).onOptionsItemSelected(menuItem)

        // Assert
        assertThat(result).isTrue
    }

    @Test
    fun onOptionsItemSelected_switchGameSystemToDnDv35_executeSwitchGameSystemAsyncTask() {
        // Arrange
        val activity: CharacterListActivity = mock()
        val menuItem: MenuItem = mock()
        whenever(menuItem.itemId).doReturn(R.id.menu_activity_character_list_switch_dndv35)

        // Act
        val result = CharacterListOptionsMenu(activity).onOptionsItemSelected(menuItem)

        // Assert
        assertThat(result).isTrue
    }

    @Test
    fun onOptionsItemSelected_switchGameSystemToDnD5e_executeSwitchGameSystemAsyncTask() {
        // Arrange
        val activity: CharacterListActivity = mock()
        val menuItem: MenuItem = mock()
        whenever(menuItem.itemId).doReturn(R.id.menu_activity_character_list_switch_dnd5e)

        // Act
        val result = CharacterListOptionsMenu(activity).onOptionsItemSelected(menuItem)

        // Assert
        assertThat(result).isTrue
    }

    @Test
    fun openPurchaseDialog_purchasePremiumVersion_showPurchaseDialog() {
        // Arrange
        val activity: AppCompatActivity = mock()
        val purchaseDialog: PurchaseDialog = mock()

        // Act
        val result = CharacterListOptionsMenu(activity).openPurchaseDialog(purchaseDialog)

        // Assert
        assertThat(result).isTrue
        verify(purchaseDialog).show(activity)
    }

    @Test
    fun openImport_requiresPurchase_showPurchaseDialog() {
        // Arrange
        val activity: AppCompatActivity = mock()
        val purchaseDialog: PurchaseDialog = mock()
        whenever(billing.requiresPurchase(any())).doReturn(true)
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.allCharacters).doReturn(emptyList())
        gameSystemHolder.gameSystem = gameSystem

        // Act
        val result = CharacterListOptionsMenu(activity).openImport(purchaseDialog)

        // Assert
        assertThat(result).isTrue
        verify(purchaseDialog).show(activity)
    }

    @Test
    fun openImport_purchaseNotRequired_startActivity() {
        // Arrange
        val activity: AppCompatActivity = mock()
        val purchaseDialog: PurchaseDialog = mock()
        whenever(billing.requiresPurchase(any())).doReturn(false)
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.allCharacters).doReturn(emptyList())
        gameSystemHolder.gameSystem = gameSystem

        // Act
        val result = CharacterListOptionsMenu(activity).openImport(purchaseDialog)

        // Assert
        assertThat(result).isTrue
        verify(activity).startActivity(any())
    }

}
