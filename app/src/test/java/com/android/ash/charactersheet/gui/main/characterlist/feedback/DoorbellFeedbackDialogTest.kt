package com.android.ash.charactersheet.gui.main.characterlist.feedback

import androidx.appcompat.app.AppCompatActivity
import io.doorbell.android.Doorbell
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class DoorbellFeedbackDialogTest {

    @Test
    fun show_display_showDoorbellFeedbackDialog() {
        // arrange
        val activity: AppCompatActivity = mock()
        val doorbellDialog: Doorbell = mock()

        // act
        DoorbellFeedbackDialog().show(activity, doorbellDialog)

        // assert
        verify(doorbellDialog).show()
    }

}