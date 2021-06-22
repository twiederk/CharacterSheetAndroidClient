package com.android.ash.charactersheet.gui.main.charactercreator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.theme.D20CharacterSheetTheme
import com.d20charactersheet.framework.boc.model.CharacterClass

@Composable
fun ClassScreen(
    name: String,
    player: String,
    clazz: CharacterClass,
    classList: List<CharacterClass>,
    gender: String,
    genderList: List<String>,
    alignment: String,
    alignmentList: List<String>,
    onChangeName: (String) -> Unit,
    onChangePlayer: (String) -> Unit,

    onGenderChange: (String) -> Unit,
    onAlignmentChange: (String) -> Unit,
    onClassChange: (CharacterClass) -> Unit,
    onNavigateToPrevious: () -> Unit,
    onNavigateToNext: () -> Unit,
    onCreateCharacter: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxHeight()) {
            Scaffold(
                topBar = {
                    TopBarTitle(
                        title = stringResource(R.string.class_title),
                        progress = 2
                    )
                },

                content = {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(bottom = 50.dp)
                    ) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = onChangeName,
                            label = { Text(stringResource(id = R.string.create_name_label)) },
                            maxLines = 1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )

                        OutlinedTextField(
                            value = player,
                            onValueChange = onChangePlayer,
                            label = { Text(stringResource(id = R.string.create_player_label)) },
                            maxLines = 1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )

                        ClassDropdownMenu(
                            labelResourceId = R.string.create_class_label,
                            clazz = clazz,
                            classList = classList,
                            onClassChange = onClassChange
                        )

                        StringDropdownMenu(
                            labelResourceId = R.string.create_gender_label,
                            name = gender,
                            nameList = genderList,
                            onNameChange = onGenderChange
                        )
                        StringDropdownMenu(
                            labelResourceId = R.string.create_alignment_label,
                            name = alignment,
                            nameList = alignmentList,
                            onNameChange = onAlignmentChange
                        )
                    }
                },

                bottomBar = {
                    BottomBarNavigation(
                        showPrevious = true,
                        showNext = true,
                        showCreate = true,
                        onNavigateToPrevious = onNavigateToPrevious,
                        onNavigateToNext = onNavigateToNext,
                        onCreateCharacter = onCreateCharacter
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun ClassScreenPreview() {
    D20CharacterSheetTheme {
        ClassScreen(
            name = "myName",
            player = "myPlayer",
            clazz = CharacterClass().apply { id = 1; name = "Fighter" },
            classList = listOf(
                CharacterClass().apply { id = 1; name = "Fighter" },
                CharacterClass().apply { id = 2; name = "Wizard" }),
            gender = "Male",
            genderList = listOf("Male, Female"),
            alignment = "Lawful Good",
            alignmentList = listOf("Lawful Good", "Neutral Good"),
            onChangeName = {},
            onChangePlayer = {},
            onGenderChange = {},
            onAlignmentChange = {},
            onClassChange = {},
            onNavigateToPrevious = {},
            onNavigateToNext = {}
        ) {}
    }
}

