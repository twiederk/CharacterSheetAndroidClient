package com.android.ash.charactersheet.gui.main.characterlist.promocode

import android.content.Context
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.billing.Billing
import org.koin.core.KoinComponent
import org.koin.core.inject

class PromoCodeDialog : KoinComponent {

    private val billing: Billing by inject()

    fun show(context: Context, promoCode: PromoCode) {
        val view = View.inflate(context, R.layout.dialog_promo_code, null)
        val dialog = createDialog(context, view)
        setOnClickListeners(view, promoCode, context, dialog)
        dialog.show()
    }

    private fun createDialog(context: Context, view: View): AlertDialog {
        return AlertDialog.Builder(context)
                .setView(view)
                .setOnDismissListener { billing.queryPurchases() }
                .create()
    }

    private fun setOnClickListeners(view: View, promoCode: PromoCode, context: Context, dialog: AlertDialog) {
        view.findViewById<Button>(R.id.promo_activate_code_button)?.let {
            it.setOnClickListener { promoCode.activatePromoCode(context) }
        }
        view.findViewById<Button>(R.id.promo_done_button)?.let {
            it.setOnClickListener { dialog.cancel() }
        }
    }

}