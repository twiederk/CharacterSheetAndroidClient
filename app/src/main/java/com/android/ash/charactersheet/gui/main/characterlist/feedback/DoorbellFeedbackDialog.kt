package com.android.ash.charactersheet.gui.main.characterlist.feedback

import androidx.appcompat.app.AppCompatActivity
import io.doorbell.android.Doorbell

class DoorbellFeedbackDialog {

    fun show(
        activity: AppCompatActivity,
        doorbellDialog: Doorbell = Doorbell(
            activity,
            12999,
            "clQqPOPeXwgAQomdrqx0AADkA2zCMBLSDcMzBTyut04mK3cZCTAWn5Z9lZVfQ6RV"
        )
    ) {
        doorbellDialog.show()
    }

}
