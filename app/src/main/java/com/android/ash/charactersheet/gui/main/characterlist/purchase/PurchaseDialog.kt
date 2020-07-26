package com.android.ash.charactersheet.gui.main.characterlist.purchase

import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.billing.Billing
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.KoinComponent
import org.koin.core.inject

class PurchaseDialog(private val alertDialogBuilder: AlertDialog.Builder) : KoinComponent {

    private val billing: Billing by inject()
    private val firebaseAnalytics: FirebaseAnalytics by inject()

    fun show(activity: AppCompatActivity) {
        firebaseAnalytics.logEvent(FBAnalytics.Event.PURCHASE_DIALOG_SHOW, null)
        createAndShowDialog(activity)
    }

    private fun createAndShowDialog(activity: AppCompatActivity) {
        val view = View.inflate(activity, R.layout.dialog_purchase, null)
        setButtons(activity)
        alertDialogBuilder.setView(view).create().show()
    }

    private fun setButtons(activity: AppCompatActivity) {
        alertDialogBuilder.setPositiveButton(R.string.purchase_dialog_purchase_button) { _, _ -> performPurchase(activity) }
        alertDialogBuilder.setNegativeButton(R.string.purchase_dialog_cancel_button) { dialog, _ -> cancelPurchase(dialog) }
        alertDialogBuilder.setNeutralButton(R.string.purchase_dialog_restore_purchase_button) { dialog, _ -> restorePurchase(dialog) }
    }

    internal fun performPurchase(activity: AppCompatActivity) {
        firebaseAnalytics.logEvent(FBAnalytics.Event.PURCHASE_PERFORM, null)
        billing.startBillingFlow(activity)
    }

    internal fun cancelPurchase(dialog: DialogInterface) {
        firebaseAnalytics.logEvent(FBAnalytics.Event.PURCHASE_CANCEL, null)
        dialog.cancel()
    }

    internal fun restorePurchase(dialog: DialogInterface) {
        firebaseAnalytics.logEvent(FBAnalytics.Event.PURCHASE_RESTORE, null)
        billing.queryPurchases()
        dialog.cancel()
    }

}