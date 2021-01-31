package com.android.ash.charactersheet.gui.main.characterlist

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.PreferenceServiceHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.boc.service.PreferenceService
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.KoinComponent
import org.koin.core.inject

class GameSystemSelector(val activity: AppCompatActivity) : KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val preferenceServiceHolder: PreferenceServiceHolder by inject()
    private val firebaseAnalytics: FirebaseAnalytics by inject()

    internal var gameSystemSelected = false

    fun show(view: View) {
        if (isShowGameSystemSelector()) {
            show()
            setButtons(view)
        }
    }

    internal fun isShowGameSystemSelector(): Boolean = gameSystemHolder.dndDbHelper?.isCreate == true && !gameSystemSelected

    private fun show() {
        activity.findViewById<View>(R.id.character_list_game_system_selector)?.visibility = View.VISIBLE
    }

    private fun hide() {
        activity.findViewById<View>(R.id.character_list_game_system_selector)?.visibility = View.INVISIBLE
    }

    private fun setButtons(view: View) {
        view.findViewById<Button>(R.id.game_system_selector_dndv35_button)?.setOnClickListener(GameSystemSelectorOnClickListener(this, GameSystemType.DNDV35))
        view.findViewById<Button>(R.id.game_system_selector_dnd5e_button)?.setOnClickListener(GameSystemSelectorOnClickListener(this, GameSystemType.DND5E))
        view.findViewById<Button>(R.id.game_system_selector_pathfinder_button)?.setOnClickListener(GameSystemSelectorOnClickListener(this, GameSystemType.PATHFINDER))
    }

    fun switchGameSystem(gameSystemType: GameSystemType) {
        hide()
        gameSystemSelected = true
        preferenceServiceHolder.preferenceService?.setInt(PreferenceService.GAME_SYSTEM_TYPE, gameSystemType.ordinal)
        logEventGameSystemSelect(gameSystemType)
        if (gameSystemHolder.gameSystemType != gameSystemType) {
            SwitchGameSystemAsyncTask(activity, activity as AbstractAsyncTask.GameSystemLoadable, gameSystemType).execute()
        }
    }

    private fun logEventGameSystemSelect(gameSystemType: GameSystemType) {
        val bundle = Bundle()
        bundle.putString(FBAnalytics.Param.GAME_SYSTEM_NAME, gameSystemType.getName())
        firebaseAnalytics.logEvent(FBAnalytics.Event.GAME_SYSTEM_SELECT, bundle)
    }

}
