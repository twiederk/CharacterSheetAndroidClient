package com.android.ash.charactersheet.gui.util

import androidx.appcompat.app.AppCompatActivity
import com.android.ash.charactersheet.R
import com.google.android.gms.ads.AdView
import com.nhaarman.mockitokotlin2.*
import org.junit.Test

class AdViewConfigurationTest {

    @Test
    fun setAddView_adViewExists_loadAd() {
        // Arrange
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
        val activity: AppCompatActivity = mock()
        whenever(activity.findViewById<AdView>(R.id.adView)).doReturn(null)

        // Act
        AdViewConfiguration().setAdView(activity)

        // Assert
        verify(activity).findViewById<AdView>(R.id.adView)
    }

}