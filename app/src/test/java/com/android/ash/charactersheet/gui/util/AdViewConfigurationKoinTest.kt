package com.android.ash.charactersheet.gui.util

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.billing.Billing
import com.android.ash.charactersheet.billing.Product
import com.android.billingclient.api.Purchase
import com.google.android.gms.ads.AdView
import com.nhaarman.mockitokotlin2.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock

class AdViewConfigurationKoinTest : KoinTest {

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
    fun setAddView_adViewExists_loadAd() {
        // Arrange
        whenever(billing.premiumVersion).doReturn(Product("premium_Version"))
        val adView: AdView = mock()
        val activity: AppCompatActivity = mock()
        whenever(activity.findViewById<AdView>(R.id.adView)).doReturn(adView)


        // Act
        AdViewConfiguration().setAdView(activity)

        // Assert
        verify(adView).loadAd(any())
        verifyNoMoreInteractions(adView)
    }

    @Test
    fun setAddView_adViewNotExists_noException() {
        // Arrange
        whenever(billing.premiumVersion).doReturn(Product("premium_Version"))
        val activity: AppCompatActivity = mock()
        whenever(activity.findViewById<AdView>(R.id.adView)).doReturn(null)

        // Act
        AdViewConfiguration().setAdView(activity)

        // Assert
        verify(activity).findViewById<AdView>(R.id.adView)
    }

    @Test
    fun setAddView_premiumVersionPurchases_noAdsLoadedAndShown() {
        // Arrange
        whenever(billing.premiumVersion).doReturn(Product("premium_Version", Purchase.PurchaseState.PURCHASED))
        val adView: AdView = mock()
        val activity: AppCompatActivity = mock()
        whenever(activity.findViewById<AdView>(R.id.adView)).doReturn(adView)

        // Act
        AdViewConfiguration().setAdView(activity)

        // Assert
        verify(adView).visibility = View.GONE
    }

}