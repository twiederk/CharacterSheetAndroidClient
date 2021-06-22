package com.android.ash.charactersheet.gui.main.charactercreator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.ash.charactersheet.FBAnalytics
import com.d20charactersheet.framework.boc.model.StarterPackBox
import com.d20charactersheet.framework.boc.model.StarterPackBoxOption
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class StarterPackBoxViewModel(val starterPackBox: StarterPackBox) : KoinComponent {

    private val firebaseAnalytics: FirebaseAnalytics by inject()

    val initialStarterPackBoxOption = starterPackBox.options[0]
    private val _starterPackBoxOption: MutableLiveData<StarterPackBoxOption> =
        MutableLiveData(initialStarterPackBoxOption)
    val starterPackBoxOption: LiveData<StarterPackBoxOption> = _starterPackBoxOption

    fun onStarterPackBoxOptionChange(starterPackBoxOption: StarterPackBoxOption) {
        _starterPackBoxOption.value = starterPackBoxOption
        firebaseAnalytics.logEvent(FBAnalytics.Event.EQUIPMENT_SELECTION, null)
    }

}
