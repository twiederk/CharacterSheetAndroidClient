package com.android.ash.charactersheet.gui.main.characterlist.feedback

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.main.characterlist.CharacterListViewModel
import com.android.ash.charactersheet.gui.theme.D20CharacterSheetTheme

class FeedbackComposeView(
    private val activity: AppCompatActivity,
    private val characterListViewModel: CharacterListViewModel
) {

    fun show() {
        val composeView = activity.findViewById<ComposeView>(R.id.character_list_feedback)

        composeView.setContent {
            D20CharacterSheetTheme {
                FeedbackButton {
                    characterListViewModel.onFeedbackGive()
                    DoorbellFeedbackDialog().show(activity)
                }
            }
        }

    }

}

@Composable
fun FeedbackButton(
    onFeedbackGive: () -> Unit
) {
    Button(
        modifier = Modifier.padding(8.dp),
        onClick = { onFeedbackGive() }
    ) {
        Text("Give Feedback")
    }
}

@Preview
@Composable
fun FeedbackButtonPreview() {
    D20CharacterSheetTheme {
        FeedbackButton(onFeedbackGive = { })
    }
}

