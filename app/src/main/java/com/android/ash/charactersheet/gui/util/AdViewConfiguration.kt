package com.android.ash.charactersheet.gui.util

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.billing.Billing6
import com.android.billingclient.api.Purchase
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AdViewConfiguration : KoinComponent {

    private val billing: Billing6 by inject()

    fun setAdView(activity: AppCompatActivity) {
        val testDeviceIds = listOf("ECF07E3D74B89832BDAEDD0D6F9B34B0")
        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build()
        )

        val adView: AdView? = activity.findViewById(R.id.adView)

        if (billing.premiumVersion.purchaseState != Purchase.PurchaseState.PURCHASED) {
            val adRequest = AdRequest.Builder().build()
            adView?.loadAd(adRequest)
        } else {
            adView?.visibility = View.GONE
        }
    }

}