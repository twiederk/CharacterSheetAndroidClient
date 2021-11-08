package com.android.ash.charactersheet.gui.main.characterlist.purchase

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.billing.Billing
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.kotlin.*

class PurchaseListenerKoinTest : KoinTest {

    private val billing: Billing by inject()
    private val gameSystemHolder: GameSystemHolder by inject()

    @Before
    fun before() {
        startKoin {
            modules(appModule)
        }
        declareMock<Billing>()
        declareMock<GameSystemHolder>()
        whenever(gameSystemHolder.gameSystem).doReturn(mock())
    }

    @After
    fun after() {
        stopKoin()
    }


    @Test
    fun onClick_allowedToCreateCharacter_startStartCreateCharacterActivity() {
        // Arrange
        val view: View = mock()
        val context: Context = mock()
        whenever(view.context).doReturn(context)
        whenever(billing.requiresPurchase(any())).doReturn(false)

        // Act
        PurchaseListener(mock(), mock()).onClick(view)

        // Assert
        verify(context).startActivity(any())
    }

    @Test
    fun onClick_needsToPurchasePremiumVersion_displayPurchaseDialog() {
        // Arrange
        val view: View = mock()
        val context: Context = mock()
        whenever(view.context).doReturn(context)
        val purchaseDialog: PurchaseDialog = mock()
        val activity: AppCompatActivity = mock()
        whenever(billing.requiresPurchase(any())).doReturn(true)

        // Act
        PurchaseListener(activity, purchaseDialog).onClick(view)

        // Assert
        verifyNoMoreInteractions(activity)
        verify(purchaseDialog).show(any())
    }

}