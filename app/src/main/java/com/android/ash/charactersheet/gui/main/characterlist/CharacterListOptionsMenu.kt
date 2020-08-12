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
import com.android.ash.charactersheet.dac.dao.sqlite.DBHelper
import com.android.ash.charactersheet.gui.admin.AdministrationMenuActivity
import com.android.ash.charactersheet.gui.main.AboutActivity
import com.android.ash.charactersheet.gui.main.BackupRestoreActivity
import com.android.ash.charactersheet.gui.main.PreferencesActivity
import com.android.ash.charactersheet.gui.main.characterlist.AbstractAsyncTask.GameSystemLoadable
import com.android.ash.charactersheet.gui.main.characterlist.purchase.PurchaseDialog
import com.android.ash.charactersheet.gui.main.exportimport.ExportMenuActivity
import com.android.ash.charactersheet.gui.main.exportimport.ImportActivity
import org.koin.core.KoinComponent
import org.koin.core.inject


class CharacterListOptionsMenu(private val activity: AppCompatActivity) : KoinComponent {

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
        R.id.menu_activity_character_list_switch_game_system -> switchGameSystem()
        R.id.menu_activity_character_list_backup_restore -> callActivity(BackupRestoreActivity::class.java)
        R.id.menu_activity_character_list_export -> callActivity(ExportMenuActivity::class.java)
        R.id.menu_activity_character_list_import -> openImport()
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

    private fun switchGameSystem(): Boolean {
        val dbHelper: DBHelper?
        val gameSystemType = gameSystemHolder.gameSystemType
        if (gameSystemType == GameSystemType.DNDV35) {
            gameSystemHolder.gameSystemType = GameSystemType.PATHFINDER
            dbHelper = gameSystemHolder.pathfinderDbHelper
        } else {
            gameSystemHolder.gameSystemType = GameSystemType.DNDV35
            dbHelper = gameSystemHolder.dndDbHelper
        }
        SwitchGameSystemAsyncTask(activity, activity as GameSystemLoadable, dbHelper).execute()
        return true
    }


}