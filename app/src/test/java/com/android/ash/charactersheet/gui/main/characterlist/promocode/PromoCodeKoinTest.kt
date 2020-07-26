package com.android.ash.charactersheet.gui.main.characterlist.promocode

import androidx.appcompat.app.AppCompatActivity
import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.billing.Billing
import com.android.ash.charactersheet.dac.dao.sqlite.DBHelper
import com.google.firebase.analytics.FirebaseAnalytics
import com.nhaarman.mockitokotlin2.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock

class PromoCodeKoinTest : KoinTest {

    private val firebaseAnalytics: FirebaseAnalytics by inject()
    private val gameSystemHolder: GameSystemHolder by inject()

    @Before
    fun before() {
        startKoin {
            modules(appModule)
        }
        declareMock<Billing>()
        declareMock<FirebaseAnalytics>()
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun execute_updateOfAppOfVersionLower3_showPromoCode() {
        // Arrange
        val promoCodeDialog: PromoCodeDialog = mock()
        val activity: AppCompatActivity = mock()
        val dbHelper: DBHelper = mock()
        whenever(dbHelper.isUpgrade).thenReturn(true)
        whenever(dbHelper.oldVersion).thenReturn(PromoCode.VERSION_CODE_WITHOUT_INAPP_PAYMENT)
        gameSystemHolder.dndDbHelper = dbHelper
        val underTest = PromoCode(promoCodeDialog)

        // Act
        underTest.execute(activity)

        // Assert
        verify(promoCodeDialog).show(activity, underTest)
    }

    @Test
    fun execute_installAppOfVersionEqualTo3_hidePromoCode() {
        // Arrange
        val promoCodeDialog: PromoCodeDialog = mock()
        val activity: AppCompatActivity = mock()
        val dbHelper: DBHelper = mock()
        whenever(dbHelper.isUpgrade).thenReturn(true)
        whenever(dbHelper.oldVersion).thenReturn(PromoCode.VERSION_CODE_WITHOUT_INAPP_PAYMENT + 1)
        val underTest = PromoCode(promoCodeDialog)

        // Act
        underTest.execute(activity)

        // Assert
        verifyZeroInteractions(promoCodeDialog)
    }

    @Test
    fun activatePromoCode_activatePromoCode_callGooglePlayApp() {
        // Arrange
        val activity: AppCompatActivity = mock()

        // Act
        PromoCode().activatePromoCode(activity)

        // Assert
        verify(activity).startActivity(any())
        verify(firebaseAnalytics).logEvent(FBAnalytics.Event.PROMO_CODE_ACTIVATE, null)
    }

}