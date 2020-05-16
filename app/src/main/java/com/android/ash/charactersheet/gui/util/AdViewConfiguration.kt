package com.android.ash.charactersheet.gui.util

import androidx.appcompat.app.AppCompatActivity
import com.android.ash.charactersheet.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration

class AdViewConfiguration {

    fun setAdView(activity: AppCompatActivity) {
        val testDeviceIds = listOf("ECF07E3D74B89832BDAEDD0D6F9B34B0")
        MobileAds.setRequestConfiguration(RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build())

        val adView: AdView? = activity.findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView?.loadAd(adRequest)
    }

}