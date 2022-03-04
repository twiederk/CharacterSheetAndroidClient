package com.android.ash.charactersheet.gui.main.characterlist

import androidx.lifecycle.ViewModel
import com.android.ash.charactersheet.FBAnalytics
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterListViewModel : ViewModel(), KoinComponent {

    private val firebaseAnalytics: FirebaseAnalytics by inject()

    fun onFeedbackGive() {
        firebaseAnalytics.logEvent(FBAnalytics.Event.FEEDBACK_GIVE, null)
    }
}