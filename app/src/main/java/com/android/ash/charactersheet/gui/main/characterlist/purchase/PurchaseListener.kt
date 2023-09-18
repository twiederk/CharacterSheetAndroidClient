package com.android.ash.charactersheet.gui.main.characterlist.purchase

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.billing.Billing6
import com.android.ash.charactersheet.gui.main.charactercreator.CharacterCreatorActivity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PurchaseListener(
    private val activity: AppCompatActivity,
    private val purchaseDialog: PurchaseDialog
) : KoinComponent, View.OnClickListener {

    private val billing: Billing6 by inject()
    private val gameSystemHolder: GameSystemHolder by inject()

    override fun onClick(view: View?) {
        if (billing.requiresPurchase(gameSystemHolder.gameSystem!!.allCharacters)) {
            view?.context?.let {
                purchaseDialog.show(activity)
            }
        } else {
            view?.context?.startActivity(Intent(view.context, CharacterCreatorActivity::class.java))
        }
    }

}