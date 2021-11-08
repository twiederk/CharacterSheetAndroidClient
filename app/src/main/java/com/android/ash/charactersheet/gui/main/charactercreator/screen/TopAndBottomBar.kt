package com.android.ash.charactersheet.gui.main.charactercreator.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.theme.D20CharacterSheetTheme

@Composable
fun TopBarTitle(
    title: String,
    progress: Int
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            title = { Text(title) }
        )
        LinearProgressIndicator(
            progress = progress / 5f,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        )
    }
}

@Preview
@Composable
fun TopBarTitlePreview() {
    D20CharacterSheetTheme {
        TopBarTitle(
            title = "myTitle",
            progress = 1
        )
    }
}

@Composable
fun BottomBarNavigation(
    showPrevious: Boolean = false,
    showNext: Boolean = false,
    showCreate: Boolean = false,

    onNavigateToPrevious: () -> Unit = { },
    onNavigateToNext: () -> Unit = { },
    onCreateCharacter: () -> Unit = { }
) {
    Surface(
        elevation = 3.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (showPrevious) {
                    OutlinedButton(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        onClick = onNavigateToPrevious
                    ) {
                        Text(stringResource(R.string.character_creator_previous_button))
                    }
                }
                if (showNext) {
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        onClick = onNavigateToNext
                    ) {
                        Text(stringResource(R.string.character_creator_next_button))
                    }
                }
            }
            if (showCreate) {
                if (!showNext) {
                    Button(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        onClick = onCreateCharacter
                    ) {
                        Text(stringResource(R.string.character_creator_create_button))
                    }
                } else {
                    OutlinedButton(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        onClick = onCreateCharacter
                    ) {
                        Text(stringResource(R.string.character_creator_create_button))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BottomBarNavigationPreview() {
    D20CharacterSheetTheme {
        BottomBarNavigation(
            showPrevious = true,
            showNext = true,
            showCreate = false,
            onNavigateToPrevious = { },
            onNavigateToNext = { }
        ) { }
    }
}
