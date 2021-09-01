package com.android.ash.charactersheet.gui.main.characterlist.purchase

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.billing.Billing
import com.android.ash.charactersheet.gui.main.charactercreator.CharacterCreateActivity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PurchaseListener(
    private val activity: AppCompatActivity,
    private val purchaseDialog: PurchaseDialog
) : KoinComponent, View.OnClickListener {

    private val billing: Billing by inject()
    private val gameSystemHolder: GameSystemHolder by inject()

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