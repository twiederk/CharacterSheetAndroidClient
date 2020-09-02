package com.android.ash.charactersheet.gui.main.characterlist.promocode

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.net.URLEncoder

class PromoCode(private val promoCodeDialog: PromoCodeDialog = PromoCodeDialog()) : KoinComponent {

    private val firebaseAnalytics: FirebaseAnalytics by inject()
    private val gameSystemHolder: GameSystemHolder by inject()

    private var showedPromoCode = false
    private val promoCodes = PromoCodes()

    fun execute(activity: AppCompatActivity) {
        val dbHelper: DBHelper? = gameSystemHolder.dndDbHelper
        if (isAllowedToGetPromoCode(dbHelper) && !showedPromoCode) {
            showPromoCodeDialog(activity)
            showedPromoCode = true
        }
    }

    private fun isAllowedToGetPromoCode(dbHelper: DBHelper?) =
            dbHelper?.isUpgrade == true && dbHelper.oldVersion <= VERSION_CODE_WITHOUT_INAPP_PAYMENT

    private fun showPromoCodeDialog(activity: AppCompatActivity) {
        promoCodeDialog.show(activity, this)
    }

    fun activatePromoCode(context: Context) {
        firebaseAnalytics.logEvent(FBAnalytics.Event.PROMO_CODE_ACTIVATE, null)
        val code = promoCodes.getPromoCode()
        try {
            val url = "https://play.google.com/redeem?code=" + URLEncoder.encode(code, "UTF-8")
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: Exception) {
            Toast.makeText(context, R.string.promo_dialog_activation_error_message, Toast.LENGTH_LONG).show()
        }

    }

    companion object {
        const val VERSION_CODE_WITHOUT_INAPP_PAYMENT = 53
    }
}