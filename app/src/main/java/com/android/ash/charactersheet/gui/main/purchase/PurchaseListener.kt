package com.android.ash.charactersheet.gui.main.purchase

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.billing.Billing
import com.android.ash.charactersheet.gui.main.CharacterCreateActivity
import org.koin.java.KoinJavaComponent.inject

class PurchaseListener(
        private val activity: AppCompatActivity,
        private val purchaseDialog: PurchaseDialog) : View.OnClickListener {

    private val billing by inject(Billing::class.java)
    private val gameSystemHolder by inject(GameSystemHolder::class.java)

    override fun onClick(view: View?) {
        if (billing.requiresPurchase(gameSystemHolder.gameSystem!!.allCharacters)) {
            view?.context?.let {
                purchaseDialog.show(activity)
            }
        } else {
            view?.context?.startActivity(Intent(view.context, CharacterCreateActivity::class.java))
        }
    }

}