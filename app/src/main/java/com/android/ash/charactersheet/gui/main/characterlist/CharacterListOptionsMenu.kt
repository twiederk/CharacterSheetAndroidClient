package com.android.ash.charactersheet.gui.main.characterlist

import android.app.Activity
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.billing.Billing
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.gui.admin.AdministrationMenuActivity
import com.android.ash.charactersheet.gui.main.AboutActivity
import com.android.ash.charactersheet.gui.main.BackupRestoreActivity
import com.android.ash.charactersheet.gui.main.PreferencesActivity
import com.android.ash.charactersheet.gui.main.characterlist.AbstractAsyncTask.GameSystemLoadable
import com.android.ash.charactersheet.gui.main.characterlist.feedback.DoorbellFeedbackDialog
import com.android.ash.charactersheet.gui.main.characterlist.purchase.PurchaseDialog
import com.android.ash.charactersheet.gui.main.exportimport.ExportMenuActivity
import com.android.ash.charactersheet.gui.main.exportimport.ImportActivity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterListOptionsMenu(
    private val activity: AppCompatActivity,
    private val characterListViewModel: CharacterListViewModel
) : KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val billing: Billing by inject()

    fun onCreateOptionsMenu(menu: Menu): Boolean {
        activity.menuInflater.inflate(R.menu.menu_activity_character_list, menu)
        return true
    }

    fun onOptionsItemSelected(menuItem: MenuItem): Boolean = when (menuItem.itemId) {
        R.id.menu_activity_character_list_about -> callActivity(AboutActivity::class.java)
        R.id.menu_activity_character_list_administration -> callActivity(AdministrationMenuActivity::class.java)
        R.id.menu_activity_character_list_preference -> callActivity(PreferencesActivity::class.java)
        R.id.menu_activity_character_list_switch_dndv35 -> switchGameSystem(GameSystemType.DNDV35)
        R.id.menu_activity_character_list_switch_pathfinder -> switchGameSystem(GameSystemType.PATHFINDER)
        R.id.menu_activity_character_list_switch_dnd5e -> switchGameSystem(GameSystemType.DND5E)
        R.id.menu_activity_character_list_backup_restore -> callActivity(BackupRestoreActivity::class.java)
        R.id.menu_activity_character_list_export -> callActivity(ExportMenuActivity::class.java)
        R.id.menu_activity_character_list_import -> openImport()
        R.id.menu_activity_character_list_feedback -> openFeedbackDialog()
        R.id.menu_activity_character_list_purchase_premium_version -> openPurchaseDialog()
        else -> false
    }

    private fun callActivity(activityClass: Class<out Activity?>): Boolean {
        val intent = Intent(activity, activityClass)
        activity.startActivity(intent)
        return true
    }

    internal fun openPurchaseDialog(purchaseDialog: PurchaseDialog = PurchaseDialog(AlertDialog.Builder(activity))): Boolean {
        purchaseDialog.show(activity)
        return true
    }

    internal fun openImport(purchaseDialog: PurchaseDialog = PurchaseDialog(AlertDialog.Builder(activity))): Boolean {
        if (billing.requiresPurchase(gameSystemHolder.gameSystem!!.allCharacters)) {
            purchaseDialog.show(activity)
        } else {
            callActivity(ImportActivity::class.java)
        }
        return true
    }

    private fun switchGameSystem(gameSystemType: GameSystemType): Boolean {
        if (gameSystemHolder.gameSystemType != gameSystemType) {
            SwitchGameSystemAsyncTask(
                activity,
                activity as GameSystemLoadable,
                gameSystemType
            ).execute()
        }
        return true
    }

    private fun openFeedbackDialog(): Boolean {
        characterListViewModel.onFeedbackGive()
        DoorbellFeedbackDialog().show(activity)
        return true
    }

}